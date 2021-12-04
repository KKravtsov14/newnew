package groupProjectPart1.ui.mvc;

import groupProjectPart1.ui.DoctorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import groupProjectPart1.ui.Doctor;
import groupProjectPart1.ui.MysqlRepository;

@Controller
public class DoctorController {

	@Autowired
	private DoctorRepository doctorRepository;

	@RequestMapping(value="/doctorCab",method=RequestMethod.GET)
	public String doctorCab(Model model) {
		Iterable<Doctor> doctors = doctorRepository.findAll();
		model.addAttribute("doctors", doctors);
		return "doctorCab";
	}

	@RequestMapping(value="/autorize",method=RequestMethod.GET)
	public String autorizeGet(Model model) {
		return "autorize";
	}

	@RequestMapping(value="/autorize",method=RequestMethod.POST)
	public String autorizePost(@RequestParam String text, @RequestParam String text2, Model model) {
		//Doctor doctor = new Doctor(text, text2);
		//doctorRepository.save(doctor);
		//Iterable<Doctor> doctors = doctorRepository.findAll();
		//model.addAttribute("doctors", doctors);
		System.out.println(text);
		System.out.println(text2);
		return "autorize";

	}

}