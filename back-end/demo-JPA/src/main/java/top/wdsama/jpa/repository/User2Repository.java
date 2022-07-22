package top.wdsama.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import top.wdsama.jpa.pojo.User1;
import top.wdsama.jpa.pojo.User2;

@Component
public interface User2Repository extends JpaRepository<User2,Long> {



}
