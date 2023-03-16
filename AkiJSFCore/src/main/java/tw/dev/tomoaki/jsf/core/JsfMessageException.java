/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.jsf.core;

/**
 *
 * @author tomoaki
 */
public class JsfMessageException extends Exception{
    public JsfMessageException(String msg){
        super(msg);
    }
    
    public JsfMessageException(Throwable ex){
        super(ex);
    }
    
    public JsfMessageException(Exception ex){
        super(ex);
    }
}
