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
import tw.dev.tomoaki.datakeeper.entity.CachedData;
import tw.dev.tomoaki.util.commondatavalidator.StringValidator;


/**
 *
 * @author tomoaki
 * @param <DATA>
 * @param <DATA_CACHE>
 */
public abstract class CachedDataCDIConverter<DATA, DATA_CACHE extends CachedData<DATA>> implements Converter<DATA>, Serializable {
    
    protected DATA_CACHE cachedDbDataMap;
    
    public void setupCachedDataMap(List<DATA> dataList) {
        this.cachedDbDataMap = this.obtainCachedDataMap(dataList);
    }
    
    protected abstract DATA_CACHE obtainCachedDataMap(List<DATA> dataList);
    
    public void appendCachedMap(DATA... datas) {
        this.appendCachedMap(Arrays.asList(datas));
    }
    
    public void appendCachedMap(List<DATA> dataList) {
        if (this.cachedDbDataMap == null) {
            this.setupCachedDataMap(dataList);
        } else {
            dataList.forEach(this.cachedDbDataMap::add);
        }
    }
    
    public abstract DATA tryObtainDataFromDbCache(String strDataIndexAttr);
    
    public DATA tryObtainDataFromDb(DATA cachedData, String strDataIndexAttr) {
        if (cachedData != null) {
            return cachedData;
        }
        return this.obtainDataFromDb(strDataIndexAttr);
    }
    
    public abstract DATA obtainDataFromDb(String strDataIndexAttr);
    
    @Override
    public DATA getAsObject(FacesContext context, UIComponent uiComponent, String strDataIndexAttr) {
        if (!StringValidator.isValueExist(strDataIndexAttr)) {
            return null;
        }
        
        DATA data = this.tryObtainDataFromDbCache(strDataIndexAttr); //OK
        data = this.tryObtainDataFromDb(data, strDataIndexAttr);
        return data;
    }
    
}
