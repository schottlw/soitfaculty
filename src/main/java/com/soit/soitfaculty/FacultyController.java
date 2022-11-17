package com.soit.soitfaculty;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soit.soitfaculty.entity.Faculty;
import com.soit.soitfaculty.service.FacultyService;

@Controller
@RequestMapping("/Faculties")
public class FacultyController {
	
	public FacultyService facultyService;
	
	public FacultyController (FacultyService theFacultyService) {
		facultyService = theFacultyService;
	}
	
	//Mapping for "/list"
	@GetMapping("/list")
	public String listFaculties(Model theModel) {
		//Retrieve faculties from the database
		List<Faculty> theFaculties = facultyService.findAll();
		
		// Add Faculties to the Spring Model
		theModel.addAttribute("faculties", theFaculties);
		
		return "faculties/list-faculties";
	}
	
	@GetMapping("/viewAddForm")
	public String viewAddForm(Model theModel) {
		// Model attribute for data binding
		Faculty theFaculty = new Faculty();
		
		theModel.addAttribute("faculty", theFaculty);
		
		return "faculties/faculty-form";
	}
	
	@GetMapping("/viewUpdateForm")
	public String viewUpdateForm(@RequestParam("facultyId") int theId, Model theModel) {
		//Retrieve faculty info from service layer
		Faculty theFaculty = facultyService.findById(theId);
		
		//Pre-populate form by setting the faculty as a model attribute
		theModel.addAttribute("faculty", theFaculty);
		
		//Redirect to the faculty form
		return "faculties/faculty-form";
	}

	@PostMapping("/save")
	public String saveFaculty(@ModelAttribute("faculty") Faculty theFaculty) {
		// Register the Faculty
		facultyService.save(theFaculty);
		
		// Block duplicate submission for accidental refresh
		return "redirect:/Faculties/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("facultyId") int theId) {
		//Remove Faculty
		facultyService.deleteById(theId);
		
		//Redirect to Faculties directory
		return "redirect:/Faculties/list";
	}
}
