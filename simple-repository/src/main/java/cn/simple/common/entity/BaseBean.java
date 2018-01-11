package cn.simple.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;



/**
 * 
 * @author liushx
 * @date 2016年11月17日
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class BaseBean implements Serializable {

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")  
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@Column(name="STATUS", length=1)
	private String status;

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
	
	
	
	
}
