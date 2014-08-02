// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.daiyida.api.web.bankcard;

import com.daiyida.api.domain.bankcard.BankCard;
import com.daiyida.api.service.bankcard.BankCardService;
import com.daiyida.api.web.bankcard.BankCardController;
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

privileged aspect BankCardController_Roo_Controller {
    
    @Autowired
    BankCardService BankCardController.bankCardService;
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String BankCardController.create(@Valid BankCard bankCard, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, bankCard);
            return "bankcards/create";
        }
        uiModel.asMap().clear();
        bankCardService.saveBankCard(bankCard);
        return "redirect:/bankcards/" + encodeUrlPathSegment(bankCard.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String BankCardController.createForm(Model uiModel) {
        populateEditForm(uiModel, new BankCard());
        return "bankcards/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String BankCardController.show(@PathVariable("id") String id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("bankcard", bankCardService.findBankCard(id));
        uiModel.addAttribute("itemId", id);
        return "bankcards/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String BankCardController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("bankcards", BankCard.findBankCardEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) bankCardService.countAllBankCards() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("bankcards", BankCard.findAllBankCards(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "bankcards/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String BankCardController.update(@Valid BankCard bankCard, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, bankCard);
            return "bankcards/update";
        }
        uiModel.asMap().clear();
        bankCardService.updateBankCard(bankCard);
        return "redirect:/bankcards/" + encodeUrlPathSegment(bankCard.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String BankCardController.updateForm(@PathVariable("id") String id, Model uiModel) {
        populateEditForm(uiModel, bankCardService.findBankCard(id));
        return "bankcards/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String BankCardController.delete(@PathVariable("id") String id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        BankCard bankCard = bankCardService.findBankCard(id);
        bankCardService.deleteBankCard(bankCard);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/bankcards";
    }
    
    void BankCardController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("bankCard_bindtime_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    void BankCardController.populateEditForm(Model uiModel, BankCard bankCard) {
        uiModel.addAttribute("bankCard", bankCard);
        addDateTimeFormatPatterns(uiModel);
    }
    
    String BankCardController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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