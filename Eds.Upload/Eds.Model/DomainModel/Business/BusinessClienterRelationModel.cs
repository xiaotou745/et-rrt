﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Ets.Model.DataModel.Business;

namespace Ets.Model.DomainModel.Business
{
    public class BusinessClienterRelationModel : BusinessClienterRelation
    {
        /// <summary>
        /// 骑士姓名
        /// </summary>
        public string ClienterName { get; set; }
        /// <summary>
        /// 骑士电话
        /// </summary>
        public string PhoneNo { get; set; }
        /// <summary>
        /// 经度
        /// </summary>
        public double Longitude { get; set; }
        /// <summary>
        /// 纬度
        /// </summary>
        public double Latitude { get; set; }
        /// <summary>
        /// 订单推送给骑士的区域半径(单位为公里) 
        /// </summary>
        public string PushRadius { get; set; }
    }
}
