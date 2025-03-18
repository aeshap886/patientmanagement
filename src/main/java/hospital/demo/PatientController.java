package hospital.demo;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
        
    }

   @GetMapping("/addpatients")
   public String showAddpatient(Model model) {
	   Patient patient= new Patient(); 
	   model.addAttribute("patient", patient);
	   
	   return "addpatients";
   
	   //   
//    public List<Patient> getAllPatients() {  //JSON
//        return patientService.getAllPatients();
//    }
//    
//    public String showPatients(Model model) {
//     List<Patient> patients = patientService.getAllPatients();
//     List<String> doctorNames = Arrays.asList("Dr. Krisha", "Dr. Prince", "Dr. Neel");
//
//     model.addAttribute("patients", patients);
//     model.addAttribute("doctorNames", doctorNames);
//        return "patient";        
    }
       
//    @GetMapping("/{id}") //JSON
//    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
//        Patient patient = patientService.getPatientById(id);
//        if (patient == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(patient);
//    }
    
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
        return "redirect:/patient/";
    }

  
//    @PostMapping("/patients")
//    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {  //  JSON 
//        patientService.addPatient(patient);
//        return ResponseEntity.ok(patient);
//    }


    @PostMapping("/patients")
    public String addPatient(@ModelAttribute Patient patient) {
    	patientService.addPatient(patient);
        return "redirect:/patient?Success";
    }

    
    @DeleteMapping("/{id}")
    
    
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully!");
    }
}
