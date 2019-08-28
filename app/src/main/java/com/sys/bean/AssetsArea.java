package com.sys.bean;

import java.io.Serializable;

public class AssetsArea implements Serializable {
    /**
     * 序号
     */
    private String assetAreaId;

    /**
     * 地区
     */
    private String assetAreaTitle;

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
}