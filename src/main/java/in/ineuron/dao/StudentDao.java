package in.ineuron.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import in.ineuron.bean.Student;
import in.ineuron.util.JdbcUtil;

public class StudentDao implements IStudentDao {
	Connection connection = null;

	@Override
	public String save(Student std) {
		String status = null;
		PreparedStatement pstmt = null;
		String sqlQuery = "insert into student(name, age, address) values (?,?,?)";
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlQuery);
			if (pstmt != null) {
				pstmt.setString(1, std.getName());
				pstmt.setInt(2, std.getAge());
				pstmt.setString(3, std.getAddress());

				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected == 1) {
					status = "success";
				} else {
					status = "failure";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
			status = "notfound";
		}
		return status;
	}

	@Override
	public Student findById(Integer id) {
		String query = "select id, name, age, address from student where id=? ";
		PreparedStatement pstmt = null;
		Student std = null;
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(query);
			if (pstmt != null) {
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					std = new Student();
					std.setId(rs.getInt(1));
					std.setName(rs.getString(2));
					std.setAge(rs.getInt(3));
					std.setAddress(rs.getString(4));
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return std;
	}

	@Override
	public String updateById(Student std) {
		String status = null;
		PreparedStatement pstmt = null;
		String sqlQuery = "update student set name=?, age=?, address=? where id=? ";
		try {
			connection = JdbcUtil.getJdbcConnection();
			if (connection != null)
				pstmt = connection.prepareStatement(sqlQuery);
			if (pstmt != null) {
				pstmt.setString(1, std.getName());
				pstmt.setInt(2, std.getAge());
				pstmt.setString(3, std.getAddress());
				pstmt.setInt(4, std.getId());

				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected == 1) {
					status = "success";
				} else {
					status = "failure";
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public String deleteById(Integer id) {
		String status = null;
		PreparedStatement pstmt = null;
		String sqlquerydel = "delete from student where id = ? ";
		try {
			Student std = findById(id);
			if (std != null) {
				connection = JdbcUtil.getJdbcConnection();
				if (connection != null) 
					pstmt = connection.prepareStatement(sqlquerydel);
					if (pstmt != null) {
						pstmt.setInt(1, id);
						int row = pstmt.executeUpdate();
						if (row == 1) 
							status = "success";
					}
				}
			else {
							status = "failure";
				}	
		} catch (SQLException | IOException | NullPointerException e) {
			e.printStackTrace();
			status = "Not Available";

		}
		return status;
		}
		
}

