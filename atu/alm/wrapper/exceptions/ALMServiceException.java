// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.exceptions;

public class ALMServiceException extends Exception
{
    private String message;
    
    public ALMServiceException(final String message) {
        super(message);
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "ALM Service Wrapper Exception Details:  " + this.message;
    }
}
