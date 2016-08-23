package com.me.miguelferreira.platform.web.controllers;

import com.me.miguelferreira.platform.data.repositories.ProviderRepository;
import com.me.miguelferreira.platform.model.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProviderController {

    private final ProviderRepository repository;

    @Autowired
    public ProviderController(final ProviderRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/provider", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute final Provider provider) {
        repository.save(provider);
        return show();
    }

    @RequestMapping(value = "/provider", method = RequestMethod.GET)
    public ModelAndView show() {
        final ModelAndView mav = new ModelAndView("providerView");
        mav.addObject("provider", new Provider());
        mav.addObject("providers", repository.findAll());
        return mav;
    }

}
