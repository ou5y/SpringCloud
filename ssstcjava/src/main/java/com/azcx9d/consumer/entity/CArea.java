package com.azcx9d.consumer.entity;

import com.azcx9d.agency.entity.Area;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;

/**
 * Created by chenxl on 2017/4/2 0002.
 */
@ApiModel
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class CArea extends Area {
}
