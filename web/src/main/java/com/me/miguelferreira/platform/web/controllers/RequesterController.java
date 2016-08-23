package com.me.miguelferreira.platform.web.controllers;

import com.me.miguelferreira.platform.data.repositories.RequesterRepository;
import com.me.miguelferreira.platform.model.Provider;
import com.me.miguelferreira.platform.model.Requester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequesterController {

    private final RequesterRepository repository;

    @Autowired
    public RequesterController(final RequesterRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/requester", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute final Requester requester) {
        repository.save(requester);
        return show();
    }

    @RequestMapping(value = "/requester", method = RequestMethod.GET)
    public ModelAndView show() {
        final ModelAndView mav = new ModelAndView("requesterView");
        mav.addObject("requester", new Provider());
        mav.addObject("requesters", repository.findAll());
        return mav;
    }


}
