// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper;

import atu.alm.wrapper.classes.AttachmentFactory;
import atu.alm.wrapper.enums.DefectStatus;
import atu.alm.wrapper.enums.DefectSeverity;
import atu.alm.wrapper.enums.DefectPriority;

public interface IDefect extends HasAttachmentFeature
{
    void setDetectedBy(final String p0);
    
    void setAssignedTo(final String p0);
    
    void setPriority(final DefectPriority p0);
    
    void setSeverity(final DefectSeverity p0);
    
    void setStatus(final DefectStatus p0);
    
    void setSummary(final String p0);
    
    void setDetectionDate(final String p0);
    
    void setDescription(final String p0);
    
    void isReproducible(final boolean p0);
    
    void setProject(final String p0);
    
    void save();
    
    int getDefectID();
    
    AttachmentFactory getAttachments();
}
