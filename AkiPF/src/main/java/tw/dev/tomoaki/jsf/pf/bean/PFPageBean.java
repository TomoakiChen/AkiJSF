/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jsf.pf.bean;

import java.util.LinkedList;
import java.util.Queue;
import org.primefaces.PrimeFaces;
import tw.dev.tomoaki.jsf.core.JsfPageBean;
import tw.dev.tomoaki.util.entity.DataExistMap;

/**
 *
 * @author tomoaki
 */
public class PFPageBean extends JsfPageBean {

    protected DataExistMap<String> openingInnerWindowMap;

    protected Queue<String> restingInnerWindow;

    @Override
    protected void doInitJsfPageBean() {
        super.doInitJsfPageBean(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.openingInnerWindowMap = new DataExistMap();
        this.restingInnerWindow = new LinkedList();
    }

    public void excuteJs(String js) {
//        System.out.println("js : " + js);
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

    protected void openNewTab(String url) {
        this.excuteJs("window.open('" + url + "', '_target');");
    }

    protected void openNewWindow(String url, Integer width, Integer height) {
        this.excuteJs("window.open('" + url + "', '_target', config='width=" + width + ",height=" + height + "');");
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

    private void doOpenInnerWindow(String widgetVar, Boolean letOtherOpeningWindowsRest) {
        String jsCode = String.format("PF('%s').show();", widgetVar);
        this.excuteJs(jsCode);
        tryPrintLog("doOpenInnerWindow(String, Boolean): jsCode= %s", jsCode);
        
        if (letOtherOpeningWindowsRest && !openingInnerWindowMap.isEmpty()) {
//            restingInnerWindow.addAll(openingInnerWindowMap.existList()); //可能會有順序問題，DataExistMap Default 是 Hash(Map) 的
            openingInnerWindowMap.existList().forEach(innerWindowWidgetVar -> doLetInnerWindowRest(innerWindowWidgetVar));
        }
        this.openingInnerWindowMap.add(widgetVar);
    }

    private void doCloseInnerWindow(String widgetVar, Boolean awakeRestingWindows) {
        String jsCode = String.format("PF('%s').hide();", widgetVar);
        this.excuteJs(jsCode);
        tryPrintLog("doCloseInnerWindow(String, Boolean): jsCode= %s", jsCode);
        
        
        this.openingInnerWindowMap.remove(widgetVar);
        if (awakeRestingWindows && !restingInnerWindow.isEmpty()) {
            restingInnerWindow.forEach(windwowWidgetVar -> this.doAwakeInnerWindow(windwowWidgetVar));
        }
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
        restingInnerWindow.add(widgetVar);
    }

    protected void doAwakeInnerWindow(String widgetVar) {
        if (!restingInnerWindow.contains(widgetVar)) {
            String msgFmt = "[%s] doAwakeInnerWindow(String): InnerWindow[widgetVar %s] Is Not Resting";
            System.out.println(String.format(msgFmt, this.getClass().getSimpleName(), widgetVar));
            return;
        }
        this.doOpenInnerWindow(widgetVar/*, Boolean.FALSE*/);
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

    public Boolean isInnerWindowOpening(String widgetVar) {
//        String msgFmt = "[%s] isInnerWindowOpening(): widgetVar= %s";
//        System.out.println(String.format(msgFmt, this.getClass().getSimpleName(), widgetVar));
        return this.openingInnerWindowMap.contains(widgetVar);
    }

    protected Boolean isLetOtherWindowsRest() {
        return Boolean.FALSE;
    }
    
    protected Boolean isPrintLog() {
        return Boolean.FALSE;
    }

    
    protected void tryPrintLog(String appendMsgFmt, Object... args) {
        if(isPrintLog()) {
            String appendMsg = String.format(appendMsgFmt, args);
            String msgFmt = "[%s] %s";            
            System.out.println(String.format(msgFmt, this.getClass().getSimpleName(), appendMsg));
        }
    }
}
