package in.ineuron.factory;

import in.ineuron.service.IStudentService;
import in.ineuron.service.StudentService;

public class StudentServiceFactory {
	private static StudentService studentService=null;
	private StudentServiceFactory() {
		
	}
	public static IStudentService getStudentService() {
		if(studentService==null)
			studentService = new StudentService();
		return studentService;
	}
}
