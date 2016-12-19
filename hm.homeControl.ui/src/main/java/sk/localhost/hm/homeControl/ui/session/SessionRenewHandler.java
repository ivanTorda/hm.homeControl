package sk.localhost.hm.homeControl.ui.session;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.Logger;

import sk.localhost.hm.homeControl.core.qualifiers.MainLogger;
import sk.localhost.hm.homeControl.ui.resources.ResourceBundleManager;
import sk.localhost.hm.homeControl.ui.resources.ResourceBundles;

/**
 * Just provide a function to renew the current Session
 *
 * @author malyp
 * @version 1.0
 */
@Named
@RequestScoped
public class SessionRenewHandler {

    @Inject
    SessionManager sessionManager;

    @Inject
    @MainLogger
    private Logger logger;

    /**
     * Method which update the data on the session and redirect the current page
     *
     * @author malyp
     */
    public void renew() {
        sessionManager.refreshSession();
        sessionManager.updatePageAndRedirect();
    }

    /**
     * The method read the pageRefresh property file and return the poll value for the current page (current viewId)
     *
     * @author malyp
     * @return poll value for refreshing the page
     */
    public String getPollInterval() {
        final String DEFAULT_POLL_KEY = "default";
        final String DEFAULT_POLL_VALUE = "1740";

        // substring remove the first character "/" from retrieved viewId
        String viewID = FacesContext.getCurrentInstance().getViewRoot().getViewId().substring(1);
        String result = ResourceBundleManager.getString(ResourceBundles.REFRESH, viewID);
        // if result is null or empty string return the default poll value
        if (result == null || result.isEmpty()) {
            logger.warn("Poll value for viewID " + viewID + " not found! Poll value is set to default.");
            result = ResourceBundleManager.getString(ResourceBundles.REFRESH, DEFAULT_POLL_KEY);
            return result.isEmpty() ? DEFAULT_POLL_VALUE : result;
        } else {
            return result;
        }
    }

}
