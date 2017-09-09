package com.sohu.mrd.domain.beans;

import com.sohu.mrd.domain.beans.TCustomerExample;
import com.sohu.mrd.domain.beans.TStoreExample;

/**
 * Created by worgen on 2016/6/25.
 */
public class TCustomerAssociationExample extends TCustomerExample{
    private TStoreExample tStoreExample;

    public TStoreExample gettStoreExample() {
        return tStoreExample;
    }

    public void settStoreExample(TStoreExample tStoreExample) {
        this.tStoreExample = tStoreExample;
    }


}
