package com.project.diplomawork.controllers;

import com.project.diplomawork.services.SubjectService;
import com.project.diplomawork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class SubjectController {

    private final UserService userService;
    private final SubjectService subjectService;

    @GetMapping("/subjects")
    public String subjects(Model model, Principal principal) {
        var user = userService.findUserByEmail(principal.getName());
        var subjects = subjectService.findSubjectsByGroup(user.getGroup());
        model.addAttribute("subjects", subjects);
        model.addAttribute("student", user);
        return "subjects";
    }

    @GetMapping("/subjects/{id}")
    public String definiteStudent(@PathVariable Long id, Model model, Principal principal) {
        var student = userService.findUserByEmail(principal.getName());
        var subject = subjectService.findById(id).get();
        model.addAttribute("subject", subject);
        model.addAttribute("currentStudent", student);
        return "subject-page";
    }

}
