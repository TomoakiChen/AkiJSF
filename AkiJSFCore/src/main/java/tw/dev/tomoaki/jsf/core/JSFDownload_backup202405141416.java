/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.jsf.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import javax.faces.context.*;//FacesContext;

/**
 *
 * @author tomoaki
 */
public class JSFDownload_backup202405141416 { 
    
    private FacesContext fc;
    private ExternalContext ec;
    private static final int BUFFSIZE = 2048;
    File theFile;

    protected JSFDownload_backup202405141416() {
        fc = FacesContext.getCurrentInstance();
        ec = fc.getExternalContext();
    }

    public void doDownload(String theFilePath) throws IOException {
        String theFileName = theFilePath.substring(theFilePath.lastIndexOf("/") + 1);
        doDownload(theFilePath, theFileName);
    }

    public void doDownload(String theFilePath, String theFileName) throws FileNotFoundException, IOException {
        theFile = new File(theFilePath);
        if (theFile.exists()) {
            String mimeType = ec.getMimeType(theFileName);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            ec.responseReset();
            ec.setResponseContentType(mimeType);
            ec.setResponseContentLength((int) theFile.length());
            ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + theFileName + "\"");

            byte[] buff = new byte[BUFFSIZE];
            OutputStream fileWriter;
            try (FileInputStream fileReader = new FileInputStream(theFile)) {
                fileWriter = ec.getResponseOutputStream();
                int bytesReaded = -1;
                while ((fileReader != null) && ((bytesReaded = fileReader.read(buff)) != -1)) {
                    fileWriter.write(buff, 0, bytesReaded);
                }
            }
            fileWriter.flush();
            fileWriter.close();

            fc.responseComplete();
        } else {
            System.out.println("下載檔案時發生錯誤:檔案不存在");
        }
    }

    /*public void doDownload(String theFilePath, String theFileName) throws FileNotFoundException, IOException {
        theFile = new File(theFilePath);
        if (theFile.exists()) {
            String mimeType = ec.getMimeType(theFileName);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            ec.responseReset();
            ec.setResponseContentType(mimeType);
            ec.setResponseContentLength((int) theFile.length());
            ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + theFileName + "\"");

            byte[] buff = new byte[BUFFSIZE];
            FileInputStream fileReader = new FileInputStream(theFile);
            OutputStream fileWriter = ec.getResponseOutputStream();
            int bytesReaded = -1;
            while ((fileReader != null) && ((bytesReaded = fileReader.read(buff)) != -1)) {
                fileWriter.write(buff, 0, bytesReaded);
            }
            fileReader.close();
            fileWriter.flush();
            fileWriter.close();

            fc.responseComplete();
        } else {
            System.out.println("下載檔案時發生錯誤:檔案不存在");
        }
    }*/
    public void deleteFile() {
        if (theFile.exists()) {
            theFile.delete();
        }
    }
}
