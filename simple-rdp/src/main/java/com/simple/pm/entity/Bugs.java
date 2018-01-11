package com.simple.pm.entity;

import java.sql.Timestamp;
import 	java.math.BigDecimal;
import com.simple.common.entity.BaseEntity;
import java.io.Serializable;

/**
 * <p>  </p>
 * @author liushx
 * @date 2018-01-10
 */
public class Bugs extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /** BUGID */
    private Long bugId;
    /** BUG编号(项目编码_BUG_时间) */
    private String bugNo;
    /** 模块ID */
    private Long modularId;
    /** BUG标题 */
    private String bugTitle;
    /** BUG类型 */
    private Integer bugType;
    /** 操作系统 */
    private String os;
    /** 测试浏览器 */
    private Integer broswer;
    /** 阶段(完成阶段就是通过) */
    private String phase;
    /** 影响版本 */
    private Integer versionId;
    /** BUG来源 */
    private Integer source;
    /** 创建时间 */
    private Timestamp creatTime;
    /** 预计开始时间 */
    private Timestamp estimatedStartTime;
    /** 预计完成时间 */
    private Timestamp estimatedEndTime;
    /** 预估工时 */
    private BigDecimal estimatedHours;
    /** 完成时间 */
    private Timestamp expectTime;
    /** 优先级 */
    private String priority;
    /** 严重程度 */
    private String severity;
    /** BUG发起人ID */
    private Long userId;
    /** 当前指派ID */
    private Long curUserId;
    /** 当前指派人员 */
    private String curUserName;
    /** 重现步骤 */
    private String reproSteps;
    /** 状态 */
    private String status;


	public Long getBugId() {
		return bugId;
	}

	public void setBugId(Long bugId) {
		this.bugId = bugId;
	}

	public String getBugNo() {
		return bugNo;
	}

	public void setBugNo(String bugNo) {
		this.bugNo = bugNo;
	}

	public Long getModularId() {
		return modularId;
	}

	public void setModularId(Long modularId) {
		this.modularId = modularId;
	}

	public String getBugTitle() {
		return bugTitle;
	}

	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}

	public Integer getBugType() {
		return bugType;
	}

	public void setBugType(Integer bugType) {
		this.bugType = bugType;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Integer getBroswer() {
		return broswer;
	}

	public void setBroswer(Integer broswer) {
		this.broswer = broswer;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Timestamp getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Timestamp creatTime) {
		this.creatTime = creatTime;
	}

	public Timestamp getEstimatedStartTime() {
		return estimatedStartTime;
	}

	public void setEstimatedStartTime(Timestamp estimatedStartTime) {
		this.estimatedStartTime = estimatedStartTime;
	}

	public Timestamp getEstimatedEndTime() {
		return estimatedEndTime;
	}

	public void setEstimatedEndTime(Timestamp estimatedEndTime) {
		this.estimatedEndTime = estimatedEndTime;
	}

	public BigDecimal getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(BigDecimal estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public Timestamp getExpectTime() {
		return expectTime;
	}

	public void setExpectTime(Timestamp expectTime) {
		this.expectTime = expectTime;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCurUserId() {
		return curUserId;
	}

	public void setCurUserId(Long curUserId) {
		this.curUserId = curUserId;
	}

	public String getCurUserName() {
		return curUserName;
	}

	public void setCurUserName(String curUserName) {
		this.curUserName = curUserName;
	}

	public String getReproSteps() {
		return reproSteps;
	}

	public void setReproSteps(String reproSteps) {
		this.reproSteps = reproSteps;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}