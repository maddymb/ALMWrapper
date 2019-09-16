// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import atu.alm.wrapper.enums.StatusAs;
import com.jacob.com.Dispatch;
import atu.alm.wrapper.ITestCaseRun;

public class Run implements ITestCaseRun
{
    private Dispatch run;
    
    public Run(final Dispatch run) {
        this.run = run;
    }
    
    public void setStatus(final StatusAs as) {
        Dispatch.put(this.run, "Status", (Object)as.getStatus());
    }
    
    public void setName(final String runName) {
        Dispatch.put(this.run, "Name", (Object)runName);
    }
    
    public void post() {
        Dispatch.call(this.run, "Post");
    }
    
    public int getID() {
        return Dispatch.call(this.run, "ID").getInt();
    }
    
    @Override
    public StepFactory getStepFactory() {
        return new StepFactory(this.run);
    }
    
    @Override
    public AttachmentFactory getAttachments() {
        return new AttachmentFactory(this.run);
    }
}
