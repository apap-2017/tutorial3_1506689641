package com.example.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.tutorial3.service.StudentService;
import com.example.tutorial3.service.InMemoryStudentService;
import com.example.tutorial3.model.StudentModel;

@Controller
public class StudentController {
	private final StudentService studentService;
	
	public StudentController () {
		studentService = new InMemoryStudentService ();
	}
	
	@RequestMapping("/student/add")
	public String add(@RequestParam(value="npm", required=true) String npm,
			@RequestParam(value="name", required=true) String name,@RequestParam(value="gpa", required=true) double gpa) {
		StudentModel student = new StudentModel(name, npm, gpa);
		studentService.addStudent(student);
		return "add";
	}
	
	@RequestMapping("/student/delete")
	public String deleteNpm(@RequestParam(value="npm", required=true) String npm) {
		StudentModel student = new StudentModel(npm);
		if (npm.isEmpty() ||!studentService.checkNpm(npm)) {
			return "deleteError";
		} else {
			studentService.removeStudent(student);
			return "viewDelete";
		}
	}
	
//	@RequestMapping ( "/student/view")
//	public String view(Model model, @RequestParam(value="npm", required=true) String npm) {
//		StudentModel student = studentService.selectStudent(npm);
//		model.addAttribute("student", student);
//		return "view";
//	}
	
	@RequestMapping(value = {"student/view", "student/view/{npm}"})
	public String viewPath(@PathVariable Optional<String> npm, Model model) {
		if(npm.isPresent()) {
			StudentModel student = studentService.selectStudent(npm.get());
			if (studentService.checkNpm(npm.get())) {
				studentService.selectStudent(npm.get());
				model.addAttribute("student", student);
				return "view";
			} else {
				model.addAttribute("salah", "NPM tidak ditemukan atau kosong");
				return "viewError";
			}
		}else{
			model.addAttribute("salah", "NPM tidak ditemukan atau kosong");
			return "viewError";
		}
	}
	
	@RequestMapping("/student/viewall")
	public String viewAll(Model model) {
		List<StudentModel> students = studentService.selectAllStudents();
		model.addAttribute("students",students);
		return "viewall";
	}
	
	
}
