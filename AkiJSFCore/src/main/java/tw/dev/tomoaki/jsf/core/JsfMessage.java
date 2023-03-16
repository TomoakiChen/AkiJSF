package tw.dev.tomoaki.jsf.core;

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
public class JsfMessage {
    public static void addFacesMessage(String message,FacesMessage.Severity serverity)
    {
        //System.out.println("Message = " + message);
        FacesMessage facesMessage = new FacesMessage();
        facesMessage.setSummary(message);
        facesMessage.setSeverity(serverity);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    
    //
    public static void addErrorFacesMessage(String message){
        addFacesMessage(message, FacesMessage.SEVERITY_ERROR);
    }    
    
    public static void addWarningFacesMessage(String message){
        addFacesMessage(message, FacesMessage.SEVERITY_WARN);
    }        
    
    public static void addInfoFacesMessage(String message){
        addFacesMessage(message, FacesMessage.SEVERITY_INFO);
    }        
}
