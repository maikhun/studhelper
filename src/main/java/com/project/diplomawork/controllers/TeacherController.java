package com.project.diplomawork.controllers;

import com.project.diplomawork.services.PairService;
import com.project.diplomawork.services.SubjectService;
import com.project.diplomawork.services.TeacherService;
import com.project.diplomawork.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class TeacherController {

    private final UserService userService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;

    @GetMapping("/teachers")
    public String teachers(Model model, Principal principal) {
        var user = userService.findUserByEmail(principal.getName());
        var teachers = teacherService.findTeachersByGroup(user.getGroup());
        model.addAttribute("teachers", teachers);
        model.addAttribute("student", user);
        return "teachers";
    }

    @GetMapping("/teachers/{id}")
    public String definiteStudent(@PathVariable Long id, Model model, Principal principal) {
        var student = userService.findUserByEmail(principal.getName());
        var teacher = teacherService.findById(id).get();
        var subjects = subjectService.findSubjectsByPair(teacherService.getPairsByTeacher(teacher));

        var contantIsTrue = teacher.getEmail() == null && teacher.getVkUrl() == null;

        boolean emailIsEnable = teacher.getEmail() != null;
        boolean vkIsEnable = teacher.getVkUrl() != null;

        model.addAttribute("contact", contantIsTrue);
        model.addAttribute("email", emailIsEnable);
        model.addAttribute("subjects", subjects);
        model.addAttribute("vk", vkIsEnable);
        model.addAttribute("teacher", teacher);
        model.addAttribute("currentStudent", student);
        return "teacher-page";
    }

}
