/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.jsf.core.helper;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tomoaki Chen
 */
public class JSFHttpHelper {

    private static final String REDIRECT_SUFFIX = "?faces-redirect=true";

    public static String obtainJsfRedirectAction(String nextPage) {
        return (nextPage != null && !nextPage.isEmpty()) ? (nextPage + REDIRECT_SUFFIX) : null;
    }

    public static HttpServletRequest obtainServletRequest() {
//        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return JSFHttpHelper.obtainServletRequest(FacesContext.getCurrentInstance());
    }

    public static HttpServletRequest obtainServletRequest(FacesContext context) {
        return (HttpServletRequest) context.getExternalContext().getRequest();
    }

    public static HttpServletResponse obtainServletResponse() {
//        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        return JSFHttpHelper.obtainServletResponse(FacesContext.getCurrentInstance());
    }
    
    public static HttpServletResponse obtainServletResponse(FacesContext context) {
        return (HttpServletResponse) context.getExternalContext().getResponse();        
    }    
    
    public static HttpSession obtainSession() {
        return obtainServletRequest().getSession();
    }      

    public static HttpSession obtainSession(FacesContext context) {
        return obtainServletRequest(context).getSession();
    }     
}
