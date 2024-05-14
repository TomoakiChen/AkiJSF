/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.jsf.core;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.faces.context.*;//FacesContext;

/**
 *
 * @author tomoaki
 */
public class JSFDownloader {

    // https://blog.csdn.net/u012561176/article/details/83418658
    
    
    // private static final FacesContext facesContext;
    // private static final ExternalContext externalContext;
    private static final int BUFFSIZE = 2048;

    // static {
    //     facesContext = FacesContext.getCurrentInstance();
    //     externalContext = facesContext.getExternalContext();
    // }
    
    

    public static void doDisplay(String downloadingFileName, byte[] fileBytes) throws FileNotFoundException, IOException {
        InputStream is = new ByteArrayInputStream(fileBytes);
        doDisplay(downloadingFileName, is);
    }
    
    public static void doDisplay(String theFilePath) throws IOException {
        Path filePath = Path.of(theFilePath);
        doDisplay(filePath.getFileName().toString(), filePath.toFile());
    }

    public static void doDisplay(String downloadingFileName, File downloadingFile) throws FileNotFoundException, IOException {
        if (downloadingFile == null || !downloadingFile.exists()) {
            return;
        }

        InputStream fileInputStream = new FileInputStream(downloadingFile);
        doDisplay(downloadingFileName, fileInputStream, (int) downloadingFile.length());
    }

    public static void doDisplay(String downloadingFileName, InputStream fileReader) throws FileNotFoundException, IOException {
        doDisplay(downloadingFileName, fileReader, null);
    }

    public static void doDisplay(String downloadingFileName, InputStream fileReader, Integer downloadingFileLength) throws FileNotFoundException, IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        
        String mimeType = externalContext.getMimeType(downloadingFileName);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        facesContext.responseComplete();
        externalContext.responseReset();
        externalContext.setResponseContentType(mimeType);
        externalContext.setResponseHeader("Content-Disposition", "inline; filename=\"" + downloadingFileName + "\"");

        if (downloadingFileLength != null) {
            externalContext.setResponseContentLength(downloadingFileLength);
        }

        byte[] buff = new byte[BUFFSIZE];
        OutputStream fileWriter;
        fileWriter = externalContext.getResponseOutputStream();
        int bytesReaded = -1;
        while ((fileReader != null) && ((bytesReaded = fileReader.read(buff)) != -1)) {
            fileWriter.write(buff, 0, bytesReaded);
        }
        fileWriter.flush();
        fileWriter.close();

        facesContext.responseComplete();

    }

    public static void doDownload(String downloadingFileName, byte[] fileBytes) throws FileNotFoundException, IOException {
        InputStream is = new ByteArrayInputStream(fileBytes);
        doDownload(downloadingFileName, is);
    }    
    
    public static void doDownload(String theFilePath) throws IOException {
        Path filePath = Path.of(theFilePath);
        doDownload(filePath.getFileName().toString(), filePath.toFile());
    }

    public static void doDownload(String downloadingFileName, File downloadingFile) throws FileNotFoundException, IOException {
        if (downloadingFile == null || !downloadingFile.exists()) {
            return;
        }

        InputStream fileInputStream = new FileInputStream(downloadingFile);
        doDownload(downloadingFileName, fileInputStream, (int) downloadingFile.length());
    }

    public static void doDownload(String downloadingFileName, InputStream fileReader) throws FileNotFoundException, IOException {
        doDownload(downloadingFileName, fileReader, null);
    }

    public static void doDownload(String downloadingFileName, InputStream fileReader, Integer downloadingFileLength) throws FileNotFoundException, IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance(); //這個好像 接近 HttpServletRequest (配合最上面連結)，而如果一開始就拉好( 比如 static {}) ，拉第二次 request 後會說 is closed 
        ExternalContext externalContext = facesContext.getExternalContext();        
        
        String mimeType = externalContext.getMimeType(downloadingFileName);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        facesContext.responseComplete();
        externalContext.responseReset();
        externalContext.setResponseContentType(mimeType);
        externalContext.setResponseHeader("Content-Disposition", "attachment; filename=\"" + downloadingFileName + "\"");

        if (downloadingFileLength != null) {
            externalContext.setResponseContentLength(downloadingFileLength);
        }

        byte[] buff = new byte[BUFFSIZE];
        OutputStream fileWriter;
        fileWriter = externalContext.getResponseOutputStream();
        int bytesReaded = -1;
        while ((fileReader != null) && ((bytesReaded = fileReader.read(buff)) != -1)) {
            fileWriter.write(buff, 0, bytesReaded);
        }
        fileWriter.flush();
        fileWriter.close();

        facesContext.responseComplete();

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
}
