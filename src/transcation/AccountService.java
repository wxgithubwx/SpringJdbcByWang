package transcation;

public class AccountService {
    AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void transfer(Integer inID,Integer outId,double balance){
        //开启事务
        accountDao.in(inID,balance);
        //int i=2/0;

        accountDao.out(outId,balance);
        int i=2/0;//只要是在这个方法体中 如果发生异常则双方金额不变化
        //提交事务
    }
}
