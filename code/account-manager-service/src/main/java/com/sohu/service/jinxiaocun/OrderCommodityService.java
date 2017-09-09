package com.sohu.service.jinxiaocun;

import com.sohu.mrd.domain.beans.*;

import java.util.Date;
import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
public interface OrderCommodityService {
    //增
    public int insert(TOrderCommodity tOrder);
    //删
//    public int delete(int id);
    //改
    public int update(int id, TOrderCommodity tOrder);
    //查
    public TOrderCommodity get(int id);
    public List<TOrderCommodity> query(TOrderCommodityExample tOrderExample);

    //

}
