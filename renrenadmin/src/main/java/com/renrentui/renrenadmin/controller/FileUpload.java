package com.renrentui.renrenadmin.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;













import org.apache.commons.fileupload.FileItemIterator;  
import org.apache.commons.fileupload.FileItemStream;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  
import org.apache.commons.fileupload.util.Streams; 
import org.apache.commons.fileupload.FileItemFactory;

import com.renrentui.renrencore.util.ImageHelper;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");

		
		String type=request.getParameter("type");
		String typeName="business";
		if(type.equals("1"))
		{
			typeName="task";			
		}
		if(type.equals("2"))
		{
			typeName="business";
		}
		if(type.equals("3"))
		{
			typeName="clienter";
		}
		String url= ImageHelper.UploadImg(request,typeName);		

		
/*	  //返回结果  
        JSONObject obj = new JSONObject();  
        obj.put("fileName", fileName);  
        response.getWriter().print(obj.toJSONString()); */	
		
		 //response.setStatus(200);
		 //String retxt ="{src:'"+url+"',status:success}";
		 
		 response.getWriter().print(url);

	}
}
