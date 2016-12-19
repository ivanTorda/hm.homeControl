package sk.localhost.hm.homeControl.ui.resources;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sk.localhost.hm.homeControl.core.logging.LoggerProducer;

/**
 * @author regerd
 */
public class ResourceBundleManager {

    private static Logger logger = LogManager.getLogger(LoggerProducer.MAIN_LOGGER);

    /**
     * Does the translation of the given bundleKey in the given bundle (bundleName).
     *
     * @param bundleName
     * @param bundleKey
     * @return the translated String
     */
    public static String getString(String bundleName, String bundleKey) {
        try {
            FacesContext fx = FacesContext.getCurrentInstance();
            ResourceBundle resourceBundle = fx.getApplication().getResourceBundle(fx, bundleName);
            if (resourceBundle != null) {
                return resourceBundle.getString(bundleKey);
            }
        } catch (MissingResourceException e) {
            logger.warn(e.toString());
        }
        return "";
    }

    public static String getString(String bundleName, String bundleKey, Object... params) {
        try {
            return MessageFormat.format(getString(bundleName, bundleKey), params);
        } catch (MissingResourceException e) {
            return '!' + bundleKey + '!';
        }
    }

}
