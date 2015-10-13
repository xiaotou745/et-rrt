package com.renrentui.renrenentity.req;

/**
 * 上传图片
 * @author 胡灵波
 *
 */
public class FileUploadReq {
	private byte [] bytes;
	private String fileName;
	private int uploadForm;
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getUploadForm() {
		return uploadForm;
	}
	public void setUploadForm(int uploadForm) {
		this.uploadForm = uploadForm;
	}
	
	
	
}
