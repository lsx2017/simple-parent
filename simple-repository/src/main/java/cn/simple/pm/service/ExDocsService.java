package cn.simple.pm.service;

import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.simple.pm.dao.ExDocsDao;
import cn.simple.pm.entity.ExDocs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ExDocsService {
	
	@Autowired 
	private ExDocsDao exDocsDao;

	public List<ExDocs> queryExDocsList() {

		return exDocsDao.getAll();
	}

	public ExDocs getExDocs(Long docsId) {

		return exDocsDao.get(docsId);
	}
	
	public void saveExDocs(ExDocs exDocs) {
		exDocsDao.save(exDocs);
	}


	public void test(){
		//public static void main(String[] args) throws Exception {


		//}

	}

	public static void main(String[] args) {
		Desktop desk=Desktop.getDesktop();  
		try  
		{  
		    File file=new File("D:\\证照数据表结构说明文档.docx");//创建一个java文件系统  
		    desk.open(file); //调用open（File f）方法打开文件   
		}catch(Exception e)  
		{  

		}   
		//getFiles(filePath);
	}


	//private static ArrayList<String> filelist = new ArrayList<String>();



}





