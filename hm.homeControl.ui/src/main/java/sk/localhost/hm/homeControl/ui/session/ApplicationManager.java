/**
 *
 */
package sk.localhost.hm.homeControl.ui.session;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.Logger;
import org.primefaces.model.TreeNode;

import sk.localhost.hm.homeControl.core.qualifiers.MainLogger;

/**
 * A Singleton bean which store data for the application
 *
 * @author malyp
 */

@Named
@Singleton
@Startup
public class ApplicationManager {

    public static final String DEFAULT_LOCATION = "Nitra";

    @Inject
    @MainLogger
    private Logger logger;

    private TreeNode root;
    private List<SessionManager> sessions;

    @PostConstruct
    public void init() {
        System.out.println("HC init()");
        sessions = new ArrayList<SessionManager>();

    }

    @Lock(LockType.READ)
    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void updateSessions() {
        for (SessionManager sm : sessions) {}
    }

    public void addSession(SessionManager sm) {
        sessions.add(sm);
    }

    public boolean removeSession(SessionManager sm) {
        return sessions.remove(sm);
    }

    public int getNumberOfSession() {
        return sessions.size();
    }

}
