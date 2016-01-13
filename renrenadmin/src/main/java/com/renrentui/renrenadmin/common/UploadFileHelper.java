package com.renrentui.renrenadmin.common;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.renrentui.renrencore.util.FileUtil;
import com.renrentui.renrencore.util.PropertyUtils;

public class UploadFileHelper {

	/**
	 * 上传图片
	 * 
	 * @author hailongzhao
	 * @date 20160104
	 * @param request
	 * @param loadFrom
	 * @return
	 * @throws Exception
	 */
	public static String UploadImg(HttpServletRequest request) throws Exception {
		return doUpload(request, 0, "");
	}

	/**
	 * 上传文件的核心逻辑
	 * 
	 * @author hailongzhao
	 * @date 20160104
	 * @param request
	 * @param loadFrom
	 * @param fileType
	 * @param originalSufix
	 * @return
	 * @throws Exception
	 */
	private static String doUpload(HttpServletRequest request, int fileType,
			String originalSufix) throws Exception {
		JSONObject obj = new JSONObject();
		if (!ServletFileUpload.isMultipartContent(request)) {
			obj.put("error", 1);
			obj.put("message", "请选择文件!");
			return obj.toJSONString();
		}
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
		List<String> ext = Arrays.<String> asList(extMap.get("image")
				.split(","));
		if (fileType > 0) {
			ext = Arrays.<String> asList(extMap.get("file").split(","));
			List<String> mediaExt = Arrays.<String> asList(extMap.get("media")
					.split(","));
			ext.addAll(mediaExt);
		}
		String regionPath = "";
		String rootPath = "";
		List<FileItem> fileList = new ArrayList<FileItem>();
		DiskFileItemFactory dff = new DiskFileItemFactory();
		dff.setSizeThreshold(1024000);
		ServletFileUpload sfu = new ServletFileUpload(dff);
		sfu.setHeaderEncoding("UTF-8");
		Iterator fii = sfu.parseRequest(request).iterator();
		regionPath = PropertyUtils
				.getProperty("FileUploadFolderNameCustomerIcon");
		rootPath = PropertyUtils.getProperty("FileUploadPath") + "/"
				+ regionPath;
		FileUtil.createDirectory(rootPath);// 创建目录
	
		while (fii.hasNext()) {
			FileItem fis = (FileItem) fii.next();

			if (fis.getSize() > 1000000) {
				obj.put("error", 1);
				obj.put("message", "上传文件大小超过限制");
				return obj.toJSONString();
			}
			// 检查扩展名
			String fileExt = fis.getName()
					.substring(fis.getName().lastIndexOf(".") + 1)
					.toLowerCase();
			if (!ext.contains(fileExt)) {
				obj.put("error", 1);
				obj.put("message",
						"上传文件扩展名是不允许的扩展名。\n只允许" + String.join(",", ext));
				return obj.toJSONString();
			}
			fileList.add(fis);
		}//while 
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;//request强制转换注意
	        MultipartFile file = mRequest.getFile("imgFile");
	        if(file!=null)
		    {
	        	CommonsMultipartFile cf= (CommonsMultipartFile)file; 
			    FileItem fi = cf.getFileItem(); 
			    fileList.add(fi);
		    }
		return saveFiles(fileList, originalSufix, regionPath, rootPath);

	}

	private static String saveFiles(List<FileItem> fileList,
			String originalSufix, String regionPath, String rootPath)
			throws Exception {
		JSONObject obj = new JSONObject();
		if (fileList.size() == 0) {
			obj.put("error", 1);
			obj.put("message", "请选择文件");
			return obj.toJSONString();
		}
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);// 获取年份
		int month = cal.get(Calendar.MONTH) + 1;// 获取月份
		int day = cal.get(Calendar.DATE);// 获取日
		int hour = cal.get(Calendar.HOUR_OF_DAY);// 小时
		String temp = year + "/" + String.format("%02d", month) + "/"
				+ String.format("%02d", day) + "/"
				+ String.format("%02d", hour);
		String fullPath = rootPath + "/" + temp;
		// 创建目录
		FileUtil.createDirectory(fullPath);
			for (FileItem fileItem : fileList) {
				String uploadFileName = fileItem.getName();
				// 上传文件重命名
				String realFileName = new Date().getTime() + originalSufix
						+ uploadFileName.substring(uploadFileName.lastIndexOf("."));
				try {
					File uploadedFile = new File(rootPath + "/" + temp,
							realFileName);
					fileItem.write(uploadedFile);
				} catch (Exception e) {
					obj.put("error", 1);
					obj.put("message", "上传文件失败");
					return obj.toJSONString();
				}
				String relativePath = regionPath + "/" + temp + "/" + realFileName;
				String url = PropertyUtils.getProperty("ImgShowUrl") + "/"
						+ relativePath;
				obj.put("error", 0);
				obj.put("url", url);
				return obj.toJSONString();
			}//for
		obj.put("error", 1);
		obj.put("message", "请选择文件");
		return obj.toJSONString();

	}
}
