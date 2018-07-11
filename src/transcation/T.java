package transcation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:transcation/applicationContext.xml")
public class T {
    @Resource
    AccountService accountService;
    @Test
    public void t(){
        accountService.transfer(2,1,1500);
        System.out.println("转账成功");
    }
}
