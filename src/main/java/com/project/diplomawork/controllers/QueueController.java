package com.project.diplomawork.controllers;

import com.project.diplomawork.models.VirtualQueue;
import com.project.diplomawork.services.PairService;
import com.project.diplomawork.services.SubjectService;
import com.project.diplomawork.services.UserService;
import com.project.diplomawork.services.VirtualQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class QueueController {

    private final PairService pairService;
    private final UserService userService;
    private final VirtualQueueService virtualQueueService;

    @GetMapping("/queue/{id}")
    public String getQueuePage(@PathVariable Long id, Principal principal, Model model) {
        var user = userService.findUserByEmail(principal.getName());
        var pair = pairService.getPairById(id).get();
        model.addAttribute("student", user);
        model.addAttribute("pair", pair);
        return "virtualqueue";
    }

    @PostMapping("/adduserintoqueue/{id}")
    public String addUserIntoQueue(@PathVariable Long id,
                                   Principal principal, Model model) {
        var pair = pairService.getPairById(id).get();
        var user = userService.findUserByEmail(principal.getName());
        if (virtualQueueService.addUserInQueue(user, pair))
            return "redirect:/schedule";
        String error = "Возникла ошибка при записи в очередь";
        model.addAttribute("error", error);
        return "virtualqueue";
    }

    @GetMapping("/listqueue/{id}")
    public String getQueueListPage(@PathVariable Long id, Principal principal, Model model) {
        var user = userService.findUserByEmail(principal.getName());
        var pair = pairService.getPairById(id).orElse(null);
        var virtualQueue = virtualQueueService.findByPair(pair);
        if (virtualQueue == null) {
            return "redirect:/schedule";
        }
        model.addAttribute("currentUser", user);
        model.addAttribute("virtualQueue", virtualQueue);
        return "virtualqueuelist";
    }

}
