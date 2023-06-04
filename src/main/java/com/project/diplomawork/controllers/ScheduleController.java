package com.project.diplomawork.controllers;


import com.project.diplomawork.services.PairService;
import com.project.diplomawork.services.ScheduleService;
import com.project.diplomawork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

    private final UserService userService;
    private final ScheduleService scheduleService;
    private final PairService pairService;
    @GetMapping("/schedule")
    public String schedule(Principal principal, Model model) {
        var user = userService.findUserByEmail(principal.getName());
        scheduleService.generateSchedule(user.getGroup());
        var schedule = scheduleService.findByGroup(user.getGroup());
        var pairsM = pairService.getPairsByDay("Понедельник", schedule);
        var pairsT = pairService.getPairsByDay("Вторник", schedule);
        var pairsW = pairService.getPairsByDay("Среда", schedule);
        var pairsTh = pairService.getPairsByDay("Четверг", schedule);
        var pairsF = pairService.getPairsByDay("Пятница", schedule);
        var pairsS = pairService.getPairsByDay("Суббота", schedule);

        model.addAttribute("monday", pairsM);
        model.addAttribute("tuesday", pairsT);
        model.addAttribute("wednesday", pairsW);
        model.addAttribute("thursday", pairsTh);
        model.addAttribute("friday", pairsF);
        model.addAttribute("saturday", pairsS);

        model.addAttribute("schedule", schedule);
        model.addAttribute("user", user);
        return "schedule";
    }

}
