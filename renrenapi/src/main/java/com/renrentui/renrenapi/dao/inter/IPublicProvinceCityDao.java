package com.renrentui.renrenapi.dao.inter;

import com.renrentui.renrenentity.PublicProvinceCity;

public interface IPublicProvinceCityDao {
    int insert(PublicProvinceCity record);

    int insertSelective(PublicProvinceCity record);
}