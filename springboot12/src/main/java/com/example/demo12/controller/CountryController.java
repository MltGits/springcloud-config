package com.example.demo12.controller;

import com.example.demo12.bean.Country;
import com.example.demo12.service.CountryService;
import com.example.demo12.utils.ResultEntity;

import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 孟凌涛
 * @create 2020-07-28 13:05
 */
@CrossOrigin
@RestController
public class CountryController {

    @Autowired
    private CountryService countryService;

    Logger logger = LoggerFactory.getLogger(CountryController.class);

    @PutMapping("update/message")
    public ResultEntity<String> updateMessage(Country country){

        logger.info("country="+country);
        if(country == null){
            return ResultEntity.failed("数据修改失败");
        }

        try {

            boolean result = countryService.updateMessage(country);

            if(result){
                return ResultEntity.successWithData("删除成功");
            }

            return ResultEntity.failed("删除失败");

        }catch (Exception e){
            return ResultEntity.failed(e.getMessage());
        }

    }

    @DeleteMapping("delete/some")
    public ResultEntity<String> deleteSome(String codes){
        logger.info("codes="+codes);
        try {
            List<String> cos = new ArrayList<>();
            String[] split = codes.split(",");
            for (int i = 0; i < split.length; i++){
                cos.add(split[i]);
            }

            logger.info("cos="+cos);
            boolean result = countryService.deleteBatch(cos);

            if(result){
                return ResultEntity.successWithData("删除成功");
            }

            return ResultEntity.failed("删除失败");

        }catch (Exception e){
           return ResultEntity.failed(e.getMessage());
        }
    }

    @PostMapping("add/message")
    public ResultEntity<String> addMessage(Country country){
        logger.info("country="+country);
        if(country == null){
            return ResultEntity.failed("数据插入失败");
        }

        try {

            boolean result = countryService.addMessage(country);

            if(!result){
                return ResultEntity.failed("数据插入失败");
            }

            return ResultEntity.successWithData("数据插入成功");
        }catch (Exception e){
            logger.debug(e.getMessage());
            return ResultEntity.failed(e.getMessage());
        }

    }

    @GetMapping("get/country/by/code")
    public ResultEntity<Country> getCountryByCode(String code){

        logger.info("code = "+code);

        try {
            if(code == null || "".equals(code)){
                return  ResultEntity.failed("查询信息失败");
            }

            Country country = countryService.getCountryByCode(code);
            logger.info("country="+country);
            if(country == null){
                return ResultEntity.failed("没有该记录");
            }

            return ResultEntity.successWithData(country);
        }catch (Exception e){
            return  ResultEntity.failed(e.getMessage());
        }

    }


    @DeleteMapping("delete/by/code")
    public ResultEntity<String> deleteByCode(String code){

        logger.info("code="+code);

        ResultEntity<String> resultEntity = new ResultEntity<>();

        if(code == null || "".equals(code)){
            return resultEntity.failed("删除失败");
        }

        try {

            boolean result = countryService.deleteByCode(code);

            if(result){
                return resultEntity.successWithData("删除成功");
            }else{
                return resultEntity.failed("删除失败");
            }

        }catch (Exception e){
            return resultEntity.failed(e.getMessage());
        }

    }

    @GetMapping("getAll")
    public ResultEntity getAll(
            @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5")int pageSize,
            @RequestParam(value = "reason",defaultValue = "")String reason
    ){

        ResultEntity<Object> resultEntity = new ResultEntity<>();

        try {
            PageInfo<Country> countries = countryService.getAllCountry(pageNum, pageSize,reason);
            return resultEntity.successWithData(countries);
        }catch (Exception e){
            return resultEntity.failed(e.getMessage());
        }
    }

}
