package com.customer.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by HuangQing on 2017/4/3 0003 14:39
 */
public class FileUpload {

	/**上传文件
	 * @param file 			//文件对象
	 * @param filePath		//上传路径
	 * @param fileName		//文件名
	 * @return  文件名
	 */
	public static String fileUp(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			}
			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}

	/**网页端上传文件
	 * @param file 			//文件对象
	 * @param filePath		//上传路径
	 * @param fileName		//文件名
	 * @return  文件名
	 */
	public static String fileUpWeb(MultipartFile file, String filePath, String fileName){
		String extName = ""; // 扩展名格式：
		try {
			if (file.getOriginalFilename().lastIndexOf(".") >= 0){
				extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				if (StringUtils.isNotBlank(extName)) {
					extName = extName.toLowerCase();
				}
			}
			copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}
	
	/**
	 * 写文件到当前目录的upload目录中
	 * @param in
	 * @param fileName
	 * @throws IOException
	 */
	private static String copyFile(InputStream in, String dir, String fileName)
			throws IOException {
		File file = mkdirsmy(dir,fileName);
		FileUtils.copyInputStreamToFile(in, file);
		return fileName;
	}
	
	
	/**判断路径是否存在，否：创建此路径
	 * @param dir  文件路径
	 * @param realName  文件名
	 * @throws IOException 
	 */
	public static File mkdirsmy(String dir, String realName) throws IOException{
		File file = new File(dir, realName);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}
	
	
	/**下载网络图片上传到服务器上
	 * @param httpUrl 图片网络地址
	 * @param filePath	图片保存路径
	 * @param myFileName  图片文件名(null时用网络图片原名)
	 * @return	返回图片名称
	 */
	public static String getHtmlPicture(String httpUrl, String filePath , String myFileName) {
		
		URL url;						//定义URL对象url
		BufferedInputStream in;			//定义输入字节缓冲流对象in
		FileOutputStream file;			//定义文件输出流对象file
		try {
			String fileName = null == myFileName?httpUrl.substring(httpUrl.lastIndexOf("/")).replace("/", ""):myFileName; //图片文件名(null时用网络图片原名)
			url = new URL(httpUrl);		//初始化url对象
			in = new BufferedInputStream(url.openStream());									//初始化in对象，也就是获得url字节流
			//file = new FileOutputStream(new File(filePath +"\\"+ fileName));
			file = new FileOutputStream(mkdirsmy(filePath,fileName));
			int t;
			while ((t = in.read()) != -1) {
				file.write(t);
			}
			file.close();
			in.close();
			return fileName;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	/**网页端专用(剪切图片)
	 * @param file 			//文件对象
	 * @param filePath		//上传路径
	 * @param fileName		//文件名
	 * @return  文件名
	 */
	public static String fileUpImgCut(String file, String filePath, String fileName){
		String extName = ".jpg"; // 扩展名格式：jpg

		try {
			generateImage(file, filePath, fileName+extName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		return fileName+extName;
	}

	public static String generateImage(String imgStr, String filePath, String fileName) throws IOException {
		//对字节数组字符串进行Base64解码并生成图片
		BASE64Decoder decoder = new BASE64Decoder();
		OutputStream out = null;
		String str1 = imgStr.split(",")[1];
		try {
			//Base64解码
			byte[] b = decoder.decodeBuffer(str1);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {//调整异常数据
					b[i] += 256;
				}
			}
			//创建文件夹
			File file = mkdirsmy(filePath,fileName);

			//生成jpeg图片
			out = new FileOutputStream(file);
			out.write(b);
			out.flush();
			return fileName;
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	/**上传URL文件
	 * @param url 		//图片url地址
	 * @param type		//上传类型:1 头像
	 * @return  文件名
	 */
	public static String fileUpUrl(String url, String type){
		String writePath = "";
		String readPath = "";
		writePath = SystemConfig.getProperty("file", type,"phycial");	//写入硬盘物理路径
		readPath = SystemConfig.getProperty("file", type,"url");	//文件读取路径
		String  ffile = DateUtil.getDays(), fileName = UuidUtil.get32UUID();
		String filePath = writePath + ffile;		//文件写入硬盘的物理路径
		String extName = ".jpg"; // 扩展名格式：默认为.jpg
		try {
			URL destUrl = new URL(url);
			HttpURLConnection httpUrl = (HttpURLConnection) destUrl.openConnection();
			httpUrl.connect();
			copyFile(httpUrl.getInputStream(), filePath, fileName+extName).replaceAll("-", "");
		} catch (IOException e) {
			System.out.println(e);
		}
		String dbUrl =  ffile + "/" + fileName+extName;	 //路径
		String readUrl = readPath + dbUrl;	//文件读取路径
		return readUrl;
	}

}
