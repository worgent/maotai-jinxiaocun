package com.sohu.service.jinxiaocun;

import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TCommodity;

import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
public interface CommodityService {

    //增
    public int insert(TCommodity tCommodity);
    //删
    public int delete(int id);
    //改
    public int update(int id, TCommodity tCommodity);
    //查
    public TCommodity get(int id);
    public List<TCommodity> query(TCommodityExample tCommodityExample);

    //
    public List<TCommodity> query(int pageNumber, int pageSize);
}
