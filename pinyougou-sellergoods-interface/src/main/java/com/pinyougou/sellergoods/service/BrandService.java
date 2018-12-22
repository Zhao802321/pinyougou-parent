package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface BrandService {
    List<TbBrand> findAll();

    public PageResult findPage(int pageNum,int pageSize);

    void insert(TbBrand tbBrand);

    void update(TbBrand tbBrand);

    TbBrand findOne(long id);

    void delete(long[] ids);

    PageResult findPage(TbBrand tbBrand, int page, int size);

    public List<Map> selectOptionList();

}
