package com.customer.web;

import com.alibaba.fastjson.JSONArray;
import com.customer.entity.YunpianReaultEntity;
import com.customer.entity.index.CBanner;
import com.customer.exception.FileUpException;
import com.customer.service.PhoneCodeService;
import com.customer.service.index.StrollAroundService;
import com.customer.util.*;
import com.customer.util.myJson.MyJsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fangbaoyan on 2017/5/8.
 */
@RestController
@RequestMapping(value = "/v1/api/common")
@Api(value = "公共接口",description = "系统公共接口")
public class CommonController {
    @Autowired
    private StrollAroundService strollAroundService;

    @Autowired
    private PhoneCodeService phoneCodeService;

    @ApiOperation(notes = "获取验证码",value = "获取验证码")
    @RequestMapping(value = "/{phone}/getCode", method = RequestMethod.GET)
    public JsonResult identifyingCode(@PathVariable("phone") String phone
                                      )
    {

        YunpianReaultEntity yre;
        try {
            yre = getCode(phone);
            if(yre.getCode()!=0)
            {
                if(yre.getCode()==33){yre.setMsg("您操作太频繁了，请稍后再重新获取验证码！");}
                if(yre.getCode()==3){yre.setMsg("短信发送失败,请联系客服，400-9612606");}
                return new JsonResult(yre.getCode(),yre.getMsg());
            }else
            {
                return new JsonResult(0,"OK");
            }
        } catch (ParseException e) {
            return new JsonResult(2,"服务器出错啦");
        } catch (IOException e) {
            return new JsonResult(2,"网络不太好，稍后在试试");
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }


    }

    @ApiOperation(notes = "校验验证码",value = "校验验证码")
    @RequestMapping(value = "/{phone}/{code}/checkCode", method = RequestMethod.GET)
    public JsonResult checkCode(@PathVariable("phone")String phone,@PathVariable("code")String code)
    {


        if (phoneCodeService.checkPhoneCode(phone, code)) {
            return new 	JsonResult(0,"ok");
        }
        return new 	JsonResult(2,"验证码错误");

    }

    @ApiOperation(value = "根据手机号码获取短信验证码(网页端专用,APP勿用)",notes = "根据手机号码获取短信验证码(网页端专用,APP勿用)")
    @RequestMapping(value = "/getPhoneCode",method = RequestMethod.GET)
    public JsonResult getPhoneCode(@ApiParam(value = "手机号码") @RequestParam String phone){
        if(phone.length()!=11){
            return new JsonResult(2,"手机号码不正确，请重新输入");
        }
        //YunpianReaultEntity yre;
        try {
            int randNum = (int)((Math.random()*9+1)*100000);
            //yre = getCodeByPhone(phone, randNum);
            phoneCodeService.setPhoneAndCode(phone, randNum+"");
            return new JsonResult(0,"OK", randNum);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 文件上传
     * @param files
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "图片、文件上传",notes = "图片、文件上传接口")
    @RequestMapping(value="/fileUpload",method = RequestMethod.POST)
    public Object save(@RequestHeader("token") String token,
                       @ApiParam(required=true,value="文件类型")@RequestParam String type,
                       @RequestParam(required=true) MultipartFile[] files){
        if(StringUtils.isBlank(type)){
            return new JsonResult(2,"图片类型type不能为空");
        }
        if(files==null || files.length==0){
            return new JsonResult(2,"图片不能为空");
        }
        String writePath = "";
        String readPath = "";
        try {
            writePath = SystemConfig.getProperty("file", type,"phycial");	//写入硬盘物理路径
            readPath = SystemConfig.getProperty("file", type,"url");	//文件读取路径
        } catch (FileUpException e) {
            e.printStackTrace();
            return new JsonResult(2,e.getMessage());
        }

        String path = "";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            try {
                String  ffile = DateUtil.getDays(), fileName = "";
                if (null != file && !file.isEmpty()) {
                    String filePath = writePath + ffile;		//文件写入硬盘的物理路径
                    fileName = FileUpload.fileUp(file, filePath, UuidUtil.get32UUID());				//执行上传
                }else{
                    System.out.println("上传失败");
                }
                String dbUrl =  ffile + "/" + fileName;				//路径
                String readUrl = readPath + dbUrl;	//文件读取路径
                if(StringUtils.isBlank(path)){
                    path = readUrl;
                } else {
                    path += "," + readUrl;
                }
            }catch (Exception e){
                e.printStackTrace();
                return new JsonResult(2,"服务端异常");
            }
        }
        if (type.equals("3"))//添加banner插入数据
        {
            List<CBanner> cBannerList = new ArrayList<>();
            String paths[] = path.split(",");
            for (int i = 0; i < files.length; i++) {
                CBanner cBanner = new CBanner();
                cBanner.setName("banner"+RandomUtil.getRandomString(6));
                cBanner.setType(Integer.valueOf(type));
                cBanner.setPic(paths[i]);
                cBannerList.add(cBanner);
            }


            Thread t =new Thread(()->strollAroundService.addBanner(cBannerList));

            t.start();
        }

        return new JsonResult(0,"上传成功",path);
    }



