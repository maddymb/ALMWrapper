// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper;

import com.jacob.com.ComThread;
import atu.alm.wrapper.enums.DefectStatus;
import atu.alm.wrapper.enums.DefectSeverity;
import atu.alm.wrapper.enums.DefectPriority;
import atu.alm.wrapper.classes.AttachmentStorage;
import atu.alm.wrapper.classes.Attachment;
import java.io.File;
import atu.alm.wrapper.classes.Step;
import atu.alm.wrapper.classes.StepFactory;
import atu.alm.wrapper.classes.Run;
import atu.alm.wrapper.classes.RunFactory;
import java.util.Iterator;
import atu.alm.wrapper.collection.ListWrapper;
import atu.alm.wrapper.classes.TSTestFactory;
import atu.alm.wrapper.classes.TestSet;
import atu.alm.wrapper.classes.TestSetFolder;
import atu.alm.wrapper.classes.TestSetTreeManager;
import atu.alm.wrapper.classes.TSTest;
import atu.alm.wrapper.enums.StatusAs;
import com.jacob.com.ComFailException;
import com.jacob.activeX.ActiveXComponent;
import atu.alm.wrapper.exceptions.ALMServiceException;
import atu.alm.wrapper.classes.TDConnection;
import atu.alm.wrapper.bean.ServerDetails;

public class ALMServiceWrapper
{
    private ServerDetails serverDetails;
    private TDConnection almObj;
    private String testSetFolder;
    private String testSetName;
    
    public ALMServiceWrapper(final String url) {
        this.serverDetails = null;
        this.almObj = null;
        (this.serverDetails = new ServerDetails()).setUrl(url);
    }
    
    public boolean connect(final String username, final String password, final String domain, final String project) throws ALMServiceException {
        this.serverDetails.setUsername(username);
        this.serverDetails.setPassword(password);
        this.serverDetails.setDomain(domain);
        this.serverDetails.setProject(project);
        this.connectToOTA();
        return true;
    }
    
    private void releaseConnection() {
        try {
            if (this.getAlmObj().isConnected()) {
                this.getAlmObj().disconnect();
            }
            if (this.getAlmObj().isLoggedIn()) {
                this.getAlmObj().logout();
            }
            this.getAlmObj().releaseConnection();
        }
        catch (Exception ex) {}
    }
    
    private void connectToOTA() throws ALMServiceException {
        try {
            final ActiveXComponent activexObject = new ActiveXComponent("TDApiOle80.TDConnection");
            this.setAlmObj(new TDConnection(activexObject, this.getServerDetails()));
            this.releaseConnection();
            this.getAlmObj().initConnectionEx(this.getServerDetails().getUrl());
            this.getAlmObj().login(this.getServerDetails().getUsername(), this.getServerDetails().getPassword());
            this.getAlmObj().connect(this.getServerDetails().getDomain(), this.getServerDetails().getProject());
        }
        catch (UnsatisfiedLinkError e) {
            throw new ALMServiceException("Please add the jacob-(version-bit-type).dll file to the System path");
        }
        catch (ComFailException e2) {
            throw new ALMServiceException("Please Register the OTAClient.dll with your system");
        }
    }
    
    public ITestCase updateResult(final String testSetFolderPath, final String testSetName, final int testSetID, final String tcName, final StatusAs as) throws ALMServiceException {
        final TestSetTreeManager testSetTreeManager = this.getAlmObj().getTestSetTreeManager();
        final TestSetFolder testSetFolder = testSetTreeManager.getNodeByPath(testSetFolderPath);
        final TestSet testSet = testSetFolder.findTestSet(testSetName, testSetID);
        final TSTestFactory tsTestFactory = testSet.getTSTestFactory();
        final ListWrapper<TSTest> listWrapper = tsTestFactory.getNewList();
        for (final TSTest tsTest : listWrapper) {
            if (tcName.equals(tsTest.getTestName())) {
                tsTest.putStatus(as);
                tsTest.post();
                return tsTest;
            }
        }
        throw new ALMServiceException("The Given Test Name \"" + tcName + "\" Not Found");
    }
    
