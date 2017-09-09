package com.sohu.mrd.domain.beans;

/**
 * Created by worgen on 2016/6/25.
 */
public class TOrderAssociationExample extends TOrderExample{
    private TStoreExample tStoreExample;

    private TOrderCommodityExample tOrderCommodityExample;

    public TOrderCommodityExample gettOrderCommodityExample() {
        return tOrderCommodityExample;
    }

    public void settOrderCommodityExample(TOrderCommodityExample tOrderCommodityExample) {
        this.tOrderCommodityExample = tOrderCommodityExample;
    }

    public TStoreExample gettStoreExample() {
        return tStoreExample;
    }

    public void settStoreExample(TStoreExample tStoreExample) {
        this.tStoreExample = tStoreExample;
    }


}
