// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import atu.alm.wrapper.collection.ListWrapper;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

public class TSTestFactory
{
    private Dispatch testSet;
    private Dispatch tsTestFactory;
    
    public TSTestFactory(final ActiveXComponent almObject, final Dispatch testSet) {
        this.testSet = testSet;
        this.tsTestFactory = this.init();
    }
    
    private Dispatch init() {
        final Dispatch tsTestFactory = Dispatch.get(this.testSet, "TSTestFactory").toDispatch();
        return tsTestFactory;
    }
    
    public ListWrapper<TSTest> getNewList() {
        final Dispatch listOfTests = Dispatch.call(this.tsTestFactory, "NewList", new Object[] { "" }).toDispatch();
        final int count = Dispatch.call(listOfTests, "Count").getInt();
        final ListWrapper<TSTest> listWrapper = new ListWrapper<TSTest>();
        for (int i = 1; i <= count; ++i) {
            final Dispatch dispatchTSTest = Dispatch.call(listOfTests, "Item", new Object[] { i }).toDispatch();
            final TSTest tsTest = new TSTest(dispatchTSTest);
            listWrapper.add(tsTest);
        }
        return listWrapper;
    }
}
