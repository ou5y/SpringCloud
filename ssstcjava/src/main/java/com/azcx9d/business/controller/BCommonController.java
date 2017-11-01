package com.azcx9d.business.controller;

import com.azcx9d.business.exception.FileUpException;
import com.azcx9d.business.util.DateUtil;
import com.azcx9d.business.util.FileUpload;
import com.azcx9d.business.util.SystemConfig;
import com.azcx9d.business.util.UuidUtil;
import com.azcx9d.common.entity.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 类名：图片上传
 * author：HuangQing
 * createTime：2017-04-03 14:56
 */
@RestController
@RequestMapping(value = "v1/bApi/common")
@Api(value = "公用接口", description = "公用接口")
public class BCommonController {

    /**
     * 网页端专用(图片、文件上传)
     *
     * @param file
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "网页端专用(图片、文件上传)", notes = "网页端专用(图片、文件上传)")
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    @ApiIgnore
    public Object fileUpload(@ApiParam(value = "图片", required = true) @RequestParam MultipartFile file,
                             @ApiParam(value = "图片类型(1:头像 2:商铺 3:商品 4:消费凭证)", required = true) @RequestParam String imgType) {

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
                fileName = FileUpload.fileUp(file, filePath, UuidUtil.get32UUID());                //执行上传
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

}