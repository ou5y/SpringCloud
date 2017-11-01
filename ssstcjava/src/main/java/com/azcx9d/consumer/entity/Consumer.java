package com.azcx9d.consumer.entity;

import com.azcx9d.user.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
@ApiModel
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Consumer extends User {

    @ApiModelProperty(value = "上级id")
    private long parentId;

    @ApiModelProperty(value = "消费者id")
    private long licenseId;//license


    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public Consumer(){

    }
    public Consumer(String phone, String password){
        super();
        super.setPhone(phone);
        super.setPassword(password);
    }
}
