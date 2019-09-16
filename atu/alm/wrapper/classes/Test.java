// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import com.jacob.com.Dispatch;

public class Test
{
    private Dispatch tsTest;
    private Dispatch test;
    
    public Test(final Dispatch tsTest) {
        this.tsTest = tsTest;
        this.test = this.init();
    }
    
    private Dispatch init() {
        final Dispatch test = Dispatch.get(this.tsTest, "Test").toDispatch();
        return test;
    }
    
    public DesignStepFactory getDesignStepFactory() {
        return new DesignStepFactory(this.test);
    }
}
