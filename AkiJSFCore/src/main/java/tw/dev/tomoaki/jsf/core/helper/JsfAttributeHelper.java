/*
 * Copyright 2023 tomoaki.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tw.dev.tomoaki.jsf.core.helper;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author tomoaki
 */
public class JSFAttributeHelper {

    public static Object getLocalComponentAttrValue(String attrName) {
        FacesContext context = FacesContext.getCurrentInstance();
//        UIComponent component = UIComponent.getCurrentComponent(context);
//        return component.getAttributes().get(attrName);
        return JSFAttributeHelper.getGlobalAttrValue(context, attrName);
    }

    public static Object getLocalComponentAttrValue(FacesContext context, String attrName) {
        UIComponent component = UIComponent.getCurrentComponent(context);
        return component.getAttributes().get(attrName);
    }

    public static Object getGlobalAttrValue(String attrName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return JSFAttributeHelper.getGlobalAttrValue(context, attrName);
    }

    public static Object getGlobalAttrValue(FacesContext context, String attrName) {
        return context.getAttributes().get(attrName);
    }
}
