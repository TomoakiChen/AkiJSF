package tw.dev.tomoaki.jsf.core.helper;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tomoaki
 */
public class JsfMessageHelper {

//<editor-fold defaultstate="collapsed" desc="核心methods">
    public static void addFacesMessage(FacesMessage.Severity serverity, String message) {
        appendFacesMessage(null, serverity, message);
    }

    public static void appendFacesMessage(String uiComponentId, FacesMessage.Severity serverity, String message) {
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary(message);
        facesMessage.setSeverity(serverity);
        FacesContext.getCurrentInstance().addMessage(uiComponentId, facesMessage);
    }

    public static void addFacesMessage(FacesMessage.Severity serverity, String summary, String detail) {
        appendFacesMessage(null, serverity, summary, detail);
    }

    public static void appendFacesMessage(String uiComponentId, FacesMessage.Severity serverity, String summary, String detail) {
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary(summary);
        facesMessage.setDetail(detail);
        facesMessage.setSeverity(serverity);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
//</editor-fold>

    //
    public static void addErrorFacesMessage(String message) {
        addFacesMessage(FacesMessage.SEVERITY_ERROR, message);
    }

    public static void addErrorFacesMessage(String summary, String detail) {
        addFacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
    }

    public static void addWarningFacesMessage(String message) {
        addFacesMessage(FacesMessage.SEVERITY_WARN, message);
    }

    public static void addWarningFacesMessage(String summary, String detail) {
        addFacesMessage(FacesMessage.SEVERITY_WARN, summary, detail);
    }

    public static void addInfoFacesMessage(String message) {
        addFacesMessage(FacesMessage.SEVERITY_INFO, message);
    }

    public static void addInfoFacesMessage(String summary, String detail) {
        addFacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
    }

    public static void appendErrorFacesMessage(String uiComponentId, String message) {
        appendFacesMessage(uiComponentId, FacesMessage.SEVERITY_ERROR, message);
    }

    public static void appendErrorFacesMessage(String uiComponentId, String summary, String detail) {
        appendFacesMessage(uiComponentId, FacesMessage.SEVERITY_ERROR, summary, detail);
    }

    public static void appendWarningFacesMessage(String uiComponentId, String message) {
        appendFacesMessage(uiComponentId, FacesMessage.SEVERITY_WARN, message);
    }

    public static void appendWarningFacesMessage(String uiComponentId, String summary, String detail) {
        appendFacesMessage(uiComponentId, FacesMessage.SEVERITY_ERROR, summary, detail);
    }

    public static void appendInfoFacesMessage(String uiComponentId, String message) {
        appendFacesMessage(uiComponentId, FacesMessage.SEVERITY_INFO, message);
    }

    public static void appendInfoFacesMessage(String uiComponentId, String summary, String detail) {
        appendFacesMessage(uiComponentId, FacesMessage.SEVERITY_INFO, summary, detail);
    }

}
