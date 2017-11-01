package com.azcx9d.business.entity;

import io.swagger.annotations.ApiModel;

/**
 * Created by HuangQing on 2017/4/3 0003 12:50.
 */
@ApiModel
public class BBusinessType {

    private int id;

    private String big_categorie;
    private String big_guid;
    private String small_categorie;
    private String small_guid;
    private String parent_guid;
}
