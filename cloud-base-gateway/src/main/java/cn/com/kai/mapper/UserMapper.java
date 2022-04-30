package cn.com.kai.mapper;

import cn.com.kai.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName:UserMapper
 * Package:cn.com.kai.mappers
 * Description:
 *
 * @Author:gkr
 * @Date:2022-04-30 11:05
 */
@Mapper
@Repository
public interface UserMapper {

    @Select(value = "select id,username,password,name from user where id >1 order by id limit 10")
    List<UserEntity> list();
}