    private YunpianReaultEntity getCode(String phone) throws ParseException, IOException {
        int randNum = (int)((Math.random()*9+1)*100000);
        String text = "【全团了】您的验证码是"+String.valueOf(randNum)+"。如非本人操作，请忽略本短信";
        YunpianReaultEntity yre = new YunpianReaultEntity();
        yre = MobileServerUtils.singleSend(text, phone,randNum);
        phoneCodeService.setPhoneAndCode(phone, randNum+"");
        return yre;
    }

    private YunpianReaultEntity getCodeByPhone(String phone, int randNum) throws ParseException, IOException {
        String text = "【全团了】您的验证码是"+String.valueOf(randNum)+"。如非本人操作，请忽略本短信";
        YunpianReaultEntity yre = new YunpianReaultEntity();
        yre = MobileServerUtils.singleSend(text, phone,randNum);
        phoneCodeService.setPhoneAndCode(phone, randNum+"");
        return yre;
    }

    /**
     * 网页端专用(图片、文件上传)
     *
     * @param file
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "网页端专用(图片、文件上传)", notes = "网页端专用(图片、文件上传)")
    @RequestMapping(value = "/fileUploadWeb", method = RequestMethod.POST)
    @ApiIgnore
    public Object fileUpload(@ApiParam(value = "图片", required = true) @RequestParam MultipartFile file,
                             @ApiParam(value = "图片类型(3:商品图片)", required = true) @RequestParam String imgType) {

        if("3".equals(imgType)){
            imgType = "0";
        }
        String writePath = "";
        String readPath = "";
        try {
            writePath = SystemConfig.getProperty("file", imgType, "phycial");    //写入硬盘物理路径
            readPath = SystemConfig.getProperty("file", imgType, "url");    //文件读取路径
        } catch (FileUpException e) {
            e.printStackTrace();
            return new JsonResult(0, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(0, e.getMessage());
        }

        String path = "";
        try {
            String ffile = DateUtil.getDays(), fileName = "";
            if (null != file && !file.isEmpty()) {
                String filePath = writePath + ffile;        //文件写入硬盘的物理路径
                fileName = FileUpload.fileUpWeb(file, filePath, UuidUtil.get32UUID());                //执行上传
            } else {
                System.out.println("上传失败");
            }
            String dbUrl = ffile + "/" + fileName;                //路径
            path = readPath + dbUrl;    //文件读取路径

        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(0, "服务端异常");
        }
        return new JsonResult(1, "上传成功", path);
    }

    /**
     * 网页端专用(剪切图片)
     *
     * @param file
     * @param imgType
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "网页端专用(剪切图片)", notes = "网页端专用(剪切图片)")
    @RequestMapping(value = "/fileUploadWebCutImg", method = RequestMethod.POST)
    @ApiIgnore
    public Object fileUploadCutImg(@ApiParam(value = "图片", required = true) @RequestParam String file,
                                   @ApiParam(value = "图片类型(3:商品图片)", required = true) @RequestParam String imgType) {

        if ("3".equals(imgType)) {
            imgType = "0";
        }
        String writePath = "";
        String readPath = "";
        try {
            writePath = SystemConfig.getProperty("file", imgType, "phycial");    //写入硬盘物理路径
            readPath = SystemConfig.getProperty("file", imgType, "url");    //文件读取路径
        } catch (FileUpException e) {
            e.printStackTrace();
            return new JsonResult(0, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(0, e.getMessage());
        }

        String path = "";
        try {
            String ffile = DateUtil.getDays(), fileName = "";
            if (StringUtils.isNotBlank(file)) {
                String filePath = writePath + ffile;        //文件写入硬盘的物理路径
                fileName = FileUpload.fileUpImgCut(file, filePath, UuidUtil.get32UUID());                //执行上传
            } else {
                System.out.println("上传失败");
            }
            String dbUrl = ffile + "/" + fileName;                //路径
            path = readPath + dbUrl;    //文件读取路径

        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(0, "服务端异常");
        }
        return new JsonResult(1, "上传成功", path);
    }

    @ApiOperation(value = "获取省市区编号", notes = "获取省市区编号")
    @RequestMapping(value = "/getCityCode", method = RequestMethod.GET)
    public JsonResult getCityCode() {
        try {
            JSONArray jsonContext = MyJsonUtil.getJsonContext();
            if (null != jsonContext) {
                return new JsonResult(0, "查询成功", jsonContext);
            } else {
                return new JsonResult(4, "暂无数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

}
