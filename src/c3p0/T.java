package c3p0;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:c3p0/applicationContext.xml")
public class T {

    @Resource
    JdbcTemplate jt;
    @Test
    public void t(){
        String sql="INSERT INTO `springjdbc`.`account` (`id`, `username`, `balance`) VALUES (NULL, ?, ?)";
        jt.update(sql,"ddd",1000);
        System.out.println("添加成功");
    }
}
