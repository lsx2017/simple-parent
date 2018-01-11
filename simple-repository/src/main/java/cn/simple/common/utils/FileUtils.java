package cn.simple.common.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtils {

	private final static String ID_NAME = "docs_id";
	private final static String PID_NAME = "parent_id";
	private final static String NODE_NAME = "name";
	private final static String CHILDREN = "children";
	/**
	public static List<File> getFileList(String strPath) {
		
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
               
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                	 System.out.println("dirName::---" + fileName);
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (files[i].isFile()) { // 判断文件名是否以.avi结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("fileName::---" + strFileName);
//                    filelist.add(files[i]);
                } 
            }

        }
        return null;
    }*/
	
	public static List<File> getFileList(String strPath) {
		
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
           
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                	 System.out.println("dirName::---" + fileName);
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (files[i].isFile()) { // 判断文件名是否以.avi结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("fileName::---" + strFileName);
//                    filelist.add(files[i]);
                } 
            }

        }
        return null;
    }

	public static List<Map<String, Object>> buildFileList(String parentId, String strPath) {
		
		List<Map<String, Object>> rootList = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> rootNode = new HashMap<String, Object>();
		rootNode.put(ID_NAME, "1");
		rootNode.put(PID_NAME, "0");
		rootNode.put(NODE_NAME, "知识库");
	
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
            	Map<String, Object> fileMap = new HashMap<String, Object>();
            	String fileName = files[i].getName();
            	String fileId = parentId+"_"+i;
            	fileMap.put(ID_NAME, fileId);
            	fileMap.put(PID_NAME, parentId);
            	fileMap.put(NODE_NAME, fileName);
            	/**
            	fileMap.put("", value)
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                	System.out.println("dirName::---" + fileName);
                	fileMap.put(CHILDREN, buildFileList(fileId, files[i].getAbsolutePath())); // 获取文件绝对路径
                } 
                */
                result.add(fileMap);
                /**
                else if (files[i].isFile()) { // 判断文件名是否以.avi结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("fileName::---" + strFileName);
//                    filelist.add(files[i]);
                } */
            }

        }
        
        rootNode.put(CHILDREN, result); // 获取文件绝对路径
        rootList.add(rootNode);
        
        return rootList;
    }
}
