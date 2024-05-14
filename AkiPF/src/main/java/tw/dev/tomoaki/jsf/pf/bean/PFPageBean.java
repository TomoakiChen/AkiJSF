/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jsf.pf.bean;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.primefaces.PrimeFaces;
import tw.dev.tomoaki.jsf.core.JSFPageBean;
import tw.dev.tomoaki.util.entity.DataExistMap;

/**
 *
 * @author tomoaki
 */
public class PFPageBean extends JSFPageBean {

    private static final String DEFAULT_NEW_WINDOW_OR_TAB_NAME = "_target";

    protected DataExistMap<String> openingInnerWindowMap;
    protected Queue<String> restingInnerWindow;

    @Override
    protected void doInitJsfPageBean() {
        super.doInitJsfPageBean(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.openingInnerWindowMap = DataExistMap.Factory.createOrdered();
        this.restingInnerWindow = new ConcurrentLinkedQueue();//LinkedList();
    }

    public void excuteJs(String js) {        
        String msgFmt = "excuteJs(): js= %s";
        this.tryPrintLog(msgFmt, js);
        PrimeFaces.current().executeScript(js);
    }

    protected void doAlert4WindowUnbind() {
        this.excuteJs(" "
                + "window.onbeforeunload = function () {\n"
                + "    var dialogText = \"確定離開當前頁面嗎？\";\n"
                + "    var e = window.event || e;\n"
                + "    e.returnValue = (dialogText);\n"
                + "    return dialogText;\n"
                + "};");
    }

    protected void doCancelAlert4WindowUnbind() {
        this.excuteJs(""
                + "window.onbeforeunload = function () {\n"
                + "};");
    }

    protected void doLockPage() {
        this.doLockElement(null, null);
    }

    protected void doLockPage(String msg) {
        this.doLockElement(null, msg);
    }

    protected void doLockElement(String desigElementId) {
        this.doLockElement(desigElementId, null);
    }

    protected void doLockElement(String desigElementId, String msg) {
        String selector = (desigElementId == null) ? "null" : String.format("[id=\"%s\"]", desigElementId);
        msg = (msg == null) ? "請稍後" : msg;
        String script = String.format("ScreenAnimation.loadingLock(%s, '%s')", selector, msg);
        this.excuteJs(script);
    }

    protected void doUnlockPage() {
        this.doUnlockElement(null);
    }

    protected void doUnlockElement(String desigElementId) {
        String selector = (desigElementId == null) ? "null" : String.format("[id=\"%s\"]", desigElementId);
        String script = String.format("ScreenAnimation.loadingUnlock(%s)", selector);
        this.excuteJs(script);
    }

//<editor-fold defaultstate="collapsed" desc="以下是開啟新分頁(Tab)或新視窗(Window)相關">
    /**
     *
     * @param url 要打開的頁面網址
     * @Deprecated 之後都使用有 do 開頭的 ，比如 doOpenNewTab doXxxxx
     */
    protected void openNewTab(String url) {
//        this.excuteJs("window.open('" + url + "', '_target');");
        this.doOpenNewTab(url, null);
    }

    protected void doOpenNewTab(String url) {
        doOpenNewTab(url, null);
    }

    protected void doOpenNewTab(String url, String tabName) {
        String targetName = (tabName == null) ? DEFAULT_NEW_WINDOW_OR_TAB_NAME : tabName;
        this.excuteJs("window.open('" + url + "', '" + targetName + "');");
    }

    protected void openNewWindow(String url, Integer width, Integer height) {
        this.excuteJs("window.open('" + url + "', '_target', config='width=" + width + ",height=" + height + "');");
    }

    protected void doOpenNewWindow(String url, Integer width, Integer height) {
//        this.excuteJs("window.open('" + url + "', '_target', config='width=" + width + ",height=" + height + "');");    
        this.doOpenNewWindow(url, null, width, height);
    }

    protected void doOpenNewWindow(String url, String windowName, Integer width, Integer height) {
        String targetName = (windowName == null) ? DEFAULT_NEW_WINDOW_OR_TAB_NAME : windowName;
        String js = "window.open('" + url + "', '" + targetName + "', config='width=" + width + ",height=" + height + "');";
        this.excuteJs(js);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="以下是開啟、關閉內頁(Inner Window)相關">
    private void doOpenInnerWindow(String widgetVar, Boolean letOtherOpeningWindowsRest) {
        //把打開中的都存到 restingInnerWinwdow 中記錄
        if (letOtherOpeningWindowsRest && !openingInnerWindowMap.isEmpty()) {
//            restingInnerWindow.addAll(openingInnerWindowMap.existList());
//            openingInnerWindowMap.existList().forEach(innerWindowWidgetVar -> doLetInnerWindowRest(innerWindowWidgetVar));
            openingInnerWindowMap.existList().forEach(openingWindowWidgetVar -> {
//                this.restingInnerWindow.add(openingWindowWidgetVar);
//                this.openingInnerWindowMap.remove(openingWindowWidgetVar);
                this.doRecordWindowRest(openingWindowWidgetVar);
                this.doRecordWinowClose(openingWindowWidgetVar);
            });
        }
//        this.openingInnerWindowMap.add(widgetVar);
        this.doRecordWinowOpen(widgetVar);
        String jsCode = String.format("PF('%s').show();", widgetVar);
        this.excuteJs(jsCode);
        tryPrintLog("doOpenInnerWindow(String, Boolean): jsCode= %s", jsCode);

    }

    private void doCloseInnerWindow(String widgetVar, Boolean awakeRestingWindows) {
//        this.openingInnerWindowMap.remove(widgetVar);
        this.doRecordWinowClose(widgetVar);
        tryPrintLog("doCloseInnerWindow(String, Boolean): awakeRestingWindows= %s, !restingInnerWindow.isEmpty()= %s", awakeRestingWindows, !restingInnerWindow.isEmpty());
        if (awakeRestingWindows && !restingInnerWindow.isEmpty()) {
            tryPrintLog("doCloseInnerWindow(String, Boolean): Will Awake Inner Window");
            restingInnerWindow.forEach(windwowWidgetVar -> this.doAwakeInnerWindow(windwowWidgetVar));
        }

        String jsCode = String.format("PF('%s').hide();", widgetVar);
        this.excuteJs(jsCode);
        tryPrintLog("doCloseInnerWindow(String, Boolean): jsCode= %s", jsCode);

    }

    protected void doOpenInnerWindow(String widgetVar) {
        this.doOpenInnerWindow(widgetVar, isLetOtherWindowsRest());
    }

    protected void doCloseInnerWindow(String widgetVar) {
        this.doCloseInnerWindow(widgetVar, isLetOtherWindowsRest());
    }

    protected void doCloseOpeningInnerWindow() {
        for (String widgetVar : this.openingInnerWindowMap.existList()) {
            this.doCloseInnerWindow(widgetVar);
        }
    }

    protected void doLetInnerWindowRest(String widgetVar) {
        if (!openingInnerWindowMap.contains(widgetVar)) {
            String msgFmt = "[%s] doLetInnerWindowRest(String): InnerWindow[widgetVar= %s] Is Not Opening";
            System.out.println(String.format(msgFmt, this.getClass().getSimpleName(), widgetVar));
            return;
        }
        this.doCloseInnerWindow(widgetVar/*, Boolean.FALSE*/);
//        openingInnerWindowMap.remove(widgetVar);        
//        restingInnerWindow.add(widgetVar);
        this.doRecordWindowRest(widgetVar);
    }

    protected void doAwakeInnerWindow(String widgetVar) {
        tryPrintLog("doAwakeInnerWindow(String): widgetVar= %s", widgetVar);
        if (!restingInnerWindow.contains(widgetVar)) {
            this.tryPrintLog("doAwakeInnerWindow(String): InnerWindow[widgetVar %s] Is Not Resting", widgetVar);
            return;
        }
        this.doOpenInnerWindow(widgetVar, Boolean.FALSE);
        restingInnerWindow.remove(widgetVar);
//        openingInnerWindowMap.add(widgetVar);        
    }

    protected void doAwakeRestingWindow() {
//        restingInnerWindow.forEach(windwowWidgetVar -> this.doOpenInnerWindow(windwowWidgetVar));                
        do {
            String windowWidgetVar = restingInnerWindow.poll();
            this.doAwakeInnerWindow(windowWidgetVar);
        } while (!restingInnerWindow.isEmpty());
    }

    protected void doOpenDialog(String widgetVar) {
        this.doOpenInnerWindow(widgetVar);
    }

    protected void doCloseDialog(String widgetVar) {
        this.doCloseInnerWindow(widgetVar);
    }

    protected void doCloaseOpeningDialog() {
        this.doCloseOpeningInnerWindow();
    }

    protected void doOpenSiderBar(String widgetVar) {
        this.doOpenInnerWindow(widgetVar);
    }

    protected void doCloseSideBar(String widgetVar) {
        this.doCloseInnerWindow(widgetVar);
    }

    protected void doCloseOpeningSideBar() {
        this.doCloseOpeningInnerWindow();
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="輔助內頁(Inner Window)">
    protected void doRecordWinowOpen(String widgetVar) {
        openingInnerWindowMap.add(widgetVar);
    }
    
    protected void doRecordWinowClose(String widgetVar) {
        openingInnerWindowMap.remove(widgetVar);
    }    
    
    protected void doRecordWindowRest(String widgetVar) {
        restingInnerWindow.add(widgetVar);
    }
    
    protected void tryRecordWinowClose(String widgetVar) {
        if(openingInnerWindowMap.contains(widgetVar)) {
            openingInnerWindowMap.remove(widgetVar);
        }
    }
    
//    protected void doRecordWindowAwake(String widgetVar) {
//       
//    }
//</editor-fold>
    

    public Boolean isInnerWindowOpening(String widgetVar) {
//        String msgFmt = "[%s] isInnerWindowOpening(): widgetVar= %s";
//        System.out.println(String.format(msgFmt, this.getClass().getSimpleName(), widgetVar));
        return this.openingInnerWindowMap.contains(widgetVar);
    }

    protected Boolean isLetOtherWindowsRest() {
        return Boolean.FALSE;
    }

    protected Boolean isPrintLog() {
        return printLog != null && printLog;
    }

    protected void tryPrintLog(String appendMsgFmt, Object... args) {
        if (isPrintLog()) {
            String appendMsg = String.format(appendMsgFmt, args);
            String msgFmt = "[%s] %s";
            System.out.println(String.format(msgFmt, this.getClass().getSimpleName(), appendMsg));
        }
    }
}
