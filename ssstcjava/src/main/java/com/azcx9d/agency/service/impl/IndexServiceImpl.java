package com.azcx9d.agency.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.azcx9d.agency.dao.IndexDao;
import com.azcx9d.agency.service.IndexService;

@Service
@Transactional
public class IndexServiceImpl implements IndexService{
    @Autowired
    private IndexDao dao;
    
    public List<HashMap<String, Object>> recommendedRecords(){
    	return dao.recommendedRecords();
    }
}
