package com.azcx9d.pay.util;

import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPSer {

    protected String host="sftp.nbcip.com";//sit服务器ip
    protected String username="001";//用户名
    protected String password="rtaV@I~F";//密码
    protected int port = 10888;//默认的sftp端口号
    
    /**
     * 获取连接
     */
    public ChannelSftp connectSFTP() throws JSchException {
        JSch jsch = new JSch();
        Channel channel = null;
        Session session = jsch.getSession(username, host, port);
        if (password != null && !"".equals(password)) {
            session.setPassword(password);
        }
        Properties sshConfig = new Properties();
        sshConfig.put("StrictHostKeyChecking", "no");
        session.setConfig(sshConfig);
        //session.setTimeout(timeout);
        session.setServerAliveInterval(92000);
        session.connect();
        channel = session.openChannel("sftp");
        channel.connect();
      
        return (ChannelSftp) channel;
    }
    
    /**
     * 上传文件
     * @param directory        上传的目录
     * @param uploadFile        要上传的文件
     * @param sftp
     */
    public void upload(String directory, String uploadFile, String fileName, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            URL destUrl = new URL(uploadFile);
            HttpURLConnection httpUrl = (HttpURLConnection) destUrl.openConnection();
            httpUrl.connect();
            sftp.put(httpUrl.getInputStream(), fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/**
 * @throws SftpException 
 * 
 */
    public void ls(String directory,ChannelSftp sftp) throws SftpException
    {
    	sftp.cd(directory);
    	Vector v=sftp.ls("*.*");
//        for(int i=0;i<v.size();i++)
//        {
//         System.out.println(v.get(i));
//        }
    }
    /**
     * 下载文件
     * @param directory      下载目录
     * @param downloadFile   下载的文件
     * @param saveFile       存在本地的路径
     * @param sftp
     */
    public void download(String directory, String downloadFile,
            String saveFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.get(downloadFile,saveFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     * @param directory   要删除文件所在目录
     * @param deleteFile  要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void disconnected(ChannelSftp sftp){
        if (sftp != null) {
            try {
                sftp.getSession().disconnect();
            } catch (JSchException e) {
                e.printStackTrace();
            }
            sftp.disconnect();
        }
    }
}