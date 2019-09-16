// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.enums;

public enum DefectPriority
{
    LOW("1-Low"), 
    MEDIUM("2-Medium"), 
    HIGH("3-High"), 
    VERY_HIGH("4-Very High"), 
    URGENT("5-Urgent");
    
    private String priority;
    
    private DefectPriority(final String priority) {
        this.setPriority(priority);
    }
    
    public String getPriority() {
        return this.priority;
    }
    
    private void setPriority(final String priority) {
        this.priority = priority;
    }
}
