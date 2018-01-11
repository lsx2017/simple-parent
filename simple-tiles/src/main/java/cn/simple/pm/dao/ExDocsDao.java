package cn.simple.pm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.simple.common.em.Status;
import cn.simple.common.hibernate.HibernateDao;
import cn.simple.pm.entity.ExDocs;

@Repository
public class ExDocsDao extends HibernateDao<ExDocs, Long> {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ExDocs> getAll() {
		
		//String hql ="select new ExDocs(docsId, docsName, parentId) from ExDocs";
		String sql ="SELECT DOCS_ID, DOCS_NAME, PARENT_ID FROM ex_docs where status=? order by docs_sort asc";
		Query query = createSqlQuery(sql, null);
		//createQuery(hql, null);
		query.setParameter(0, Status.NORMAL.getIndex());
		return query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		//.addOrder(Order.asc("sort"))
		//return super.createCriteria(Restrictions.eq("status",  Res.NORMAL)).list();
	}

}
