package com.azcx9d.system.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by HuangQing on 2017/4/6 0006 16:33.
 */
@ApiModel("系统后台管理员用户")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class SSystemUser {

    @ApiModelProperty("主键id")
    private int id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("姓名")
    private String realName;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("最后登录时间")
    private Date lastLogin;

    @ApiModelProperty("登录ip")
    private String ip;

    @ApiModelProperty("用户状态")
    private String status;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("用户手机")
    private String phone;

    @ApiModelProperty("部门id")
    private String departmentId;

    @ApiModelProperty("token")
    private String token;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
