// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import com.jacob.com.Dispatch;
import com.jacob.activeX.ActiveXComponent;

public class BugFactory
{
    private ActiveXComponent almObject;
    private Dispatch bugFactory;
    
    public BugFactory(final ActiveXComponent almObject) {
        this.almObject = almObject;
        this.bugFactory = this.init();
    }
    
    private Dispatch init() {
        final Dispatch bugFactory = Dispatch.call((Dispatch)this.almObject, "BugFactory").toDispatch();
        return bugFactory;
    }
    
    public Bug addItem() {
        return new Bug(this.bugFactory);
    }
}
