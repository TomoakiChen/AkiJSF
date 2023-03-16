/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.jsf.core;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Tomoaki Chen
 */
public class JsfHelper {

    private static final String REDIRECT_SUFFIX = "?faces-redirect=true";

    public static HttpServletRequest obtainServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static HttpServletRequest obtainServletResponse() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static String obtainJsfRedirectAction(String nextPage) {
        return (nextPage != null && !nextPage.isEmpty()) ? (nextPage + REDIRECT_SUFFIX) : null;
    }
}
