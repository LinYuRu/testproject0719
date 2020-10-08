package service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.StudentDao;
import model.MailSend;
import model.Student;
@Service
public class StudentServiceImpl implements StudentService {
	

	@Autowired
	private StudentDao dao;

	@Override
	public ArrayList<Student> selectStudent() throws Exception {
		return dao.getAll();
	}

	@Override
	public Boolean insertStudent(Student student) {
		try {
			return dao.insert(student);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public Integer updateStudent(Student student) {
		try {
			dao.update(student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean deleteStudent(String sno) {
		try {
			dao.delete(sno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Student setData(Student student) throws ParseException {

		String id = student.getId().toUpperCase();
		String sno = student.getRandomSno().toUpperCase();
		String sex = student.getStudentSex(id);
		String name = student.getStudentName(id);
		Date birthday = student.birthday();
		student.setSno(sno);
		student.setSsex(sex);
		student.setSname(name);
		student.setSmail("yahoo@gmail.com");
		student.setSbday(birthday);
		student.setSpwd("123456");
		student.setSid(id);
		try {
			student.setSage(student.minusDate(birthday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Student updateData() {
		Student student = new Student();
		student.setSno("A01");
		student.setSmail("kkk@gmail.com");
		student.setSname("Ted");
		student.setSsex("男");
		student.setSbday(Date.valueOf("2020-04-10"));
		student.setSpwd("kkk");
		student.setSid(student.getId());
		try {
			student.setSage(student.minusDate(student.birthday()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return student;
	}


	@Override
	public Student getOne(String sno) throws SQLException {		
		return dao.getOne(sno);
	}

	@Override
	public String MailSend(Student student) {
	MailSend mailsend = new MailSend();
	boolean send ;
	send = mailsend.sendMail(student.getSno(), student.getSpwd(), student.getSmail());
		if(send) {
			return "success";
		} else {
			return "failure";
		}
	}

	@Override
	public String newPwd() {
		String pattern = "ABCDEFGHJKLMNPQRSTUVWXYZIO";
		Random r = new Random();
		int num = (r.nextInt(26)+65);
		String str = "";
		str += (char)num;
		num = pattern.indexOf((char)num)+10;
		
		for(int i=1;i<5;i++) {
			str +=Integer.toString(num=r.nextInt(10));
		}
		
		return str;
	}

	@Override
	public String updatePwd(String sno) {
		String npwd = newPwd();
		try {
			dao.updatePwd(npwd, sno);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return npwd;
	}
	
	@Override
	public String convertMD5(String inStr) {

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}


}
