// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import atu.alm.wrapper.enums.StatusAs;
import com.jacob.com.Variant;
import com.jacob.com.Dispatch;

public class Step
{
    private Dispatch step;
    
    public Step(final Dispatch step) {
        this.step = step;
    }
    
    public void setStepName(final String stepName) {
        Dispatch.invoke(this.step, "Field", 4, new Object[] { "ST_STEP_NAME", new Variant((Object)stepName) }, new int[1]);
    }
    
    public void setStatus(final StatusAs as) {
        Dispatch.invoke(this.step, "Field", 4, new Object[] { "ST_STATUS", new Variant((Object)as.getStatus()) }, new int[1]);
    }
    
    public void setDescription(final String description) {
        Dispatch.invoke(this.step, "Field", 4, new Object[] { "ST_DESCRIPTION", new Variant((Object)description) }, new int[1]);
    }
    
    public void setActual(final String actual) {
        Dispatch.invoke(this.step, "Field", 4, new Object[] { "ST_ACTUAL", new Variant((Object)actual) }, new int[1]);
    }
    
    public void setExpected(final String expected) {
        Dispatch.invoke(this.step, "Field", 4, new Object[] { "ST_EXPECTED", new Variant((Object)expected) }, new int[1]);
    }
    
    public void post() {
        Dispatch.call(this.step, "Post");
    }
}
