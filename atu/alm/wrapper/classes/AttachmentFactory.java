// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import com.jacob.com.Dispatch;

public class AttachmentFactory
{
    private Dispatch attachmentFactory;
    private Dispatch bug;
    
    public AttachmentFactory(final Dispatch bug) {
        this.bug = bug;
        this.attachmentFactory = this.init();
    }
    
    private Dispatch init() {
        final Dispatch attachmentFactory = Dispatch.call(this.bug, "Attachments").toDispatch();
        return attachmentFactory;
    }
    
    public Attachment addItem(final String fileName) {
        final Dispatch attachment = Dispatch.call(this.attachmentFactory, "AddItem", new Object[] { fileName }).toDispatch();
        return new Attachment(attachment);
    }
}
