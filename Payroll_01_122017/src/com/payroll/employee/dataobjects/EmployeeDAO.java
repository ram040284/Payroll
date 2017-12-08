package com.payroll.employee.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.payroll.HibernateConnection;
import com.payroll.Utils;
import com.payroll.employee.vo.EmployeeVO;

public class EmployeeDAO {

	Session session = null;
	public List<EmployeeVO> getEmployees(int deptId, int headId, String name){
		List<EmployeeVO> employeeList = null;
		Session session = null;
		try{
			//String queryString = " from Employee";
			StringBuffer searchCriteria = new StringBuffer("");
			searchCriteria.append(" select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName,"
					+ " e.email, e.phone, e.pan, e.adharNo, e.dob,"
					+ "(select dept.departmantName from Department dept where dept.departmentId = (select eDept.departmentId from EmployeeMaster eDept where eDept.empId = e.employeeId)),"
					+ "(select h.headName from HeadInfo h where h.headId = (select eMas.headId from EmployeeMaster eMas where eMas.empId = e.employeeId)),"
					+ "(select desg.designationName from Designation desg where desg.designationId = "
					+ "(select eDesg.designationId from EmployeeMaster eDesg where eDesg.empId = e.employeeId)), "
					+ "e.addressLine1, e.addressLine2, e.addressLine3, e.gender, e.joiningDate) from Employee e where e.status= ?");		
			
			if(deptId != 0)
				searchCriteria.append(" and e.employeeId = (select eDept.empId from EmpDepartment eDept where e.employeeId = eDept.empId and eDept.departmentId = ?)");
			if(headId != 0){
				searchCriteria.append(" and e.employeeId = (select eMas.empId from EmployeeMaster eMas where e.employeeId = eMas.empId and eMas.headId = ?)");
			}
			if(!Utils.isEmpty(name))
				searchCriteria.append(" and (e.firstName like :fname or e.middleName like :mname or e.lastName like :lname)");
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(searchCriteria.toString());
			int i=0;
			query.setParameter(i++, "A");
			if(deptId != 0)
				query.setParameter(i++, deptId);
			if(headId != 0)
				query.setParameter(i++, headId);
			if(!Utils.isEmpty(name)){
				query.setParameter("fname", "%"+name+"%");
				query.setParameter("mname", "%"+name+"%");
				query.setParameter("lname", "%"+name+"%");
			}
			employeeList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employeeList;
	}
	
	public List<EmployeeVO> getEmployees(int deptId, int desgId){
		List<EmployeeVO> employeeList = null;
		Session session = null;
		
		try{
			//String queryString = " from Employee";
			String queryString = " select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, "
					+ "e.lastName, e.middleName) from Employee e where e.status = ? and e.employeeId = "
					+ "(select eDept.empId from EmpDepartment eDept where eDept.empId=e.employeeId and eDept.departmentId = ? ) and "
					+ "e.employeeId = (select eDesg.empId from EmpDesignation eDesg where eDesg.empId=e.employeeId and "
					+ " eDesg.designationId = ? and eDesg.lastWokingDate is null)";		
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			query.setParameter(1, deptId);
			query.setParameter(2, desgId);
			employeeList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employeeList;
	}
	
	public EmployeeVO getEmployeeById(int empId){
		//Session session = null;
		EmployeeVO employee = null;
		try{
			//String queryString = " from Employee e where e.employeeId = ?";
			String queryString = " select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName,"
					+ " e.email, e.phone, e.pan, e.adharNo, e.dob, (select eDept.departmentId from EmployeeMaster eDept where eDept.empId = e.employeeId), "
					+ "(select dh.headId from EmployeeMaster dh where dh.empId = e.employeeId), "
					+ "(select eDesg.designationId from EmpDesignation eDesg where eDesg.empId = e.employeeId and eDesg.lastWokingDate is null), "
					+ "e.addressLine1, e.addressLine2, e.addressLine3, e.gender, e.joiningDate) from Employee e where e.employeeId = ? and e.status = ?";		
			if(session == null || !session.isOpen()) 
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			employee = (EmployeeVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employee;
	}
	
	public EmpDesignation getEmpDesignationByIds(int empId, int desgId, Session session){
		EmpDesignation empDesig = null;
		try{
			String queryString = " from EmpDesignation ed where ed.empId = ? and ed.designationId = ? and ed.status = ?";
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			query.setParameter(1, desgId);
			query.setParameter(2, "A");
			empDesig = (EmpDesignation)((!query.list().isEmpty())? query.list().get(0) : null);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return empDesig;
	}
	
	private EmployeeVO getEmployeeById(int empId, Session session){
		this.session = session;
		return getEmployeeById(empId);
	}
	
	public boolean deleteEmp(int empId){
		boolean success = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(empId != 0){
				Query query = session.createQuery("Update Employee e set e.status = ?, e.rowUpdatedDate = ? where e.employeeId = ?");
				query.setParameter(0, "S");
				query.setParameter(1, new Date());
				query.setParameter(2, empId);
				int updated = query.executeUpdate();
				System.out.println("Deleted:"+updated);
				if(updated == 1){
					EmployeeVO empDB = getEmployeeById(empId, null);
					Query queryED = session.createQuery("Update EmpDesignation eDesg set eDesg.status = ?, eDesg.rowUpdDate = ? where eDesg.empId = ? and eDesg.designationId = ?");
					queryED.setParameter(0, "S");
					queryED.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryED.setParameter(2, empId);
					queryED.setParameter(3, empDB.getDesignationId());
					updated = queryED.executeUpdate();
					Query queryEDp = session.createQuery("Update EmpDepartment eDept set eDept.status = ?, eDept.rowUpdDate = ? where eDept.empId = ? and eDept.departmentId = ?");
					queryEDp.setParameter(0, "S");
					queryEDp.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryEDp.setParameter(2, empId);
					queryEDp.setParameter(3, empDB.getDepartmentId());
					updated = queryEDp.executeUpdate();
					Query queryEM = session.createQuery("Update EmployeeMaster eMas set eMas.status = ?, eMas.rowUpdDate = ? where eMas.empId = ? ");
					queryEM.setParameter(0, "S");
					queryEM.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryEM.setParameter(2, empId);
					updated = queryEM.executeUpdate();
					Query queryLev = session.createQuery("update Leave l set l.status = ?, l.rowUpdDate = ? where l.empId = ?");
					queryLev.setParameter(0, "S");
					queryLev.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryLev.setParameter(2, empId);
					updated = queryLev.executeUpdate();
					Query querySal = session.createQuery("update Salary s set s.status = ?, s.rowUpdDate = ? where s.empId = ?");
					querySal.setParameter(0, "S");
					querySal.setParameter(1, new Timestamp(System.currentTimeMillis()));
					querySal.setParameter(2, empId);
					updated = querySal.executeUpdate();
					Query queryBank = session.createQuery("update Bank b set b.status = ?, b.rowUpdDate = ? where b.empId = ?");
					queryBank.setParameter(0, "S");
					queryBank.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryBank.setParameter(2, empId);
					updated = queryBank.executeUpdate();
					Query queryEQ = session.createQuery("update EmpQuarters q set q.status = ?, q.rowUpdDate = ? where q.empId = ?");
					queryEQ.setParameter(0, "S");
					queryEQ.setParameter(1, new Date());
					queryEQ.setParameter(2, empId);
					updated = queryEQ.executeUpdate();
					Query queryPf = session.createQuery("update EmpPf p set p.status = ?, p.rowUpdDate = ? where p.empId = ?");
					queryPf.setParameter(0, "S");
					queryPf.setParameter(1, new Date());
					queryPf.setParameter(2, empId);
					updated = queryPf.executeUpdate();
					Query queryLic = session.createQuery("update EmpLic l set l.status = ?, l.rowUpdDate = ? where l.empId = ?");
					queryLic.setParameter(0, "S");
					queryLic.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryLic.setParameter(2, empId);
					updated = queryLic.executeUpdate();
				}
			}
			transaction.commit();
			success = true;
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			success = false;
		}finally {
			HibernateConnection.closeSession(session);
		}
		return success;
	}
	
	public String addUpdateEmployee(Employee emp){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(emp.getEmployeeId() != 0) {
				/*EmployeeVO empDB = getEmployeeById(emp.getEmployeeId());
				if(empDB.getDepartmentId() != emp.getDepartmentId()){
					EmpDepartment empDept = new EmpDepartment();
					empDept.setDepartmentId(emp.getDepartmentId());
					empDept.setEmpId(emp.getEmployeeId());
					empDept.setStartDate(new Date());
					empDept.setStatus("A");
					empDept.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
					session.save(empDept);
				}
				if(empDB.getDesignationId() != emp.getDesignationId()){
					// Updating Last working day for Employee designation
					EmpDesignation empDesgDB = getEmpDesignationByIds(emp.getEmployeeId(), empDB.getDesignationId(), session);
					empDesgDB.setLastWokingDate(new Date());
					empDesgDB.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
					session.update(empDesgDB);
					// Adding new designation for Employee
					EmpDesignation empDesg = new EmpDesignation();
					empDesg.setDesignationId(emp.getDesignationId());
					empDesg.setEmpId(emp.getEmployeeId());
					empDesg.setStartDate(new Date());
					empDesg.setStatus("A");
					empDesg.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
					session.save(empDesg);
				}*/
				emp.setStatus("A");
				emp.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
				session.update(emp);
				
			}else {
				emp.setEmployeeId(getMaxEmpId(session));
				emp.setStatus("A");
				emp.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
				session.save(emp);
				//Inserting Employee Department:
				/*EmpDepartment empDept = new EmpDepartment();
				empDept.setDepartmentId(emp.getDepartmentId());
				empDept.setEmpId(emp.getEmployeeId());
				empDept.setStartDate(new Date());
				empDept.setStatus("A");
				empDept.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				
				session.save(empDept);
				//Inserting Employee Designation:
				EmpDesignation empDesg = new EmpDesignation();
				empDesg.setDesignationId(emp.getDesignationId());
				empDesg.setEmpId(emp.getEmployeeId());
				empDesg.setStartDate(new Date());
				empDesg.setStatus("A");
				empDesg.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.save(empDesg);
				//Inserting Employee Master:
				EmployeeMaster eMas = new EmployeeMaster();
				eMas.setDepartmentId(emp.getDepartmentId());
				eMas.setDesignationId(emp.getDesignationId());
				eMas.setHeadId(emp.getHeadId());
				eMas.setEmpId(emp.getEmployeeId());
				eMas.setStatus("A");
				eMas.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.save(eMas);*/
			}
			transaction.commit();
			result = "Yes";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Unable Add/Update Employee!";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	private int getMaxEmpId(Session session){
		int maxEmpId = 0;
		//Session session = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Employee emp = (Employee)session.createQuery("select e from Employee e order by e.employeeId desc").setMaxResults(1).uniqueResult();
			int empId = (emp != null) ? emp.getEmployeeId() : 0;
			maxEmpId = empId + 1;
			System.out.println("maxEmpId:"+maxEmpId);
		}catch(Exception e){
			e.printStackTrace();
			maxEmpId = 0;
		}/*finally {
			HibernateConnection.closeSession(session);
		}*/
		return maxEmpId;
	}
	
}
