package com.renrentui.api.dao.inter;

import com.renrentui.entity.PublicProvinceCity;

public interface IPublicProvinceCityDao {
    int insert(PublicProvinceCity record);

    int insertSelective(PublicProvinceCity record);
}