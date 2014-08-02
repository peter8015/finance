package com.daiyida.api.web.role;
import com.daiyida.api.domain.role.Role;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/role")
@Controller
@RooWebScaffold(path = "role", formBackingObject = Role.class)
@RooWebJson(jsonObject = Role.class)
public class RoleController {
}
