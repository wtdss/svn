package com.sys.bean;

import java.io.Serializable;

public class AssetsAreaAddr implements Serializable {
    /**
     * 序号
     */
    private String assetAddrId;

    /**
     * 地区标识
     */
    private String assetAreaId;

    /**
     * 地区
     */
    private String assetAreaTitle;

    /**
     * 地址
     */
    private String assetAreaAddr;

    /**
     * 备注
     */
    private String assetAreaRemark;

    public String getAssetAddrId() {
        return assetAddrId;
    }

    public void setAssetAddrId(String assetAddrId) {
        this.assetAddrId = assetAddrId;
    }

    public String getAssetAreaId() {
        return assetAreaId;
    }

    public void setAssetAreaId(String assetAreaId) {
        this.assetAreaId = assetAreaId;
    }

    public String getAssetAreaTitle() {
        return assetAreaTitle;
    }

    public void setAssetAreaTitle(String assetAreaTitle) {
        this.assetAreaTitle = assetAreaTitle;
    }

    public String getAssetAreaAddr() {
        return assetAreaAddr;
    }

    public void setAssetAreaAddr(String assetAreaAddr) {
        this.assetAreaAddr = assetAreaAddr;
    }

    public String getAssetAreaRemark() {
        return assetAreaRemark;
    }

    public void setAssetAreaRemark(String assetAreaRemark) {
        this.assetAreaRemark = assetAreaRemark;
    }
}