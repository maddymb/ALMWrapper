// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import com.jacob.com.ComFailException;
import atu.alm.wrapper.exceptions.ALMServiceException;
import com.jacob.com.Dispatch;
import com.jacob.activeX.ActiveXComponent;

public class TestSetTreeManager
{
    private ActiveXComponent almObject;
    private Dispatch testSetTreeManager;
    private static final String ROOT = "Root";
    
    public TestSetTreeManager(final ActiveXComponent almObject) {
        this.almObject = almObject;
        this.testSetTreeManager = this.init();
    }
    
    private Dispatch init() {
        final Dispatch testSetTreeManager = Dispatch.get((Dispatch)this.almObject, "TestSetTreeManager").toDispatch();
        return testSetTreeManager;
    }
    
    public TestSetFolder getNodeByPath(final String testSetFolderPath) throws ALMServiceException {
        Dispatch testSetFolder;
        try {
            testSetFolder = Dispatch.call(this.testSetTreeManager, "NodeByPath", new Object[] { "Root\\" + testSetFolderPath }).toDispatch();
        }
        catch (ComFailException e) {
            throw new ALMServiceException("The Given Test Set Folder Path \"" + testSetFolderPath + "\" Not Found");
        }
        return new TestSetFolder(this.almObject, testSetFolder);
    }
}
