package transcation2;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

public class AccountDao {
    @Resource
    JdbcTemplate jdbcTemplate;

   /* public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
*/
    public void in(Integer id, double balance){
        String sql="UPDATE `springjdbc`.`account` SET  balance=balance+? WHERE (`id`=?)";
        jdbcTemplate.update(sql,balance,id);
    }

    public void out(Integer id,double balance){
        String sql="UPDATE `springjdbc`.`account` SET  balance=balance-? WHERE (`id`=?)";
        jdbcTemplate.update(sql,balance,id);
    }
}
