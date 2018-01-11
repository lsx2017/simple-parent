package com.simple.platform.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.simple.common.controller.BaseController;
import com.simple.common.entity.Page;
import com.simple.common.utils.Result;
import com.simple.platform.entity.DataDict;
import com.simple.platform.service.DataDictService;




/**
 * 
 * @author liushx
 * @date 2017-12-23
 */
@Controller
@RequestMapping("system/dataDict")
public class DataDictController extends BaseController<DataDict, Long> {
	
    @Autowired
    private DataDictService dataDictService;
    
	@RequestMapping("list")
	public String list(Page<DataDict> page, Map<String, Object> params, ModelMap model) {
		
//		List<DataDict> dataDictList = dataDictService.findAll();
//		for (DataDict dataDict : dataDictList) {
//			System.out.println("name=="+dataDict.getDictName());
//		}
//		page.set
		page.setPage(3);
		params.put("dictType", "nation");
		
		dataDictService.queryList(params, page);
		List<DataDict> dataDictList = page.getDataList();
		for (DataDict dataDict : dataDictList) {
			System.out.println("id=="+dataDict.getDictId()+"&&name==" + dataDict.getDictName());
		}
		model.put("page", page);
		
		return "/system/dataDict/dataDict_list";	
	}


    @RequestMapping("edit")
    public String edit(DataDict dataDict, ModelMap model) {

        if (dataDict.getDictId() != null) {
            dataDict = dataDictService.get(dataDict.getDictId());
        } 

        model.put("bean", dataDict);

        return "system/dataDict/dataDict_edit";
    }


    @ResponseBody
    @RequestMapping("save")
    public Result save(DataDict dataDict) {
          
		dataDictService.create(dataDict);
		return Result.SUCCESS;
    }
    
	
	@ResponseBody
	@RequestMapping("remove")
	public Result remove(Long id) {

		try {
			dataDictService.deleteById(id);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Result.FAILURE;
		}

	}
    
    
}
