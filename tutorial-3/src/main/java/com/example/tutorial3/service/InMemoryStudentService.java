package com.example.tutorial3.service;

import java.util.List;
import java.util.ArrayList;
import com.example.tutorial3.model.StudentModel;;

public class InMemoryStudentService implements StudentService{
	
	private static List<StudentModel> studentList = new ArrayList<StudentModel>();

	@Override
	public StudentModel selectStudent(String npm) {
		for(int i=0;i<studentList.size();i++) {
			if(studentList.get(i).getNpm().equals(npm)) {
				return studentList.get(i);
			}
		}
		return null;	
	}

	@Override
	public List<StudentModel> selectAllStudents() {
		return studentList;
	}

	@Override
	public void addStudent(StudentModel student) {
		studentList.add(student);
	}

	@Override
	public void removeStudent(StudentModel student) {
		for (int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).getNpm().equalsIgnoreCase(student.getNpm())) {
				studentList.remove(i);
			}
		}
	}

	@Override
	public boolean checkNpm(String npm) {
		boolean ada = false;
		for(int i = 0; i < studentList.size(); i++) {
			if (studentList.get(i).getNpm().equalsIgnoreCase(npm)) {
				ada = true;
				break;
			}
		}
		return ada;
	}
	


}
