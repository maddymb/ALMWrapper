// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import atu.alm.wrapper.enums.StatusAs;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import atu.alm.wrapper.ITestCase;

public class TSTest implements ITestCase
{
    private Dispatch test;
    private Dispatch tsTest;
    
    public TSTest(final ActiveXComponent almObject, final Dispatch test) {
        this.test = test;
        this.tsTest = this.init();
    }
    
    public TSTest(final Dispatch tsTest) {
        this.tsTest = tsTest;
    }
    
    private Dispatch init() {
        final Dispatch tsTest = Dispatch.call(this.test, "Item", new Object[] { 1 }).toDispatch();
        return tsTest;
    }
    
    public String getName() {
        final String name = Dispatch.call(this.tsTest, "Name").getString();
        return name;
    }
    
    public String getTestName() {
        final String testName = Dispatch.call(this.tsTest, "TestName").getString();
        return testName;
    }
    
    public void putStatus(final StatusAs as) {
        Dispatch.put(this.tsTest, "Status", (Object)as.getStatus().trim());
    }
    
    @Override
    public RunFactory getRunFactory() {
        return new RunFactory(this.tsTest);
    }
    
    public void post() {
        Dispatch.call(this.tsTest, "Post");
    }
    
    @Override
    public AttachmentFactory getAttachments() {
        return new AttachmentFactory(this.tsTest);
    }
}
