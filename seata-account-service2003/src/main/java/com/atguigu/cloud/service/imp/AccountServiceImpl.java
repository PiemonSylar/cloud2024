package com.atguigu.cloud.service.imp;

import com.atguigu.cloud.mapper.AccountMapper;
import com.atguigu.cloud.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper AccountMapper;

    @Override
    public int decrease(Long userId, Long money) {
        log.info("扣减余额");
        int decrease = AccountMapper.decrease(userId, money);

        my_sleep(63);
//        int age=10/0;
        return decrease;

    }

    /*@Override
    public int decrease(Long productId, Integer count) {

        Account Account = new Account();
        Account.setProductId(productId);

        Account Account1 = AccountMapper.selectOne(Account);
        Account.setUsed(Account.getUsed() + count);
        Account.setResidue(Account.getResidue() - count);

        int i = AccountMapper.updateByPrimaryKeySelective(Account1);

        return i;
    }*/

    private void my_sleep(long s) {
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
