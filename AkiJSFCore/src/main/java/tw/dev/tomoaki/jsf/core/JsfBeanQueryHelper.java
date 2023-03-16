/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jsf.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import tw.dev.tomoaki.util.cast.JavaToJson;
import tw.dev.tomoaki.util.cast.JsonToJava;

/**
 *
 * @author Tomoaki Chen
 */
public class JsfBeanQueryHelper {
 
    private final static String URL_CHAR_ENCODE = "UTF-8";
    private final static Base64.Encoder encoder = Base64.getEncoder();
    private final static Base64.Decoder decoder = Base64.getDecoder();

    
    public static <T>String encode(T data) throws JsonProcessingException, UnsupportedEncodingException {
        String jsonData = JavaToJson.getJsonString(data);
        String base64Data = encoder.encodeToString(jsonData.getBytes());
        return URLEncoder.encode(base64Data, URL_CHAR_ENCODE);
    }
    
    public static <T>T decode(String encodedStr, Class<T> clazz) throws UnsupportedEncodingException, IOException {
        String base64Data = URLDecoder.decode(encodedStr, URL_CHAR_ENCODE);
        String jsonData = new String(decoder.decode(base64Data));
        return JsonToJava.getJavaObject(jsonData, clazz);
    }
}
