package com.atguigu.cloud.service.imp;

import com.atguigu.cloud.mapper.StorageMapper;
import com.atguigu.cloud.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public int decrease(Long productId, Integer count) {
        log.info("扣减库存");
        return storageMapper.decrease(productId, count);
    }

    /*@Override
    public int decrease(Long productId, Integer count) {

        Storage storage = new Storage();
        storage.setProductId(productId);

        Storage storage1 = storageMapper.selectOne(storage);
        storage.setUsed(storage.getUsed() + count);
        storage.setResidue(storage.getResidue() - count);

        int i = storageMapper.updateByPrimaryKeySelective(storage1);

        return i;
    }*/
}
