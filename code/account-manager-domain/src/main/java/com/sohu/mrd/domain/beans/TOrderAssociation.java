package com.sohu.mrd.domain.beans;

import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
public class TOrderAssociation extends TOrder{

    private TStore tStore;

    private List<TOrderCommodity> tOrderCommodities;

    public List<TOrderCommodity> gettOrderCommodities() {
        return tOrderCommodities;
    }

    public void settOrderCommodities(List<TOrderCommodity> tOrderCommodities) {
        this.tOrderCommodities = tOrderCommodities;
    }

    public TStore gettStore() {
        return tStore;
    }

    public void settStore(TStore tStore) {
        this.tStore = tStore;
    }
}
