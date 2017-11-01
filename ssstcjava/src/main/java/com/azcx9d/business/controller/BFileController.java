package com.azcx9d.business.controller;

import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.exception.FileUpException;
import com.azcx9d.business.util.*;
import com.azcx9d.common.entity.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名：图片上传
 * author：HuangQing
 * createTime：2017-04-03 14:56
 */
@RestController
@RequestMapping(value="/v1/api/fileUp")
@Api(value = "统一文件上传",description="文件上传控制器")
public class BFileController extends HQBaseController {
	
//	@Resource(name="picturesService")
//	private PicturesManager picturesService;

	/**
	 * 文件上传
	 * @param files
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value="/save",method = RequestMethod.POST)
	@ApiOperation(value = "图片上传",notes = "图片上传接口")
	public Object save(@RequestHeader("token") String token,
					   @RequestParam(required=true) MultipartFile[] files){
		Map<String,String> map = new HashMap<String,String>();
		if(files==null || files.length==0){
			return super.errorMsg(1,"图片为空");
		}
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			try {
				String  ffile = DateUtil.getDays(), fileName = "";
				Map pd = new HashMap();
				if (null != file && !file.isEmpty()) {
//					String filePath = FileUpConst.FILEPATHIMG + ffile;		//文件上传路径
					String filePath = SystemConfig.getProperty("picture","business","phycial") + ffile;		//文件写入硬盘的物理路径
//					String filePath ="";		//文件上传路径
					//			String filePath1 ="D:\\business";
					fileName = FileUpload.fileUp(file, filePath, super.get32UUID());				//执行上传
				}else{
					System.out.println("上传失败");
				}

//			============================================================================================================
				//图片上传保存到数据库记录，暂时不加该功能
				//		pd.put("PICTURES_ID", super.get32UUID());			//主键
				//		pd.put("TITLE", "图片");								//标题
				//		pd.put("NAME", fileName);							//文件名
				pd.put("PATH", ffile + "/" + fileName);				//路径
				//		pd.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
				//		pd.put("MASTER_ID", "1");							//附属与
				//		pd.put("BZ", "图片上传");						//备注
				//		Watermark.setWatemark(PathUtil.getClasspath() + UpFileConst.FILEPATHIMG + ffile + "/" + fileName);//加水印
				//		picturesService.save(pd);
//			============================================================================================================


				//		map.put("id", pd.get("PICTURES_ID").toString());
				String readPath = SystemConfig.getProperty("picture","business","url") + pd.get("PATH").toString();	//文件读取路径
//				map.put("path", path);
				//		return AppUtil.returnObject(pd, map);
				String oldPath = map.get("path");
				if (StringUtils.isBlank(oldPath)){
					map.put("result", "ok");
					map.put("path",readPath);
				}else {
					map.put("path",oldPath+"," + readPath);
				}
			}catch (Exception e){
				e.printStackTrace();
				return super.errorMsg(500,"服务器异常");
			}
		}
		return super.successMsg(map);
	}*/

	/**
	 * 文件上传
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "图片、文件上传",notes = "图片、文件上传接口")
	@RequestMapping(value="/fileUpload",method = RequestMethod.POST)
	public Object save(@RequestHeader("token") String token,
					   @ApiParam(required=true,value="文件类型(1:头像 2:商铺 3:商品 4:消费凭证)")@RequestParam String type,
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
			return new JsonResult(2, e.getMessage());
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
				if(org.apache.commons.lang3.StringUtils.isBlank(path)){
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
