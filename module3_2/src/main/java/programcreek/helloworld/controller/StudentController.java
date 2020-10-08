package programcreek.helloworld.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.Student;
import service.StudentService;;

@Controller
@Configuration
@ComponentScan("service")
public class StudentController {
	@Autowired
	private StudentService studentservice;

	@RequestMapping(value = "/pwdForget", method = RequestMethod.GET)
	public String pwdForget(@ModelAttribute Student student, Model model) {
		return "pwdForget";
	}

	@RequestMapping(value = "/pwdForget", method = RequestMethod.POST)
	public String pwdForget(@RequestParam String sno, Model model) {
		Student check;
		try {

			check = studentservice.getOne(sno);

			if (check.getSno().equals(sno)) {
				String npwd = studentservice.updatePwd(sno);
				check.setSpwd(npwd);
				studentservice.MailSend(check);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return "redirect:/index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute("Student") Student student) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("Student") Student student, BindingResult result,
			HttpServletRequest request) {
		studentservice.insertStudent(student);
//		studentservice.conClose();
		return "redirect:/index";
	}

	@RequestMapping(value = "/showStudent", method = RequestMethod.GET)
	public String showStudents(Model model) {
		ArrayList<Student> list;
		try {
			list = studentservice.selectStudent();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showStudent";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute Student student, Model model) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String sno, @RequestParam String spwd, Model model) {
		Student check;
		try {

			check = studentservice.getOne(sno);
			System.out.println("DB pwd: " + check.getSpwd());
			System.out.println("input pwd: " + spwd);

			if (check.getSno().equals(sno) && check.getSpwd().equals(spwd)) {
				model.addAttribute("list", check);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return "one";
	}

	@RequestMapping(value = "/getOne", method = RequestMethod.POST)
	public String getOne(@RequestParam("sno") String sno, Model model) {
		Student student = null;
		try {
			student = studentservice.getOne(sno);
			model.addAttribute("list", student);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "one";
	}

	@InitBinder
	public void whiteListing(WebDataBinder binder) {
		binder.setAllowedFields("sno", "sname", "smail", "spwd");
	}

}
