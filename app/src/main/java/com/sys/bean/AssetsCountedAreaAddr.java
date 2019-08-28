package com.sys.bean;

import java.io.Serializable;

public class AssetsCountedAreaAddr implements Serializable {
	/**  */
	private String countedUserId;

	/** 序号 */
	private String assetAreaId;

	/** 地区 */
	private String assetAreaTitle;

	/** 序号 */
	private String assetAddrId;

	/** 地址 */
	private String assetAreaAddr;

	private static final long serialVersionUID = 1L;

	public String getCountedUserId() {
		return countedUserId;
	}

	public void setCountedUserId(String countedUserId) {
		this.countedUserId = countedUserId;
	}

	public String getAssetAreaId() {
		return assetAreaId;
	}

	public void setAssetAreaId(String assetAreaId) {
		this.assetAreaId = assetAreaId;
	}

	public String getAssetAddrId() {
		return assetAddrId;
	}

	public void setAssetAddrId(String assetAddrId) {
		this.assetAddrId = assetAddrId;
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
}