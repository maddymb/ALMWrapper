// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import com.jacob.com.Dispatch;
import com.jacob.activeX.ActiveXComponent;
import atu.alm.wrapper.ITestSet;

public class TestSet implements ITestSet
{
    private ActiveXComponent almObject;
    private Dispatch testSet;
    
    public TestSet(final ActiveXComponent almObject, final Dispatch testSet) {
        this.almObject = almObject;
        this.testSet = testSet;
    }
    
    public TSTestFactory getTSTestFactory() {
        return new TSTestFactory(this.almObject, this.testSet);
    }
    
    public String getName() {
        final String name = Dispatch.call(this.testSet, "Name").getString();
        return name;
    }
    
    @Override
    public AttachmentFactory getAttachments() {
        return new AttachmentFactory(this.testSet);
    }
}
