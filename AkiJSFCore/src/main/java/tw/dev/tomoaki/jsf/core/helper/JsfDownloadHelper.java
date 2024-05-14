package tw.dev.tomoaki.jsf.core.helper;

import javax.faces.context.*;
/**
 *
 * @author tomoaki
 * 
 */
public class JSFDownloadHelper {
 
    /*
    之前在普通(自建)的 Servlet 有發現， 
    JsfDownload 無法使用，主因(應該)是那個狀況下「(Servlet不在JSF框架底下)拿不到 FacesContext 這個東西」(對，他應該是要有實體的...?。
    --> 更正確的說法可能是 getCurrentInstance 就會需要有實體
    
    那如果我們現在寫一個 static Method ，他到底是可以還是不拿到? (注意喔，JsfDownload 是 new 時才去嘗試拿那些 Context
    */
    
    
    public static void doDownload(String theFilePath, String theFileName) {
        System.out.println("JSFDownloadHelper(): ");
        doCheckInstance();
    }
    
    
    protected static void doCheckInstance() {
        System.out.println(FacesContext.getCurrentInstance());
    }
}
