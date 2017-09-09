package com.sohu.service.jinxiaocun.Impl;

import com.sohu.dao.TStoreMapper;
import com.sohu.mrd.domain.beans.*;
import com.sohu.service.jinxiaocun.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
@Service
public class StoreServiceImpl implements StoreService{
    @Autowired
    private TStoreMapper tStoreMapper;

    @Override
    public int insert(TStore tStore) {
        return tStoreMapper.insertSelective(tStore);
    }

    @Override
    public int delete(int id) {
        TStore tStore = get(id);
        if( tStore == null ) {
            return -1;
        }
        tStore = new TStore();
        tStore.setDelFlag(1);
        return update(id, tStore);
    }

    @Override
    public int update(int id, TStore tStore) {
        tStore.setId(id);
        return tStoreMapper.updateByPrimaryKeySelective(tStore);
    }

    @Override
    public TStore get(int id) {
        TStoreExample tStoreExample = new TStoreExample();
        tStoreExample.createCriteria().andDelFlagEqualTo(0).andIdEqualTo(id);
        List<TStore> tStores = tStoreMapper.selectByExample(tStoreExample);
        return tStores.size() > 0 ? tStores.get(0) : null;    }

    @Override
    public List<TStore> query(TStoreExample tStoreExample) {
        return tStoreMapper.selectByExample(tStoreExample);
    }

    @Override
    public List<TStore> query(int pageNumber, int pageSize) {
        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = (pageNumber)*pageSize;
        TStoreExample tStoreExample = new TStoreExample();
        tStoreExample.createCriteria().andDelFlagEqualTo(0);

        tStoreExample.setOrderByClause("id limit "+limitStart+","+limitEnd);

        return query(tStoreExample);
    }
}
