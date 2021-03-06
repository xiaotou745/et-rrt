﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ETS.Enums;
using ETS.Extension;
using ETS.Util;

namespace Ets.Model.Common
{
    public class ImageCommon
    {
        //public static List<string> ReceiptPicConvert(string receiptPic)
        //{
        //    List<string> listReceiptPic = new List<string>();

        //    if (string.IsNullOrWhiteSpace(receiptPic))
        //    {
        //        return listReceiptPic;
        //    }
        //    string[] receiptPicArray = receiptPic.Split(new char[] { '|' }, StringSplitOptions.RemoveEmptyEntries);
        //    for (int i = 0; i < receiptPicArray.Length; i++)
        //    {
        //        string rPic = ETS.Util.ConfigSettings.Instance.PicHost +
        //                      Ets.Model.ParameterModel.Clienter.CustomerIconUploader.Instance.RelativePath +
        //                      receiptPicArray[i];
        //        listReceiptPic.Add(rPic);
        //    }
        //    return listReceiptPic;
        //}

        /// <summary>
        /// 小票获取地址
        /// 窦海超
        /// </summary>
        /// <param name="receiptPic"></param>
        /// <returns></returns>
        public static string ReceiptPicConvert(UploadFrom uploadFrom, string receiptPic)
        {
            return ETS.Util.ConfigSettings.Instance.PicHost + "/" + EnumUtils.GetEnumDescription(uploadFrom) +
                            receiptPic;
        }

        /// <summary>
        /// 骑士商家获取地址
        /// 彭宜   20150710
        /// </summary>
        /// <param name="imagePic"></param>
        /// <param name="imageType"></param>
        /// <returns></returns>
        public static string GetUserImage(string imagePic, UploadFrom uploadFrom)
        {
            if (uploadFrom == UploadFrom.Business)
            {
                return ETS.Util.ConfigSettings.Instance.PicHost +
                Path.Combine("/", Path.GetFileName(ConfigSettings.Instance.FileUploadPath), ConfigSettings.Instance.FileUploadFolderNameBusiness).ToForwardSlashPath() +
                imagePic;
            }
            else if (uploadFrom == UploadFrom.Clienter)
            {
                return ETS.Util.ConfigSettings.Instance.PicHost +
                Path.Combine("/", Path.GetFileName(ConfigSettings.Instance.FileUploadPath), ConfigSettings.Instance.FileUploadFolderNameClienter).ToForwardSlashPath() +
                imagePic;
            }
            else if (uploadFrom == UploadFrom.Order)
            {
                return ETS.Util.ConfigSettings.Instance.PicHost +
                Path.Combine("/", Path.GetFileName(ConfigSettings.Instance.FileUploadPath), ConfigSettings.Instance.FileUploadFolderNameOrder).ToForwardSlashPath() +
                imagePic;
            }
            return ETS.Util.ConfigSettings.Instance.PicHost +
                            Path.Combine("/", Path.GetFileName(ConfigSettings.Instance.FileUploadPath), ParameterModel.Clienter.CustomerIconUploader.Instance.FolderName).ToForwardSlashPath() +
                            imagePic;
        }

        public static List<string> GetListImgString(string receiptPic)
        {
            string[] receiptPicArray = receiptPic.Split(new char[] { '|' }, StringSplitOptions.RemoveEmptyEntries);
            List<string> listReceiptPic = new List<string>();
            for (int i = 0; i < receiptPicArray.Length; i++)
            {
                //string rPic = ETS.Util.ConfigSettings.Instance.PicHost + Ets.Model.ParameterModel.Clienter.CustomerIconUploader.Instance.RelativePath + receiptPicArray[i];
                listReceiptPic.Add(receiptPicArray[i]);
            }
            return listReceiptPic;
        }

        //public static List<string>  GetPicDir(string )
    }
}
