package com.vcolco.android.http.entity;

import java.io.Serializable;

/**
 * 登录返回数据
 * 
 * memberId Int 手机用户ID companyCode String 企业代码 companyName String 企业名称 name
 * String 姓名 departmentId String 单位ID department String 单位名称 userType Int 用户类别
 * sex String 性别 phoneNum String 电话号码 idCard String 身份证号/驾驶证号 job String 职位
 * 驾驶员该字段为null plates List<Plate> 驾驶员所有车辆的车牌号 isRtsActive Bool 是否在安标系统激活 baiduCI
 * String 百度Channel ID url3gStatus String 3g在线状态查询地址 url3gLive String 3g实时视频请求地址
 * urlAppDownload String app下载地址 appVersion String app最新版本号 1.00,1.01,1.02以此类推
 * 
 * @author Administrator
 *
 */
public class LoginRs implements Serializable {
	private static final long serialVersionUID = -3096036211961413613L;
	private int memberId;
	private String companyCode;
	private String companyName;
	private String name;
	private String departmentId;
	private String department;
	private int userType; // 0. 其他1. 驾驶员； 2. 安管人员； 3. 车主；
	private String sex;
	private String phoneNum;
	private String idCard;
	private String job;
	private boolean isRtsActive;
	private String baiduCI;
	private String url3gStatus;
	private String url3gLive;
	private String urlGPS;
	private String url3G;

	public LoginRs() {
	}

	public LoginRs(int memberId, String companyCode, String companyName,
			String name, String departmentId, String department, int userType,
			String sex, String phoneNum, String idCard, String job,
			boolean isRtsActive, String baiduCI, String url3gStatus,
			String url3gLive, String urlGPS, String url3g) {
		this.memberId = memberId;
		this.companyCode = companyCode;
		this.companyName = companyName;
		this.name = name;
		this.departmentId = departmentId;
		this.department = department;
		this.userType = userType;
		this.sex = sex;
		this.phoneNum = phoneNum;
		this.idCard = idCard;
		this.job = job;
		this.isRtsActive = isRtsActive;
		this.baiduCI = baiduCI;
		this.url3gStatus = url3gStatus;
		this.url3gLive = url3gLive;
		this.urlGPS = urlGPS;
		url3G = url3g;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public boolean isRtsActive() {
		return isRtsActive;
	}

	public void setRtsActive(boolean isRtsActive) {
		this.isRtsActive = isRtsActive;
	}

	public String getBaiduCI() {
		return baiduCI;
	}

	public void setBaiduCI(String baiduCI) {
		this.baiduCI = baiduCI;
	}

	public String getUrl3gStatus() {
		return url3gStatus;
	}

	public void setUrl3gStatus(String url3gStatus) {
		this.url3gStatus = url3gStatus;
	}

	public String getUrl3gLive() {
		return url3gLive;
	}

	public void setUrl3gLive(String url3gLive) {
		this.url3gLive = url3gLive;
	}

	public String getUrlGPS() {
		return urlGPS;
	}

	public void setUrlGPS(String urlGPS) {
		this.urlGPS = urlGPS;
	}

	public String getUrl3G() {
		return url3G;
	}

	public void setUrl3G(String url3g) {
		url3G = url3g;
	}

	@Override
	public String toString() {
		return "LoginRs [memberId=" + memberId + ", companyCode=" + companyCode
				+ ", companyName=" + companyName + ", name=" + name
				+ ", departmentId=" + departmentId + ", department="
				+ department + ", userType=" + userType + ", sex=" + sex
				+ ", phoneNum=" + phoneNum + ", idCard=" + idCard + ", job="
				+ job + ", isRtsActive=" + isRtsActive + ", baiduCI=" + baiduCI
				+ ", url3gStatus=" + url3gStatus + ", url3gLive=" + url3gLive
				+ ", urlGPS=" + urlGPS + ", url3G=" + url3G + "]";
	}

}
