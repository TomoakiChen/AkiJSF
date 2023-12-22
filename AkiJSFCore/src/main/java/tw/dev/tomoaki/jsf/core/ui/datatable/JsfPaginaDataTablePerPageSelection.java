/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.jsf.core.ui.datatable;

import java.util.ArrayList;
import java.util.List;
import tw.dev.tomoaki.jsf.core.entity.JSFSelection;

/**
 *
 * @author Tomoaki Chen
 */
public class JSFPaginaDataTablePerPageSelection {

    private List<JSFSelection<Integer>> perPageList;
    
    private static final Integer DEFAULT_MAX_CHOOSE = 5;
    
    protected JSFPaginaDataTablePerPageSelection() {
    }

    public static class Factory {

        public static JSFPaginaDataTablePerPageSelection create(List dataList) {
            Integer numsOfData = dataList.size();
            return JSFPaginaDataTablePerPageSelection.Factory.create(numsOfData, DEFAULT_MAX_CHOOSE);
        }      
        
        
        public static JSFPaginaDataTablePerPageSelection create(List dataList, Integer maxChoose) {
            Integer numsOfData = dataList.size();
            return JSFPaginaDataTablePerPageSelection.Factory.create(numsOfData, maxChoose);
        }        
        
        public static JSFPaginaDataTablePerPageSelection create(Integer numsOfData) {
            return JSFPaginaDataTablePerPageSelection.Factory.create(numsOfData, DEFAULT_MAX_CHOOSE);
        }        
        
        public static JSFPaginaDataTablePerPageSelection create(Integer numsOfData, Integer maxChoose) {
            JSFPaginaDataTablePerPageSelection perPageSelection = new JSFPaginaDataTablePerPageSelection();
            perPageSelection.perPageList = new ArrayList();
            for (Integer index = 5; index <= 5 + (maxChoose - 2) * 5; index += 5) {
                JSFSelection<Integer> selection = new JSFSelection();
                selection.setLabel(index.toString());
                selection.setValue(index);
                perPageSelection.perPageList.add(selection);
            }
            JSFSelection<Integer> allDataSelection = new JSFSelection();
            allDataSelection.setLabel("All");
            allDataSelection.setValue(numsOfData);
            perPageSelection.perPageList.add(allDataSelection);
            return perPageSelection;
        }
    }

    public List<JSFSelection<Integer>> getPerPageList() {
        return perPageList;
    }
    
    
}
