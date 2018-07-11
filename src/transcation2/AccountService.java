package transcation2;

import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class AccountService {
    @Resource
    AccountDao accountDao;

  /*  public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
*/
  @Transactional
    public void transfer(Integer inID,Integer outId,double balance){
        //开启事务
        accountDao.in(inID,balance);
        //int i=2/0;

        accountDao.out(outId,balance);
        int i=2/0;//只要是在这个方法体中 如果发生异常则双方金额不变化
        //提交事务
    }
}
