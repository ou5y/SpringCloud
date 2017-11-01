package com.azcx9d.agency.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface AgencyBatchService {
	
	/**
	 * 批量添加代理商
	 * @param inputStream			文件输入流
	 * @param originalFilename		文件原始名称
	 * @return Map<String,String>
	 */
	Map<String, List<String>> saveAgencyBatch(InputStream inputStream, String originalFilename) throws Exception;

}
