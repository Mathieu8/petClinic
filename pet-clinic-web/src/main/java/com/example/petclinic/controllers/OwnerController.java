package com.example.petclinic.controllers;

import com.example.petclinic.model.Owner;
import com.example.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {
    private static final String VIEW_MAP = "owners/";
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE = VIEW_MAP + "createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

//    @RequestMapping({"","/", "/index", "/index.html"})
//    public String listOwners(Model model){
//
//        model.addAttribute("owners" ,  ownerService.findAll());
//        return "owners/index";
//    }

    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());

        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("")
    public String processFindForm(Owner owner, BindingResult result,Model model){
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        List<Owner> results = ownerService.findByLastNameContaining(owner.getLastName());

        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping(("/new"))
    public String initCreationForm(Model model){
        model.addAttribute("owner", Owner.builder().build());

        return VIEWS_OWNER_CREATE_OR_UPDATE;
    }

    @PostMapping("/new")
    public String processCreationForm(@Validated Owner owner, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE;
        } else {
            ownerService.save(owner);
        return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping(("/{id}/edit"))
    public String initUpdateOwner(Model model, @PathVariable Long id){
        System.out.println("id " + id);
        Owner owner = ownerService.findById(id);
        model.addAttribute("owner", owner);
        System.out.println("owner.name() " + owner.getLastName());
        return VIEWS_OWNER_CREATE_OR_UPDATE;
    }

    @PostMapping("/{id}/edit")
    public String updateOwner(@PathVariable Long id, @Validated Owner owner, BindingResult result){
        if(result.hasErrors()){
            return VIEWS_OWNER_CREATE_OR_UPDATE;
        } else {
            owner.setId(id);
            ownerService.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }



}
