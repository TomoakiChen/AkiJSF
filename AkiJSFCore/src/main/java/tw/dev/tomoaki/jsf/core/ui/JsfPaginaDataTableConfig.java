/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.jsf.core.ui;

/**
 *
 * @author Tomoaki Chen
 * @deprecated 
 */
public class JsfPaginaDataTableConfig {
    
    private Integer datasPerPage;
    private Integer maxPageNumbers;

    protected JsfPaginaDataTableConfig() {
    }
    
    public static class Factory {
        public static JsfPaginaDataTableConfig create() {
            JsfPaginaDataTableConfig config = new JsfPaginaDataTableConfig();
            config.datasPerPage = 10;
            config.maxPageNumbers = 5;
            return config;
        }
    }
    
    public Integer getDatasPerPage() {
        return datasPerPage;
    }

    public void setDatasPerPage(Integer datasPerPage) {
        this.datasPerPage = datasPerPage;
    }

    public Integer getMaxPageNumbers() {
        return maxPageNumbers;
    }

    public void setMaxPageNumbers(Integer maxPageNumbers) {
        this.maxPageNumbers = maxPageNumbers;
    }
    
    
}
