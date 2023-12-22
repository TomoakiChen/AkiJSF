/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.jsf.core.ui.datatable;

/**
 *
 * @author Tomoaki Chen
 */
public class JSFPaginaDataTableConfig {

    private Integer datasPerPage;
    private Integer maxPageNumbers;

    protected JSFPaginaDataTableConfig() {
    }

    public static class Factory {

        public static JSFPaginaDataTableConfig create() {
            JSFPaginaDataTableConfig config = new JSFPaginaDataTableConfig();
            config.datasPerPage = 10;
            config.maxPageNumbers = 5;
            return config;
        }

        public static JSFPaginaDataTableConfig create(Integer dataPerPage) {
            JSFPaginaDataTableConfig config = new JSFPaginaDataTableConfig();
            config.datasPerPage = dataPerPage == null ? 10 : dataPerPage;
            config.maxPageNumbers = 5;
            return config;
        }
    }

    public Integer getDatasPerPage() {
        return datasPerPage;
    }

    public void setDatasPerPage(Integer datasPerPage) {
        this.datasPerPage = datasPerPage;
        this.datasPerPage = (this.datasPerPage < 5 || this.datasPerPage == null) ? 5 : this.datasPerPage;
        
        
    }

    public Integer getMaxPageNumbers() {
        return maxPageNumbers;
    }

    public void setMaxPageNumbers(Integer maxPageNumbers) {
        this.maxPageNumbers = maxPageNumbers;
    }

}
