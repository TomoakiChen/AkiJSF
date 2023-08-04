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
package tw.dev.tomoaki.jsf.core.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author tomoaki
 */
@FacesConverter(value = "tw.dev.tomoaki.jsf.core.converter.StringTrimmer"/*, forClass = String.class*/)
public class StringTrimmer implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String strValue) {
        return strValue == null ? null : strValue.trim();
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object objValue) {
        return objValue.toString();
    }

}
