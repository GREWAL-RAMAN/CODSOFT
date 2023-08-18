package bean;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Admission_no;
    @Column(name ="StudentName")
    private String name;
    @Column(name="StudentRollNumber")
    private Long rollNumber;
    @Column(name="studentGrade")
    private String grade;
    @Column(name="StudentContactNumber")
    private Long contact;
    @Column(name="GuardianName")
    private String Guardian_name;
    @Column(name="SessionYear")
    private String Year;

    

    public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public Student(String name, Long rollNumber, String grade, Long contact, String guardian_name, String year) {
		super();
		this.name = name;
		this.rollNumber = rollNumber;
		this.grade = grade;
		this.contact = contact;
		Guardian_name = guardian_name;
		Year = year;
	}

	  @Override
	    public Student clone() {
	        try {
	            return (Student) super.clone();
	        } catch (CloneNotSupportedException e) {
	            throw new AssertionError(); // Should never happen
	        }
	    }

	public Student(Student newStudent) {
		this.Admission_no=newStudent.getAdmission_no();
		this.name = newStudent.getName();
		this.rollNumber = newStudent.getRollNumber();
		this.grade = newStudent.getGrade();
		this.contact = newStudent.getContact();
		Guardian_name = newStudent.getGuardian_name();
		Year = newStudent.getYear();
    }

	public Student() {
    }

    public Long getAdmission_no() {
        return Admission_no;
    }

    public void setAdmission_no(Long admission_no) {
        Admission_no = admission_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(Long rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public String getGuardian_name() {
        return Guardian_name;
    }

    public void setGuardian_name(String guardian_name) {
        Guardian_name = guardian_name;
    }

	@Override
	public String toString() {
		return "Student [Admission_no=" + Admission_no + ", name=" + name + ", rollNumber=" + rollNumber + ", grade="
				+ grade + ", contact=" + contact + ", Guardian_name=" + Guardian_name + ", Year=" + Year + "]";
	}


}
