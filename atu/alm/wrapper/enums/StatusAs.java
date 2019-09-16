// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.enums;

public enum StatusAs
{
    NO_RUN("No Run"), 
    PASSED("Passed"), 
    FAILED("Failed"), 
    BLOCKED("Blocked"), 
    N_A("N/A"), 
    NOT_COMPLETED("Not Completed");
    
    private String status;
    
    private StatusAs(final String status) {
        this.setStatus(status);
    }
    
    public String getStatus() {
        return this.status;
    }
    
    private void setStatus(final String status) {
        this.status = status;
    }
}
