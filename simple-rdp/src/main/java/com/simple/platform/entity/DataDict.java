package com.simple.platform.entity;

import java.sql.Timestamp;
import java.util.Date;
import com.simple.common.entity.BaseEntity;
import java.io.Serializable;

/**
 * <p>  </p>
 * @author liushx
 * @date 2017-12-23
 */
public class DataDict extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long dictId;
    private Long parentId;
    private String dictName;
    private String dictCode;
    private String dictType;
    private Date createDate;
    private String status;
    private String remarks;
    private Integer seq;
    private Long creater;
    private Timestamp editDate;
    private Long editor;


	public Long getDictId() {
		return dictId;
	}

	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Long getCreater() {
		return creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	public Timestamp getEditDate() {
		return editDate;
	}

	public void setEditDate(Timestamp editDate) {
		this.editDate = editDate;
	}

	public Long getEditor() {
		return editor;
	}

	public void setEditor(Long editor) {
		this.editor = editor;
	}

}