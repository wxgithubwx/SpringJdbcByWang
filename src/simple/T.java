package simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:simple/applicationContext.xml")
public class T {
    String sql = "create table account(" +
            "id int primary key auto_increment," +
            "username varchar(50)," +
            "balance double)";

    /**
     * 不加载xml配置文件创建表
     */
    @Test
    public void t1() {
        //1. Spring提供的连接池
        DriverManagerDataSource source =new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUsername("root");
        source.setPassword("12345");
        source.setUrl("jdbc:mysql:///springJdbc");
        //2.使用jdbc模板
        JdbcTemplate jt=new JdbcTemplate(source);
        jt.execute(sql);
        System.out.println("创建表成功");
    }

    /**
     * 加载xml配置文件创建表 Spring帮我们创建对象 不用new
     */
    @Test
    public void t2() {
       ApplicationContext ac =
               new ClassPathXmlApplicationContext("simple/applicationContext.xml");
        JdbcTemplate jt = (JdbcTemplate)ac.getBean("jt");
        jt.execute(sql);
        System.out.println("创建表成功");
    }

    /**
     * 使用注解和junit加载配置文件
     *
     */
    @Resource
    JdbcTemplate jt;

    @Test
    public void t3() {
        jt.execute(sql);
        System.out.println("创建表成功");
    }

    @Test
    public void t4() {
        String sql="INSERT INTO `springjdbc`.`account` (`id`, `username`, `balance`) VALUES (NULL, ?, ?)";
        jt.update(sql,"bbb",1000);
        System.out.println("添加成功");
    }

    @Test
    public void t5() {
        String sql="UPDATE `springjdbc`.`account` SET  `username`=?, `balance`=? WHERE (`id`=?)";
        jt.update(sql,"aaa",2000,1);
        System.out.println("更新成功");
    }

    @Test
    public void t6() {
        String sql="delete from `springjdbc`.`account` WHERE (`id`=?)";
        jt.update(sql,3);
        System.out.println("删除成功");
    }

    /**
     * 根据id查询
     */
    @Test
    public void t7() {
        String sql="select * from `springjdbc`.`account` WHERE (`id`=?)";
        RowMapper<Account> rowMapper=new BeanPropertyRowMapper<>(Account.class);
        Account account = jt.queryForObject(sql, rowMapper, 1);
        System.out.println(account.toString());
    }

    @Test
    public void t8() {
        String sql="select * from `springjdbc`.`account` WHERE (`id`=?)";
        Account account = jt.queryForObject(sql, new MYRowMapper(), 1);
        System.out.println(account.toString());
    }

    class MYRowMapper implements RowMapper<Account>{
        @Override
        public Account mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            double balance = resultSet.getDouble("balance");
            Account account = new Account();
            account.setBalance(balance);
            account.setId(id);
            account.setUsername(username);
            return account;
        }
    }

    @Test
    public void t9() {
       String sql="select * from account";
        BeanPropertyRowMapper<Account> rowMapper =
                new BeanPropertyRowMapper<>(Account.class);
        List<Account> query = jt.query(sql, rowMapper);
        System.out.println(query);
    }



}
