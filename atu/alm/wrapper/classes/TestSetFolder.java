// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import atu.alm.wrapper.exceptions.ALMServiceException;
import com.jacob.com.Dispatch;
import com.jacob.activeX.ActiveXComponent;

public class TestSetFolder
{
    private ActiveXComponent almObject;
    private Dispatch testSetFolder;
    
    public TestSetFolder(final ActiveXComponent almObject, final Dispatch testSetFolder) {
        this.almObject = almObject;
        this.testSetFolder = testSetFolder;
    }
    
    public TestSet findTestSet(final String testSetName, final int searchTestSetID) throws ALMServiceException {
        final Dispatch listOfTestSet = Dispatch.call(this.testSetFolder, "FindTestSets", new Object[] { testSetName, true, null }).toDispatch();
        Dispatch testSet = null;
        try {
            for (int count = Dispatch.call(listOfTestSet, "Count").getInt(), i = 1; i <= count; ++i) {
                testSet = Dispatch.call(listOfTestSet, "Item", new Object[] { i }).toDispatch();
                final int testSetID = Dispatch.call(testSet, "ID").getInt();
                if (searchTestSetID == testSetID) {
                    return new TestSet(this.almObject, testSet);
                }
            }
            throw new ALMServiceException("The Given Test Set Name \"" + testSetName + "\" Not Found");
        }
        catch (NullPointerException e) {
            throw new ALMServiceException("The Given Test Set Name \"" + testSetName + "\" Not Found ");
        }
    }
    
    public int getCount() {
        final int count = Dispatch.call(this.testSetFolder, "Count").getInt();
        return count;
    }
    
    public String getName() {
        final String name = Dispatch.call(this.testSetFolder, "Name").getString();
        return name;
    }
    
    public String getPath() {
        final String path = Dispatch.call(this.testSetFolder, "Path").getString();
        return path;
    }
}
