package cn.simple.pm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/simplejson")
public class SimpleJsonController {

	@RequestMapping(value = "view")
	public String view(ModelMap modelMap) {
		return "simplejson/simplejson_view";
	}

	@RequestMapping("edit")
	public String edit(Long docsId, ModelMap modelMap) {

		return "simplejson/simplejson_edit";

	}

}