    public ITestSet getTestSet(final String testSetFolderPath, final String testSetName, final int testSetID) throws ALMServiceException {
        final TestSetTreeManager testSetTreeManager = this.getAlmObj().getTestSetTreeManager();
        final TestSetFolder testSetFolder = testSetTreeManager.getNodeByPath(testSetFolderPath);
        final TestSet testSet = testSetFolder.findTestSet(testSetName, testSetID);
        return testSet;
    }
    
    public ITestCaseRun createNewRun(final ITestCase tsTest, final String runName, final StatusAs as) {
        final RunFactory runFactory = tsTest.getRunFactory();
        final Run run = runFactory.addItem();
        run.setStatus(as);
        run.setName(runName);
        run.post();
        return run;
    }
    
    public void addStep(final ITestCaseRun run, final String stepName, final StatusAs as, final String description, final String expected, final String actual) {
        final StepFactory stepFactory = run.getStepFactory();
        final Step step = stepFactory.addItem();
        step.setStepName(stepName);
        step.setStatus(as);
        step.setDescription(description);
        step.setExpected(expected);
        step.setActual(actual);
        step.post();
    }
    
    public void newAttachment(final String attachment, final String description, final HasAttachmentFeature hasAttachment) throws ALMServiceException {
        final File file = new File(attachment);
        if (file.exists()) {
            final Attachment attachmentFile = hasAttachment.getAttachments().addItem(file.getName());
            attachmentFile.setDescription(description);
            attachmentFile.post();
            final AttachmentStorage as = attachmentFile.getAttachmentStorage();
            as.clientPath(file.getParent());
            as.save(file.getName());
            return;
        }
        throw new ALMServiceException("The Specified Attachment file does not exist");
    }
    
    public IDefect newDefect() {
        final IDefect defect = this.getAlmObj().getBugFactory().addItem();
        return defect;
    }
    
    public int newDefect(final String detectedBy, final String assignedTo, final DefectPriority priority, final DefectSeverity severity, final DefectStatus status, final String summary, final String detectedDate, final String description, final boolean isReproducible, final String project, final String attachment) throws ALMServiceException {
        final IDefect bug = this.getAlmObj().getBugFactory().addItem();
        if (detectedBy != null && detectedBy.length() > 0) {
            bug.setDetectedBy(detectedBy);
        }
        if (assignedTo != null && assignedTo.length() > 0) {
            bug.setAssignedTo(assignedTo);
        }
        if (priority != null) {
            bug.setPriority(priority);
        }
        if (severity != null) {
            bug.setSeverity(severity);
        }
        if (status != null) {
            bug.setStatus(status);
        }
        if (summary != null && summary.length() > 0) {
            bug.setSummary(summary);
        }
        if (detectedDate != null && detectedDate.length() > 0) {
            bug.setDetectionDate(detectedDate);
        }
        if (description != null && description.length() > 0) {
            bug.setDescription(description);
        }
        bug.isReproducible(isReproducible);
        if (project != null && project.length() > 0) {
            bug.setProject(project);
        }
        final File file = new File(attachment);
        if (file.exists()) {
            final Attachment attachmentFile = bug.getAttachments().addItem(file.getName());
            attachmentFile.setDescription("Sample Attchment Desc");
            attachmentFile.post();
            final AttachmentStorage as = attachmentFile.getAttachmentStorage();
            as.clientPath(file.getParent());
            as.save(file.getName());
            bug.save();
            return bug.getDefectID();
        }
        throw new ALMServiceException("The Specified Attachment file does not exist");
    }
    
    public void close() {
        try {
            this.releaseConnection();
        }
        catch (Exception e) {}
        finally {
            try {
                ComThread.Release();
            }
            catch (Exception ex) {}
        }
    }
    
    public ServerDetails getServerDetails() {
        return this.serverDetails;
    }
    
    public TDConnection getAlmObj() {
        return this.almObj;
    }
    
    public void setAlmObj(final TDConnection almObj) {
        this.almObj = almObj;
    }
    
    public String getTestSetFolder() {
        return this.testSetFolder;
    }
    
    public void setTestSetFolder(final String testSetFolder) {
        this.testSetFolder = testSetFolder;
    }
    
    public String getTestSetName() {
        return this.testSetName;
    }
    
    public void setTestSetName(final String testSetName) {
        this.testSetName = testSetName;
    }
}
