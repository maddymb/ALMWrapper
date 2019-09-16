// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import atu.alm.wrapper.enums.DefectPriority;
import atu.alm.wrapper.enums.DefectStatus;
import com.jacob.com.Variant;
import atu.alm.wrapper.enums.DefectSeverity;
import com.jacob.com.Dispatch;
import atu.alm.wrapper.IDefect;

public class Bug implements IDefect
{
    private Dispatch bugFactory;
    private Dispatch bug;
    
    public Bug(final Dispatch bugFactory) {
        this.bugFactory = bugFactory;
        this.bug = this.init();
    }
    
    @Override
    public AttachmentFactory getAttachments() {
        return new AttachmentFactory(this.bug);
    }
    
    private Dispatch init() {
        final Dispatch bug = Dispatch.call(this.bugFactory, "AddItem", new Object[] { "" }).toDispatch();
        return bug;
    }
    
    @Override
    public void setAssignedTo(final String assignedTo) {
        Dispatch.put(this.bug, "AssignedTo", (Object)assignedTo);
    }
    
    @Override
    public void setSeverity(final DefectSeverity severity) {
        Dispatch.invoke(this.bug, "Field", 4, new Object[] { "BG_SEVERITY", new Variant((Object)severity.getSeverity()) }, new int[1]);
    }
    
    @Override
    public void setDescription(final String description) {
        Dispatch.invoke(this.bug, "Field", 4, new Object[] { "BG_DESCRIPTION", new Variant((Object)description) }, new int[1]);
    }
    
    @Override
    public void setProject(final String project) {
        Dispatch.invoke(this.bug, "Field", 4, new Object[] { "BG_PROJECT", new Variant((Object)project) }, new int[1]);
    }
    
    @Override
    public void isReproducible(final boolean isReproducible) {
        String reproducible;
        if (isReproducible) {
            reproducible = "Y";
        }
        else {
            reproducible = "N";
        }
        Dispatch.invoke(this.bug, "Field", 4, new Object[] { "BG_REPRODUCIBLE", new Variant((Object)reproducible) }, new int[1]);
    }
    
    @Override
    public void setDetectionDate(final String date) {
        Dispatch.invoke(this.bug, "Field", 4, new Object[] { "BG_DETECTION_DATE", new Variant((Object)date) }, new int[1]);
    }
    
    @Override
    public int getDefectID() {
        final int id = Dispatch.call(this.bug, "ID").getInt();
        return id;
    }
    
    @Override
    public void setStatus(final DefectStatus status) {
        Dispatch.put(this.bug, "Status", (Object)status.getStatus());
    }
    
    @Override
    public void setPriority(final DefectPriority priority) {
        Dispatch.put(this.bug, "Priority", (Object)priority.getPriority());
    }
    
    @Override
    public void setSummary(final String summary) {
        Dispatch.put(this.bug, "Summary", (Object)summary);
    }
    
    @Override
    public void setDetectedBy(final String detectedBy) {
        Dispatch.put(this.bug, "DetectedBy", (Object)detectedBy);
    }
    
    @Override
    public void save() {
        Dispatch.call(this.bug, "Post");
    }
}
