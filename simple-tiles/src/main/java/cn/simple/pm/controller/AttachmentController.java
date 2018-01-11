package cn.simple.pm.controller;
     
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.simple.common.em.Status;
import cn.simple.common.utils.Constants;
import cn.simple.common.utils.JsonHelper;
import cn.simple.common.utils.Result;
import cn.simple.pm.entity.Attachment;
import cn.simple.pm.service.AttachmentService;
 
/**
 * 附件管理(文件上传)
 * @author liushx
 * @date 2016年10月3日
 */
@Controller
@RequestMapping("/attachment")
public class AttachmentController {
	
	Logger logger = LoggerFactory.getLogger(AttachmentController.class);
	
	private static final Pattern picPattern = Pattern.compile("jpg|png|jpeg|gif|bmp");
	private static final Pattern extPattern = Pattern.compile("doc|docx|xls|xlsx|txt|jpg|gif|png|rar|zip|tif|pdf|apk|ppt|pptx");
	private static final String POINT = ".";
 
	@Autowired 
	private AttachmentService attachmentService;
	
	 /**
	  * 附件上传
	  * @param request
	  * @param response
	  * @return
	  * @throws Exception
	  */
	@RequestMapping("/upload")
	public String upload(HttpServletRequest request, HttpServletResponse response, String attaType) {
		try {
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			String moudle = request.getParameter("moudle");//对应模块
			if (moudle == null || moudle.equals("")) {
				moudle = "interview";
			}
			// 返回数据
			if (ServletFileUpload.isMultipartContent(request)) {
				DiskFileItemFactory factory = new DiskFileItemFactory();//磁盘对象
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);
				// 获取存储路径
				String tempPath =  moudle + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date());
				String savePath = rootPath + File.separator + "upload" + File.separator + tempPath;
				File saveDir = new File(savePath);
				if (!saveDir.exists()) {
					saveDir.mkdirs();
				}

				Iterator<FileItem> itr = items.iterator();
				String newFileName = null;
				Attachment att = null;
				while (itr.hasNext()) {
					
					FileItem item = itr.next();
					if ((!item.isFormField()) && (item.getName().length() > 0)) {
						String extName = FilenameUtils.getExtension(item.getName()).toLowerCase(Locale.ENGLISH);
						
						//扩展名判断
						//if(extPattern.matcher(extName).find()){
						att = new Attachment();
						newFileName = System.currentTimeMillis() + POINT + extName;
						att.setFilePath("upload" + File.separator + tempPath + File.separator + newFileName);
						att.setAttaType(attaType);
						att.setFileName(item.getName());
						att.setFileSize(String.valueOf(item.getSize()));
						att.setTitle(request.getParameter("title"));
						att.setStatus(Status.NORMAL.getIndex());
					 	attachmentService.save(att);
						String imgPath = savePath + File.separator + newFileName;
						File file = new File(imgPath);
						
						item.write(file);
							
						//} //else{
						//	response.getWriter().print("{error:'上传文件格式不符合要求！'}");
						//}
					}
				}
				//response.getWriter().print(.getJsonString(att));
		        //下面response返回的json格式是editor.md所限制的，规范输出就OK
				
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("success", "1");
				result.put("message", "上传成功");
				result.put("url", "http://localhost:8100/simple-repository/"+att.getFilePath());
		        response.getWriter().write(JsonHelper.getJsonString(result));
			}

		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.getWriter().print("{error:'未知异常！请检查文件大小、格式是否符合要求！'}");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
		
	}
	
	/**
	 * @Description：下载附件
	 * @Author:ffwf
	 * @param request
	 * @param response
	 * @return String
	 */
	@RequestMapping("/down")
	public String down(HttpServletRequest request, HttpServletResponse response, Long attId, Long id, String attaType) {
		try {
		
			if(attId != null && !"".equals(attId)){

				Attachment attachment = attachmentService.get(attId);
				String rootPath = request.getSession().getServletContext().getRealPath("/");
				//rootPath = Constants.UPLOAD_ROOT_PATH + File.separator;
				String allPath = rootPath+attachment.getFilePath();
				logger.info("allPath===="+allPath);
				// path是指欲下载的文件的路径。
				File file = new File(allPath);
				String newfilename = attachment.getFileName();
				InputStream fis = new BufferedInputStream(new FileInputStream(allPath));
				byte[] buffer = new byte[fis.available()];
				fis.read(buffer);
				fis.close();
				// 清空response
				response.reset();
				// 设置response的Header
				response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(newfilename, "UTF-8"));
				response.addHeader("Content-Length", "" + file.length());
				OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
				response.setContentType("application/octet-stream");
				toClient.write(buffer);
				toClient.flush();
				toClient.close();
			}
			return null;
		} catch(FileNotFoundException e){
			logger.error("---------文件下载失败，该文件不存在。-----------");
			return "common/downloadError";
		}catch (IOException ex) {
			ex.printStackTrace();
			return "common/downloadError";
		}
		
	}

	/**
	 * 删除附件(伪删除)
	 * @param attId
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public Result delete(Long attId) throws IOException {
		
		if(attId != null && attId > 0) {
			Attachment att = attachmentService.get(attId);
			att.setStatus(Status.DELETE.getIndex());
			attachmentService.save(att);
			return Result.SUCCESS;
		} else {
			return Result.FAILURE;
		}
	}
	
	
//	
//	/**
//	 * @Description：展示附件信息
//	 * @Author:ffwf
//	 * @param request
//	 * @param response
//	 * @return String
//	 * @throws IOException
//	 */
//	@RequestMapping("/slideShow")
//	public String slideShow(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		Long bizId = RequestUtils.getLong("bizId", request);
//		String attachmentType = RequestUtils.getString("attachmentType", request);
//		List<Attachment> list = null;
//		if(bizId==null){
//			sendJSON(list, response);
//		}else{
//			list = attachmentService.getAttachmentlist(bizId);  
//			sendJSON(list, response);
//		}
//		return null;
//	}

