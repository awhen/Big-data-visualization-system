package com.wen.bdv.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	
    /*
	 *图片上传工具类 
	 */
    public String upload(HttpServletRequest request, MultipartFile file, String path) {
        //String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        fileName=UUID.randomUUID()+fileName.substring(fileName.indexOf("."),fileName.length());
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return fileName;
    }

}
