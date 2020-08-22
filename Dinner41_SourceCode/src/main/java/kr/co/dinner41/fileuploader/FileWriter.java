package kr.co.dinner41.fileuploader;

import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;
 
public class FileWriter {
 
    private FileOutputStream fos;
    public void writeFile(MultipartFile file, String path, String fileName){
        try{
            byte fileData[] = file.getBytes();
            fos = new FileOutputStream(path + "\\" + fileName);
            fos.write(fileData);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fos != null){
                try{
                    fos.close();
                }catch(Exception e){}
                }
            
        }// try end;
         
    }// wirteFile() end;
}


