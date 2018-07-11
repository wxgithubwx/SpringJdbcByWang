package transcation;

import org.springframework.jdbc.core.JdbcTemplate;

public class AccountDao {
    JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void in(Integer id, double balance){
        String sql="UPDATE `springjdbc`.`account` SET  balance=balance+? WHERE (`id`=?)";
        jdbcTemplate.update(sql,balance,id);
    }

    public void out(Integer id,double balance){
        String sql="UPDATE `springjdbc`.`account` SET  balance=balance-? WHERE (`id`=?)";
        jdbcTemplate.update(sql,balance,id);
    }
}
