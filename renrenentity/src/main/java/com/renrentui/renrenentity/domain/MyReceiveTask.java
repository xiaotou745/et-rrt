package com.renrentui.renrenentity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class MyReceiveTask extends TaskModel{
@JsonIgnore
private int auditStatus;
@JsonIgnore
private long auditNum;
private long auditWaitNum;
private long auditPassNum;
private long auditRefuseNum;
private long complateNum;
private long ctId;


public long getCtId() {
	return ctId;
}
public void setCtId(long ctId) {
	this.ctId = ctId;
}
public int getAuditStatus() {
	return auditStatus;
}
public void setAuditStatus(int auditStatus) {
	this.auditStatus = auditStatus;
}
public long getAuditNum() {
	return auditNum;
}
public void setAuditNum(long auditNum) {
	this.auditNum = auditNum;
}

public long getAuditWaitNum() {
	return auditWaitNum;
}
public void setAuditWaitNum(long auditWaitNum) {
	this.auditWaitNum = auditWaitNum;
}
public long getAuditPassNum() {
	return auditPassNum;
}
public void setAuditPassNum(long auditPassNum) {
	this.auditPassNum = auditPassNum;
}

public long getComplateNum() {
	return complateNum;
}
public void setComplateNum(long complateNum) {
	this.complateNum = complateNum;
}
public long getAuditRefuseNum() {
	return auditRefuseNum;
}
public void setAuditRefuseNum(long auditRefuseNum) {
	this.auditRefuseNum = auditRefuseNum;
}
}
