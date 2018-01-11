
package cn.simple.pm.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.simple.common.em.Status;
import cn.simple.common.utils.Result;
import cn.simple.pm.entity.ExDocs;
import cn.simple.pm.service.ExDocsService;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;


@Controller
@RequestMapping("/explorer")
public class ExplorerController {
	protected static final Log logger = LogFactory.getLog(ExplorerController.class);

	@Autowired 
	private ExDocsService exDocsService;
	
	@RequestMapping(value = "view")
	public String view(ModelMap modelMap) {

		System.out.println("view");
		return "oa/explorer/explorer_view";
	}
	
	@ResponseBody
	@RequestMapping("exDocsTree")
	public String exDocsTree() {
	
		//String id = RequestUtils.getString("id", request);
		List<ExDocs> exDocsList = exDocsService.queryExDocsList();
		Gson gson = new Gson();
		String result = gson.toJson(exDocsList);
				//JSON.toJSONString(exDocsList);
		System.out.println(result);
		return result;
		
	}
	
	@RequestMapping("openDocs")
	public String openDocs(Long docsId, ModelMap modelMap) {
		
		if(docsId != null){
			System.out.println("-----77---------44---openDocs---------------");
			ExDocs exDocs = exDocsService.getExDocs(docsId);
			modelMap.put("exDocs", exDocs);
			//return exDocs.getDocsPath();
		}
		return "oa/explorer/explorer_edit_ueditor";
	}
	
	
	
	@ResponseBody
	@RequestMapping("addExDocs")
	public String addExDocs(Long docsId, String docsName, Long parentId, HttpServletRequest request, Long isProcess) {
		
		ExDocs exDocs = null;
		try{
			if(docsId != null) {
				exDocs = exDocsService.getExDocs(docsId);
				exDocs.setUpdateDate(new Date());
				exDocs.setDocsName(docsName);
			} else {
				exDocs = new ExDocs();
				exDocs.setDocsName("新建笔记");
				exDocs.setParentId(parentId);
				exDocs.setCreateDate(new Date());
				exDocs.setDocsSort(100);
				exDocs.setStatus(Status.NORMAL.getIndex());
			}
			
			exDocsService.saveExDocs(exDocs);
			
		} catch(Exception e){
			e.printStackTrace();
			return JSON.toJSONString(Result.FAILURE);
		}
		
		return JSON.toJSONString(new Result(exDocs));
	}
	
	@ResponseBody
	@RequestMapping("updateExDocsSort")
	public String updateExDocsSort(Long srcDocsId, Long targetDocsId, String moveType) {
		
		
		try{
			
			if(srcDocsId != null && targetDocsId != null) {
				ExDocs srcExDocs = exDocsService.getExDocs(srcDocsId);
				ExDocs targetExDocs = exDocsService.getExDocs(targetDocsId);
				//"prev"：成为同级前一个节点，"next"：成为同级后一个节点
				if("prev".equals(moveType)) {
					srcExDocs.setDocsSort(targetExDocs.getDocsSort()-1);
					
				} else if("next".equals(moveType)) {
					srcExDocs.setDocsSort(targetExDocs.getDocsSort()+1);
				} 
				
				exDocsService.saveExDocs(srcExDocs);
	
			} 
			
		} catch(Exception e){
			e.printStackTrace();
			return JSON.toJSONString(Result.FAILURE);
		}
		
		return JSON.toJSONString(Result.SUCCESS);
	}
	
	
	
	
	@ResponseBody
	@RequestMapping("save")
	public String save(Long docsId, String content, HttpServletRequest request, Long isProcess) {
		
		logger.info("-------------------"+new Date().getTime()+"----------------------------");
		try{
			
			if(docsId != null) {
				
				ExDocs exDocs = exDocsService.getExDocs(docsId);
				
				exDocs.setUpdateDate(new Date());
				exDocs.setContent(content);
				exDocsService.saveExDocs(exDocs);
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
			return JSON.toJSONString(Result.FAILURE);
		}
		
		return JSON.toJSONString(Result.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping("delete")
	public String delete(Long docsId) {
		

		try{
			
			if(docsId != null) {
				
				ExDocs exDocs = exDocsService.getExDocs(docsId);
				
				exDocs.setStatus(Status.DELETE.getIndex());
				exDocsService.saveExDocs(exDocs);
			}
			
			
		} catch(Exception e){
			e.printStackTrace();
			return JSON.toJSONString(Result.FAILURE);
		}
		
		return JSON.toJSONString(Result.SUCCESS);
	}
	
	/**
	private List<Map<String, Object>> buildTree(Long parentId, List<CatalogType> typeList){

		List<Map<String, Object>> types = new ArrayList<Map<String, Object>>();
		Map<String, Object> node = null;
		for (CatalogType catalogType : typeList) {
			if(parentId == catalogType.getParentId()) {
				node = new HashMap<String, Object>();
				node.put("id", catalogType.getId());
				node.put("pId", catalogType.getParentId());
				node.put("name", catalogType.getName());
				node.put("imagePath", catalogType.getImagePath());
				List<Map<String, Object>> children = buildTree(catalogType.getId(), typeList);
				node.put("children", children);
				types.add(node);
			}
		}
		
		return types;
	}*/
	
}
