package com.example.demo12.service.impl;

import com.example.demo12.bean.Country;
import com.example.demo12.dao.CountryMapper;
import com.example.demo12.service.CountryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 孟凌涛
 * @create 2020-07-28 13:10
 */
@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    private Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

    @Override
    public PageInfo<Country> getAllCountry(int pageNum, int pageSize,String reason) {
        PageHelper.startPage(pageNum,pageSize);

        List<Country> countries = countryMapper.getAll(reason);
        PageInfo<Country> pageInfo = new PageInfo<>(countries);
        return pageInfo;
    }

    @Override
    public boolean deleteByCode(String code) {

        if(null == code || "".equals(code)){
            return false;
        }
//        CountryExample example = new CountryExample();
//        CountryExample.Criteria criteria = example.createCriteria();
//        criteria.andCodeEqualTo(code);
//        countryMapper.deleteByExample(example);
        countryMapper.deleteByPrimaryKey(code);

        return true;
    }

    @Override
    public Country getCountryByCode(String code) {

        if(code == null && !code.equals("")){
            return null;
        }

//        CountryExample countryExample = new CountryExample();
//        CountryExample.Criteria criteria = countryExample.createCriteria();
//        criteria.andCodeEqualTo(code);
//        List<Country> countries = countryMapper.selectByExample(countryExample);
        Country country = countryMapper.selectByPrimaryKey(code);
        logger.info("country="+country);

        return country;
    }

    @Override
    public boolean addMessage(Country country) {

        logger.info("country="+country);

        if(country == null){
            return false;
        }

        countryMapper.insertSelective(country);

        return true;
    }

    @Override
    public boolean deleteBatch(List<String> codes) {

        if(codes == null || codes.size() == 0){
            return false;
        }

        countryMapper.deleteBatch(codes);

        return true;
    }

    @Override
    public boolean updateMessage(Country country) {
        if(country == null ){
            return false;
        }

        int i = countryMapper.updateByPrimaryKeySelective(country);

        logger.info("i = "+i);

        return true;
    }


}
