package com.realestate.courseproject.controller;

import com.realestate.courseproject.model.Apartment;
import com.realestate.courseproject.repository.ApartmentRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("admin")
public class AdminController {

    @Autowired
    ApartmentRepo apartmentRepo;

    @GetMapping("/displayApartments")
    public ModelAndView displayApartments(Model model){
        System.out.println(Apartment.Type.values());
        List<Apartment> apartments = apartmentRepo.findAll();
        ModelAndView modelAndView = new ModelAndView("apartments_secure.html");
        modelAndView.addObject("apartments",apartments);
        modelAndView.addObject("apartment", new Apartment());
        //Apartment.Type[] types = Apartment.Type.values();
        modelAndView.addObject("apartTypes", Apartment.Type.values());
        return modelAndView;
    }



    @PostMapping("/addNewApartment")
    public ModelAndView addNewApartment(Model model, @ModelAttribute("apartment") Apartment apartment) {
        ModelAndView modelAndView = new ModelAndView();
        apartmentRepo.save(apartment);
        modelAndView.setViewName("redirect:/admin/displayApartments");
        return modelAndView;
    }
}