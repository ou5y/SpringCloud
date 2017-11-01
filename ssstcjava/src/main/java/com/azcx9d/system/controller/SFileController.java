package com.azcx9d.system.controller;

import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.util.DateUtil;
import com.azcx9d.business.util.FileUpload;
import com.azcx9d.common.util.SystemConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名：图片上传
 * author：HuangQing
 * createTime：2017-04-03 14:56
 */
@RestController
@RequestMapping(value="/sApi/fileUp")
@Api(value = "统一文件上传",description="文件上传控制器")
@ApiIgnore
public class SFileController extends HQBaseController {
	
//	@Resource(name="picturesService")
//	private PicturesManager picturesService;

	/**
	 * 文件上传
	 * @param files
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/save",method = RequestMethod.POST)
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
	}
	

}
