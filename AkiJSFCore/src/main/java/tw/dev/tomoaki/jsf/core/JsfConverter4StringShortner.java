/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.jsf.core;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import tw.dev.tomoaki.util.string.AkiStringUtil;
//import tw.dev.tomoaki.util.wordanalysis.AkiStringUtil;

/**
 *
 * @author tomoaki
 */
@FacesConverter("tw.dev.tomoaki.util.jsf.JsfConverter4StringShortner")
public class JSFConverter4StringShortner implements Converter {

    public static String ATTR_PREFIX_LENGTH = "tw.dev.tomoaki.util.jsf.JsfConverter4StringShortner.PrefixLength";
    public static String ATTR_SUFFIX_LENGTH = "tw.dev.tomoaki.util.jsf.JsfConverter4StringShortner.SuffixLength";
    public static String ATTR_OMIT_SYMBOL = "tw.dev.tomoaki.util.jsf.JsfConverter4StringShortner.OmitSymbol";

    private Integer prefixLength = 14;
    private Integer suffixLength = 10;
    private String omitSymbol;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        this.doLengthSetup(component);
        this.doOmitSymbolSetup(component);

        String oriValue = (String) value;
        if (omitSymbol == null) {
            return AkiStringUtil.shorten(oriValue, prefixLength, suffixLength);
        } else {
            return AkiStringUtil.shorten(oriValue, prefixLength, suffixLength, this.omitSymbol);
        }
    }

    public void doLengthSetup(UIComponent component) {
        String tempPrefixLength = (String) component.getAttributes().get(ATTR_PREFIX_LENGTH);
        if (tempPrefixLength != null && tempPrefixLength.isEmpty() == false) {
            this.prefixLength = Integer.valueOf(tempPrefixLength);
        }

        String tempSuffixLength = (String) component.getAttributes().get(ATTR_SUFFIX_LENGTH);
        if (tempSuffixLength != null && tempSuffixLength.isEmpty() == false) {
            this.suffixLength = Integer.valueOf(tempSuffixLength);
        }
    }

    public void doOmitSymbolSetup(UIComponent component) {
        Object objOmitSymbol = component.getAttributes().get(ATTR_OMIT_SYMBOL);
        if (objOmitSymbol != null) {
            this.omitSymbol = (String) objOmitSymbol;
        }
    }

}
