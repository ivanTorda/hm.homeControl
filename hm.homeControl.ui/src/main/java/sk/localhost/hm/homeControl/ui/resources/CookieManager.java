package sk.localhost.hm.homeControl.ui.resources;
 
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Object responsible for the management of the cookies
 *
 * @author malyp
 *
 */
public class CookieManager {
 
    private final static String CATEGORY_COOKIE_KEY = "mp_last_category";
    private final static String CATEGORY_COOKIE_KEY_TYPE = "mp_last_category_type";
 
    public String getCategoryKey() {
        return CATEGORY_COOKIE_KEY;
    }
 
    /**
     * Add new cookies
     *
     * @param key
     *            represents the key to the value in cookie
     * @param value
     *            represents the value stored in the cookies
     * @author malyp
     */
    public void addNewCookie(String key, String value) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        Cookie cookie = new Cookie(key, value);
 
        // prehodnotit http://www.javaworld.com/article/2073096/don-t-trust-cookie-setmaxage.html
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
 
    }
 
    /**
     * Set the selected category to cookies
     *
     * @author malyp
     * @return String Category NAME
     */
    public void setCategoryInCookie(String value) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        Cookie cookie = new Cookie(CATEGORY_COOKIE_KEY, value);
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
    }
    
    public void setCategoryTypeInCookie(String value){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        Cookie cookie = new Cookie(CATEGORY_COOKIE_KEY_TYPE, value);
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);        
    }
 
    /**
     * Returns from cookies the name of the last selected category
     *
     * @author malyp
     * @return String Category NAME
     */
    public String getCategoryFromCookies() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
 
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
 
        Cookie[] userCookies = request.getCookies();
        if ((userCookies != null) && (userCookies.length > 0)) {
            for (Cookie cookie : userCookies) {
                if (cookie.getName().equals(CATEGORY_COOKIE_KEY)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    
    public String getCategoryTypeFromCookies() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
 
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
 
        Cookie[] userCookies = request.getCookies();
        if ((userCookies != null) && (userCookies.length > 0)) {
            for (Cookie cookie : userCookies) {
                if (cookie.getName().equals(CATEGORY_COOKIE_KEY_TYPE)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
    /**
     * Get the value of the cookie which has the key from the argument
     *
     * @author malyp
     * @return String value of cookie
     */
    public String getCookieValue(String key) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
 
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
 
        Cookie[] userCookies = request.getCookies();
 
        if ((userCookies != null) && (userCookies.length > 0)) {
            for (Cookie cookie : userCookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
}