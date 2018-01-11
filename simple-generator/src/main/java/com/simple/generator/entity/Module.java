package com.simple.generator.entity;

import java.util.List;

public class Module {

	/** entity包路名称 */
	private String moduleName;
	/** entity包路名称 */
	private String entityPkgName;
	/** entity包路径 */
	private String entityPkgPath;
	/** mapping xml包路名称 */
	private String mappingPkgName;
	/** mapping xml包路名路径 */
	private String mappingPkgPath;
	/** dao包路径(mybatis mapping路径或hibernate DAO路径) */
	private String daoPkgName;
	
	private String daoPkgPath;
	
	/** service包路径 */
	private String servicePkgName;
	private String servicePkgPath;
	/** controller 包路径 */
	private String controllerPkgName;
	private String controllerPkgPath;
	
	List<TableInfo> tableInfos;
	
    /** dao Java类后缀（Dao、Mapper） DAO_SUFFIX  */
    private String daoSuffix;

	public String getEntityPkgName() {
		return entityPkgName;
	}

	public void setEntityPkgName(String entityPkgName) {
		this.entityPkgName = entityPkgName;
	}

	public String getEntityPkgPath() {
		return entityPkgPath;
	}

	public void setEntityPkgPath(String entityPkgPath) {
		this.entityPkgPath = entityPkgPath;
	}

	public String getMappingPkgName() {
		return mappingPkgName;
	}

	public void setMappingPkgName(String mappingPkgName) {
		this.mappingPkgName = mappingPkgName;
	}

	public String getMappingPkgPath() {
		return mappingPkgPath;
	}

	public void setMappingPkgPath(String mappingPkgPath) {
		this.mappingPkgPath = mappingPkgPath;
	}

	public String getDaoPkgName() {
		return daoPkgName;
	}

	public void setDaoPkgName(String daoPkgName) {
		this.daoPkgName = daoPkgName;
	}

	public String getDaoPkgPath() {
		return daoPkgPath;
	}

	public void setDaoPkgPath(String daoPkgPath) {
		this.daoPkgPath = daoPkgPath;
	}

	public String getServicePkgName() {
		return servicePkgName;
	}

	public void setServicePkgName(String servicePkgName) {
		this.servicePkgName = servicePkgName;
	}

	public String getServicePkgPath() {
		return servicePkgPath;
	}

	public void setServicePkgPath(String servicePkgPath) {
		this.servicePkgPath = servicePkgPath;
	}

	public String getControllerPkgName() {
		return controllerPkgName;
	}

	public void setControllerPkgName(String controllerPkgName) {
		this.controllerPkgName = controllerPkgName;
	}

	public String getControllerPkgPath() {
		return controllerPkgPath;
	}

	public void setControllerPkgPath(String controllerPkgPath) {
		this.controllerPkgPath = controllerPkgPath;
	}

	public List<TableInfo> getTableInfos() {
		return tableInfos;
	}

	public void setTableInfos(List<TableInfo> tableInfos) {
		this.tableInfos = tableInfos;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getDaoSuffix() {
		return daoSuffix;
	}

	public void setDaoSuffix(String daoSuffix) {
		this.daoSuffix = daoSuffix;
	}
}
