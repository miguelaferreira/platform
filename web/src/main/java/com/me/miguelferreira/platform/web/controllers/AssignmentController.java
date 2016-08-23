package com.me.miguelferreira.platform.web.controllers;

import com.me.miguelferreira.platform.data.repositories.AssignmentRepository;
import com.me.miguelferreira.platform.data.repositories.RequesterRepository;
import com.me.miguelferreira.platform.model.Assignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AssignmentController {

    private static final Logger logger = LoggerFactory.getLogger(AssignmentController.class);

    private final AssignmentRepository assignmentRepository;
    private final RequesterRepository requesterRepository;

    @Autowired
    public AssignmentController(final AssignmentRepository assignmentRepository, final RequesterRepository requesterRepository) {
        this.assignmentRepository = assignmentRepository;
        this.requesterRepository = requesterRepository;
    }

    @RequestMapping(value = "/assignment", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute(value = "assignment") final Assignment assignment, final BindingResult bindingResult) {
        logger.info("Creating Assignment");
        logger.debug("Creating Assignment: {}", assignment);
        logger.debug("Creating Assignment: {}", bindingResult);
        assignment.setRequester(requesterRepository.findOne(assignment.getRequester().getId()));
        assignmentRepository.save(assignment);
        return show();
    }

    @RequestMapping(value = "/assignment", method = RequestMethod.GET)
    public ModelAndView show() {
        final ModelAndView mav = new ModelAndView("assignmentView");
        mav.addObject("requesters", requesterRepository.findAll());
        mav.addObject("assignment", new Assignment());
        mav.addObject("assignments", assignmentRepository.findAll());
        return mav;
    }

}
