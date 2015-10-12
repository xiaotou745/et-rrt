package com.renrentui.renrencore.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.apache.commons.fileupload.FileItemIterator;  
import org.apache.commons.fileupload.FileItemStream;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  
import org.apache.commons.fileupload.util.Streams; 
import org.apache.commons.fileupload.FileItemFactory;

public class ImageHelper {

	public static String UploadImg(HttpServletRequest request,String typeName)  
            throws ServletException, IOException {        

        try {       
        //String realPath = "d:\\"+"\\"+"temprenren"+"\\"+typeName;  //目录                
        
        String rootPath =PropertyUtils.getProperty("UploadPath")+"\\"+typeName;
        FileUtil.createDirectory(rootPath);// 创建目录                
        
        String uploadFileName = ""; //上传文件名
        String realFileName="";    
        String fullPath="";   
        String url = "";    //图片地址                                 

  
        if(ServletFileUpload.isMultipartContent(request)){  
  
            DiskFileItemFactory dff = new DiskFileItemFactory();              
            dff.setSizeThreshold(1024000);  
            ServletFileUpload sfu = new ServletFileUpload(dff);  
            FileItemIterator fii = sfu.getItemIterator(request);              
        
            while(fii.hasNext()){  
            	FileItemStream fis = fii.next();    
                if(!fis.isFormField() && fis.getName().length()>0){  
                		//上传文件名
                		uploadFileName = fis.getName(); 
                		//上传文件重命名
                        realFileName = new Date().getTime()+uploadFileName.substring(uploadFileName.lastIndexOf("."),uploadFileName.length());
                        
                        Calendar cal = Calendar.getInstance();
                        int year = cal.get(Calendar.YEAR);//获取年份
                        int month=cal.get(Calendar.MONTH)+1;//获取月份 
                        int day=cal.get(Calendar.DATE);//获取日 
                        int hour=cal.get(Calendar.HOUR);//小时                                             
                        String temp="\\"+year+"\\"+month+"\\"+day+"\\"+hour;    
                        fullPath=rootPath+temp;                        
                        //创建目录
                        FileUtil.createDirectory(fullPath);
                        
                        url = rootPath+temp+"\\"+realFileName;  
                        
                        BufferedInputStream in = new BufferedInputStream(fis.openStream());//获得文件输入流  
            	        FileOutputStream ab = new FileOutputStream(new File(url));              	
            	        byte[] inOutb = StreamUtils.copyToByteArray(in);
            	        ab.write(inOutb);        
            	        
            	        return "/"+typeName+"/"+year+"/"+month+"/"+day+"/"+hour+"/"+realFileName;  
                   }                
            }         
        }  
        }catch(Exception ee) {  
            ee.printStackTrace();  
        }  
          
        return "";
    }      

}
