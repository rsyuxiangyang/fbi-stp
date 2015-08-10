package skyline.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 2010-11-16
 * Time: 13:22:58
 * To change this template use File | Settings | File Templates.
 */
public class MessageUtil {
    public static void addInfo(String resourceId) {
        addInfo(resourceId, null);
    }
    public static void addInfo(String resourceId, Object[] params) {
        addMessage(resourceId, FacesMessage.SEVERITY_INFO, params);
    }
    public static void addInfoWithClientID(String clientId, String resourceId) {
        addInfoWithClientID(clientId, resourceId, null);
    }
    public static void addInfoWithClientID(String clientId, String resourceId, Object[] params) {
        addMessage(clientId, resourceId, FacesMessage.SEVERITY_INFO, params);
    }
    public static void addWarn(String resourceId) {
        addWarn(resourceId, null);
    }
    public static void addWarn(String resourceId, Object[] params) {
        addMessage(resourceId, FacesMessage.SEVERITY_WARN, params);
    }
    public static void addWarnWithClientID(String clientId, String resourceId) {
        addWarnWithClientID(clientId, resourceId, null);
    }
    public static void addWarnWithClientID(String clientId, String resourceId, Object[] params) {
        addMessage(clientId, resourceId, FacesMessage.SEVERITY_WARN, params);
    }
    public static void addError(String resourceId) {
        addError(resourceId, null);
    }
    public static void addError(String resourceId, Object[] params) {
        addMessage(resourceId, FacesMessage.SEVERITY_ERROR, params);
    }
    public static void addErrorWithClientID(String clientId, String resourceId) {
        addErrorWithClientID(clientId, resourceId, null);
    }
    public static void addErrorWithClientID(String clientId, String resourceId, Object[] params) {
        addMessage(clientId, resourceId, FacesMessage.SEVERITY_ERROR, params);
    }
    public static void addFatal(String resourceId) {
        addFatal(resourceId, null);
    }
    public static void addFatal(String resourceId, Object[] params) {
        addMessage(resourceId, FacesMessage.SEVERITY_FATAL, params);
    }
    public static void addFatalWithClientID(String clientId, String resourceId) {
        addFatalWithClientID(clientId, resourceId, null);
    }
    public static void addFatalWithClientID(String clientId, String resourceId, Object[] params) {
        addMessage(clientId, resourceId, FacesMessage.SEVERITY_FATAL, params);
    }
    private static void addMessage(String resourceId, FacesMessage.Severity severityInfo, Object[] params) {
        addMessage(null, resourceId, severityInfo, params);
    }
    private static void addMessage(String clientId, String resourceId, FacesMessage.Severity severityInfo, Object[] params) {
        if (resourceId == null) {
            throw new NullPointerException("信息ID未指定");
        }
        FacesMessage message = Message.getMessage(resourceId, params);
        message.setSeverity(severityInfo);
        FacesContext.getCurrentInstance().addMessage(clientId, message);
    }
}
