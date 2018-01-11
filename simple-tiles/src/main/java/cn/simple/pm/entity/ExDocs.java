package cn.simple.pm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cn.simple.common.entity.BaseBean;

import com.alibaba.fastjson.annotation.JSONField;

@SuppressWarnings("serial")
@Entity
@Table(name="ex_docs")
public class ExDocs extends BaseBean {

//	docs_id	bigint	20	0	0	-1	0	0	0		0					-1	0
//	docs_name	varchar	100	0	-1	0	0	0	0		0		utf8	utf8_general_ci		0	0
//	docs_icon	varchar	50	0	-1	0	0	0	0		0		utf8	utf8_general_ci		0	0
//	docs_type	varchar	32	0	-1	0	0	0	0		0		utf8	utf8_general_ci		0	0
//	parent_id	bigint	20	0	-1	0	0	0	0		0					0	0
//	key_word	varchar	100	0	-1	0	0	0	0		0		utf8	utf8_general_ci		0	0
//	create_date	timestamp	0	0	-1	0	0	0	0		0					0	0
//	status	char	1	0	-1	0	0	0	0		0		utf8	utf8_general_ci		0	0
//	docs_path	varchar	256	0	-1	0	0	0	0		0		utf8	utf8_general_ci		0	0
	
	@Id
    @SequenceGenerator(name="generator") 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="docs_id", unique=true, nullable=false, precision=22, scale=0)
    private Long docsId;
	
	@Column(name="docs_name")
	private String docsName;
	
	@Column(name="content")
	private String content;
	
	@Column(name="docs_icon")
	private String docsIcon;
	
	@Column(name="parent_id")
	private Long parentId;
	
	@Column(name="key_word")
	private String keyworld;
	
	@Column(name="docs_sort")
	private Integer docsSort; 
	
	@JSONField(format="yyyy-MM-dd HH:mm")
	@Column(name="update_date")
	private Date updateDate;
	
	@Column(name="docs_path")
	private String docsPath;


	public Long getDocsId() {
		return docsId;
	}

	public void setDocsId(Long docsId) {
		this.docsId = docsId;
	}

	public String getDocsName() {
		return docsName;
	}

	public void setDocsName(String docsName) {
		this.docsName = docsName;
	}

	public String getDocsIcon() {
		return docsIcon;
	}

	public void setDocsIcon(String docsIcon) {
		this.docsIcon = docsIcon;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getKeyworld() {
		return keyworld;
	}

	public void setKeyworld(String keyworld) {
		this.keyworld = keyworld;
	}

	

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDocsPath() {
		return docsPath;
	}

	public void setDocsPath(String docsPath) {
		this.docsPath = docsPath;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDocsSort() {
		return docsSort;
	}

	public void setDocsSort(Integer docsSort) {
		this.docsSort = docsSort;
	}

	

	
}
