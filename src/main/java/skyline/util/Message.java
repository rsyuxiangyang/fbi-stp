/**
 * zhanrui
 *
 */
package skyline.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Message {

    private static final Logger logger = LoggerFactory.getLogger(Message.class);

    private static String  bundleName = "messages";

    public static FacesMessage getMessage(String resourceId) {
          return getMessage( bundleName,  resourceId,  null);
    }

    public static FacesMessage getMessage(String resourceId, Object[] params) {
          return getMessage( bundleName,  resourceId,  params);
    }

    public static FacesMessage getMessage(String bundleName, String resourceId, Object[] params) {
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        String appBundle = app.getMessageBundle();
        Locale locale = getLocale(context);
        ClassLoader loader = getClassLoader();

        String resourceContent = getString(appBundle, bundleName, resourceId, locale, loader, params);
        if (resourceContent == null) resourceContent = resourceId;

        return new FacesMessage(resourceId, resourceContent);
    }

    public static String getString(String bundle, String resourceId, Object[] params) {
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        String appBundle = app.getMessageBundle();
        Locale locale = getLocale(context);
        ClassLoader loader = getClassLoader();
        return getString(appBundle, bundle, resourceId, locale, loader, params);
    }

    public static String getString(String bundle1, String bundle2,
                                   String resourceId, Locale locale, ClassLoader loader,
                                   Object[] params) {
        String resource = null;
        ResourceBundle bundle;

        if (bundle1 != null) {
            bundle = ResourceBundle.getBundle(bundle1, locale, loader);
            if (bundle != null)
                try {
                    resource = bundle.getString(resourceId);
                } catch (MissingResourceException ex) {
                    throw new RuntimeException("Resource not found!");
                }
        }

        if (resource == null) {
            bundle = ResourceBundle.getBundle(bundle2, locale, loader);
            if (bundle != null)
                try {
                    resource = bundle.getString(resourceId);
                } catch (MissingResourceException ex) {
                    return("");
                    //return("信息ID：" + resourceId + "未定义!");
                }
        }

        if (resource == null || "".equals(resource)) {
            if (params == null) {
                return null;
            }else{
                resource = "{0}";
            }
        }

        if (params == null)
            return resource;

        MessageFormat formatter = new MessageFormat(resource, locale);
        return formatter.format(params);
    }

    public static Locale getLocale(FacesContext context) {
        Locale locale = null;
        UIViewRoot viewRoot = context.getViewRoot();
        if (viewRoot != null) locale = viewRoot.getLocale();
        if (locale == null) locale = Locale.getDefault();
        return locale;
    }

    public static ClassLoader getClassLoader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) loader = ClassLoader.getSystemClassLoader();
        return loader;
    }
}
