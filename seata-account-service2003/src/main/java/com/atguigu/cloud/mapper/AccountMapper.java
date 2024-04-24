package com.atguigu.cloud.mapper;

import com.atguigu.cloud.entities.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

public interface AccountMapper extends Mapper<Account> {

    /**
     * 扣减余额
     */
    @Update("update t_account set used = used + #{money},residue = residue - #{money} where user_id= #{userId}")
    int decrease(@Param("userId") Long userId, @Param("money") Long money);
}