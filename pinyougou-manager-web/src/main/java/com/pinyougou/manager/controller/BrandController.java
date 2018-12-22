package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import entity.PageResult;
import entity.Result;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll.do")
    public List<TbBrand> findAll(){
        return brandService.findAll();
    }

    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestParam(name = "page" , defaultValue = "1") int page,
                                @RequestParam(name = "size" , defaultValue = "10") int size){
       return brandService.findPage(page, size);
    }

    @RequestMapping("/insert.do")
    public Result insert(@RequestBody TbBrand tbBrand){
        try {
            brandService.insert(tbBrand);
           return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    @RequestMapping("/findOne.do")
    public TbBrand findOne(long id){
        return brandService.findOne(id);
    }

    @RequestMapping("/update.do")
    public Result update(@RequestBody TbBrand tbBrand){
        try {
            brandService.update(tbBrand);
            return  new Result(true,"修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(true,"修改失败");
        }
    }

    @RequestMapping("/delete.do")
    public Result delete(long[] ids){
        try {
            brandService.delete(ids);
            return  new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(true,"删除成功");
        }

    }

    @RequestMapping("/search.do")
    public PageResult search(@RequestBody TbBrand tbBrand,
                             @RequestParam(name = "page" , defaultValue = "1") int page,
                             @RequestParam(name = "size" , defaultValue = "10") int size){
         return brandService.findPage(tbBrand,page,size);

    }

    @RequestMapping("/selectOptionList.do")
    public List<Map> selectOptionList(){
       return brandService.selectOptionList();
    }

}
