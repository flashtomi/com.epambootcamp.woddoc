package com.epambootcamp.woddoc.web.controller;


import com.epambootcamp.woddoc.model.WeightPR;
import com.epambootcamp.woddoc.service.WeightPRService;
import com.epambootcamp.woddoc.web.ExerciseNames;
import com.epambootcamp.woddoc.web.FlashMessage;
import com.epambootcamp.woddoc.web.WeightUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
public class WeightPRController {
    @Autowired
    private WeightPRService weightPRService;

    // Index of all personal records
    @SuppressWarnings("unchecked")
    @RequestMapping("/weightprs")
    public String listWeightPRs(Model model) {

        List<WeightPR> weightPRs = weightPRService.findAll();

        model.addAttribute("weightPRs", weightPRs);

        return "weightpr/index";
    }

    // Single record page
    @RequestMapping("/weightprs/{weightPRId}")
    public String weightPR(@PathVariable Long weightPRId, Model model) {

        WeightPR weightPR = null;

        model.addAttribute("weightPR", weightPR);
        return "weightpr/details";
    }

    // Form for adding a new record
    @RequestMapping("weightprs/add")
    public String formNewWeightPR(Model model) {

        if (!model.containsAttribute("weightPR")) {
            model.addAttribute("weightPR", new WeightPR());
        }
        model.addAttribute("exercises", ExerciseNames.values());
        model.addAttribute("weightUnits", WeightUnit.values());
        model.addAttribute("action", "/weightprs");
        model.addAttribute("heading", "New Weightlifting Personal Record");
        model.addAttribute("submit", "Add");

        return "weightpr/form";
    }

    // Form for editing an existing personal record
    @RequestMapping("weightprs/{weightPRId}/edit")
    public String formEditWeightPR(@PathVariable Long weightPRId, Model model) {

        if (!model.containsAttribute("weightPR")) {
            model.addAttribute("weightPR", weightPRService.findById(weightPRId));
        }
        model.addAttribute("exercises", ExerciseNames.values());
        model.addAttribute("action", String.format("/weightprs/%s", weightPRId));
        model.addAttribute("heading", "Edit Weightlifting Personal Record");
        model.addAttribute("submit", "Update");

        return "weightpr/form";
    }

    // Update an existing personal record
    @RequestMapping(value = "/weightprs/{weightPRId}", method = RequestMethod.POST)
    public String updateWeightPR(@Valid WeightPR weightPR, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.weightPR", result);

            // Add  weightpr if invalid was received
            redirectAttributes.addFlashAttribute("weightPR", weightPR);

            // Redirect back to the form
            return String.format("redirect:/weightprs/%s/edit", weightPR.getId());
        }

        weightPRService.save(weightPR);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category successfully updated!", FlashMessage.Status.SUCCESS));

        return "redirect:/weightprs";
    }

    // Add a personal record
    @RequestMapping(value = "/weightprs", method = RequestMethod.POST)
    public String addWeightPR(@Valid WeightPR weightPR, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.weightPR", result);

            // Add  weightpr if invalid was received
            redirectAttributes.addFlashAttribute("weightPR", weightPR);

            // Redirect back to the form
            return "redirect:/weightprs/add";
        }

        weightPRService.save(weightPR);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category successfully added!", FlashMessage.Status.SUCCESS));

        return "redirect:/weightprs";
    }

    // Delete an existing personal record
    @RequestMapping(value = "/weightprs/{weightPRId}/delete", method = RequestMethod.POST)
    public String deleteWeightPR(@PathVariable Long weightPRId, RedirectAttributes redirectAttributes) {
        WeightPR wtpr = weightPRService.findById(weightPRId);

        weightPRService.delete(wtpr);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Category deleted!", FlashMessage.Status.SUCCESS));

        return "redirect:/weightprs";
    }
}
