package in.ineuron.dao;

import in.ineuron.bean.Student;

public interface IStudentDao {
	String save(Student std);
	Student findById(Integer id);
	String updateById(Student std);
	String deleteById(Integer id);

}
