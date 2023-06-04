package com.project.diplomawork.controllers;

import com.project.diplomawork.models.Schedule;
import com.project.diplomawork.models.User;
import com.project.diplomawork.services.GroupService;
import com.project.diplomawork.services.ScheduleService;
import com.project.diplomawork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final GroupService groupService;
    private final ScheduleService scheduleService;
    // Начальная страница
    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    // Собственная страница авторизации
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Собственная страница регистрации
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    // Обработка данных с формы регистрации
    @PostMapping("/registration")
    public String createUser(User user) {
        if (!userService.createUser(user))
            return "registration";
        groupService.addUserInGroup(user);
        return "redirect:/login";
    }

    // Основная страница после авторизации
    @RequestMapping("/main-page")
    public String mainPage(Model model, Principal principal) {
        var user = userService.findUserByEmail(principal.getName());
        model.addAttribute("currentUser", user);
        return "main-page";
    }

}
