package service;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import model.Student;

public interface StudentService {
	
	public ArrayList<Student> selectStudent() throws Exception; //查詢學生資料

	public Boolean insertStudent(Student student); //新增學生資料

	public Integer updateStudent(Student student); //修改學生資料

	public Boolean deleteStudent(String sno); //修改學生資料
	
	public Student setData(Student student) throws ParseException;//差入學生資料
	
	public Student updateData();//更新學生資料
		
	public Student getOne(String sno) throws SQLException;//取得個人資料
	
	public String MailSend(Student student);//寄信
	
	public String newPwd();//取得新密碼
	
	public String updatePwd(String sno); //修改學生資料
	
	public String convertMD5(String inStr);//MD5加密

}
