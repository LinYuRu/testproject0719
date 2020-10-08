package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import model.Student;
@Repository
public class StudentDao {
	private static final String INSERT_STMT = "INSERT INTO Student VALUES (?, ?, ?, ?, ?, ?, ? ,?)";
	private static final String UPDATE_STMT = "UPDATE Student SET SNAME=?, SSEX=?, SBDAY=?, SMAIL=?, SPWD=? ,SID=? ,SAGE=? WHERE SNO=?";
	private static final String UPDATEPWD_STMT = "UPDATE Student SET SPWD=? WHERE SNO=?";
	private static final String DELETE_STMT = "DELETE FROM Student WHERE sno=?";
	private static final String GET_ALL_STMT = "SELECT * FROM Student";
	private static final String GET_ONE_STMT = "SELECT * FROM Student WHERE SNO=?";
	DataSource dataSource = DataSource.getInstance();
	
	public boolean insert(Student student) throws SQLException {
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT);
			//"INSERT INTO Student VALUES (?, ?, ?, ?, ?, ?, ? ,?)"
			java.sql.Date birth = null;
			int age = 0;
			try {
				birth = student.birthday();
				age = student.minusDate(birth);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt.setString(1, student.getSno());
			pstmt.setString(2, student.getSname());
			pstmt.setDate(3, birth);
			pstmt.setString(4, student.getSsex());
			pstmt.setString(5, student.getSmail());
			pstmt.setString(6, convertMD5(student.getSpwd()));
			pstmt.setString(7, student.getId());
			pstmt.setInt(8, age);
			System.out.println(
					"Sno: " + student.getSno() + " SName: " + student.getSname() + " Ssx: " + student.getSsex());
			System.out.println(
					"Sbday: " + student.getSbday() + " Smail: " + student.getSmail() + " Spwd: " + student.getSpwd());
			System.out.println("Sid: " + student.getSid() + "Sage: " + student.getSage());
			System.out.println("insert execute");
			pstmt.executeUpdate();
			dataSource.closeConnection(pstmt.getConnection());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		return false;
	}

	public Integer update(Student student) throws SQLException {
		int updateCount = 0;
		Connection connection = dataSource.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(UPDATE_STMT);
//		UPDATE Student SET SNAME=?, SSEX=?, SBDAY=?, SMAIL=?, SPWD=? ,SID=? ,SAGE=? WHERE SNO=?

		pstmt.setString(1, student.getSname());
		pstmt.setString(2, student.getSsex());
		pstmt.setDate(3, student.getSbday());
		pstmt.setString(4, student.getSmail());
		pstmt.setString(5, convertMD5(student.getSpwd()));
		pstmt.setString(6, student.getSid());
		pstmt.setInt(7, student.getSage());
		pstmt.setString(8, student.getSno());
		updateCount = pstmt.executeUpdate();
		System.out.println("Sno: " + student.getSno() + " SName: " + student.getSname() + " Ssx: " + student.getSsex());
		System.out.println(
				"Sbday: " + student.getSbday() + " Smail: " + student.getSmail() + " Spwd: " + student.getSpwd());
		System.out.println("");
		dataSource.closeConnection(pstmt.getConnection());
		return updateCount;
	}
	
	public Student getOne(String sno) throws SQLException {
		Student student = new Student();
		Connection connection = dataSource.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(GET_ONE_STMT);
		pstmt.setString(1, sno);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			student.setSno(rs.getString(1));
			student.setSname(rs.getString(2));
			student.setSmail(rs.getString(5));
			student.setSpwd(convertMD5(rs.getString(6)));
			System.out.println(
					"Sno: " + student.getSno() + " SName: " + student.getSname() + " Ssx: " + student.getSsex());
			System.out.println(
					"Sbday: " + student.getSbday() + " Smail: " + student.getSmail() + " Spwd: " + student.getSpwd());
			System.out.println("Sid: " + student.getSid() + "Sage: " + student.getSage());
			System.out.println("");
		}
		dataSource.closeConnection(pstmt.getConnection());
		return student;
	}

	public ArrayList<Student> getAll() throws SQLException {
		Student student = null;
		Connection connection = dataSource.getConnection();
		ArrayList<Student> st = new ArrayList<Student>();
		PreparedStatement pstmt = connection.prepareStatement(GET_ALL_STMT);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			student = new Student();
			student.setSno(rs.getString(1));
			student.setSname(rs.getString(2));
			student.setSbday(rs.getDate(3));
			student.setSsex(rs.getString(4));
			student.setSmail(rs.getString(5));
			student.setSpwd(convertMD5(rs.getString(6)));
			System.out.println("原pwd: "+convertMD5(rs.getString(6)));
			student.setSid(rs.getString(7));
			student.setSage(rs.getInt(8));
			System.out.println(
					"Sno: " + student.getSno() + " SName: " + student.getSname() + " Ssx: " + student.getSsex());
			System.out.println(
					"Sbday: " + student.getSbday() + " Smail: " + student.getSmail() + " Spwd: " + student.getSpwd());
			System.out.println("Sid: " + student.getSid() + "Sage: " + student.getSage());
			System.out.println("");
			st.add(student);
		}
		dataSource.closeConnection(pstmt.getConnection());
		return st;
	}
	
	/**
	 * 加密解密演算法 執行一次加密,兩次解密
	 */
	public static String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}
	
	public String updatePwd(String pwd, String sno) throws SQLException {
		int updateCount = 0;
		Connection connection = dataSource.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(UPDATEPWD_STMT);
//		UPDATE Student SET SPWD=? WHERE SNO=?

		pstmt.setString(1, convertMD5(pwd));
		pstmt.setString(2, sno);
		updateCount = pstmt.executeUpdate();
		System.out.println("Sno: " + sno + " Spwd: " + pwd + " Ssx: ");	
		System.out.println("");
		dataSource.closeConnection(pstmt.getConnection());
		return "success";
	}

	public boolean delete(String sno) throws SQLException {
		Connection connection = dataSource.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(DELETE_STMT);
		pstmt.setString(1, sno);
		System.out.println("Delete SNO: " + sno);
		pstmt.executeQuery();
		dataSource.closeConnection(pstmt.getConnection());
		return true;
	}
}
