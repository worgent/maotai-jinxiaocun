package com.sohu.mrd.domain.beans;

public class TReceiptAssociation extends  TReceipt{

    private TStore tStore;


    private TCustomer tCustomer;

    public TCustomer gettCustomer() {
        return tCustomer;
    }

    public void settCustomer(TCustomer tCustomer) {
        this.tCustomer = tCustomer;
    }

    public TStore gettStore() {
        return tStore;
    }

    public void settStore(TStore tStore) {
        this.tStore = tStore;
    }
}