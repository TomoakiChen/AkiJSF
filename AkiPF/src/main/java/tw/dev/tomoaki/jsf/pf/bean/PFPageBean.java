/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jsf.pf.bean;

import org.primefaces.PrimeFaces;
import tw.dev.tomoaki.jsf.core.JsfPageBean;
import tw.dev.tomoaki.util.entity.DataExistMap;

/**
 *
 * @author tomoaki
 */
public class PFPageBean extends JsfPageBean {

    protected DataExistMap<String> openingInnerWindowMap;

    @Override
    protected void doInitJsfPageBean() {
        super.doInitJsfPageBean(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.openingInnerWindowMap = new DataExistMap();
    }

    public void excuteJs(String js) {
//        System.out.println("js : " + js);
        PrimeFaces.current().executeScript(js);
    }

    protected void doAlert4WindowUnbind() {
        this.excuteJs(" window.onbeforeunload = function () {\n"
                + "                    var dialogText = \"確定離開當前頁面嗎？\";\n"
                + "                    var e = window.event || e;\n"
                + "                    e.returnValue = (dialogText);\n"
                + "                    return dialogText;\n"
                + "                };        ");
    }

    protected void doCancelAlert4WindowUnbind() {
        this.excuteJs(" window.onbeforeunload = function () {\n"
                + "                };        ");
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

    protected void openInnerWindow(String widgetVar) {
        String jsCode = String.format("PF('%s').show();", widgetVar);
        this.excuteJs(jsCode);
        this.openingInnerWindowMap.add(widgetVar);
    }

    protected void closeInnerWindow(String widgetVar) {//String id) {
        String jsCode = String.format("PF('%s').hide();", widgetVar);
        this.excuteJs(jsCode);
        this.openingInnerWindowMap.remove(widgetVar);
    }

    protected void closeOpeningInnerWindow() {
        for (String widgetVar : this.openingInnerWindowMap.existList()) {
            this.closeInnerWindow(widgetVar);
        }
    }

    protected void openDialog(String widgetVar) {
        this.openInnerWindow(widgetVar);
    }

    protected void closeDialog(String widgetVar) {//String id) {
        this.closeInnerWindow(widgetVar);
    }

    protected void cloaseOpeningDialog() {
        this.closeOpeningInnerWindow();
    }

    protected void openSiderBar(String widgetVar) {
        this.openInnerWindow(widgetVar);
    }

    protected void closeSideBar(String widgetVar) {//String id) {
        this.closeInnerWindow(widgetVar);
    }

    protected void closeOpeningSideBar() {
        this.closeOpeningInnerWindow();
    }
}
