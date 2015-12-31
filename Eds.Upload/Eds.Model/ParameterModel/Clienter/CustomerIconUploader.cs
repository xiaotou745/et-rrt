using ETS.Enums;
using ETS.Util;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ETS.Extension;
namespace Ets.Model.ParameterModel.Clienter
{
    public class CustomerIconUploader
    {
        public static CustomerIconUploader Instance = new CustomerIconUploader();
        public  int Width
        {
            get { return 150; }
        }

        public  int Height
        {
            get { return 150; }
        }
        public int PartnerWidth
        {
            get { return 95; }
        }

        public int PartnerHeight
        {
            get { return 95; }
        }

        /// <summary>
        /// 50k
        /// </summary>
        public  int MaxBytesLength
        {
            get { return 50 * 1024; }
        }
        /// <summary>
        /// 文件名称
        /// </summary>
        public string FolderName 
        { 
            get { return ConfigSettings.Instance.FileUploadFolderNameCustomerIcon; }
        }

        private string physicalPath;
        /// <summary>
        /// 文件物理路径
        /// </summary>
        public string PhysicalPath
        {
            get
            {
                if (physicalPath == null)
                {
                    physicalPath = Path.Combine(ConfigSettings.Instance.FileUploadPath, ConfigSettings.Instance.FileUploadFolderNameCustomerIcon);
                    if (!System.IO.Directory.Exists(physicalPath))
                    {
                        System.IO.Directory.CreateDirectory(physicalPath);
                    }
                }
                return physicalPath;
            }
        }

        ///// <summary>
        ///// 文件相对路径
        ///// </summary>
        //public string RelativePath
        //{
        //    get { return Path.Combine("/", Path.GetFileName(ConfigSettings.Instance.FileUploadPath), this.FolderName).ToForwardSlashPath(); }
        //}

        /// <summary>
        /// 文件相对父路径
        /// </summary>
        public string ParentRelativePath
        {
            get { return Path.Combine("/", Path.GetFileName(ConfigSettings.Instance.FileUploadPath)).ToForwardSlashPath(); }
        }
        public string PicHost
        {
            get { return ConfigSettings.Instance.PicHost; }
        }
        private string businessPicPhysicalPath;
        /// <summary>
        /// 商户图片物理路径  add by pengyi 20150709
        /// </summary>
        public string BusinessPicPhysicalPath
        {
            get
            {
                if (businessPicPhysicalPath == null)
                {
                    businessPicPhysicalPath = Path.Combine(ConfigSettings.Instance.FileUploadPath,ConfigSettings.Instance.FileUploadFolderNameBusiness);
                    if (!System.IO.Directory.Exists(businessPicPhysicalPath))
                    {
                        System.IO.Directory.CreateDirectory(businessPicPhysicalPath);
                    }
                }
                return businessPicPhysicalPath;
            }
        }
        private string clienterPicPhysicalPath;
        /// <summary>
        /// 骑士图片物理路径  add by pengyi 20150709
        /// </summary>
        public string ClienterPicPhysicalPath
        {
            get
            {
                if (clienterPicPhysicalPath == null)
                {
                    clienterPicPhysicalPath = Path.Combine(ConfigSettings.Instance.FileUploadPath,ConfigSettings.Instance.FileUploadFolderNameClienter);
                    if (!System.IO.Directory.Exists(clienterPicPhysicalPath))
                    {
                        System.IO.Directory.CreateDirectory(clienterPicPhysicalPath);
                    }
                }
                return clienterPicPhysicalPath;
            }
        }
        public string orderPicPhysicalPath;
        /// <summary>
        /// 订单图片物理路径 
        /// </summary>
        public string OrderPicPhysicalPath
        {
            get
            {
                if (orderPicPhysicalPath == null)
                {
                    orderPicPhysicalPath = Path.Combine(ConfigSettings.Instance.FileUploadPath,ConfigSettings.Instance.FileUploadFolderNameOrder);
                    if (!System.IO.Directory.Exists(orderPicPhysicalPath))
                    {
                        System.IO.Directory.CreateDirectory(orderPicPhysicalPath);
                    }
                }
                return orderPicPhysicalPath;
            }
        }

        /// <summary>
        /// 获得图片物理路径
        /// 彭宜
        /// 20150710
        /// </summary>
        /// <param name="imageType">图片类型</param>
        /// <returns></returns>
        public string GetPhysicalPath(UploadFrom uploadFrom)
        {
            //如果是小票,放在CustomerIcon/中,否则图片为商家或骑士的验证图片,需要保存在CustomerIcon/Business(或Clients)/中
            switch (uploadFrom)
            {
                case UploadFrom.Business:
                    return BusinessPicPhysicalPath;
                case UploadFrom.Clienter:
                    return ClienterPicPhysicalPath;
                case UploadFrom.Order:
                    return OrderPicPhysicalPath;
                default:
                    return PhysicalPath;
            }
        }

        
    }
}
