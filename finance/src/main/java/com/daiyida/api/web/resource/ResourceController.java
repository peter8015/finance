package com.daiyida.api.web.resource;
import com.daiyida.api.domain.resource.Resource;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/resources")
@Controller
@RooWebScaffold(path = "resources", formBackingObject = Resource.class)
@RooWebJson(jsonObject = Resource.class)
public class ResourceController {
	
}
