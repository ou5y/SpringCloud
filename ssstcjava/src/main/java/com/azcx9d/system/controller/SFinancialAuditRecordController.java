//package com.azcx9d.system.controller;
//
//import com.azcx9d.business.base.HQBaseController;
//import com.azcx9d.business.entity.ParaMap;
//import com.azcx9d.business.service.BOrderFormService;
//import com.azcx9d.business.util.DateUtil;
//import com.azcx9d.common.entity.JsonResult;
//import com.azcx9d.common.util.Page;
//import com.azcx9d.system.annotation.Before;
//import com.azcx9d.system.entity.SSystemUser;
//import com.azcx9d.system.service.SSystemUserService;
//import com.azcx9d.system.validator.PermissionValidator;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.apache.commons.lang.StringUtils;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.CellType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.*;
//
///**
// * Created by HuangQing on 2017/4/13 0013 15:22.
// */
//@RestController
//@RequestMapping("/sApi/auditRecord")
//@Api(value = "财务审核记录控制器",description="审核记录")
//@EnableAspectJAutoProxy
////@ApiIgnore
//public class SFinancialAuditRecordController extends HQBaseController {
//
////    private final Long BEFORETIME = 3 * 24 * 60 * 60 * 1000L; //默认查看七天的审核记录
//    private final Integer DAYS = -7; //默认查看七天的审核记录
//
//    @Autowired
//    private BOrderFormService orderService;
//
//    @Autowired
//    private SSystemUserService systemUserService;
//
//    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
//    @ApiOperation(value = "审核历史：查询startDate到endDate的所有记录，按照商家id分组", notes = "currentPage：当前页<br/>pageSize：每页条数<br/>startDate、endDate：查询日期：毫秒数时间戳<br/>")
//    @Before(PermissionValidator.class)
//    public JsonResult auditRecordList(@ApiParam(required = true,value = "token")@RequestParam("token") String token,
//                                        @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
//                                        @ApiParam(required = true,value = "每页条数",defaultValue = "10")@RequestParam("pageSize") int pageSize){
////        paraMap.put("caiwuId",super.getUserId());
//        ParaMap paraMap = super.getParaMap();
//        //===========================权限判定，后续抽取到aop中==================================
//        paraMap.put("userId",super.getUserId());
//        try {
//            SSystemUser user = systemUserService.selectById(paraMap);
//            if(!user.getRoleId().contains("1")){  //非财务
//                return super.errorMsg(1,"权限不足");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return super.serverExcptionMsg();
//        }
//        //===========================权限判定，后续抽取到aop中==================================
//
//        String startDate = paraMap.getString("startDate");
//        String endDate = paraMap.getString("endDate");
//
//        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(startDate.replace("NaN",""))){
//            startDate = DateUtil.getMillisecondsAfterDays(DateUtil.timeStamp(), DAYS);
//        }
//        paraMap.put("startDate", DateUtil.timeStamp2Date(startDate,null));
//        if (StringUtils.isBlank(endDate) || StringUtils.isBlank(endDate.replace("NaN",""))){
//            endDate = String.valueOf(new Date().getTime());
//        }
//        paraMap.put("endDate", DateUtil.timeStamp2Date(endDate,null));
//
//        Page page = new Page();
//        page.setCurrentPage(currentPage);
//        page.setPageSize(pageSize);
//        try {
//            orderService.selectAuditRecordsList(page,paraMap);
//            HashMap data = new HashMap();
//            data.put("totalMoney",paraMap.get("totalMoney"));
//            data.put("page",page);
//            return super.successMsg(data);
//        } catch (Exception e){
//            e.printStackTrace();
//            return  super.serverExcptionMsg();
//        }
//    }
//
//    //导出excel
//    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
//    @ApiOperation(value = "导出审核历史", notes = "")
//    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
//                            @ApiParam(required = true,value = "token")@RequestParam("token") String token){
//
//
//        List<Map> records = new ArrayList<>();
//        ParaMap paraMap = super.getParaMap();
//        //如果没有传递下载时间，则默认今天到七天前
//        String startDate = paraMap.getString("startDate");
//        String endDate = paraMap.getString("endDate");
//
//        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(startDate.replace("NaN",""))){
//            startDate = DateUtil.getYMDAfterDays(DateUtil.timeStamp(), DAYS);
//        }
//        paraMap.put("startDate", DateUtil.timeStamp2Date(startDate,""));
//        if (StringUtils.isBlank(endDate) || StringUtils.isBlank(startDate.replace("NaN",""))){
//            endDate = String.valueOf(new Date().getTime());
//        }
//        paraMap.put("endDate", DateUtil.timeStamp2Date(endDate,""));
//        //===========================权限判定，后续抽取到aop中==================================
//        paraMap.put("userId",super.getUserId());
//        try {
//            SSystemUser user = systemUserService.selectById(paraMap);
//            if(!user.getRoleId().contains("1")||!user.getRoleId().contains("3")){  //非财务,并且没有3(下载)权限
//                return;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//        //===========================权限判定，后续抽取到aop中==================================
//        try {
//            records = orderService.selectExportAudit(paraMap);
//            if (records==null || records.size()==0){
//                return;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return;
//        }
//
//
//        HttpSession session = request.getSession();
//        session.setAttribute("state", null);
//        // 生成提示信息，
//        response.setContentType("application/vnd.ms-excel");
//        String codedFileName = null;
//        OutputStream fOut = null;
//        try
//        {
//            // 进行转码，使其支持中文文件名
////            codedFileName = java.net.URLEncoder.encode("中文", "UTF-8");
//            response.setHeader("content-disposition", "attachment;filename=" + DateUtil.getTime().replace(' ',':') + ".xls");
////            response.setHeader("content-disposition", "attachment;filename=" + DateUtil.getTime().replace(' ',':') + ".xls");
//            // response.addHeader("Content-Disposition", "attachment;   filename=" + codedFileName + ".xls");
//            // 产生工作簿对象
//            HSSFWorkbook workbook = new HSSFWorkbook();
////            workbook.setSheetName();
//            //产生工作表对象
//            HSSFSheet sheet = workbook.createSheet();
//            sheet.setColumnWidth(1,3000);
//            sheet.setColumnWidth(2,5000);
//            HSSFRow rowHeader0 = sheet.createRow(0);//创建第一行
//            HSSFCell cell0Header0 = rowHeader0.createCell(0);
//            cell0Header0.setCellType(CellType.STRING);
//            cell0Header0.setCellValue("日期");
//            HSSFCell cell1Header0 = rowHeader0.createCell(1);
//            cell1Header0.setCellType(CellType.STRING);
//            cell1Header0.setCellValue(paraMap.getString("auditTime"));
//
//
//            HSSFRow rowHeader1 = sheet.createRow(1);//创建第二行
//            //商家id(店铺id)
//            HSSFCell cell0Header = rowHeader1.createCell(0);//创建一格
//            cell0Header.setCellType(CellType.STRING);
//            cell0Header.setCellValue("商家id(店铺id)");
//            //商家让利
//            HSSFCell cell1Header = rowHeader1.createCell(1);//创建一格
//            cell1Header.setCellType(CellType.NUMERIC);
//            cell1Header.setCellValue("商家让利");
//            //审核时间
//            HSSFCell cell2Header = rowHeader1.createCell(2);//创建一格
//            cell2Header.setCellType(CellType.STRING);
//            cell2Header.setCellValue("审核时间");
//
//            for (int i=1;i<=records.size();i++) {
//                Map record = records.get(i-1);
//
//                HSSFRow row = sheet.createRow(i+1);//创建一行
//                //商家id(店铺id)
//                HSSFCell cell0 = row.createCell(0);//创建一格
//                cell0.setCellType(CellType.STRING);
//                //商家姓名
//                HSSFCell cell1 = row.createCell(1);//创建一格
//                cell1.setCellType(CellType.NUMERIC);
//                //商家身份证
//                HSSFCell cell2 = row.createCell(2);//创建一格
//                cell2.setCellType(CellType.STRING);
//
//                cell0.setCellValue(record.get("storeId").toString());
//                cell1.setCellValue(Double.valueOf(record.get("storeRangli").toString()));
//                cell2.setCellValue(record.get("auditTime").toString());
//            }
//            fOut = response.getOutputStream();
//            workbook.write(fOut);
//        }catch (UnsupportedEncodingException e1){
//            e1.printStackTrace();
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally{
//            try
//            {
//                fOut.flush();
//                fOut.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//            session.setAttribute("state", "open");
//        }
//    }
//
//
//}
