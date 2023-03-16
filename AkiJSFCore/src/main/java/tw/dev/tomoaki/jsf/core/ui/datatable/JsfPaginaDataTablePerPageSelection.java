/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.jsf.core.ui.datatable;

import java.util.ArrayList;
import java.util.List;
import tw.dev.tomoaki.jsf.core.entity.JsfSelection;

/**
 *
 * @author Tomoaki Chen
 */
public class JsfPaginaDataTablePerPageSelection {

    private List<JsfSelection<Integer>> perPageList;
    
    private static final Integer DEFAULT_MAX_CHOOSE = 5;
    
    protected JsfPaginaDataTablePerPageSelection() {
    }

    public static class Factory {

        public static JsfPaginaDataTablePerPageSelection create(List dataList) {
            Integer numsOfData = dataList.size();
            return JsfPaginaDataTablePerPageSelection.Factory.create(numsOfData, DEFAULT_MAX_CHOOSE);
        }      
        
        
        public static JsfPaginaDataTablePerPageSelection create(List dataList, Integer maxChoose) {
            Integer numsOfData = dataList.size();
            return JsfPaginaDataTablePerPageSelection.Factory.create(numsOfData, maxChoose);
        }        
        
        public static JsfPaginaDataTablePerPageSelection create(Integer numsOfData) {
            return JsfPaginaDataTablePerPageSelection.Factory.create(numsOfData, DEFAULT_MAX_CHOOSE);
        }        
        
        public static JsfPaginaDataTablePerPageSelection create(Integer numsOfData, Integer maxChoose) {
            JsfPaginaDataTablePerPageSelection perPageSelection = new JsfPaginaDataTablePerPageSelection();
            perPageSelection.perPageList = new ArrayList();
            for (Integer index = 5; index <= 5 + (maxChoose - 2) * 5; index += 5) {
                JsfSelection<Integer> selection = new JsfSelection();
                selection.setLabel(index.toString());
                selection.setValue(index);
                perPageSelection.perPageList.add(selection);
            }
            JsfSelection<Integer> allDataSelection = new JsfSelection();
            allDataSelection.setLabel("All");
            allDataSelection.setValue(numsOfData);
            perPageSelection.perPageList.add(allDataSelection);
            return perPageSelection;
        }
    }

    public List<JsfSelection<Integer>> getPerPageList() {
        return perPageList;
    }
    
    
}
