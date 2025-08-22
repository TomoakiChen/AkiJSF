/*
 * Copyright 2025 tomoaki.
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

import tw.dev.tomoaki.util.web.UrlAppender;

/**
 *
 * @author tomoaki
 */
public class JSFOutcomeAppender {

    private final static String JSF_QUERY_PARAM_REDIRECT = "faces-redirect";
    private final static String JSF_QUERY_PARAM_INCLUDE_VIEW_PARAMS = "includeViewParams";

    private final UrlAppender appender;

    public JSFOutcomeAppender(String nextPage) {
        this.appender = UrlAppender.create(nextPage);
    }
    
    public static JSFOutcomeAppender create(String nextPage) {
        return new JSFOutcomeAppender(nextPage);
    }
        
    public JSFOutcomeAppender isRedirect(Boolean needRedirect) {
        appender.appendQueryParam(JSF_QUERY_PARAM_REDIRECT, needRedirect); // 嚴格來說 false 不用帶
        return this;
    }

    public JSFOutcomeAppender isIncludeViewParams(Boolean needIncludeViewParams) {
        appender.appendQueryParam(JSF_QUERY_PARAM_INCLUDE_VIEW_PARAMS, needIncludeViewParams); // 嚴格來說 false 不用帶
        return this;
    }

    public JSFOutcomeAppender appendQueryParam(String paramName, Object paramValue) {
        appender.appendQueryParam(paramName, paramValue);
        return this;
    }

    public String buildOutcome() {
        return appender.buildUrl();
    }
}
