package com.payroll.department.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.payroll.Utils;
import com.payroll.department.dataobjects.Department;
import com.payroll.department.dataobjects.DepartmentDAO;

public class DepartmentService {
	public List<Department> getDepartments(){
		return new DepartmentDAO().getDepartments();
	}
	
	public String addUpdateDepartment(Department dept){
		return new DepartmentDAO().addUpdateDepartment(dept);
	}
	
	public boolean deleteDepartment(int deptId){
		return new DepartmentDAO().deleteDepartment(deptId);
	}
	
	public Department getDeptById(int deptId){
		return new DepartmentDAO().getDepartmentById(deptId);
	}
	
	public List<Department> getDeptSections(){
		List<Department> deptSections = new DepartmentDAO().getDeptSections();
		List<Department> newDeptSections = new ArrayList<Department>();
		if(deptSections != null && !deptSections.isEmpty()) {
			String section = "";
			for (Iterator iterator = deptSections.iterator(); iterator.hasNext();) {
				Department department = (Department) iterator.next();
				if(!Utils.isEmpty(section) && section.equals(department.getSection())) {
					continue;
				}else {
					section = department.getSection();
					newDeptSections.add(department);
				}
			}
		}
			
		return newDeptSections;
	}
	
	public List<Department> getDepartmentsBySection(String section){
		return new DepartmentDAO().getDepartmentsBySection(section);
	}
	
}
