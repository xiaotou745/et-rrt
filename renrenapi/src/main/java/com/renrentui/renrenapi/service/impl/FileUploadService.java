package com.renrentui.renrenapi.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.stereotype.Service;


import com.renrentui.renrenapi.service.inter.IFileUploadService;
import com.renrentui.renrenentity.req.FileUploadReq;


@Service
public class FileUploadService implements IFileUploadService{
   
	@Override
	public void FileUpload(FileUploadReq req)
	{
		byte [] bytes=req.getBytes();
		String fileName=req.getFileName();
		int uploadForm=req.getUploadForm();
		
		FileOutputStream fos = null;  
		       try{  
		            fos = new FileOutputStream("E:\\"+fileName);  
		              
		            //将字节数组bytes中的数据，写入文件输出流fos中  
		            fos.write(bytes);  
		            fos.flush();  
		        }catch (Exception e){  
		            e.printStackTrace();  		       
		        }finally{  
		            try {  
		                fos.close();  
		            } catch (IOException e) {  
		                e.printStackTrace();  
		            }     
		        }  		        

	}
}
