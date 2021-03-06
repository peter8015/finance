// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.daiyida.api.web.capital;

import com.daiyida.api.domain.capital.Capital;
import com.daiyida.api.service.capital.CapitalService;
import com.daiyida.api.web.capital.CapitalController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect CapitalController_Roo_Controller {
    
    @Autowired
    CapitalService CapitalController.capitalService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String CapitalController.create(@Valid Capital capital, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, capital);
            return "capital/create";
        }
        uiModel.asMap().clear();
        capitalService.saveCapital(capital);
        return "redirect:/capital/" + encodeUrlPathSegment(capital.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String CapitalController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Capital());
        return "capital/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String CapitalController.show(@PathVariable("id") String id, Model uiModel) {
        uiModel.addAttribute("capital", capitalService.findCapital(id));
        uiModel.addAttribute("itemId", id);
        return "capital/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String CapitalController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("capitals", Capital.findCapitalEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) capitalService.countAllCapitals() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("capitals", Capital.findAllCapitals(sortFieldName, sortOrder));
        }
        return "capital/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String CapitalController.update(@Valid Capital capital, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, capital);
            return "capital/update";
        }
        uiModel.asMap().clear();
        capitalService.updateCapital(capital);
        return "redirect:/capital/" + encodeUrlPathSegment(capital.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String CapitalController.updateForm(@PathVariable("id") String id, Model uiModel) {
        populateEditForm(uiModel, capitalService.findCapital(id));
        return "capital/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String CapitalController.delete(@PathVariable("id") String id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Capital capital = capitalService.findCapital(id);
        capitalService.deleteCapital(capital);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/capital";
    }
    
    void CapitalController.populateEditForm(Model uiModel, Capital capital) {
        uiModel.addAttribute("capital", capital);
    }
    
    String CapitalController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
