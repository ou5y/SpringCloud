package com.azcx9d.system.controller;

import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BOrderFormService;
import com.azcx9d.business.util.DateUtil;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.SystemConfig;
import com.azcx9d.system.entity.SSystemUser;
import com.azcx9d.system.service.SSystemAuditRecordService;
import com.azcx9d.system.service.SSystemUserService;
import com.azcx9d.system.util.ExcelReader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/9 0009 16:47.
 */
@RestController
//@RequestMapping("/systemAudit")
@RequestMapping("/sApi/systemAudit")
@Api(value = "测试控制器",description="测试")
public class SSystemAuditController extends HQBaseController {

//    @Autowired
//    private SSystemUserService systemUserService;

    @Autowired
    private BOrderFormService orderService;

    @Autowired
    private SSystemAuditRecordService systemAuditRecordService;

    @RequestMapping(value = "/upExcel",method = RequestMethod.POST)
    @ApiOperation(value = "excel上传",notes = "excel上传接口")
//    public JsonResult upload(@ApiParam(required = true,value = "token")@RequestParam("token") String token,
    public JsonResult upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        boolean i = true;
        if (i){
            HashMap map = new HashMap();
            map.put("xxx","yyy");
            JsonResult js = new JsonResult(0, "返回测试", map);
            return js;
        }

//        //===========================权限判定，后续抽取到aop中==================================
        ParaMap paraMap = super.getParaMap();
//        paraMap.put("userId",super.getUserId());
//        try {
//            SSystemUser user = systemUserService.selectById(paraMap);
//            if(!user.getRoleId().contains("1")||!user.getRoleId().contains("3")){  //非财务,并且没有3(下载)权限
//                return super.errorMsg(1,"权限不足");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return super.errorMsg(1,"错误,程序异常");
//        }
//        //===========================权限判定，后续抽取到aop中==================================
        if(file==null){
            return super.errorMsg(1,"操作错误,未选择文件");
        }

        System.out.println("开始");
//        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        if (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls")){
            return super.errorMsg(1,"操作错误,不正确的文件格式");
        }
//        File targetFile = new File(path, DateUtil.getSdfTimes() + "_" + fileName);
        File targetFile = null;
        try {
            targetFile = new File(SystemConfig.getProperty("excel","system","path"), DateUtil.getSdfTimes() + "_" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return super.errorMsg(1,"错误,程序异常");
        }
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return super.errorMsg(1,"错误,程序异常");
        }
        //1、将file转为InputStream
        try {
            InputStream inputStream = file.getInputStream();
            ExcelReader excelReader = new ExcelReader();
            //2、将excel内容读取到一个List<Map>中
            List<Map> transferRecordsList = excelReader.readExcelContentToList(inputStream);
            System.out.println(transferRecordsList);
            //3、通过map的key(商家id)，到数据库查询对应的商家id今天之前的所有应让利金额，如果匹配则审核，如果不匹配，则不审核 // TODO 由于要多次操作数据库，所以此处应该全部放入service层，并且添加上事务
            orderService.auditOrderByExcelTransferRecords(transferRecordsList,paraMap);
        } catch (Exception e){
            e.printStackTrace();
            return super.errorMsg(1,"错误,程序异常");
        }
        return new JsonResult(0,"上传成功");
    }

    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    @ApiOperation(value = "excel失败列表下载",notes = "自动审核失败excel下载")
//    public void systemAuditRecordExcel(@ApiParam(required = true,value = "token")@RequestParam("token") String token){
    public void systemAuditRecordExcel(HttpServletRequest request, HttpServletResponse response){
        ParaMap paraMap = super.getParaMap();
        String milliseconds = DateUtil.getMillisecondsAfterDays(DateUtil.timeStamp(), 0);  //应让利总和统计结束时间
        String sellerTimeEnd = DateUtil.timeStamp2Date(milliseconds,"yyyy-MM-dd"); //年月日时分秒字符串
        paraMap.put("queryDay",sellerTimeEnd);
        List<Map> records;
        try {
            records = systemAuditRecordService.selectFaildList(paraMap);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }



        HttpSession session = request.getSession();
        session.setAttribute("state", null);
        // 生成提示信息，
        response.setContentType("application/vnd.ms-excel");
        String codedFileName = null;
        OutputStream fOut = null;
        try
        {
            // 进行转码，使其支持中文文件名
//            codedFileName = java.net.URLEncoder.encode("中文", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + DateUtil.getTime().replace(' ',':') + ".xls");
//            response.setHeader("content-disposition", "attachment;filename=" + DateUtil.getTime().replace(' ',':') + ".xls");
            // response.addHeader("Content-Disposition", "attachment;   filename=" + codedFileName + ".xls");
            // 产生工作簿对象
            HSSFWorkbook workbook = new HSSFWorkbook();
//            workbook.setSheetName();
            //产生工作表对象
            HSSFSheet sheet = workbook.createSheet();
            sheet.setColumnWidth(1,3000);
            sheet.setColumnWidth(2,5000);
            HSSFRow rowHeader0 = sheet.createRow(0);//创建第一行
            HSSFCell cell0Header0 = rowHeader0.createCell(0);
            cell0Header0.setCellType(CellType.STRING);
            cell0Header0.setCellValue("自动审核失败列表");
//            HSSFCell cell1Header0 = rowHeader0.createCell(1);
//            cell1Header0.setCellType(CellType.STRING);
//            cell1Header0.setCellValue(paraMap.getString("queryDay"));


            HSSFRow rowHeader1 = sheet.createRow(1);//创建第二行
            //商家id(店铺id)
            HSSFCell cell0Header = rowHeader1.createCell(0);//创建一格
            cell0Header.setCellType(CellType.NUMERIC);
            cell0Header.setCellValue("商家id(店铺id)");
            //商家姓名
            HSSFCell cell1Header = rowHeader1.createCell(1);//创建一格
            cell1Header.setCellType(CellType.NUMERIC);
            cell1Header.setCellValue("打款金额");
            //商家身份证
            HSSFCell cell2Header = rowHeader1.createCell(2);//创建一格
            cell2Header.setCellType(CellType.STRING);
            cell2Header.setCellValue("自动审核失败时间");

            for (int i=1;i<=records.size();i++) {
                Map record = records.get(i-1);

                HSSFRow row = sheet.createRow(i+1);//创建一行
                //商家id(店铺id)
                HSSFCell cell0 = row.createCell(0);//创建一格
                cell0.setCellType(CellType.NUMERIC);
                //打款金额
                HSSFCell cell1 = row.createCell(1);//创建一格
                cell1.setCellType(CellType.NUMERIC);
                //自动审核失败时间
                HSSFCell cell2 = row.createCell(2);//创建一格
                cell2.setCellType(CellType.STRING);

                cell0.setCellValue(Integer.valueOf(record.get("businessId").toString()));
                cell1.setCellValue(Double.valueOf(record.get("money").toString()));
                cell2.setCellValue(record.get("create_time").toString());
            }
            fOut = response.getOutputStream();
            workbook.write(fOut);
        }catch (UnsupportedEncodingException e1){
            e1.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try
            {
                fOut.flush();
                fOut.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            session.setAttribute("state", "open");
        }


    }


}
