package com.azcx9d.system.controller;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.system.util.ExcelReader;

//@RestController
//@RequestMapping(value="/sApi/agencyImport")
//@Api(value = "代理商批量导入",description="代理商批量导入")
public class SAgencyImportBatch extends HQBaseController{
	//excel文件导入,批量导入数据  
	
//	@ApiOperation(value="代理商批量导入",notes="代理商批量导入")
//	@RequestMapping(value="",method=RequestMethod.POST)
//    public String upload(@RequestHeader(value="token",defaultValue="120_31b21ec9378344bea49a619d54787dba") String token,
//    		@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile){  
//    	
//    	try {
//			InputStream inputStream = uploadFile.getInputStream();
//			ExcelReader excelReader = new ExcelReader();
//			//2、将excel内容读取到一个List<Map>中
//			List<Map> transferRecordsList = excelReader.readExcelContentToList(inputStream);
//			System.out.println(transferRecordsList);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    	
//    	
//        //此时的Workbook应该是从 客户端浏览器上传过来的 uploadFile了,其实跟读取本地磁盘的一个样  
//    	
//    	
////        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(userUploadFile));  
////        HSSFWorkbook wb=new HSSFWorkbook(fs);  
////        HSSFSheet hssfSheet=wb.getSheetAt(0);  
////          
////        if(hssfSheet!=null){  
////            //遍历excel,从第二行开始 即 rowNum=1,逐个获取单元格的内容,然后进行格式处理,最后插入数据库  
////            for(int rowNum=1;rowNum<=hssfSheet.getLastRowNum();rowNum++){  
////                HSSFRow hssfRow=hssfSheet.getRow(rowNum);  
////                if(hssfRow==null){  
////                    continue;  
////                }  
////                  
////                User user=new User();  
////                user.setName(ExcelUtil.formatCell(hssfRow.getCell(0)));  
////                user.setPhone(ExcelUtil.formatCell(hssfRow.getCell(1)));  
////                user.setEmail(ExcelUtil.formatCell(hssfRow.getCell(2)));  
////                user.setQq(ExcelUtil.formatCell(hssfRow.getCell(3)));  
//                  
////                //对于单元格日期需要进行特殊处理  
////                user.setBirth(DateUtil.formatString(ExcelUtil.formatCell2(hssfRow.getCell(4)), "yyyy-MM-dd"));  
////                Connection con=null;  
////                try{  
////                    con=dbUtil.getCon();  
////                    userDao.userAdd(con, user);  
////                }catch(Exception e){  
////                    e.printStackTrace();  
////                }finally{  
////                    dbUtil.closeCon(con);  
////                }  
////            }  
////        }  
////        JSONObject result=new JSONObject();  
////        result.put("success", "true");  
////        ResponseUtil.write(ServletActionContext.getResponse(), result);  
//        return null;  
//    }  

}
