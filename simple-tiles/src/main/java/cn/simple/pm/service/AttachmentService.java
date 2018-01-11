package cn.simple.pm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.simple.pm.dao.AttachmentDao;
import cn.simple.pm.entity.Attachment;

/**
 * 附件Service
 * @author liushx
 * @date 2016年10月9日
 */
@Service
@Transactional
public class AttachmentService {
	
	@Autowired
	private AttachmentDao attachmentDao;

	public List<Attachment> queryAttListByBizId(Long bizId, String attaType){
		return attachmentDao.queryAttListByBizId(bizId, attaType);
	}

	public void save(Attachment att) {
		attachmentDao.save(att);
	}

	public Attachment get(Long attId) {
		
		return attachmentDao.get(attId);
	}
	
	/**
	 * 关联附件业务ID
	 * @param attId
	 * @param formId
	 */
	public void relationAttBizId(Long[] attId, Long formId) {
		// -- 保存流程附件
		if(attId != null && attId.length > 0){
			for(int i=0; i< attId.length; i++){
				Attachment attachment = attachmentDao.get(attId[i]);
				attachment.setBizId(formId);
				//attachment.setAttaType(attType);
				attachmentDao.save(attachment);
			}
		}
	}


}
