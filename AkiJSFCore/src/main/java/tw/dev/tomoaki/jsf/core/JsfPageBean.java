/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.jsf.core;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tw.dev.tomoaki.jsf.core.JsfMessage;
//import tw.dev.hcfeng.util.secutiry.AccessKeyUtil;
//import tw.dev.tomoaki.util.collection.UrlCreator;
import tw.dev.tomoaki.util.web.PageInfo;
import tw.dev.tomoaki.util.web.PageStack;
import tw.dev.tomoaki.util.web.UrlProvider;
import tw.dev.tomoaki.util.web.WebHelper;

/**
 *
 * @author tomoaki
 */
public class JsfPageBean {

    protected final static String SESSION_ATTR_PAGESTACK = "sEssSionKeYPAgeSTaCk";

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

//    protected UrlCreator urlCreator;
    protected String message;
    protected Queue<String> msgBuffer;
    protected String nextPage;

    protected String systemRootPath;
    protected UrlProvider urlProvider;

    public JsfPageBean() {
        msgBuffer = new LinkedList();
        this.doInitJsfPageBean();
    }

    protected void doInitJsfPageBean() {
        request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        session = request.getSession();
        this.doSetupUrlProvider();
    }

    protected void doSetupUrlProvider() {
        urlProvider = UrlProvider.Factory.create(request);
    }

    protected void showErrorMessage(String message) {
        FacesMessage.Severity serverity = FacesMessage.SEVERITY_ERROR;
        this.showMessage(message, serverity);
    }

    protected void showInfoMessage(String message) {
//        JsfMessage.addFacesMessage(message, FacesMessage.SEVERITY_INFO);
        FacesMessage.Severity serverity = FacesMessage.SEVERITY_INFO;
        this.showMessage(message, serverity);
    }

    protected void showWarningMessage(String message) {
//        JsfMessage.addFacesMessage(message, FacesMessage.SEVERITY_WARN);
        FacesMessage.Severity serverity = FacesMessage.SEVERITY_WARN;
        this.showMessage(message, serverity);
    }

    protected void showFatalMessage(String message) {
//        JsfMessage.addFacesMessage(message, FacesMessage.SEVERITY_FATAL);        
        FacesMessage.Severity serverity = FacesMessage.SEVERITY_FATAL;
        this.showMessage(message, serverity);
    }

    private void showMessage(String message, FacesMessage.Severity serverity) {
        this.message = message;
        JsfMessage.addFacesMessage(message, serverity);
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getSystemRootPath() {
        return systemRootPath;
    }

    /**
     * 會轉頁
     */
    public String redirectToNextPage() {
        System.out.println("nextPage : " + nextPage);
        if (nextPage != null) {
//            this.recordNowPage();
            return nextPage + "?faces-redirect=true";
        } else {
            return null;
        }
    }

    /**
     * 不會實際轉頁，還是同一個 request。
     */
    public String requestToNextPage() {
        //System.out.println("nextPage = " + nextPage);
        return this.nextPage;
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public void doUpdateComponent(String id) {
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(id);
    }

    protected void recordNowPage() {
        String nowPage = WebHelper.getNowPage(this.request);
        PageStack pageStack = (PageStack) this.session.getAttribute(JsfPageBean.SESSION_ATTR_PAGESTACK);
        if (pageStack == null) {
            pageStack = new PageStack();
        }
        pageStack.push(nowPage);
        this.session.setAttribute(JsfPageBean.SESSION_ATTR_PAGESTACK, pageStack);
    }

    protected PageInfo popLastPage() {
        PageStack pageStack = (PageStack) this.session.getAttribute(JsfPageBean.SESSION_ATTR_PAGESTACK);
        PageInfo pageInfo = pageStack.pop();
        this.session.setAttribute(JsfPageBean.SESSION_ATTR_PAGESTACK, pageStack);
        return pageInfo;
    }

    protected void redirectTo(String url) throws IOException {
        String resultUrl = this.urlProvider.appendUrl(url);
        FacesContext.getCurrentInstance().getExternalContext().redirect(resultUrl);
    }

//    protected String createRedirectAccessKey(String url) {
//        return AccessKeyUtil.generateAccessKey(url);
//    }
// 
    protected void recordMessage(String msg) {
        this.msgBuffer.add(msg);
    }

    protected List<String> pollAllMessage() {
        List<String> msgList = this.msgBuffer.stream().collect(Collectors.toList());
        this.msgBuffer.clear();
        return msgList;
    }

//    public UIComponent findComponent(final String id) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        UIViewRoot root = context.getViewRoot();
//        final UIComponent[] found = new UIComponent[1];
//
//        root.visitTree(new FullVisitContext(context), new VisitCallback() {
//            @Override
//            public VisitResult visit(VisitContext context, UIComponent component) {
//                if (component.getId().equals(id)) {
//                    found[0] = component;
//                    return VisitResult.COMPLETE;
//                }
//                return VisitResult.ACCEPT;
//            }
//        });
//
//        return found[0];
//    }    
    /*
    https://maxkatz.org/2011/05/04/how-to-hightlight-a-field-in-jsf-when-validation-fails/    
    https://stackoverflow.com/questions/10993615/highlight-an-inputtext-in-jsf-when-a-validation-error-occurs
    https://stackoverflow.com/questions/29756049/pinplace-still-toggle-back-when-validation-fails
    
    
    https://www.primefaces.org/styling-invalid-input-fields-with-jsf/
     */
    protected UIInput findUiInputComponent(String formId, String inputWidgetId) {
        FacesContext context = FacesContext.getCurrentInstance();
        UIInput input = (UIInput) context.getViewRoot().findComponent(formId + ":" + inputWidgetId);
        return input;
    }

    protected void letInputInValid(String formId, String inputWidgetId) {
        UIInput input = this.findUiInputComponent(formId, inputWidgetId);
        input.setValid(false);
    }
}
