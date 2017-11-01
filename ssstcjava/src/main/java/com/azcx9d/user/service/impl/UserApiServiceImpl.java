package com.azcx9d.user.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.azcx9d.business.service.PhoneCodeService;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.azcx9d.agency.dao.AgencyLoginDao;
import com.azcx9d.agency.dto.LoginExecution;
import com.azcx9d.agency.entity.Agency;
import com.azcx9d.agency.entity.BankCard;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.business.util.DateUtil;
import com.azcx9d.business.util.FileUpload;
import com.azcx9d.common.SendMessage.MobileServerUtils;
import com.azcx9d.common.entity.QRCode;
import com.azcx9d.common.token.TokenManagerDao;
import com.azcx9d.common.util.RandomUtil;
import com.azcx9d.common.util.SystemConfig;
import com.azcx9d.user.dao.UserDao;
import com.azcx9d.user.entity.SystemMessage;
import com.azcx9d.user.entity.User;
import com.azcx9d.user.entity.UserSuggest;
import com.azcx9d.user.entity.YunpianReaultEntity;
import com.azcx9d.user.service.UserApiService;

/**
 * Created by fangbaoyan on 2017/3/14.
 */

@Service("userApiService")
public class UserApiServiceImpl implements UserApiService {

	
    @Autowired
    private AgencyLoginDao dao;
    
    @Autowired
    private TokenManagerDao tokenDao;
    
    @Autowired
    private UserDao userDao;

	@Autowired
	private PhoneCodeService phoneCodeService;
    
	private static final String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";


	public LoginExecution<Agency> login(Agency agency) throws Exception {

		int count = dao.selectByIdentify(agency.getPhone());
		if (count == 0) {
			LoginExecution<Agency> loginExecution = new LoginExecution<Agency>(2, "用户不存在");
			return loginExecution;
		}else {
			Agency outAgency = dao.selectUser(agency);

			if (outAgency != null) {

				TokenModel tokenModel = tokenDao.createToken(outAgency.getId());

				String token = tokenModel.getUserId() + "_" + tokenModel.getToken();
				outAgency.setToken(token);

//				Map<String, Object> lovePercent = userDao.queryLovePercent();
//				if (lovePercent != null && lovePercent.size() > 0) {
//					outAgency.setShanxin(Double.parseDouble((lovePercent.get("percent").toString())));            //设置大盘善心比例
//				} else {
//					outAgency.setShanxin(0.0);            //设置大盘善心比例
//				}

				LoginExecution<Agency> loginExecution = new LoginExecution<Agency>(0, "登录成功", outAgency);
				return loginExecution;
			} else {
				LoginExecution<Agency> loginExecution = new LoginExecution<Agency>(1, "用户名密码错误");
				return loginExecution;
			}
		}
	}

	@Override
	public Agency queryUserInfo(long id) throws Exception {

		return dao.selectUserById(id);
	}

	@Override
	public int doRegister(User user) throws Exception {
		
		return dao.insertUser(user);
	}
	
	@Override
	public YunpianReaultEntity identifyingCode(String phone) throws ParseException, IOException {
		int randNum = (int)((Math.random()*9+1)*100000);
		String text = "【全团了】您的验证码是" + randNum + "。如非本人操作，请忽略本短信";
		YunpianReaultEntity yre = new YunpianReaultEntity();
		yre = MobileServerUtils.singleSend(text, phone,randNum);
		phoneCodeService.setPhoneAndCode(phone, randNum+"");
		return yre;
	}

	@Override
	public YunpianReaultEntity identifyingCodeByphone(String phone, int randNum) throws ParseException, IOException {
		String text = "【全团了】您的验证码是" + randNum + "。如非本人操作，请忽略本短信";
		YunpianReaultEntity yre = new YunpianReaultEntity();
		yre = MobileServerUtils.singleSend(text, phone,randNum);
		phoneCodeService.setPhoneAndCode(phone, randNum+"");
		return yre;
	}

	private static int getRandNum(int min, int max) {
		int randNum = min + (int)(Math.random() * ((max - min) + 1));
		return randNum;
	}
	
	
	// 根据id查询用户的银行卡
	@Override
	public List<BankCard> queryMyBankCard(long id){
		return userDao.queryMyBankCard(id);
	}
	
	// 新增银行卡
	@Override
	@Transactional
	public int addBankCard(HashMap<String, String> params){
		return userDao.addBankCard(params);
	}
	
