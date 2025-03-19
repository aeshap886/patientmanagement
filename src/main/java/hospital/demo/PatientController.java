package hospital.demo;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hospital")
public class PatientController {

    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
        
    }

   @GetMapping("/addpatients")
   public String showAddpatient(Model model) {
	   Patient patient= new Patient(); 
	   model.addAttribute("patient", patient);
	   model.addAttribute("doctors",patientService.getAlldoctors());  
	   return "addpatients";
   }
   
    

    @PostMapping("/patients")
    public String addPatient(@ModelAttribute Patient patient) {
    	System.out.println(patient.getDoctorName());
    	patientService.addPatient(patient);
        return "redirect:/hospital/patients";
    }
    
    @GetMapping("/patients")
    public String showPatients(Model model) {
     List<Patient> patients = patientService.getAllPatients();

     model.addAttribute("patients", patients);
        return "patient";        
    }
       
 @GetMapping("/{id}")
 public String showPatientDetail(@PathVariable Long id, Model model) {
    Patient patient = patientService.getPatientById(id);
    if(patient == null) {
 	   return "error";
    }
    model.addAttribute("patient", patient);
    	return "patient";
 }


    @PostMapping("/assignDoctor")
    public String assignDoctor(@RequestParam Long patientId, @RequestParam String doctorName) {
        Patient patient = patientService.getPatientById(patientId);
        if (patient != null) {
            patient.setDoctorName(doctorName);
            patientService.addPatient(patient);
        }
        return "redirect:/hospital/patients/";
    }

    
    @DeleteMapping("/{id}")
    
    
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully!");
    }
}
