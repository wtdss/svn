package com.sys.bean;

import java.io.Serializable;

public class DetailCountView implements Serializable {
	
	/** 序号 */
    private String assetAreaId;

    /** 地区 */
    private String assetAreaTitle;
    
    /** 默认盘点责任人 */
    private String assetDefaultResponsibleId;
    
    /** 默认盘点责任人 */
    private String assetDefaultResponsibleName;

    /** 实际盘点责任人 */
    private String assetActualResponsibleId;
    
    /** 实际盘点责任人 */
    private String assetActualResponsibleName;
	
	/** 盘点数量 */
    private String totalQuantity;

    /** 已盘点数量 */
    private String countedQuantity;

    /** 已盘点位置不符数量 */
    private String countedQuantityOutOfPosition;

    /** 未盘点数量 */
    private String uncountedQuantity;
    
    /** 被其他地区盘点数量 */
    private String countedQuantityByOther;

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

	public String getAssetDefaultResponsibleId() {
		return assetDefaultResponsibleId;
	}

	public void setAssetDefaultResponsibleId(String assetDefaultResponsibleId) {
		this.assetDefaultResponsibleId = assetDefaultResponsibleId;
	}

	public String getAssetDefaultResponsibleName() {
		return assetDefaultResponsibleName;
	}

	public void setAssetDefaultResponsibleName(String assetDefaultResponsibleName) {
		this.assetDefaultResponsibleName = assetDefaultResponsibleName;
	}

	public String getAssetActualResponsibleId() {
		return assetActualResponsibleId;
	}

	public void setAssetActualResponsibleId(String assetActualResponsibleId) {
		this.assetActualResponsibleId = assetActualResponsibleId;
	}

	public String getAssetActualResponsibleName() {
		return assetActualResponsibleName;
	}

	public void setAssetActualResponsibleName(String assetActualResponsibleName) {
		this.assetActualResponsibleName = assetActualResponsibleName;
	}

	public String getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(String totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public String getCountedQuantity() {
		return countedQuantity;
	}

	public void setCountedQuantity(String countedQuantity) {
		this.countedQuantity = countedQuantity;
	}

	public String getCountedQuantityOutOfPosition() {
		return countedQuantityOutOfPosition;
	}

	public void setCountedQuantityOutOfPosition(String countedQuantityOutOfPosition) {
		this.countedQuantityOutOfPosition = countedQuantityOutOfPosition;
	}

	public String getUncountedQuantity() {
		return uncountedQuantity;
	}

	public void setUncountedQuantity(String uncountedQuantity) {
		this.uncountedQuantity = uncountedQuantity;
	}

	public String getCountedQuantityByOther() {
		return countedQuantityByOther;
	}

	public void setCountedQuantityByOther(String countedQuantityByOther) {
		this.countedQuantityByOther = countedQuantityByOther;
	}
}