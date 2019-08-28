package com.sys.bean;

import java.io.Serializable;

public class AssetDetail implements Serializable {
    /**
     * 序号
     */
    private Integer assetId;

    /**
     * 资产分类（1：低耗，2：固定资产，3：账外低耗）
     */
    private Integer assetType;

    /**
     * 资产分类（1：低耗，2：固定资产，3：账外低耗）
     */
    private String assetTypeTitle;

    /**
     * 主资产描述
     */
    private String assetTitle;

    /**
     * 主资产图片
     */
    private String assetImgPath;

    /**
     * 成本中心标识
     */
    private Integer assetDeptId;

    /**
     * 成本中心
     */
    private String assetDeptCode;

    /**
     * 工厂/部门
     */
    private String assetDeptTitle;

    /**
     * 存货号
     */
    private String assetInventoryNo;

    /**
     * 资产资本化日期
     */
    private String assetString;

    /**
     * 工厂代码
     */
    private String assetFactoryCode;

    /**
     * 公司代码
     */
    private String assetCompanyCode;

    /**
     * 主资产号
     */
    private String assetMainCoding;

    /**
     * 总数量
     */
    private Integer assetSecNum;

    /**
     * 显示行数
     */
    private Integer assetSecRowNum;

    /**
     * 次级编码
     */
    private String assetSecCoding;

    /**
     * 资产描述（规格型号）
     */
    private String assetDescription;

    /**
     * 房间号工号
     */
    private String assetRoom;

    /**
     * 房间号
     */
    private String assetRoomName;

    /**
     * 地区标识
     */
    private Integer assetAreaId;

    /**
     * 地区
     */
    private String assetAreaTitle;

    /**
     * 地址标识
     */
    private Integer assetAddrId;

    /**
     * 地址
     */
    private String assetAreaAddr;

    /**
     * 资产状态（1：正常使用，2：闲置，3：待报废，4：报废）
     */
    private Integer assetState;

    /**
     * 资产状态（1：正常使用，2：闲置，3：待报废，4：报废）
     */
    private String assetStateTitle;

    /**
     * 未使用原因
     */
    private String assetUnusedCause;

    /**
     * 二级资产管理员工号
     */
    private String assetSecManagerId;

    /**
     * 二级资产管理员
     */
    private String assetSecManagerName;

    /**
     * 备注
     */
    private String assetRemark;

    /**
     * 创建人
     */
    private String assetCreaterId;

    /**
     * 创建人
     */
    private String assetCreaterName;

    /**
     * 创建时间
     */
    private String assetCreateTime;

    /**
     * 修改人
     */
    private String assetEditorId;

    /**
     * 修改人
     */
    private String assetEditorName;

    /**
     * 修改时间
     */
    private String assetEditTime;

    /**
     * 盘点状态（1：已盘点，2：已盘点位置不符，3：被其他人盘点，0：未盘点）
     */
    private Integer countingState;

    /**
     * 盘点状态（1：已盘点，2：已盘点位置不符，3：被其他人盘点，0：未盘点）
     */
    private String countingStateTitle;

    /**
     * 实际盘点地区标识
     */
    private Integer assetActualAreaId;

    /**
     * 实际盘点地区
     */
    private String assetActualAreaTitle;

    /**
     * 盘点地址标识
     */
    private Integer assetActualAddrId;

    /**
     * 实际盘点地址
     */
    private String assetActualAreaAddr;

    /**
     * 默认盘点人
     */
    private String assetDefaultResponsibleId;

    /**
     * 默认盘点人
     */
    private String assetDefaultResponsibleName;

    /**
     * 实际盘点人
     */
    private String assetActualResponsibleId;

    /**
     * 实际盘点人
     */
    private String assetActualResponsibleName;

    /**
     * 盘点图片标识
     */
    private Integer assetImgId;

    /**
     * 盘点时间
     */
    private String countedTime;

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

    public String getAssetTypeTitle() {
        return assetTypeTitle;
    }

    public void setAssetTypeTitle(String assetTypeTitle) {
        this.assetTypeTitle = assetTypeTitle;
    }

    public String getAssetTitle() {
        return assetTitle;
    }

    public void setAssetTitle(String assetTitle) {
        this.assetTitle = assetTitle;
    }

    public String getAssetImgPath() {
        return assetImgPath;
    }

    public void setAssetImgPath(String assetImgPath) {
        this.assetImgPath = assetImgPath;
    }

    public Integer getAssetDeptId() {
        return assetDeptId;
    }

    public void setAssetDeptId(Integer assetDeptId) {
        this.assetDeptId = assetDeptId;
    }

    public String getAssetDeptCode() {
        return assetDeptCode;
    }

    public void setAssetDeptCode(String assetDeptCode) {
        this.assetDeptCode = assetDeptCode;
    }

    public String getAssetDeptTitle() {
        return assetDeptTitle;
    }

    public void setAssetDeptTitle(String assetDeptTitle) {
        this.assetDeptTitle = assetDeptTitle;
    }

    public String getAssetInventoryNo() {
        return assetInventoryNo;
    }

    public void setAssetInventoryNo(String assetInventoryNo) {
        this.assetInventoryNo = assetInventoryNo;
    }

    public String getAssetString() {
        return assetString;
    }

    public void setAssetString(String assetString) {
        this.assetString = assetString;
    }

    public String getAssetFactoryCode() {
        return assetFactoryCode;
    }

    public void setAssetFactoryCode(String assetFactoryCode) {
        this.assetFactoryCode = assetFactoryCode;
    }

    public String getAssetCompanyCode() {
        return assetCompanyCode;
    }

    public void setAssetCompanyCode(String assetCompanyCode) {
        this.assetCompanyCode = assetCompanyCode;
    }

