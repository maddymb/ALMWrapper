// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import com.jacob.com.Dispatch;

public class StepFactory
{
    private Dispatch stepFactory;
    private Dispatch run;
    
    public StepFactory(final Dispatch run) {
        this.run = run;
        this.stepFactory = this.init();
    }
    
    private Dispatch init() {
        final Dispatch stepFactory = Dispatch.get(this.run, "StepFactory").toDispatch();
        return stepFactory;
    }
    
    public Step addItem() {
        final Dispatch step = Dispatch.call(this.stepFactory, "AddItem", new Object[] { "Null" }).toDispatch();
        return new Step(step);
    }
}
