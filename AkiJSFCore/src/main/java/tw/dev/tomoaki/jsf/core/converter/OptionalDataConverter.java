/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tw.dev.tomoaki.jsf.core.converter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import tw.dev.tomoaki.datakeeper.entity.SimpleSerializedCachedData;
import tw.dev.tomoaki.util.commondatavalidator.ListValidator;

/**
 *
 * @author tomoaki
 * 
 * https://stackoverflow.com/questions/32272489/unserializabledependencyexception-weld-001413-the-bean-declares-a-passivating
 */
public abstract class OptionalDataConverter<DATA, DATA_CACHE extends SimpleSerializedCachedData<DATA>> implements Converter<DATA>, Serializable {

    private DATA_CACHE cachedMap;
    
    protected abstract DATA_CACHE obtainInitCachedMap();
    
    public void appendData(DATA... datas) {
        if(cachedMap == null) {
            cachedMap = this.obtainInitCachedMap();
        }
        this.appendData(Arrays.asList(datas));
    }
    
    public void appendData(List<DATA> dataList) {
        if(cachedMap == null) {
            cachedMap = this.obtainInitCachedMap();
        }        
        
        if(ListValidator.isListExist(dataList)) {
            dataList.forEach(cachedMap::add);
        }
    }
    
    @Override
    public DATA getAsObject(FacesContext context, UIComponent component, String dataString) {
        return cachedMap.getByDataSerialization(dataString);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, DATA dataObject) {
        return (dataObject != null) ? dataObject.toString() : null;
    }
    
}
