// 
// Decompiled by Procyon v0.5.36
// 

package atu.alm.wrapper.classes;

import com.jacob.com.ComFailException;
import atu.alm.wrapper.exceptions.ALMServiceException;
import com.jacob.com.Dispatch;
import atu.alm.wrapper.bean.ServerDetails;
import com.jacob.activeX.ActiveXComponent;

public class TDConnection
{
    private ActiveXComponent almObject;
    
    public TDConnection(final ActiveXComponent almObject, final ServerDetails serverDetails) {
        this.almObject = null;
        this.setAlmObject(almObject);
    }
    
    public BugFactory getBugFactory() {
        return new BugFactory(this.getAlmObject());
    }
    
    public TestFactory getTestFactory() {
        return new TestFactory(this.getAlmObject());
    }
    
    public TreeManager getTreeManager() {
        return new TreeManager(this.getAlmObject());
    }
    
    public TestSetTreeManager getTestSetTreeManager() {
        return new TestSetTreeManager(this.getAlmObject());
    }
    
    public boolean initConnectionEx(final String url) throws ALMServiceException {
        try {
            Dispatch.call((Dispatch)this.getAlmObject(), "InitConnectionEx", new Object[] { url });
            return true;
        }
        catch (ComFailException e) {
            throw new ALMServiceException("Unable to Establish connection for the Given URL: " + url);
        }
    }
    
    public boolean login(final String username, final String password) throws ALMServiceException {
        try {
            Dispatch.call((Dispatch)this.getAlmObject(), "login", new Object[] { username, password });
            return true;
        }
        catch (ComFailException e) {
            throw new ALMServiceException("Invalid Username or Password");
        }
    }
    
    public boolean connect(final String domain, final String project) throws ALMServiceException {
        try {
            Dispatch.call((Dispatch)this.getAlmObject(), "connect", new Object[] { domain, project });
            return true;
        }
        catch (ComFailException e) {
            throw new ALMServiceException("Invalid Project or Domain");
        }
    }
    
    public boolean isConnected() {
        boolean isLoggedIn = false;
        try {
            isLoggedIn = Dispatch.call((Dispatch)this.getAlmObject(), "Connected").getBoolean();
        }
        catch (IllegalStateException e) {
            return isLoggedIn;
        }
        return isLoggedIn;
    }
    
    public boolean isLoggedIn() {
        boolean isLoggedIn = false;
        try {
            isLoggedIn = Dispatch.call((Dispatch)this.getAlmObject(), "loggedIn").getBoolean();
        }
        catch (IllegalStateException e) {
            return isLoggedIn;
        }
        return isLoggedIn;
    }
    
    public boolean disconnect() {
        Dispatch.call((Dispatch)this.getAlmObject(), "disconnectProject");
        return true;
    }
    
    public boolean logout() {
        Dispatch.call((Dispatch)this.getAlmObject(), "logout");
        return true;
    }
    
    public boolean releaseConnection() {
        Dispatch.call((Dispatch)this.getAlmObject(), "releaseConnection");
        return true;
    }
    
    public ActiveXComponent getAlmObject() {
        return this.almObject;
    }
    
    public void setAlmObject(final ActiveXComponent almObject) {
        this.almObject = almObject;
    }
}
