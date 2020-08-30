package com.example.demo12.service;

import com.example.demo12.bean.Country;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author 孟凌涛
 * @create 2020-07-28 13:10
 */
public interface CountryService {

    PageInfo<Country> getAllCountry(int pageNum, int pageSize,String reason);

    boolean deleteByCode(String code);

    Country getCountryByCode(String code);

    boolean addMessage(Country country);

    boolean deleteBatch(List<String> codes);

    boolean updateMessage(Country country);
}
