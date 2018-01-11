package com.simple.pm.controller;

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
import com.simple.pm.entity.Bugs;
import com.simple.pm.service.BugsService;




/**
 * 
 * @author liushx
 * @date 2018-01-10
 */
@Controller
@RequestMapping("pm/bugs")
public class BugsController extends BaseController<Bugs, Long> {

    @Autowired
    private BugsService bugsService;
    
	@RequestMapping("list")
	public String list(Page<Bugs> page, Map<String, Object> params, ModelMap model) {
		
		bugsService.queryList(params, page);
		model.put("page", page);
		
		return "/pm/bugs/bugs_list";	
	}


    @RequestMapping("edit")
    public String edit(Bugs bugs, ModelMap model) {

        if (bugs.getBugId() != null) {
            bugs = bugsService.get(bugs.getBugId());
        } 

        model.put("bean", bugs);

        return "pm/bugs/bugs_edit";
    }


    @ResponseBody
    @RequestMapping("save")
    public Result save(Bugs bugs) {
          
		bugsService.create(bugs);
		return Result.SUCCESS;
    }
    
	
	@ResponseBody
	@RequestMapping("remove")
	public Result remove(Long bugId) {

		try {
			bugsService.deleteById(bugId);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Result.FAILURE;
		}

	}
    
    
}
