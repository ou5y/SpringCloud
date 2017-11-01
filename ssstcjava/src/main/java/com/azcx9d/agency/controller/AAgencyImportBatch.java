package com.azcx9d.agency.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.http.HttpResponse;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.azcx9d.agency.service.AgencyBatchService;
import com.azcx9d.business.base.HQBaseController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value="/agency")
@Api(value = "代理商批量导入",description="代理商批量导入")
@ApiIgnore
public class AAgencyImportBatch extends HQBaseController{
	
	@Autowired
	private AgencyBatchService agencyBatchService;
	
	@ApiOperation(value="代理商批量导入",notes="代理商批量导入")
	@RequestMapping(value="/agencyImport",method=RequestMethod.POST)
    public String upload(@RequestHeader(value="token",defaultValue="120_31b21ec9378344bea49a619d54787dba") String token,
						 @RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile,
						 HttpServletResponse response){
    	
		try {
			InputStream inputStream = uploadFile.getInputStream();
			Map<String,List<String>> result = agencyBatchService.saveAgencyBatch(inputStream, uploadFile.getOriginalFilename());

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<br/><font size='14px'>导入成功:</font><font size='16px' color='green'>"+result.get("success").size()+"</font>条数据");
			out.println("<br/><font size='14px'>导入失败：</font><font size='16px' color='red'>"+result.get("failed").size()+"</font>条数据");
			for (String str:result.get("failed")) {
				out.println("<br/>"+str);
			}
			out.flush();

			inputStream.close();

		} catch (IOException e) {

			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;  
    }  

}
