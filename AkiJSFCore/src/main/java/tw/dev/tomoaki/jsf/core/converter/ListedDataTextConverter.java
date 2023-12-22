/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jsf.core.converter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


import tw.dev.tomoaki.jsf.core.helper.JSFAttributeHelper;

/**
 *
 * @author tomoaki
 */
public abstract class ListedDataTextConverter<T> implements Converter<List<T>>, Serializable {

    @Override
    public List<T> getAsObject(FacesContext context, UIComponent component, String string) {
        throw new UnsupportedOperationException("Not supported."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, List<T> dataList) {
        return dataList.stream().map(this::obtainDataText).collect(Collectors.joining(getSeparator(), getPrefix(), getSuffix()));
    }

    protected abstract String obtainDataText(T data);

    protected String getPrefix() {
        return "";
    }

    protected String getSeparator() {
        return ", ";
    }

    protected String getSuffix() {
        return "";
    }
}
