package cn.simple.pm.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 上传附件
 * 
 * @author liushx
 * @date 2016年10月3日
 */
@Entity
@Table(name = "s_attachment")
@SuppressWarnings("serial")
public class Attachment implements java.io.Serializable {

	@Id
    @SequenceGenerator(name="generator") 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "attachment_id", length = 8)
	private Long attId;

	/** 附件类型 */
	@Column(name = "ATTACHMENT_TYPE", length = 28)
	private String attaType;
	/** 关联业务主键ID */
	@Column(name = "BIZ_ID", precision = 8)
	private Long bizId;

	/** 附件名称 */
	@Column(name = "TITLE", length = 256)
	private String title;
	/** 附件路径 */
	@Column(name = "FILE_PATH", length = 512)
	private String filePath;
	/** 附件内容 */
	@Column(name = "FILE_CONTENT")
	private Blob fileContent;
	/** 附件名 */
	@Column(name = "FILE_NAME", length = 256)
	private String fileName;
	/** 附件大小 */
	@Column(name = "FILE_SIZE", length = 10)
	private String fileSize;
	/** 下载次数 */
	@Column(name = "DOWN_COUNT", precision = 32)
	private Integer downCount;
	/** 状态 */
	@Column(name="STATUS")
	private String status;

	public Long getAttId() {
		return attId;
	}

	public void setAttId(Long attId) {
		this.attId = attId;
	}

	public String getAttaType() {
		return attaType;
	}

	public void setAttaType(String attaType) {
		this.attaType = attaType;
	}

	public Long getBizId() {
		return bizId;
	}

	public void setBizId(Long bizId) {
		this.bizId = bizId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Blob getFileContent() {
		return fileContent;
	}

	public void setFileContent(Blob fileContent) {
		this.fileContent = fileContent;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getDownCount() {
		return downCount;
	}

	public void setDownCount(Integer downCount) {
		this.downCount = downCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}