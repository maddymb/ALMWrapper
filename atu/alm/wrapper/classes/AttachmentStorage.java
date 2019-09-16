// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import com.jacob.com.Dispatch;

public class AttachmentStorage
{
    private Dispatch currentAttachment;
    private Dispatch attachmentStorage;
    
    public AttachmentStorage(final Dispatch currentAttachment) {
        this.currentAttachment = currentAttachment;
        this.attachmentStorage = this.init();
    }
    
    private Dispatch init() {
        final Dispatch attachmentStorage = Dispatch.call(this.currentAttachment, "AttachmentStorage").toDispatch();
        return attachmentStorage;
    }
    
    public void clientPath(final String directoryPath) {
        Dispatch.call(this.attachmentStorage, "ClientPath");
    }
    
    public void save(final String fileName) {
        Dispatch.call(this.attachmentStorage, "Save", new Object[] { fileName, true });
    }
    
    public String getErrorMessage() {
        return Dispatch.call(this.attachmentStorage, "GetLastError").getString();
    }
}
