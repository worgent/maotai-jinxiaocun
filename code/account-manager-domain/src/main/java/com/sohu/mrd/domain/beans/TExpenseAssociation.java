package com.sohu.mrd.domain.beans;

import java.util.Date;

public class TExpenseAssociation extends  TExpense{

    private TStore tStore;

    public TStore gettStore() {
        return tStore;
    }

    public void settStore(TStore tStore) {
        this.tStore = tStore;
    }
}