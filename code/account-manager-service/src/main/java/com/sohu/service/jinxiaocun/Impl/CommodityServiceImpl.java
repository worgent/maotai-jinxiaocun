package com.sohu.service.jinxiaocun.Impl;

import com.sohu.dao.TCommodityMapper;
import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TCommodity;
import com.sohu.mrd.domain.beans.TCommodityExample;
import com.sohu.service.jinxiaocun.CommodityService;
import com.sohu.service.jinxiaocun.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private TCommodityMapper tCommodityMapper;

    @Override
    public int insert(TCommodity tCommodity) {
        return tCommodityMapper.insertSelective(tCommodity);
    }

    @Override
    public int delete(int id) {
        TCommodity tCommodity = get(id);
        if( tCommodity == null ) {
            return -1;
        }
        tCommodity = new TCommodity();

        tCommodity.setDelFlag(1);
        return update(id, tCommodity);
    }

    @Override
    public int update(int id, TCommodity tCommodity) {
        tCommodity.setId(id);
        return tCommodityMapper.updateByPrimaryKeySelective(tCommodity);
    }
    @Override
    public TCommodity get(int id) {
        TCommodityExample tCommodityExample = new TCommodityExample();
        tCommodityExample.createCriteria().andDelFlagEqualTo(0).andIdEqualTo(id);
        List<TCommodity> tCommoditys = tCommodityMapper.selectByExample(tCommodityExample);
        return tCommoditys.size() > 0 ? tCommoditys.get(0) : null;    }
    @Override
    public List<TCommodity> query(TCommodityExample tCommodityExample) {
        return tCommodityMapper.selectByExample(tCommodityExample);
    }


    @Override
    public List<TCommodity> query(int pageNumber, int pageSize) {
        TCommodityExample tCommodityExample = new TCommodityExample();
        tCommodityExample.createCriteria().andDelFlagEqualTo(0);

        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = pageNumber*pageSize;

        tCommodityExample.setOrderByClause("t_commodity.id limit "+limitStart+","+limitEnd);

        return tCommodityMapper.selectByExample(tCommodityExample);

    }
}
