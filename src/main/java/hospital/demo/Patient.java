package hospital.demo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensures ID is auto-incremented
    private long id;

    private String name;
    private int age;
    private String disease;
    private String doctorName;

    // Default Constructor (Required for JPA)
    public Patient() {
    }

    // Parameterized Constructor
    public Patient(String name, int age, String disease,String doctorName) {
        this.name = name;
        this.age = age;
        this.disease = disease;
        this.doctorName = doctorName;
    }

  
	// Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }
    
    public String getDoctorName() {
  		return doctorName;
  	}

  	public void setDoctorName(String doctorName) {
  		this.doctorName = doctorName;
  	}

}
