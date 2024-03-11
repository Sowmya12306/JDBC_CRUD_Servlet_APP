package in.ineuron.service;

import in.ineuron.bean.Student;
import in.ineuron.dao.IStudentDao;
import in.ineuron.factory.StudentDaoFactory;

public class StudentService implements IStudentService {
	IStudentDao dao;

	@Override
	public String save(Student std) {
		dao = StudentDaoFactory.getStudentDao();
		return dao.save(std);
	}

	@Override
	public Student findById(Integer id) {
		dao = StudentDaoFactory.getStudentDao();
		return dao.findById(id);
	}

	@Override
	public String updateById(Student std) {
		dao = StudentDaoFactory.getStudentDao();
        return dao.updateById(std);
	}

	@Override
	public String deleteById(Integer id) {
		dao = StudentDaoFactory.getStudentDao();
        return dao.deleteById(id);
	}

}
