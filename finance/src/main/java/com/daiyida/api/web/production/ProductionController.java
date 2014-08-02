package com.daiyida.api.web.production;
import com.daiyida.api.domain.production.Production;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;

@RequestMapping("/productions")
@Controller
@RooWebScaffold(path = "productions", formBackingObject = Production.class)
@RooWebJson(jsonObject = Production.class)
public class ProductionController {

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Production production, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, production);
            return "productions/create";
        }
        uiModel.asMap().clear();
        productionService.saveProduction(production);
        return "redirect:/productions/" + encodeUrlPathSegment(production.getId().toString(), httpServletRequest);
    }
}
