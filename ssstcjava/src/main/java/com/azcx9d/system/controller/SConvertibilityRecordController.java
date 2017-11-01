package com.azcx9d.system.controller;

import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.util.DateUtil;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.Page;
import com.azcx9d.system.entity.SSystemUser;
import com.azcx9d.system.service.SConvertibilityRecordService;
import com.azcx9d.system.service.SSystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/8 0008 09:14.
 */
@RestController
@RequestMapping("/sApi/convertibilityRecord")
@Api(value = "交易记录(出纳)",description="交易记录列表")
public class SConvertibilityRecordController extends HQBaseController {

    @Autowired
    private SConvertibilityRecordService recordService;

    @Autowired
    private SSystemUserService systemUserService;

    @RequestMapping(value = "/recordList",method = RequestMethod.GET)
    @ApiOperation(value = "交易记录列表：查询queryDate之前的所有交易记录", notes = "currentPage：当前页<br/>pageSize：每页条数<br/>queryDate：查询日期：毫秒数时间戳<br/>")
    public JsonResult recordList(@ApiParam(required = true,value = "token")@RequestParam("token") String token,
                                 @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                 @ApiParam(required = true,value = "每页条数",defaultValue = "10")@RequestParam("pageSize") int pageSize,
                                 @ApiParam(required = true,value = "查询日期：毫秒数时间戳")@RequestParam("queryDate") String queryDate){
        ParaMap paraMap = super.getParaMap();
        //===========================权限判定，后续抽取到aop中==================================
        paraMap.put("userId",super.getUserId());
        try {
            SSystemUser user = systemUserService.selectById(paraMap);
            System.out.println(user.getRoleId().contains("2"));
            if(!user.getRoleId().contains("2")){  //非出纳
                return super.errorMsg(1,"权限不足");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return super.serverExcptionMsg();
        }
        //===========================权限判定，后续抽取到aop中==================================

        paraMap.put("queryDate", DateUtil.timeStamp2Date(queryDate,""));

        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);

        try {
            recordService.recordList(page,paraMap);
            return super.successMsg(page);
        } catch (Exception e){
            e.printStackTrace();
            return super.serverExcptionMsg();
        }
    }

    //导出excel
    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    @ApiOperation(value = "导出交易记录：导出queryDay的所有交易记录", notes = "queryDay：导出日期，格式：2017-04-08,0不可省略")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
                            @ApiParam(required = true,value = "token")@RequestParam("token") String token,
                            @ApiParam(required = true,value = "导出日期：格式：：2017-04-08,0不可省略")@RequestParam("queryDay") String queryDay){
        List<Map> records = new ArrayList<>();
        ParaMap paraMap = super.getParaMap();
        //===========================权限判定，后续抽取到aop中==================================
        paraMap.put("userId",super.getUserId());
        try {
            SSystemUser user = systemUserService.selectById(paraMap);
            if(!user.getRoleId().contains("2")){  //非出纳
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        //===========================权限判定，后续抽取到aop中==================================
        try {
            records = recordService.selectAllByQueryDay(paraMap);
            if (records==null || records.size()==0){
                return;
            }
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
            sheet.setColumnWidth(2,3000);
            sheet.setColumnWidth(3,5000);
            HSSFRow rowHeader = sheet.createRow(0);//创建一行
            //银行卡号
            HSSFCell cell0Header = rowHeader.createCell(0);//创建一格
            cell0Header.setCellType(CellType.STRING);
            cell0Header.setCellValue("银行卡号");
            //姓名
            HSSFCell cell1Header = rowHeader.createCell(1);//创建一格
            cell1Header.setCellType(CellType.STRING);
            cell1Header.setCellValue("姓名");
            //金额
            HSSFCell cell2Header = rowHeader.createCell(2);//创建一格
            cell2Header.setCellType(CellType.STRING);
            cell2Header.setCellValue("金额");
            //身份证
            HSSFCell cell3Header = rowHeader.createCell(3);//创建一格
            cell3Header.setCellType(CellType.STRING);
            cell3Header.setCellValue("身份证");
            for (int i=1;i<=records.size();i++) {
                Map record = records.get(i-1);

                HSSFRow row = sheet.createRow(i);//创建一行
                //银行卡号
                HSSFCell cell0 = row.createCell(0);//创建一格
                cell0.setCellType(CellType.NUMERIC);
                //姓名
                HSSFCell cell1 = row.createCell(1);//创建一格
                cell1.setCellType(CellType.STRING);
                //金额
                HSSFCell cell2 = row.createCell(2);//创建一格
                cell2.setCellType(CellType.NUMERIC);
                //身份证
                HSSFCell cell3 = row.createCell(3);//创建一格
                cell3.setCellType(CellType.STRING);

                cell0.setCellValue(record.get("bankcardNo").toString());
                cell1.setCellValue(record.get("businessUserName").toString());
                cell2.setCellValue(Double.valueOf(record.get("money").toString()));
                cell3.setCellValue(record.get("businessIDCard").toString());
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
