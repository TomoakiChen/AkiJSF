/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jsf.core.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author tomoaki
 */
public abstract class ArticleCreatorConverter implements Converter<String> {

    @Override
    public String getAsObject(FacesContext context, UIComponent component, String string) {
        throw new UnsupportedOperationException("Not supported."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, String articleWithToken) {
        return createArticle(context, component, articleWithToken);
    }

    protected abstract String createArticle(FacesContext context, UIComponent component, String articleWithToken);
}