	// 解除银行卡绑定关系
	@Override
	public int deleteBankCard(HashMap<String, String> params){
		return userDao.deleteBankCard(params);
	}
	
	// 根据银行卡id查询银行卡信息
	@Override
	public BankCard findById(long id){
		return userDao.findBankCardById(id);
	}
	
	// 查找银行卡
	public BankCard findBankCard(Map<String,String> params){
		return userDao.findBankCard(params);
	}

	@Override
	public String getMyQRCode(User user) throws Exception {
		QRCode code = dao.selectQRCode(user);
		if (code == null)
			return "-1";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d = format.format(code.getSqsj());
		Date date = format.parse(d);
		Long s = (System.currentTimeMillis() - date.getTime()) / (1000 * 60 * 60 * 24);
		String result = "";
		if (s <= 30)
			return url + code.getTicket();
		else {// 失效重新生成
			String getUrl = url + code.getTicket();
			// 根据地址获取请求
			HttpGet request = new HttpGet(getUrl);// 这里发送get请求
			
			CloseableHttpClient client = HttpClientBuilder.create().build();

			// 通过请求对象获取响应对象
			HttpResponse response = client.execute(request);

			// 判断网络连接状态码是否正常(0--200都数正常)
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			}
			if (client!=null) 
			client.close();

		}
		return result;
	}
	
	// 上传身份证正反面
	@Override
	public int uploadIdentityCard(CommonsMultipartFile files[],long id) throws Exception{
		int count = 0;
		Map<String,String> filePath = new HashMap<String, String>();				//存储文件上传后的地址
		for (int i = 0; i < files.length; i++) {
			CommonsMultipartFile file = files[i];
			if (file != null) {
				String  ffile = DateUtil.getDays(), fileName = "";
				
				String writePath = SystemConfig.getProperty("picture","business","phycial") + ffile;
				fileName = FileUpload.fileUp(file, writePath, RandomUtil.get32UUID());				//执行上传
				String relativePath = SystemConfig.getProperty("picture","business","url") + ffile + "/" + fileName;	//文件读取路径
				switch (i) {
				case 0:
					filePath.put("identityCardUp", relativePath);
					break;
				case 1:
					filePath.put("identityCardDown", relativePath);
					break;
				default:
					break;
				}
			} else {
				return 0;
			}
		}
		User user = userDao.selectUserById(id);
		user.setIdentityCardUp(filePath.get("identityCardUp"));
		user.setIdentityCardDown(filePath.get("identityCardDown"));
		count = userDao.updateUser(user);
		return count;
	}
	
	// 查询推荐记录
	@Override
	public List<Map<String,Object>> queryRecommend(Map<String,String> params){
		return userDao.queryRecommend(params);
	}
	
	// 修改交易密码
	public int updateTransPwd(Map<String,String> params){
		return userDao.updateTransPwd(params);
	}
	
	// 查询用户密码
	public Map<String,String> queryPassword(Map<String,String> params){
		return userDao.queryPassword(params);
	}

	@Override
	public int updateLoginPwd(Map<String, String> params) {
		return userDao.updateLoginPwd(params);
	}
	
	// 查询用户交易密码
	@Override
	public Agency queryTransPwd(long id) throws Exception{
		return userDao.selectTransPwd(id);
	}
	
	// 查询系统消息
	@Override
	public List<SystemMessage> queryMessage(Map<String,Object> params){
		return userDao.queryMessage(params);
	}
	
	// 更新消息阅读状态
	@Override
	public int updateReadState (Map<String,Object> params){
		return userDao.updateReadState(params);
	}
	
	// 查询用户的善心善点
	@Override
	public Map<String,String> queryShanxin(Map<String,String> params){
		return userDao.queryShanxin(params);
	}
	
	// 添加用户建议反馈
	@Override
	public int addUserSuggest(UserSuggest suggest){
		return userDao.addUserSuggest(suggest);
	}
	
	// 查询更新版本信息
	@Override
	public Map<String,Object> queryVersionInfo(Map<String,Object> params){
		return userDao.queryVersionInfo(params);
	}
	
	// 根据手机号码
	@Override
    public Map<String,Object> selectByPhone(Map<String,Object> params){
    	return userDao.selectByPhone(params);
    }
    
    // 保存用户信息
	@Override
    public int saveUser(Map<String,Object> params){
    	return userDao.saveUser(params);
    }
    
}
