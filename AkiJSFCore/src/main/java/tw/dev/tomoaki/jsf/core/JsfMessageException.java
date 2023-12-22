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
public class JSFMessageException extends Exception{
    public JSFMessageException(String msg){
        super(msg);
    }
    
    public JSFMessageException(Throwable ex){
        super(ex);
    }
    
    public JSFMessageException(Exception ex){
        super(ex);
    }
}
