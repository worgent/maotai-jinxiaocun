package com.sohu.mrd.domain.beans;

/**
 * Created by worgen on 2016/6/25.
 */
public class TReceiptAssociationExample extends TReceiptExample{
    private TStoreExample tStoreExample;

    private TCustomerExample tCustomerExample;

    public TCustomerExample gettCustomerExample() {
        return tCustomerExample;
    }

    public void settCustomerExample(TCustomerExample tCustomerExample) {
        this.tCustomerExample = tCustomerExample;
    }
    public TStoreExample gettStoreExample() {
        return tStoreExample;
    }

    public void settStoreExample(TStoreExample tStoreExample) {
        this.tStoreExample = tStoreExample;
    }


}
