// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import com.jacob.com.Dispatch;

public class RunFactory
{
    private Dispatch runFactory;
    private Dispatch tsTest;
    
    public RunFactory(final Dispatch tsTest) {
        this.tsTest = tsTest;
        this.runFactory = this.init();
    }
    
    private Dispatch init() {
        final Dispatch runFactory = Dispatch.get(this.tsTest, "RunFactory").toDispatch();
        return runFactory;
    }
    
    public Run addItem() {
        final Dispatch run = Dispatch.call(this.runFactory, "AddItem", new Object[] { "Null" }).toDispatch();
        return new Run(run);
    }
}
