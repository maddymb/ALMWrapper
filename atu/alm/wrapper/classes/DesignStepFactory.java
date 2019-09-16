// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import com.jacob.com.Dispatch;

public class DesignStepFactory
{
    private Dispatch test;
    
    public DesignStepFactory(final Dispatch test) {
        this.test = test;
        this.init();
    }
    
    private Dispatch init() {
        final Dispatch designStepFactory = Dispatch.get(this.test, "DesignStepFactory").toDispatch();
        return designStepFactory;
    }
}
