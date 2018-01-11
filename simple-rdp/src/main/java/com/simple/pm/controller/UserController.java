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
import com.simple.pm.entity.User;
import com.simple.pm.service.UserService;




/**
 * 
 * @author liushx
 * @date 2018-01-10
 */
@Controller
@RequestMapping("pm/user")
public class UserController extends BaseController<User, Integer> {

    @Autowired
    private UserService userService;
    
	@RequestMapping("list")
	public String list(Page<User> page, Map<String, Object> params, ModelMap model) {
		
		userService.queryList(params, page);
		model.put("page", page);
		
		return "/pm/user/user_list";	
	}


    @RequestMapping("edit")
    public String edit(User user, ModelMap model) {

        if (user.getUserId() != null) {
            user = userService.get(user.getUserId());
        } 

        model.put("bean", user);

        return "pm/user/user_edit";
    }


    @ResponseBody
    @RequestMapping("save")
    public Result save(User user) {
          
		userService.create(user);
		return Result.SUCCESS;
    }
    
	
	@ResponseBody
	@RequestMapping("remove")
	public Result remove(Integer userId) {

		try {
			userService.deleteById(userId);
			return Result.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return Result.FAILURE;
		}

	}
    
    
}
