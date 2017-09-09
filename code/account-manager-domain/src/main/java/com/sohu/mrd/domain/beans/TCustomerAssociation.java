package com.sohu.mrd.domain.beans;

/**
 * Created by worgen on 2016/6/25.
 */
public class TCustomerAssociation extends TCustomer{

    private TStore tStore;

    public TStore gettStore() {
        return tStore;
    }

    public void settStore(TStore tStore) {
        this.tStore = tStore;
    }
}
