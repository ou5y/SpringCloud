package com.customer.web.file;

import com.customer.exception.FileUpException;
import com.customer.util.*;
import com.customer.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 类名：图片上传
 * author：HuangQing
 * createTime：2017-05-13 09:50
 */
@RestController
@RequestMapping(value="/api/fileUp")
@Api(value = "统一文件上传",description="文件上传控制器")
@ApiIgnore
public class FileUploadController extends BaseController {

	/**
	 * 文件上传
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
	@ApiOperation(value = "图片上传",notes = "图片上传接口")
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
		return new JsonResult(0,"上传成功",path);
	}


}
