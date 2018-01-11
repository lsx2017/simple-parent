package com.simple.common.utils;

import java.io.File;

/**
 * Created by liushx on 2018/1/5.
 */
public class FormatMavenProject {
	
	//LocalIntfManager
	//LocalSoManager
	
    /** 要格式化Maven工程目录 */
    public static String MAVEN_PROJECT_CATALOG = "D:\\IdeaProjects\\NCRM";
    public static String POM_FILENAME = "pom.xml";
    public static String SRC_DIRECTORY[] = new String[]{"Bss.configs",".svn","src","docs","configs","bin","snack"};
    public static String TARGET_DIRECTORY = "target";

    public static String[] DELETE_DIRS = new String[]{"pom.xml","src"};

    public static void main(String[] args) {

        formatFileList(MAVEN_PROJECT_CATALOG); 
    }

    public static boolean formatFileList(String strPath) {
        boolean isDel = true;
        File dir = new File(strPath);
        File[] files = dir.listFiles();
        if (files == null)
        {
            System.out.println("该目录下没有任何一个文件！");
            return isDel;
        }
        for (int i = 0; i < files.length; i++) {
            File curFile = files[i];
            if (curFile.isDirectory()) {
                if (TARGET_DIRECTORY.equals(curFile.getName())) {
                    delDir(curFile.getAbsolutePath());
                    continue;
                }
                if (StringUtils.notIn(curFile.getName(), SRC_DIRECTORY)) {
                    String filePath = files[i].getAbsolutePath();
                    System.out.println("filePath=="+filePath);
                    boolean isDelDir = formatFileList(filePath);
                    if(isDelDir) {
                        files[i].delete();
                    }
                }
            }else {
                if(POM_FILENAME.equals(curFile.getName())){
                    isDel = false;
                } else {
                    files[i].delete();
                }
            }

        }

        return isDel;

    }

    public static void delDir(String strPath){
        File dir = new File(strPath);
        File[] files = dir.listFiles();
        if (files == null)
        {
            System.out.println("该目录下没有任何一个文件！");
            dir.delete();
        }
        for (int i = 0; i < files.length; i++) {
            File curFile = files[i];
            if (curFile.isDirectory()) {
                delDir(curFile.getAbsolutePath());
                curFile.delete();
            } else {
                curFile.delete();
            }
        }
        dir.delete();
    }
}
