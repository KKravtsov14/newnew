/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package doctor.ui.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import doctor.ui.Doctor;
import doctor.ui.DoctorRepository;


@Controller
@RequestMapping("/")
public class DoctorController {
	private final DoctorRepository doctorRepository;

	@Autowired
	public DoctorController(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	@RequestMapping
	public ModelAndView doctorCab() {
		Iterable<Doctor> doctors = this.doctorRepository.findAll();
		return new ModelAndView("doctors/doctorCab", "doctors", doctors);
	}

	@RequestMapping("{id}")
	public ModelAndView view(@PathVariable("id") Doctor message) {
		return new ModelAndView("doctors/view", "doctors", message);
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(@ModelAttribute Doctor message) {
		return "doctors/form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView create(@Valid Doctor doctor, BindingResult result,
							   RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("doctors/form", "formErrors", result.getAllErrors());
		}
		doctor = this.doctorRepository.save(doctor);
		redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
		return new ModelAndView("redirect:/{doctors.id}", "doctors.id", doctor.getId());
	}

	@RequestMapping("foo")
	public String foo() {
		throw new RuntimeException("Expected exception in controller");
	}

}
