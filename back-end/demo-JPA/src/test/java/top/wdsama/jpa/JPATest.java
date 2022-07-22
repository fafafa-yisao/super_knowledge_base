package top.wdsama.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import top.wdsama.jpa.pojo.User1;
import top.wdsama.jpa.pojo.User2;
import top.wdsama.jpa.repository.User1Repository;
import top.wdsama.jpa.repository.User2Repository;

import java.time.LocalDate;

@SpringBootTest
public class JPATest {

    @Autowired
    private User1Repository user1Repository;
    @Autowired
    private User2Repository user2Repository;

    @Test
    public void saveUser1(){

        User1 user1 = User1.builder().name("wdsama").creatDate(LocalDate.now()).build();

        user1Repository.save(user1);

        Assert.notNull(user1.getId(),"ID生成失败");

    }
    @Test
    public void saveUser2(){

        User2 user2 = User2.builder().name("wdsama").creatDate(LocalDate.now()).build();

        user2Repository.save(user2);

        Assert.notNull(user2.getId(),"ID生成失败");

    }

}
