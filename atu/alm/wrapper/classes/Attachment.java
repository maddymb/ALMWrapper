// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import com.jacob.com.Dispatch;

public class Attachment
{
    private Dispatch currentAttachment;
    
    public Attachment(final Dispatch currentAttachment) {
        this.currentAttachment = currentAttachment;
    }
    
    public void setDescription(final String attachmentDescription) {
        Dispatch.put(this.currentAttachment, "Description", (Object)attachmentDescription);
    }
    
    public void setFileName(final String filePath) {
        System.out.println("FILE PATH" + filePath);
        Dispatch.put(this.currentAttachment, "FileName", (Object)filePath);
    }
    
    public void setType(final String type) {
        Dispatch.put(this.currentAttachment, "Type", (Object)type);
    }
    
    public void post() {
        Dispatch.call(this.currentAttachment, "Post");
    }
    
    public AttachmentStorage getAttachmentStorage() {
        return new AttachmentStorage(this.currentAttachment);
    }
}
