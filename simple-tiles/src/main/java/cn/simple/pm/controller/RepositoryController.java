package cn.simple.pm.controller;

import cn.simple.common.utils.FileUtils;
import cn.simple.pm.entity.ExDocs;
import cn.simple.pm.service.ExDocsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by lsx on 2016/8/9.
 */

@Controller
@RequestMapping("/repository")
public class RepositoryController {
	
	@Autowired 
	private ExDocsService exDocsService;
	
	
	String filePath = "D:\\XRJ";
	
    @RequestMapping(value = "view")
    public String view(ModelMap modelMap) {

        return "pm/repository/repository_view";
    }
    
	@RequestMapping("openDocs")
	public String openDocs(Long docsId, ModelMap modelMap) {
		
		if(docsId != null){
			System.out.println("--------------openDocs---------------");
			ExDocs exDocs = exDocsService.getExDocs(docsId);
			modelMap.put("exDocs", exDocs);
			//return exDocs.getDocsPath();
		}
		return "pm/repository/repository_detail";
	}

	@RequestMapping("edit")
	public String edit(Long docsId, ModelMap modelMap) {

		if(docsId != null){
			System.out.println("--------------openDocs---------------");
			ExDocs exDocs = exDocsService.getExDocs(docsId);
			modelMap.put("exDocs", exDocs);
			//return exDocs.getDocsPath();
		}
		return "pm/repository/repository_edit";

	}

	@ResponseBody
	@RequestMapping("repTree")
	public String exDocsTree() {
	
		List<Map<String, Object>> resultMap = FileUtils.buildFileList("0", filePath);
		//String id = RequestUtils.getString("id", request);
//		List<ExDocs> exDocsList = exDocsService.queryExDocsList();
		Gson gson = new Gson();
		String result = gson.toJson(resultMap);
				//JSON.toJSONString(exDocsList);
		System.out.println(result);
		return result;
		
	}
	
    @RequestMapping(value = "search")
    public String search(ModelMap modelMap) {
        
        return "pm/repository/repository_search";
    }
    
    /**
    @ResponseBody
    @RequestMapping(value = "dirs")
    public String dirs() {
    	
        List<FileProperty> fps = getDirs(filePath);
		Gson gson = new Gson();
		String result = gson.toJson(fps);
				//JSON.toJSONString(exDocsList);
		System.out.println(result);
		return result;
    }
    
    @RequestMapping(value = "toFiles")
    public String toFiles(String dirName, ModelMap modelMap) {
    	
        System.out.println("repository--view");
        List<FileProperty> fileList = new ArrayList<FileProperty>();
		File dir = new File(filePath + File.separator + dirName);
		if(dir.exists()){
			File[] files = dir.listFiles();
			for(File file:files){
				String fileName = file.getName();
				FileProperty fp = new FileProperty();
				fp.setFileName(fileName);
				fp.setAbsolutePath(file.getAbsolutePath());
				fp.setSuffix(fileName.substring(fileName.lastIndexOf(".")+1));
				fileList.add(fp);
			
			}
		}
		modelMap.put("fileList", fileList);
        return "repository/repository_list";
    }

    @ResponseBody
    @RequestMapping(value = "openFile")
    public String openFile(String fileName) {
    	
    	String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
    	if(!"md".equals(suffix)) {
    		Desktop desk=Desktop.getDesktop();  
    		try  
    		{  
    		    File file=new File(filePath + File.separator + "JavaScript" + File.separator + fileName);//创建一个java文件系统  
    		    desk.open(file); //调用open（File f）方法打开文件   
    		}catch(Exception e)  
    		{  
    		    System.out.println(e.toString());  
    		}   
    	}
    	return "";
    }
    
    
	private List<FileProperty> getDirs(String filePath){
		List<FileProperty> dirList = new ArrayList<FileProperty>();
		File root = new File(filePath);
		File[] files = root.listFiles();
		for(File file:files){
			if(file.isDirectory()){
				FileProperty fp = new FileProperty();
				fp.setFileName(file.getName());
				fp.setAbsolutePath(file.getAbsolutePath());
				dirList.add(fp);
		  		//递归调用
				//getFiles(file.getAbsolutePath());
				//filelist.add(file.getAbsolutePath());
				System.out.println("显示"+filePath+"下所有子目录及其文件"+file.getAbsolutePath());
			}//else{
			//	System.out.println("显示"+filePath+"下所有子目录"+file.getAbsolutePath());
			//}
		}
		
		return  dirList;
	}
	*/
}
