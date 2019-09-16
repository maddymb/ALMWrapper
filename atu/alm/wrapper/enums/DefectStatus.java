// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.enums;

public enum DefectStatus
{
    NEW("New"), 
    OPEN("Open"), 
    REJECTED("Rejected"), 
    FIXED("Fixed"), 
    REOPEN("Reopen"), 
    CLOSED("Closed");
    
    private String status;
    
    private DefectStatus(final String status) {
        this.setStatus(status);
    }
    
    public String getStatus() {
        return this.status;
    }
    
    private void setStatus(final String status) {
        this.status = status;
    }
}