    public String getAssetMainCoding() {
        return assetMainCoding;
    }

    public void setAssetMainCoding(String assetMainCoding) {
        this.assetMainCoding = assetMainCoding;
    }

    public Integer getAssetSecNum() {
        return assetSecNum;
    }

    public void setAssetSecNum(Integer assetSecNum) {
        this.assetSecNum = assetSecNum;
    }

    public Integer getAssetSecRowNum() {
        return assetSecRowNum;
    }

    public void setAssetSecRowNum(Integer assetSecRowNum) {
        this.assetSecRowNum = assetSecRowNum;
    }

    public String getAssetSecCoding() {
        return assetSecCoding;
    }

    public void setAssetSecCoding(String assetSecCoding) {
        this.assetSecCoding = assetSecCoding;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public String getAssetRoom() {
        return assetRoom;
    }

    public void setAssetRoom(String assetRoom) {
        this.assetRoom = assetRoom;
    }

    public String getAssetRoomName() {
        return assetRoomName;
    }

    public void setAssetRoomName(String assetRoomName) {
        this.assetRoomName = assetRoomName;
    }

    public Integer getAssetAreaId() {
        return assetAreaId;
    }

    public void setAssetAreaId(Integer assetAreaId) {
        this.assetAreaId = assetAreaId;
    }

    public String getAssetAreaTitle() {
        return assetAreaTitle;
    }

    public void setAssetAreaTitle(String assetAreaTitle) {
        this.assetAreaTitle = assetAreaTitle;
    }

    public Integer getAssetAddrId() {
        return assetAddrId;
    }

    public void setAssetAddrId(Integer assetAddrId) {
        this.assetAddrId = assetAddrId;
    }

    public String getAssetAreaAddr() {
        return assetAreaAddr;
    }

    public void setAssetAreaAddr(String assetAreaAddr) {
        this.assetAreaAddr = assetAreaAddr;
    }

    public Integer getAssetState() {
        return assetState;
    }

    public void setAssetState(Integer assetState) {
        this.assetState = assetState;
    }

    public String getAssetStateTitle() {
        return assetStateTitle;
    }

    public void setAssetStateTitle(String assetStateTitle) {
        this.assetStateTitle = assetStateTitle;
    }

    public String getAssetUnusedCause() {
        return assetUnusedCause;
    }

    public void setAssetUnusedCause(String assetUnusedCause) {
        this.assetUnusedCause = assetUnusedCause;
    }

    public String getAssetSecManagerId() {
        return assetSecManagerId;
    }

    public void setAssetSecManagerId(String assetSecManagerId) {
        this.assetSecManagerId = assetSecManagerId;
    }

    public String getAssetSecManagerName() {
        return assetSecManagerName;
    }

    public void setAssetSecManagerName(String assetSecManagerName) {
        this.assetSecManagerName = assetSecManagerName;
    }

    public String getAssetRemark() {
        return assetRemark;
    }

    public void setAssetRemark(String assetRemark) {
        this.assetRemark = assetRemark;
    }

    public String getAssetCreaterId() {
        return assetCreaterId;
    }

    public void setAssetCreaterId(String assetCreaterId) {
        this.assetCreaterId = assetCreaterId;
    }

    public String getAssetCreaterName() {
        return assetCreaterName;
    }

    public void setAssetCreaterName(String assetCreaterName) {
        this.assetCreaterName = assetCreaterName;
    }

    public String getAssetCreateTime() {
        return assetCreateTime;
    }

    public void setAssetCreateTime(String assetCreateTime) {
        this.assetCreateTime = assetCreateTime;
    }

    public String getAssetEditorId() {
        return assetEditorId;
    }

    public void setAssetEditorId(String assetEditorId) {
        this.assetEditorId = assetEditorId;
    }

    public String getAssetEditorName() {
        return assetEditorName;
    }

    public void setAssetEditorName(String assetEditorName) {
        this.assetEditorName = assetEditorName;
    }

    public String getAssetEditTime() {
        return assetEditTime;
    }

    public void setAssetEditTime(String assetEditTime) {
        this.assetEditTime = assetEditTime;
    }

    public Integer getCountingState() {
        return countingState;
    }

    public void setCountingState(Integer countingState) {
        this.countingState = countingState;
    }

    public String getCountingStateTitle() {
        return countingStateTitle;
    }

    public void setCountingStateTitle(String countingStateTitle) {
        this.countingStateTitle = countingStateTitle;
    }

    public Integer getAssetActualAreaId() {
        return assetActualAreaId;
    }

    public void setAssetActualAreaId(Integer assetActualAreaId) {
        this.assetActualAreaId = assetActualAreaId;
    }

    public String getAssetActualAreaTitle() {
        return assetActualAreaTitle;
    }

    public void setAssetActualAreaTitle(String assetActualAreaTitle) {
        this.assetActualAreaTitle = assetActualAreaTitle;
    }

    public Integer getAssetActualAddrId() {
        return assetActualAddrId;
    }

    public void setAssetActualAddrId(Integer assetActualAddrId) {
        this.assetActualAddrId = assetActualAddrId;
    }

    public String getAssetActualAreaAddr() {
        return assetActualAreaAddr;
    }

    public void setAssetActualAreaAddr(String assetActualAreaAddr) {
        this.assetActualAreaAddr = assetActualAreaAddr;
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

    public Integer getAssetImgId() {
        return assetImgId;
    }

    public void setAssetImgId(Integer assetImgId) {
        this.assetImgId = assetImgId;
    }

    public String getCountedTime() {
        return countedTime;
    }

    public void setCountedTime(String countedTime) {
        this.countedTime = countedTime;
    }
}