package com.sohu.mrd.domain.beans;

/**
 * Created by worgen on 2016/6/25.
 */
public class TExpenseAssociationExample extends TExpenseExample{
    private TStoreExample tStoreExample;

    public TStoreExample gettStoreExample() {
        return tStoreExample;
    }

    public void settStoreExample(TStoreExample tStoreExample) {
        this.tStoreExample = tStoreExample;
    }


}
