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
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
///**
// * 财务审核控制器
// * Created by HuangQing on 2017/4/6 0006 17:22.
// */
//@RestController
//@RequestMapping("/sApi/financialAudit")
//@Api(value = "订单控制器",description="订单")
//@EnableAspectJAutoProxy
//public class SFinancialAuditController extends HQBaseController {
//
//    @Autowired
//    private BOrderFormService orderService;
//
//    @Autowired
//    private SSystemUserService systemUserService;
//
//
//    //按照商家id groupby的订单统计
//    @ApiOperation(value = "待审核列表：查询queryDate之前的所有记录，按照商家id和商家让利比例分组", notes = "currentPage：当前页<br/>pageSize：每页条数<br/>queryDate：查询日期：毫秒数时间戳<br/>")
//    @RequestMapping(value = "/auditListByBidAndRangli",method = RequestMethod.GET)
//    @Before(PermissionValidator.class)
//    public JsonResult auditListByBusinessId(@ApiParam(required = true,value = "token")@RequestParam("token") String token,
//                                            @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
//                                            @ApiParam(required = true,value = "每页条数",defaultValue = "10")@RequestParam("pageSize") int pageSize,
////                                            @ApiParam(required = false,value = "商家id")@RequestParam("businessId") String businessId,
//                                            @ApiParam(required = true,value = "查询日期：毫秒数时间戳")@RequestParam("queryDate") String queryDate){
//        ParaMap paraMap = super.getParaMap();
//
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
//
//        if (StringUtils.isBlank(queryDate.replace("NaN",""))){
//            queryDate = String.valueOf(new Date().getTime());
//        }
//
//        paraMap.put("queryDate", DateUtil.timeStamp2Date(queryDate,""));
//
//        Page page = new Page();
//        page.setCurrentPage(currentPage);
//        page.setPageSize(pageSize);
//
//        try {
////            orderService.auditListByBusinessId(page,paraMap);
//            orderService.auditListByBusinessId2(page,paraMap);
//            return super.successMsg(page);
//        } catch (Exception e){
//            e.printStackTrace();
//            return super.serverExcptionMsg();
//        }
//
//    }
//
////    @ApiOperation(value = "审核", notes = "<br/><br/>")
////    @RequestMapping(value = "/auditByBidAndRangli",method = RequestMethod.GET)
////    public JsonResult auditByBusinessIdAndRangli(@ApiParam(required = true,value = "token")@RequestParam("token") String token,
////                                                 @ApiParam(required = true,value = "商家id")@RequestParam("businessIds") String businessIds,
////                                                 @ApiParam(required = true,value = "让利比例")@RequestParam("ranglis") String ranglis,
////                                                 @ApiParam(required = true,value = "查询日期：毫秒数时间戳")@RequestParam("queryDate") String queryDate){
////        ParaMap paraMap = super.getParaMap();
////
////        //===========================权限判定，后续抽取到aop中==================================
////        paraMap.put("userId",super.getUserId());
////        try {
////            SSystemUser user = systemUserService.selectById(paraMap);
////            if(!user.getRoleId().contains("1")){  //非财务
////                return super.errorMsg(1,"权限不足");
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        //===========================权限判定，后续抽取到aop中==================================
////
////
////        paraMap.put("queryDay",DateUtil.getDay(new Date(Long.valueOf(queryDate)))); //放入查询日期 : yyyy-MM-dd
////        paraMap.put("caiwuTime",new Date());
////        paraMap.put("caiwuId",super.getUserId());   //从redis中获取到财务的id
////        paraMap.put("queryDate", DateUtil.timeStamp2Date(queryDate,""));    //放入查询日期 : yyyy-MM-dd HH:mm:ss
////
////
////        try {
//////            String[] bids = businessIds.split(",");
//////            String[] rangs = ranglis.split(",");
//////            for (int i = 0; i < bids.length; i++) {
////////                String businessId = bids[i];
//////                Long businessId = Long.valueOf(bids[i]);
////////                String rangli = rangs[i];
//////                Double rangli = Double.valueOf(rangs[i]);
//////                paraMap.put("businessId",businessId);
//////                paraMap.put("rangli",rangli);
////                orderService.auditByBusinessIdAndRangli(paraMap);
//////            }
////            return super.successMsg("审核成功");
////        } catch (Exception e) {
////            e.printStackTrace();
////            return super.serverExcptionMsg();
////        }
////    }
//
//
//
//    //订单列表
//    //pageSize前端设置无穷大
//    @ApiOperation(value = "订单列表，通过businessId和和查询时间queryDate查询所有订单", notes = "<br/>businessId：商户id<br/>queryDate：查询时间，毫秒数时间戳")
//    @RequestMapping(value = "/orderListByBidAndTime",method = RequestMethod.GET)
//    public JsonResult orderListByBidAndTime(@ApiParam(required = true,value = "token")@RequestParam("token") String token,
//                                            @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
//                                            @ApiParam(required = true,value = "每页条数",defaultValue = "10")@RequestParam("pageSize") int pageSize,
//                                            @ApiParam(required = true,value = "查询日期：毫秒数时间戳")@RequestParam("queryDate") String queryDate,
//                                            @ApiParam(required = true,value = "商家id")@RequestParam("businessId") String businessId){
//        ParaMap paraMap = super.getParaMap();
//
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
//        //设置每页条数pageSize为最大int，一次性查询出所有待审核订单
////        pageSize = Integer.MAX_VALUE;
//        pageSize = 100000;
//        if (StringUtils.isBlank(queryDate.replace("NaN",""))){
//            queryDate = String.valueOf(new Date().getTime());
//        }
//        paraMap.put("queryDate", DateUtil.timeStamp2Date(queryDate,""));    //设置查询时间
//
//        Page page = new Page();
//        page.setCurrentPage(currentPage);
//        page.setPageSize(pageSize);
//        try {
//            orderService.selectByBIdAndTime(page, paraMap);
//            return super.successMsg(page);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return super.serverExcptionMsg();
//        }
//    }
//
//    /**
//     * 财务根据订单id审核
//     * @param token
//     * @param ids
//     * @return
//     */
//    @ApiOperation(value = "批量审核：根据订单id", notes = "财务批量审核订单")
//    @RequestMapping(value = "/auditByIds",method = RequestMethod.GET)
//    public Object auditByIds(@ApiParam(required = true,value = "token")@RequestParam("token") String token,
//                             @ApiParam(required = true,value = "多个id ，数组") @RequestParam("ids") String[] ids){
//        ParaMap paraMap = super.getParaMap();
//
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
//        paraMap.put("caiwuId",super.getUserId());
//        paraMap.put("caiwuTime",new Date());
//        paraMap.put("idsArr",ids);
//        try {
//            orderService.updateOrderStateInIds(paraMap);
//            return super.successMsg("审核成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return super.serverExcptionMsg();
//        }
//    }
//
//
//
//    //导出excel
//    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
//    @ApiOperation(value = "导出待审核订单：导出queryDay的所有待审核订单", notes = "queryDay：导出日期，格式：2017-04-08,0不可省略")
//    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
//                            @ApiParam(required = true,value = "token")@RequestParam("token") String token,
//                            @ApiParam(required = true,value = "导出日期：格式：2017-04-08,0不可省略")@RequestParam("queryDay") String queryDay){
//
//
//        List<Map> orders = new ArrayList<>();
//        ParaMap paraMap = super.getParaMap();
//        //如果没有传递queryDay，则默认今天
//        if (StringUtils.isBlank(queryDay)){
//            paraMap.put("queryDay",DateUtil.getDay());
//        }
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
//            orders = orderService.selectAllByQueryDay(paraMap);
//            if (orders==null || orders.size()==0){
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
//            cell1Header0.setCellValue(paraMap.getString("queryDay"));
//
//
//            HSSFRow rowHeader1 = sheet.createRow(1);//创建第二行
//            //商家id(店铺id)
//            HSSFCell cell0Header = rowHeader1.createCell(0);//创建一格
//            cell0Header.setCellType(CellType.STRING);
//            cell0Header.setCellValue("商家id(店铺id)");
//            //商家姓名
//            HSSFCell cell1Header = rowHeader1.createCell(1);//创建一格
//            cell1Header.setCellType(CellType.STRING);
//            cell1Header.setCellValue("商家姓名");
//            //商家身份证
//            HSSFCell cell2Header = rowHeader1.createCell(2);//创建一格
//            cell2Header.setCellType(CellType.STRING);
//            cell2Header.setCellValue("商家身份证");
//            //让利金额
//            HSSFCell cell3Header = rowHeader1.createCell(5);//创建一格
//            cell3Header.setCellType(CellType.STRING);
//            cell3Header.setCellValue("让利金额");
//            //让利比例
//            HSSFCell cell4Header = rowHeader1.createCell(6);//创建一格
//            cell4Header.setCellType(CellType.STRING);
//            cell4Header.setCellValue("让利比例");
//
//            //法人姓名
//            HSSFCell cell5Header = rowHeader1.createCell(3);//创建一格
//            cell5Header.setCellType(CellType.STRING);
//            cell5Header.setCellValue("法人姓名");
//            //法人手机号
//            HSSFCell cell6Header = rowHeader1.createCell(4);//创建一格
//            cell6Header.setCellType(CellType.STRING);
//            cell6Header.setCellValue("法人手机号");
//
//            for (int i=1;i<=orders.size();i++) {
//                Map record = orders.get(i-1);
//
//                HSSFRow row = sheet.createRow(i+1);//创建一行
//                //商家id(店铺id)
//                HSSFCell cell0 = row.createCell(0);//创建一格
//                cell0.setCellType(CellType.NUMERIC);
//                //商家姓名
//                HSSFCell cell1 = row.createCell(1);//创建一格
//                cell1.setCellType(CellType.STRING);
//                //商家身份证
//                HSSFCell cell2 = row.createCell(2);//创建一格
//                cell2.setCellType(CellType.STRING);
//                HSSFCell cell3 = row.createCell(5);//创建一格
//                cell3.setCellType(CellType.STRING);
//                //让利比例
//                HSSFCell cell4 = row.createCell(6);//创建一格
//                //让利金额
//                cell4.setCellType(CellType.STRING);
//                //法人姓名
//                HSSFCell cell5 = row.createCell(3);//创建一格
//                cell5.setCellType(CellType.STRING);
//                //法人手机号
//                HSSFCell cell6 = row.createCell(4);//创建一格
//                cell6.setCellType(CellType.STRING);
//
//                cell0.setCellValue(Double.valueOf(record.get("storeId").toString()));
//                cell1.setCellValue(record.get("name").toString());
//                cell2.setCellValue(record.get("identityCard").toString());
//                cell3.setCellValue(Double.valueOf(record.get("totalRangli").toString()));
//                cell4.setCellValue(Double.valueOf(record.get("rangli").toString()));
//                cell5.setCellValue(record.get("legalperson").toString());
//                cell6.setCellValue(record.get("legalpersonPhone").toString());
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
//
//
//
//
//
//
//
////    /**
////     * 处理中列表
////     * @return
////     */
////    @ApiOperation(value = "待审核列表", notes = "id：订单id<br/>businessId：商家id<br/>businessName：商家姓名<br/>idCard：商家身份证<br/>rangliMoney：让利金额<br/>")
////    @RequestMapping(value = "/auditList", method = RequestMethod.GET)
////    public JsonResult auditList(@RequestHeader("token") String token,
////                                @ApiParam(required = true,value = "商家id")@RequestParam("businessId") int businessId,
////                                @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
////                                @ApiParam(required = true,value = "每页条数",defaultValue = "10")@RequestParam("pageSize") int pageSize){
////        try {
////            HashMap<String, Object> paraMap = new HashMap<>();
////            paraMap.put("state",1);
////            paraMap.put("currentPage",currentPage);
////            paraMap.put("pageSize",pageSize);
////
////            Page page = new Page();
////            page.setCurrentPage(currentPage);
////            page.setPageSize(pageSize);
////
////            orderService.selectOrderByState(page, paraMap);
////            return new JsonResult(page);
////        } catch (Exception e) {
////            e.printStackTrace();
////            return super.serverExcptionMsg();
////        }
////    }
////
//
////
////    @ApiOperation(value = "批量审核：根据商家和订单截止时间批量审核", notes = "财务批量审核订单")
////    @RequestMapping(value = "/auditByBIdAndTime",method = RequestMethod.GET)
////    public Object auditByBusinessIdAndEndTime(@RequestHeader("token") String token,
////                                              @ApiParam(required = true,value = "商家id")@RequestParam("businessId") int businessId,
////                                              @ApiParam(required = true,value = "结束时间")@RequestParam("endTime") Date endTime){
////        ParaMap paraMap = super.getParaMap();
////        try {
//////            orderService.auditByBusinessIdAndEndTime(paraMap);
//////            orderService.auditByBusinessIdAndEndTime(paraMap);
//////            orderService.auditByBusinessIdAndEndTime(paraMap);
//////            orderService.auditByBusinessIdAndEndTime(paraMap);
//////            orderService.auditByBusinessIdAndEndTime(paraMap);
//////            orderService.auditByBusinessIdAndEndTime(paraMap);
////            return super.successMsg();
////        } catch (Exception e) {
////            e.printStackTrace();
////            return super.serverExcptionMsg();
////        }
////    }
////
//
//    //财务审核订单
//    //参数：商家id，订单截止时间(审核某个时间点前的所有订单)
//}
