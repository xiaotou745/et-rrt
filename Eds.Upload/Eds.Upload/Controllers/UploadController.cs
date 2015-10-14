using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using Eds.Upload.Providers;
using Ets.Model.Common;
using Ets.Model.ParameterModel.Clienter;
using ETS.Enums;
using ETS.Util;
using System.IO;
using ETS.Extension;

namespace Eds.Upload.Controllers
{
    [RoutePrefix("upload")]
    public class UploadController : ApiController
    {
        [Route("uploadimg")]
        [HttpPost]
        public ResultModel<UploadResultModel> UploadImg(int uploadFrom)
        {
            #region 参数验证
            
            if (HttpContext.Current.Request.Params.Count == 0)
            {
                return ResultModel<UploadResultModel>.Conclude(UploadIconStatus.NOFormParameter);
            }
             
            if (uploadFrom <= 0)
            {
                return ResultModel<UploadResultModel>.Conclude(UploadIconStatus.InvalidOrderId);
            } 
            if (HttpContext.Current.Request.Files.Count == 0) //图片
            {
                return ResultModel<UploadResultModel>.Conclude(UploadIconStatus.InvalidFileFormat);
            }
            var file = HttpContext.Current.Request.Files[0]; //照片  
            #endregion
            //上传图片
            ImgInfo imgInfo = new ImageHelper().UploadImg(file, (UploadFrom)(ParseHelper.ToInt(uploadFrom))); 
            return ResultModel<UploadResultModel>.Conclude(UploadIconStatus.Success, new UploadResultModel() { FileUrl  = imgInfo.PicUrl,ModifyOriginalName = imgInfo.OriginFileName, OriginalName = imgInfo.OriginalName,RelativePath = imgInfo.RelativePath});

        }
        [Route("uploadfile")]
        [HttpPost]
        public ResultModel<UploadResultModel> UploadFile(int uploadFrom)
        {
            #region 参数验证
            if (HttpContext.Current.Request.Params.Count == 0)
            {
                return ResultModel<UploadResultModel>.Conclude(UploadIconStatus.NOFormParameter);
            } 
            if (uploadFrom <= 0)
            {
                return ResultModel<UploadResultModel>.Conclude(UploadIconStatus.InvalidOrderId);
            }
            if (HttpContext.Current.Request.Files.Count == 0)  
            {
                return ResultModel<UploadResultModel>.Conclude(UploadIconStatus.InvalidFileFormat);
            }
            var file = HttpContext.Current.Request.Files[0];  
            #endregion
            //上传图片
            ImgInfo imgInfo = new ImageHelper().UploadFile(file, "", (UploadFrom)(ParseHelper.ToInt(uploadFrom)));
            return ResultModel<UploadResultModel>.Conclude(UploadIconStatus.Success, new UploadResultModel() { FileUrl = imgInfo.PicUrl, ModifyOriginalName = imgInfo.OriginFileName, OriginalName = imgInfo.OriginalName, RelativePath = imgInfo.RelativePath }); 
        }
        
        [Route("deletefile")]
        [HttpPost]
        public ResultModel<UploadResultModel> DeleteFile(string fileName)
        { 
            if (string.IsNullOrWhiteSpace(fileName))
            {
                return ResultModel<UploadResultModel>.Conclude(UploadIconStatus.InvalidOrderId);
            }
            new ImageHelper().DeleteFile(fileName);
            return ResultModel<UploadResultModel>.Conclude(UploadIconStatus.Success);
        }
    }
}
