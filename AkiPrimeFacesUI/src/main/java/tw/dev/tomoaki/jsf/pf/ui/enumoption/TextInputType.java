/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jsf.pf.ui.enumoption;

import tw.dev.tomoaki.enumoption.CodedEnum;
import tw.dev.tomoaki.enumoption.helper.EnumHelper;

/**
 *
 * @author tomoaki
 */
public enum TextInputType implements CodedEnum {

    PLAIN_TEXT_AREA("PlainTextArea", "Plain Textarea"/*"純文字的 TextArea"*/),
    TEXT_EDITOR("TextEditor", "Rich Text Editor");

    private String code;
    private String description;

    private TextInputType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static TextInputType codeOf(String desigCode) {
        return EnumHelper.codeOf(desigCode, TextInputType.class);
    }

}
