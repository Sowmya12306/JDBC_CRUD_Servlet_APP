package in.ineuron.service;

import in.ineuron.bean.Student;

public interface IStudentService {
	String save(Student std);
	Student findById(Integer id);
	String updateById(Student std);
	String deleteById(Integer id);

}
