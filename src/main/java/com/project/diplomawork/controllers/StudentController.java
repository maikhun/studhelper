package com.project.diplomawork.controllers;

import com.project.diplomawork.models.User;
import com.project.diplomawork.services.GroupService;
import com.project.diplomawork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final UserService userService;
    private final GroupService groupService;

    @GetMapping("/students")
    public String students(Model model, Principal principal) {
        var user = userService.findUserByEmail(principal.getName());
        List<User> userGroup = groupService.getGroupByNumber(user.getGroupNumber()).getUsers();
        model.addAttribute("groupStudents", userGroup);
        model.addAttribute("student", user);
        return "students";
    }

    @GetMapping("/students/{id}")
    public String definiteStudent(@PathVariable Long id, Principal principal,Model model) {
        var student = userService.findById(id).get();
        var user = userService.findUserByEmail(principal.getName());
        boolean isTrue = student.getGithubUrl() == null && student.getVkUrl() == null && student.getTelegram() == null;
        boolean telegramIsEnable = student.getTelegram() != null;
        boolean vkIsEnable = student.getVkUrl() != null;
        boolean githubIsEnable = student.getGithubUrl() != null;

        model.addAttribute("user", user);
        model.addAttribute("telegram", telegramIsEnable);
        model.addAttribute("urlValid", isTrue);
        model.addAttribute("vk", vkIsEnable);
        model.addAttribute("github", githubIsEnable);
        model.addAttribute("currentStudent", student);
        return "student-page";
    }

    @GetMapping("/students/{id}/change-student-page")
    public String getChangeStudentInfo(@PathVariable Long id, Principal principal, Model model) {
        var student = userService.findById(id).get();
        model.addAttribute("student", student);
        var userStudent = userService.findUserByEmail(principal.getName());
        if (id != userStudent.getId()){
            return "redirect:/students/{id}";
        }
        return "change-student-page";
    }

    @PostMapping("/students/{id}/change-student-page")
    public String changeStudentInfo(@PathVariable Long id, User user, Model model) {
        if (!userService.updateUser(user))
            return "redirect:/students";
        return "redirect:/students/{id}";

    }


}
