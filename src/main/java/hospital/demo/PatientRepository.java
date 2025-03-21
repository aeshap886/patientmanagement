package hospital.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
//	List<Patient> findAll();
//	
//	Patient findById(long id);
//	
//	//delete
//	
//	void deleteById(Long id);
	  @Query("SELECT DISTINCT p.doctorName FROM Patient p WHERE p.doctorName IS NOT NULL")
  List<String>findDistinctDoctors();
	
}
 	