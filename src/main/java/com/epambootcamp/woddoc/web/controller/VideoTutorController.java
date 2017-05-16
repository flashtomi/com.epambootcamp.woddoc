package com.epambootcamp.woddoc.web.controller;

import com.epambootcamp.woddoc.model.VideoTutor;
import com.epambootcamp.woddoc.web.FlashMessage;
import com.epambootcamp.woddoc.web.YouTubeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.epambootcamp.woddoc.service.VideoTutorService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
public class VideoTutorController {
    @Autowired
    private VideoTutorService videoTutorService;

    // Home page
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    // Index of all videos
    @RequestMapping("/videotutors")
    public String listVideos(Model model) {

        List<VideoTutor> videoTutors = videoTutorService.findAll();
        YouTubeHelper youTubeID = new YouTubeHelper();

        model.addAttribute("videoTutors", videoTutors);
        model.addAttribute("YouTubeHelper", youTubeID);

        return "videotutor/index";
    }

    // Single video page
    @RequestMapping("/videotutors/{videoId}")
    public String videoTutor(@PathVariable Long videoId, Model model) {

        VideoTutor videoTutor = null;

        model.addAttribute("videoTutor", videoTutor);
        return "videotutor/details";
    }

    // Form for adding a new video
    @RequestMapping("videotutors/add")
    public String formNewVideo(Model model) {

        if (!model.containsAttribute("videoTutor")) {
            model.addAttribute("videoTutor", new VideoTutor());
        }
        model.addAttribute("action", "/videotutors");
        model.addAttribute("heading", "New Video");
        model.addAttribute("submit", "Add");

        return "videotutor/form";
    }

    // Form for editing an existing video
    @RequestMapping("videotutors/{videoId}/edit")
    public String formEditVideo(@PathVariable Long videoId, Model model) {

        if (!model.containsAttribute("videoTutor")) {
            model.addAttribute("videoTutor", videoTutorService.findById(videoId));
        }
        model.addAttribute("action", String.format("/videotutors/%s", videoId));
        model.addAttribute("heading", "Edit Video");
        model.addAttribute("submit", "Update");

        return "videotutor/form";
    }

    // Update an existing video
    @RequestMapping(value = "/videotutors/{videoId}", method = RequestMethod.POST)
    public String updateVideo(@Valid VideoTutor videoTutor, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.videoTutor", result);

            // Add  videotutor if invalid was received
            redirectAttributes.addFlashAttribute("videoTutor", videoTutor);

            // Redirect back to the form
            return String.format("redirect:/videotutors/%s/edit", videoTutor.getId());
        }

        videoTutorService.save(videoTutor);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Video successfully updated!", FlashMessage.Status.SUCCESS));

        return "redirect:/videotutors";
    }

    // Add a video tutorial
    @RequestMapping(value = "/videotutors", method = RequestMethod.POST)
    public String addVideo(@Valid VideoTutor videoTutor, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.videoTutor", result);

            // Add  videotutor if invalid was received
            redirectAttributes.addFlashAttribute("videoTutor", videoTutor);

            // Redirect back to the form
            return "redirect:/videotutors/add";
        }

        videoTutorService.save(videoTutor);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Video successfully added!", FlashMessage.Status.SUCCESS));

        return "redirect:/videotutors";
    }

    // Delete an existing video
    @RequestMapping(value = "/videotutors/{videoId}/delete", method = RequestMethod.POST)
    public String deleteVideo(@PathVariable Long videoId, RedirectAttributes redirectAttributes) {
        VideoTutor vid = videoTutorService.findById(videoId);

        videoTutorService.delete(vid);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Video deleted!", FlashMessage.Status.SUCCESS));

        return "redirect:/videotutors";
    }


}
