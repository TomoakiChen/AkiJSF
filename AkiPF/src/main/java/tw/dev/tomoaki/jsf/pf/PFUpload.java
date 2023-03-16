/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.dev.tomoaki.jsf.pf;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.primefaces.model.file.UploadedFile; //PF8 以上
//import org.primefaces.model.UploadedFile;

/**
 *
 * @author tomoaki
 */
public class PFUpload {

    private static void startUploadFile(UploadedFile theFile, String theFilePath) {
        try {
            theFile.write(theFilePath);
        } catch (Exception ex) {
            Logger.getLogger(PFUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String startUploadFile(UploadedFile theFile, String theFileDir, String theFileName) {
        String theWholeFileName = null;
        if (theFile != null && theFile.getSize() > 0) {
            String theFileType = getFileType(theFile.getFileName());
            String theFilePath = theFileDir + theFileName + theFileType;
            File saveDir = new File(theFileDir);
            saveDir.mkdirs();
            //System.out.println(theFilePath);
            startUploadFile(theFile, theFilePath);
            theWholeFileName = theFileName + theFileType;
        } else {
            System.out.println(theFileName + "is empty or not exist");
        }
        return theWholeFileName;
    }

    public static String getFileType(String theFileName) {
        String theFileType = "";
        int index = theFileName.lastIndexOf("."); // 拿到檔名的"."的位置
//        System.out.println("index :" + index);
        if (index != -1) {
            theFileType = theFileName.substring(index);
        }
        return theFileType;
    }
}
