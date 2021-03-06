﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Web;
using ETS.Enums;
using Ets.Model.Common;
using ETS.Const;
using ETS.IO;
using ETS.Util;

namespace Eds.Upload.Providers
{
    public class ImageHelper
    {
        /// <summary>
        /// 上传图片   修改：彭宜    20150710
        /// </summary>
        /// <param name="httpPostedFile">待传输文件</param>
        /// <param name="orderId"></param>
        /// <param name="imageType">图片类型</param>
        /// <returns></returns>
        public ImgInfo UploadImg(HttpPostedFile httpPostedFile, UploadFrom uploadFrom)
        {
            ImgInfo imgInfo = new ImgInfo();
            try
            {
                System.Drawing.Image img = System.Drawing.Image.FromStream(httpPostedFile.InputStream);
            }
            catch (Exception)
            {
                imgInfo.FailRemark = "无图片流";
                return imgInfo;
            }
            var fileName = ETS.Util.ImageTools.GetFileName(Path.GetExtension(httpPostedFile.FileName));
            imgInfo.FileName = fileName;
            imgInfo.OriginalName = httpPostedFile.FileName;
             
            int fileNameLastDot = fileName.LastIndexOf('.');
            //原图 
            string rFileName = string.Format("{0}{1}{2}", fileName.Substring(0, fileNameLastDot), SystemConst.OriginSize, Path.GetExtension(fileName));
            //原始的
            imgInfo.OriginFileName = rFileName;
            string saveDbFilePath;
            string saveDir = "";
            string basePath = Ets.Model.ParameterModel.Clienter.CustomerIconUploader.Instance.GetPhysicalPath(uploadFrom); 
                string fullFileDir = ETS.Util.ImageTools.CreateDirectory(basePath, "", out saveDbFilePath);
            imgInfo.FullFileDir = fullFileDir;
            imgInfo.SaveDbFilePath = saveDbFilePath;
            if (fullFileDir == "0")
            {
                imgInfo.FailRemark = "创建目录失败";
                return imgInfo;
            }
            //保存原图，
            ///TODO记录图片大小
            var fullFilePath = Path.Combine(fullFileDir, rFileName);
            httpPostedFile.SaveAs(fullFilePath);
            //裁图
            var transformer = new ETS.Compress.FixedDimensionTransformerAttribute(Ets.Model.ParameterModel.Clienter.CustomerIconUploader.Instance.Width, Ets.Model.ParameterModel.Clienter.CustomerIconUploader.Instance.Height, Ets.Model.ParameterModel.Clienter.CustomerIconUploader.Instance.MaxBytesLength / 1024);
            //保存到数据库的图片路径
            var destFullFileName = System.IO.Path.Combine(fullFileDir, fileName);
            transformer.Transform(fullFilePath, destFullFileName);
             
            var picUrl = saveDbFilePath + fileName; 
            imgInfo.RelativePath = EnumUtils.GetEnumDescription(uploadFrom) + picUrl;
            imgInfo.PicUrl = ImageCommon.ReceiptPicConvert(uploadFrom, picUrl);
            return imgInfo;
        }

        /// <summary>
        ///  
        /// </summary>
        /// <param name="httpPostedFile">待传输文件</param>
        /// <param name="orderId"></param>
        /// <param name="imageType">图片类型</param>
        /// <returns></returns>
        public ImgInfo UploadFile(HttpPostedFile httpPostedFile, string uploadType, UploadFrom uploadFrom)
        {
            ImgInfo imgInfo = new ImgInfo(); 
            var fileName = ETS.Util.ImageTools.GetFileName(Path.GetExtension(httpPostedFile.FileName));
            imgInfo.FileName = fileName;
            imgInfo.OriginalName = httpPostedFile.FileName; 
            int fileNameLastDot = fileName.LastIndexOf('.');
            //原图 
            string rFileName = string.Format("{0}{1}", fileName.Substring(0, fileNameLastDot), Path.GetExtension(fileName)); 
            imgInfo.OriginFileName = rFileName;
            string saveDbFilePath;
            string saveDir = "";
            string basePath = Ets.Model.ParameterModel.Clienter.CustomerIconUploader.Instance.GetPhysicalPath(uploadFrom);
            string fullFileDir = ETS.Util.ImageTools.CreateDirectory(basePath, uploadType, out saveDbFilePath);
            imgInfo.FullFileDir = fullFileDir;
            imgInfo.SaveDbFilePath = saveDbFilePath;
            if (fullFileDir == "0")
            {
                imgInfo.FailRemark = "创建目录失败";
                return imgInfo;
            } 
            var fullFilePath = Path.Combine(fullFileDir, rFileName);
            httpPostedFile.SaveAs(fullFilePath);
            var picUrl = saveDbFilePath + fileName;
            imgInfo.RelativePath = EnumUtils.GetEnumDescription(uploadFrom) + picUrl;
            imgInfo.PicUrl = ImageCommon.ReceiptPicConvert(uploadFrom, picUrl);
            return imgInfo;
        }

        /// <summary>
        /// 删除磁盘中的图片
        /// wc
        /// </summary>
        /// <param name="ticketUrl"></param>
        /// <returns></returns>
        public void DeleteFile(string fileUrl)
        {
            //Regex regex = new Regex(@"(/\w+/\d{4}/\d{2}/\d{2}.*?).+", RegexOptions.IgnoreCase | RegexOptions.CultureInvariant | RegexOptions.Multiline | RegexOptions.Singleline);

            //MatchCollection matchCollection = regex.Matches(fileUrl);
            //string delPicDir = "1.jpg";
            //foreach (Match match in matchCollection)
            //{
            //    delPicDir = match.Value;
            //}
            var delDir = Path.Combine(ConfigSettings.Instance.FileUploadPath ,fileUrl);
            var fileName = Path.GetFileName(delDir);
            int fileNameLastDot = fileName.LastIndexOf('.');
            //原图 
            string orginalFileName = string.Format("{0}{1}{2}", Path.GetDirectoryName(delDir) + "\\" + fileName.Substring(0, fileNameLastDot), SystemConst.OriginSize, Path.GetExtension(fileName));

            //删除磁盘中的裁图
            FileHelper.DeleteFile(delDir);
            //删除缩略图
            FileHelper.DeleteFile(orginalFileName);
        }
    }
     

    /// <summary>
    /// 图片信息
    /// </summary>
    public class ImgInfo
    {
        /// <summary>
        /// 文件名称，无后缀
        /// </summary>
        public string FileNameNoSuffix { get; set; }
        /// <summary>
        /// 文件名无后缀
        /// </summary>
        public string FileName { get; set; }
        /// <summary>
        /// 用户传递过来的图片名字
        /// </summary>
        public string OriginalName { get; set; }
        /// <summary>
        /// 原图名称，加_0_0 
        /// </summary>
        public string OriginFileName { get; set; }
        /// <summary>
        /// 目录结构
        /// </summary>
        public string SaveDbFilePath { get; set; }
        /// <summary>
        /// 文件保存的相对目录
        /// </summary>
        public string RelativePath { get; set; }
        /// <summary>
        /// 
        /// </summary>
        public string FullFileDir { get; set; }

        public string PicUrl { get; set; }

        public string FailRemark { get; set; }
    }

}
