package com.sohu.service.jinxiaocun;

import com.sohu.mrd.domain.beans.TOrder;
import com.sohu.mrd.domain.beans.TOrderExample;
import com.sohu.mrd.domain.beans.TStore;
import com.sohu.mrd.domain.beans.TStoreExample;

import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
public interface StoreService {

    //增
    public int insert(TStore tStore);
    //删
    public int delete(int id);
    //改
    public int update(int id, TStore tStore);
    //查
    public TStore get(int id);
    public List<TStore> query(TStoreExample tStoreExample);

    //查询所有分公司列表
    public List<TStore> query(int pageNumber, int pageSize);

}