//	/**
//	* @Description：输出
//	 * @Author:ffwf
//	 * @param object
//	 * @param response
//	 * @throws IOException
//	 */
//	public void sendJSON(Object object, HttpServletResponse response)throws IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		String message = mapper.writeValueAsString(object);
//		sendJSON(message, response);
//		
//	}
//	/**
//	* @Description：输出
//	 * @Author:ffwf
//	 * @param object
//	 * @param response
//	 * @throws IOException
//	 */
//	public void sendJSON(String message, HttpServletResponse response)
//		throws IOException {
//	
//		response.setContentType("application/json;charset=utf-8");
//		response.setCharacterEncoding("UTF-8");
//		response.setHeader("Cache-Control", "no-cache");
//		
//		response.getWriter().write(message);
//		response.getWriter().close();
//	}
//	
//	/**
//	* @Description：附件浏览
//	 * @Author:ffwf
//	 * @param object
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping("/showImageViewer")
//	@ResponseBody
//	public String showImageViewer(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		try {
//			String attId = request.getParameter("attId");
//			if(!StrUtils.isBlank(attId)){
//				Attachment att = attachmentService.getAttachment(Long.parseLong(attId));
//				
//				String extName = FilenameUtils.getExtension(att.getFilePath()).toLowerCase(Locale.ENGLISH);
//				
//				//扩展名判断
//				if(picPattern.matcher(extName).find()){
//					//Result r = new Result(Constants.IMAGE_SHOW_PATH + File.separator + att.getFilePath(),true,"");
//					Result r = new Result(Constants.IMAGE_SHOW_PATH + File.separator + att.getFilePath(),true,"");
//					sendJSON(r,response);
//				}else{
//					Result r = new Result("",false,"");
//					sendJSON(r,response);
//				}
//			}else{
//				Result r = new Result("",false,"");
//				sendJSON(r,response);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			sendJSON(new Result(false),response);
//		}
//		return null;
//	}

}
