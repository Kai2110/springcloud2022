package cn.com.kai.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import java.util.List;
import java.util.function.BiFunction;

/**
 * ClassName:MybatisBatchUtils
 * Package:cn.com.kai.util
 * Description: Mybatis批量插入工具类
 *
 * @Author:gkr
 * @Date:2022-05-07 10:34
 */
@Slf4j
@Component
public class MybatisBatchUtils {

    private static final int BATCH_SIZE=1000;//每次处理的记录数

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /**
     *  批量处理修改或插入
     * @param data  需要被处理的数据
     * @param mapperClass   Mybatis的Mapper类
     * @param function  自定义处理逻辑
     * @return  影响的总行数
     */
    public <T,U,R> int batchUpdateOrInsert(List<T> data, Class<U> mapperClass, BiFunction<T,U,R> function){
        int i = 1;
        //默认情况下使用ExecutorType,Simple,获取的SqlSession未开启批处理
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            U mapper = sqlSession.getMapper(mapperClass);
            int size = data.size();
            for (T element : data){
                function.apply(element,mapper);
                if((i % BATCH_SIZE == 0) || i == size){
                    sqlSession.flushStatements();
                }
                i++;
            }
            //非事务环境下强制commit，事务情况下该Commit相当于无效
            sqlSession.commit(TransactionSynchronizationManager.isSynchronizationActive());
        }catch (Exception e){
            sqlSession.rollback();
            //TODO  :此处可以抛出一个自定义异常
            //throw new CustomException(e);
        }finally {
            sqlSession.close();
        }
        return i-1;
    }

}
