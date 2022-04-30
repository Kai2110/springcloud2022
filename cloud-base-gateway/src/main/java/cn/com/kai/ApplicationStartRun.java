package cn.com.kai;

import cn.com.kai.entity.UserEntity;
import cn.com.kai.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName:ApplicationStartRun
 * Package:cn.com.kai
 * Description:
 *
 * @Author:gkr
 * @Date:2022-04-30 11:48
 */
@Component
public class ApplicationStartRun implements CommandLineRunner {

    @Autowired
    UserMapper userMapper;

    @Override
    public void run(String... args) throws Exception {
        List<UserEntity> list = userMapper.list();
        System.out.println(list.get(0));
    }
}
