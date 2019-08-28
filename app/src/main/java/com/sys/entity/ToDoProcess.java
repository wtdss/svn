package com.sys.entity;

import java.io.Serializable;

public class ToDoProcess implements Serializable {
    public String processId;
    public String taskId;
    public String taskName;
    public String recieveTime;
    public String initiator;
    public String processStartTime;
    public String bussinessKey;
    public String activiName;

    public ToDoProcess() {
    }


    public ToDoProcess(String processId, String taskId, String taskName, String recieveTime, String initiator, String processStartTime, String bussinessKey, String activiName) {
        this.processId = processId;
        this.taskId = taskId;
        this.taskName = taskName;
        this.recieveTime = recieveTime;
        this.initiator = initiator;
        this.processStartTime = processStartTime;
        this.bussinessKey = bussinessKey;
        this.activiName = activiName;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getRecieveTime() {
        return recieveTime;
    }

    public void setRecieveTime(String recieveTime) {
        this.recieveTime = recieveTime;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getProcessStartTime() {
        return processStartTime;
    }

    public void setProcessStartTime(String processStartTime) {
        this.processStartTime = processStartTime;
    }

    public String getBussinessKey() {
        return bussinessKey;
    }

    public void setBussinessKey(String bussinessKey) {
        this.bussinessKey = bussinessKey;
    }

    public String getActiviName() {
        return activiName;
    }

    public void setActiviName(String activiName) {
        this.activiName = activiName;
    }

    @Override
    public String toString() {
        return "ToDoProcess{" +
                "processId='" + processId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", taskName='" + taskName + '\'' +
                ", recieveTime='" + recieveTime + '\'' +
                ", initiator='" + initiator + '\'' +
                ", processStartTime='" + processStartTime + '\'' +
                ", bussinessKey='" + bussinessKey + '\'' +
                ", activiName='" + activiName + '\'' +
                '}';
    }
}

