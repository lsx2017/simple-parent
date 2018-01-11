package cn.simple.pm.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.simple.common.em.Status;
import cn.simple.common.hibernate.HibernateDao;
import cn.simple.pm.entity.Attachment;


@Repository
public class AttachmentDao extends HibernateDao<Attachment, Long> {
	
	/**
	 * 根据bizID与attaType查询上传的附件
	 * @param bizId
	 * @param attaType
	 * @return
	 */
	public List<Attachment> queryAttListByBizId(Long bizId, String attaType) {
		Criteria c= createCriteria(
			Restrictions.eq("bizId", bizId), 
			Restrictions.eq("attaType", attaType),
			Restrictions.eq("status", Status.NORMAL.getIndex()) 
		);
		return (List<Attachment>) c.list();
	}
	
	/*
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getProjectAppFiles(int bizId, Integer guideId, Integer typeId) {

		final String sql = "SELECT T.TYPE_ID, T.NAME,T.GUIDE_ID,T.IS_REQUIRED, T2.FILE_NAME, T2.FILE_SIZE, T2.FILE_PATH, T2.ATTACHMENT_ID "+
		"FROM "+cn.ffcs.common.Constants.SCHEMA_ZJSB+".TB_PROJECT_ATTACHMENT_TYPE T"
			+ " LEFT JOIN "+cn.ffcs.common.Constants.SCHEMA_ZJSB+".TB_ATTACHMENT T2 ON T2.ATTACHMENT_TYPE = TO_CHAR(T.TYPE_ID) AND T2.BIZ_ID = ?"
			+ " WHERE T.GUIDE_ID = ? AND T.GUIDE_ATT_TYPE_ID = ? ORDER BY T.TYPE_ID";
		Query query = super.getSession().createSQLQuery(sql.toString());
		query.setParameter(0, bizId);
		query.setParameter(1, guideId);
		query.setParameter(2, typeId);
		
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	

	public void copyAttachment(Integer oldId,String oldType,Integer newId,String newType)throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO  "+Constants.SCHEMA_ZJSB+".TB_ATTACHMENT(ATTACHMENT_ID,ATTACHMENT_TYPE,BIZ_ID,TITLE,FILE_PATH,FILE_CONTENT,FILE_NAME,FILE_SIZE,DOWN_COUNT) ");
		sql.append("SELECT "+cn.ffcs.common.Constants.SCHEMA_PLATFORM+".HIBERNATE_SEQUENCE.NEXTVAL,?,?,TITLE,FILE_PATH,FILE_CONTENT,FILE_NAME,FILE_SIZE,DOWN_COUNT ");
		sql.append("FROM "+Constants.SCHEMA_ZJSB+".TB_ATTACHMENT T WHERE T.ATTACHMENT_TYPE=? AND T.BIZ_ID=? ");
		
		Query q = super.getSession().createSQLQuery(sql.toString());
		q.setString(0, newType);
		q.setInteger(1, newId);
		q.setString(2, oldType);
		q.setInteger(3, oldId);
		q.executeUpdate();
		
	}


	public List<Attachment> getByBiz(Integer expertId,
			String experts_attachment, String experts_edit_attachment_del) {
		Criteria c= createCriteria(Restrictions.eq("bizId", expertId));
		c.add(Restrictions.or(
				Restrictions.eq("attachmentType", experts_attachment),
				Restrictions.eq("attachmentType", experts_edit_attachment_del)
				));
		return (List<Attachment>) c.list();
	}
	*/
	
	@SuppressWarnings("unchecked")
	@Deprecated
	public List<Map<String, Object>> queryAttachments(String attIds) {

		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT T.ATTACHMENT_ID,T.FILE_NAME,T.FILE_PATH FROM T_SM_ATTACHMENT T ")
		.append(" WHERE ")
		.append(" 1=1 AND ")
		.append(" T.ATTACHMENT_ID IN ( ").append(attIds).append(")")
		.append(" ORDER BY ATTACHMENT_ID DESC ");
		Query query = getSession().createSQLQuery(sql.toString());
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
	
	@SuppressWarnings("unchecked")
	@Deprecated
	public List<Map<String, Object>> queryAttachments(Integer biz_id,String type) {

		StringBuffer sql = new StringBuffer("");
		sql.append(" SELECT T.ATTACHMENT_ID,T.FILE_NAME,T.FILE_PATH FROM T_SM_ATTACHMENT T ")
		.append(" WHERE ")
		.append(" 1=1 AND ")
		.append(" T.BIZ_ID= ").append(biz_id)
		.append(" AND T.ATTACHMENT_TYPE = ").append("'"+type+"'")
		.append(" ORDER BY ATTACHMENT_ID DESC ");
		Query query = getSession().createSQLQuery(sql.toString());
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	}
}
