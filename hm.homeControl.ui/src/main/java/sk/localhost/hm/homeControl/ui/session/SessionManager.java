package sk.localhost.hm.homeControl.ui.session;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.Logger;
import org.primefaces.model.TreeNode;

import sk.localhost.hm.homeControl.core.qualifiers.MainLogger;

/**
 * Session Scoped bean responsible for handling the session with the user
 *
 * @author malyp
 */
@Named
@SessionScoped
public class SessionManager implements Serializable {

    private static final long serialVersionUID = 2331640279554503238L;
    public static final String PROPERTIES_FILE_NAME = "/sk/localhost/hm/homeControl/ui/resources/buildInfo.properties";
    public static final String PROPERTIES_TEST_PORT_NAME = "configuration.test.port";
    public static final String PROPERTIES_TEST_SUFFIX_NAME = "configuration.test.suffix";
    public static final String LANG_TEST_WRONG_CONFIGURATION = "settings.test.wrong";

    @Inject
    @MainLogger
    private Logger logger;

    @Inject
    private ApplicationManager applicationManager;

    private CookieManager cookieManager;
    private Boolean testVersion;
    private Locale locale;
    private String selectedLang;
    private HashMap<String, Object> countries;
    private int numberOfPaginatorRows;

    @PostConstruct
    public void init() {
        checkTestVersion();
        cookieManager = new CookieManager();
        initLocal();
        applicationManager.addSession(this);
        numberOfPaginatorRows = 20;
    }

    @PreDestroy
    public void destroy() {
        if (!applicationManager.removeSession(this)) {
            logger.error("Unable to remove session from application manager.");
        }
    }

    /**
     * Check the version of the deployed project
     *
     * @author machoj
     */
    private void checkTestVersion() {
        Integer applicationPort = FacesContext.getCurrentInstance().getExternalContext().getRequestServerPort();
        String contextRoot = FacesContext.getCurrentInstance().getExternalContext().getContextName();
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
            // if properties cannot be read or application context or port is null, then
            if ((applicationPort == null)
                || (contextRoot == null)
                || (properties == null)
                || ((properties.get(PROPERTIES_TEST_PORT_NAME) == null) && (properties.get(PROPERTIES_TEST_SUFFIX_NAME) == null))) {
                testVersion = true;
            } else {
                // set test version variable based on the property which is set in configuration file
                // if both values are set, then they must match, otherwise the versioning is misconfigured
                if (properties.get(PROPERTIES_TEST_PORT_NAME) != null) {
                    if (properties.get(PROPERTIES_TEST_SUFFIX_NAME) != null) {
                        if (properties.get(PROPERTIES_TEST_PORT_NAME).equals(applicationPort.toString())
                            || contextRoot.endsWith(properties.get(PROPERTIES_TEST_SUFFIX_NAME).toString())) {
                            testVersion = true;
                        } else {
                            testVersion = false;
                        }
                    } else {
                        testVersion = properties.get(PROPERTIES_TEST_PORT_NAME).equals(applicationPort.toString());
                    }
                } else {
                    testVersion = contextRoot.endsWith(properties.get(PROPERTIES_TEST_SUFFIX_NAME).toString());
                }
            }
        } catch (IOException ioe) {
            logger.error("Error has occured while reading test configuration from properties file", ioe);
        }
    }

    public boolean fileExists(String fileName) {
        if (FacesContext.getCurrentInstance().getExternalContext()
            .getRealPath("/WEB-INF/tags/" + fileName + "RibbonBar.xhtml") == null) {
            return false;
        }
        File file =
            new File(FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/WEB-INF/tags/" + fileName + "RibbonBar.xhtml"));
        return file.exists();
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public HashMap<String, Object> getCountries() {
        return countries;
    }

    public String getSelectedLang() {
        return selectedLang;
    }

    public void setSelectedLang(String selectedLang) {
        this.selectedLang = selectedLang;
    }

    public Boolean getTestVersion() {
        return testVersion;
    }

    public void reloadPage() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String refreshpage = facesContext.getViewRoot().getViewId();
        ViewHandler viewHandler = facesContext.getApplication().getViewHandler();
        UIViewRoot viewroot = viewHandler.createView(facesContext, refreshpage);
        viewroot.setViewId(refreshpage);
        facesContext.setViewRoot(viewroot);
    }

    /**
     * Init the available countries for locale and set the default locale;
     *
     * @author malyp
     */
    private void initLocal() {
        locale = new Locale("en", "GB");
        countries = new LinkedHashMap<String, Object>();
        countries.put("EN", new Locale("en", "GB")); // label, value
        countries.put("SK", new Locale("sk", "SK"));
        countries.put("DE", new Locale("de", "DE"));
    }

    public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }

    /**
     * The method change the locale of the session
     *
     * @author malyp
     * @param code
     *        - the key to the map of translations
     */
    public void countryLocaleCodeChanged() {

        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if (entry.getKey().compareTo(selectedLang) == 0) {
                Locale tmp = (Locale) entry.getValue();
                if (tmp != null) {
                    FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
                    locale = tmp;
                    updatePageAndRedirect();
                    return;
                }
            }
        }
    }

    /**
     * Update the page a make a redirect
     *
     * @author malyp
     */
    public void updatePageAndRedirect() {
        FacesContext context = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
        String currentView = context.getViewRoot().getViewId();
        navigationHandler.handleNavigation(context, null, currentView + "?faces-redirect=true&includeViewParams=true");
    }

    /**
     * Refresh the session
     *
     * @author malyp
     */
    public void refreshSession() {}

    public TreeNode selectNodeByRegion(TreeNode root, Object region) {

        if (region.equals(root.getData())) {
            root.setSelected(true);
            return root;
        }

        for (TreeNode child : root.getChildren()) {
            root = selectNodeByRegion(child, region);
        }

        return root;
    }

    public TreeNode deselectAllInNode(TreeNode root) {

        if (root.isSelected()) {
            root.setSelected(false);
        }

        for (TreeNode child : root.getChildren()) {
            root = deselectAllInNode(child);
        }

        return root;
    }

    /**
     * get number of paginator rows for machineStatusBasic.xhtml
     *
     * @author malyp
     */
    public int getNumberOfPaginatorRows() {
        return numberOfPaginatorRows;
    }

    public void setNumberOfPaginatorRows(int numberOfPaginatorRows) {
        this.numberOfPaginatorRows = numberOfPaginatorRows;
    }
}