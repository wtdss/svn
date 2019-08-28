package com.sys.bean;

import java.io.Serializable;
import java.util.Date;

public class CountingTask implements Serializable {
    /**
     * 序号
     */
    private Integer countingTaskId;

    /**
     * 盘点时间
     */
    private String countingTaskTime;

    /**
     * 盘点时期（1：初盘，2：复盘）
     */
    private String countingTaskPeriod;

    /**
     * 盘点任务状态（1：未开始，2：盘点中，3：盘点完成，4：已作废）
     */
    private String countingTaskState;

    /**
     * 盘点数量
     */
    private String countingTaskTotalQuantity;

    /**
     * 已盘点数量
     */
    private String countingTaskCountedQuantity;

    /**
     * 已盘点位置不符数量
     */
    private String countingTaskCountedQuantityOutOfPosition;

    /**
     * 未盘点数量
     */
    private String countingTaskUncountedQuantity;

    /**
     * 被其他人盘点数量
     */
    private String countingTaskCountedQuantityByOther;

    /**
     * 备注
     */
    private String countingTaskRemark;

    /**
     * 创建人
     */
    private String countingTaskCreaterId;

    /**
     * 创建人
     */
    private String countingTaskCreaterName;

    /**
     * 修改人
     */
    private String countingTaskEditorId;

    /**
     * 修改人
     */
    private String countingTaskEditorName;

    /**
     * 修改时间
     */
    private String countingTaskEditTime;

    public Integer getCountingTaskId() {
        return countingTaskId;
    }

    public void setCountingTaskId(Integer countingTaskId) {
        this.countingTaskId = countingTaskId;
    }

    public String getCountingTaskTime() {
        return countingTaskTime;
    }

    public void setCountingTaskTime(String countingTaskTime) {
        this.countingTaskTime = countingTaskTime;
    }

    public String getCountingTaskPeriod() {
        return countingTaskPeriod;
    }

    public void setCountingTaskPeriod(String countingTaskPeriod) {
        this.countingTaskPeriod = countingTaskPeriod;
    }

    public String getCountingTaskState() {
        return countingTaskState;
    }

    public void setCountingTaskState(String countingTaskState) {
        this.countingTaskState = countingTaskState;
    }

    public String getCountingTaskTotalQuantity() {
        return countingTaskTotalQuantity;
    }

    public void setCountingTaskTotalQuantity(String countingTaskTotalQuantity) {
        this.countingTaskTotalQuantity = countingTaskTotalQuantity;
    }

    public String getCountingTaskCountedQuantity() {
        return countingTaskCountedQuantity;
    }

    public void setCountingTaskCountedQuantity(String countingTaskCountedQuantity) {
        this.countingTaskCountedQuantity = countingTaskCountedQuantity;
    }

    public String getCountingTaskCountedQuantityOutOfPosition() {
        return countingTaskCountedQuantityOutOfPosition;
    }

    public void setCountingTaskCountedQuantityOutOfPosition(String countingTaskCountedQuantityOutOfPosition) {
        this.countingTaskCountedQuantityOutOfPosition = countingTaskCountedQuantityOutOfPosition;
    }

    public String getCountingTaskUncountedQuantity() {
        return countingTaskUncountedQuantity;
    }

    public void setCountingTaskUncountedQuantity(String countingTaskUncountedQuantity) {
        this.countingTaskUncountedQuantity = countingTaskUncountedQuantity;
    }

    public String getCountingTaskCountedQuantityByOther() {
        return countingTaskCountedQuantityByOther;
    }

    public void setCountingTaskCountedQuantityByOther(String countingTaskCountedQuantityByOther) {
        this.countingTaskCountedQuantityByOther = countingTaskCountedQuantityByOther;
    }

    public String getCountingTaskRemark() {
        return countingTaskRemark;
    }

    public void setCountingTaskRemark(String countingTaskRemark) {
        this.countingTaskRemark = countingTaskRemark;
    }

    public String getCountingTaskCreaterId() {
        return countingTaskCreaterId;
    }

    public void setCountingTaskCreaterId(String countingTaskCreaterId) {
        this.countingTaskCreaterId = countingTaskCreaterId;
    }

    public String getCountingTaskCreaterName() {
        return countingTaskCreaterName;
    }

    public void setCountingTaskCreaterName(String countingTaskCreaterName) {
        this.countingTaskCreaterName = countingTaskCreaterName;
    }

    public String getCountingTaskEditorId() {
        return countingTaskEditorId;
    }

    public void setCountingTaskEditorId(String countingTaskEditorId) {
        this.countingTaskEditorId = countingTaskEditorId;
    }

    public String getCountingTaskEditorName() {
        return countingTaskEditorName;
    }

    public void setCountingTaskEditorName(String countingTaskEditorName) {
        this.countingTaskEditorName = countingTaskEditorName;
    }

    public String getCountingTaskEditTime() {
        return countingTaskEditTime;
    }

    public void setCountingTaskEditTime(String countingTaskEditTime) {
        this.countingTaskEditTime = countingTaskEditTime;
    }
}