package com.azcx9d.business.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * Created by HuangQing on 2017/4/2 0002 17:16.
 */
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class BBusinessMember {

    private int businessId;

    private int memberId;

    private Date joinTime;

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}
