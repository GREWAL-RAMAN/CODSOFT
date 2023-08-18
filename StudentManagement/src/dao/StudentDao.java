package dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import bean.Student;

public class StudentDao {
	public static boolean addStudent(Student s, Session y) {
		try {
			y.beginTransaction();
			y.persist(s);
			y.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println("addStudent operation Failed");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			y.getTransaction().rollback();
			return false;
		}
         return true;
	}

	public static Student getStudentByID(Session s, Long id) {
		Student student = null;
		try {
			s.beginTransaction();
			student = s.get(Student.class, id);
			if (student == null) {
				System.out.println("bo such student exist");
			} else {
				System.out.println("Success!");
			}
			s.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return student;
	}

	public static String RemoveStudentById(Session ses, Long id) {
		try {
			ses.beginTransaction();
			Student student = ses.get(Student.class, id);
			if (student == null) {
				return "The specified entity does not exists";
			}
			ses.remove(student);
			ses.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Failed to delete student");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
		return "The specified entity is removed ";
	}
	
	public static ArrayList<Student> GetAllStudent(Session ses)
	{
		ArrayList<Student>students=null;
		try {	
			ses.beginTransaction();
			String hql = "FROM Student ";
			Query<Student> query =ses.createQuery(hql, Student.class);
			 students = (ArrayList<Student>) query.getResultList();
		
			ses.getTransaction().commit();
		}catch (Exception e) {
			System.out.println("Failed to get All Student");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		
		}
		
			
		return students;
	}
	
	public static String UpdateStudent(Session ses,Student s)
	{
		try {
			ses.beginTransaction();
			Student student=ses.get(Student.class, s.getAdmission_no());
		
			if(student==null)
			{
				return "The Specified entity doesnot exists";
			}
			student.setName(s.getName());
			student.setContact(s.getContact());
			student.setGrade(s.getGrade());
			student.setYear(s.getYear());
			student.setGuardian_name(s.getGuardian_name());
			student.setRollNumber(s.getRollNumber());
			ses.persist(student);
			ses.getTransaction().commit();
		
		}catch (Exception e) {
			
			ses.getTransaction().rollback();
			e.getStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			return "The updation operation failed!";
		}
		
		return "The updation operation successful!";
	}
	public static ArrayList<Student> GetFilterStudent(Session ses, String filter)
	{
		ArrayList<Student>students=null;
		try {	
			ses.beginTransaction();
			String hql = "FROM Student " +filter;
			Query<Student> query =ses.createQuery(hql, Student.class);
			 students = (ArrayList<Student>) query.getResultList();
		
			ses.getTransaction().commit();
		}catch (Exception e) {
			System.out.println("Failed to get All Student");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			ses.getTransaction().rollback();
			
		}
		
			
		return students;
	}
	

}
