// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.daiyida.api.web.customer;

import com.daiyida.api.domain.customer.Customer;
import com.daiyida.api.service.customer.CustomerService;
import com.daiyida.api.web.customer.CustomerController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect CustomerController_Roo_Controller {
    
    @Autowired
    CustomerService CustomerController.customerService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String CustomerController.create(@Valid Customer customer, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, customer);
            return "customers/create";
        }
        uiModel.asMap().clear();
        customerService.saveCustomer(customer);
        return "redirect:/customers/" + encodeUrlPathSegment(customer.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String CustomerController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Customer());
        return "customers/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String CustomerController.show(@PathVariable("id") String id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("customer", customerService.findCustomer(id));
        uiModel.addAttribute("itemId", id);
        return "customers/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String CustomerController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("customers", Customer.findCustomerEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) customerService.countAllCustomers() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("customers", Customer.findAllCustomers(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "customers/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String CustomerController.update(@Valid Customer customer, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, customer);
            return "customers/update";
        }
        uiModel.asMap().clear();
        customerService.updateCustomer(customer);
        return "redirect:/customers/" + encodeUrlPathSegment(customer.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String CustomerController.updateForm(@PathVariable("id") String id, Model uiModel) {
        populateEditForm(uiModel, customerService.findCustomer(id));
        return "customers/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String CustomerController.delete(@PathVariable("id") String id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Customer customer = customerService.findCustomer(id);
        customerService.deleteCustomer(customer);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/customers";
    }
    
    void CustomerController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("customer_birthday_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("customer_createtime_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("customer_modifytime_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void CustomerController.populateEditForm(Model uiModel, Customer customer) {
        uiModel.addAttribute("customer", customer);
        addDateTimeFormatPatterns(uiModel);
    }
    
    String CustomerController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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
