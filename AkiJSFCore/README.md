## 依賴 Dependency



* Java EE 8 (Jarkarta EE 8)
  
  * Converter 的格式在 JEE 7 $\to$ JEE 8 有改變，lib 中用的是 JEE 8
    
    * JEE 7
      
      ```java
      public interface Converter {
      
      
          public Object getAsObject(FacesContext context, UIComponent component,
                                    String value);
      
      
          public String getAsString(FacesContext context, UIComponent component,
                                    Object value);
      
          public static final String DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE_PARAM_NAME = 
                  "javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE";
          
      }
      
      ```
    
    * JEE 8
      
      ```java
      public interface Converter<T extends Object> {
      
          public static final String DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE_PARAM_NAME = "javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE";
      
          public T getAsObject(FacesContext context, UIComponent component, String value);
      
          public String getAsString(FacesContext context, UIComponent component, T value);
      }
      
      
      ```
      
      


