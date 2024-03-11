package in.ineuron.factory;

import in.ineuron.dao.IStudentDao;
import in.ineuron.dao.StudentDao;

public class StudentDaoFactory {
	private static StudentDao studentDao= null;
	private StudentDaoFactory() {
		
	}
	public static IStudentDao getStudentDao() {
		if(studentDao==null) 
		studentDao = new StudentDao();
		return studentDao;
	}

}
