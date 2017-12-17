package com.payroll.employee.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.payroll.HibernateConnection;
import com.payroll.Utils;
import com.payroll.department.dataobjects.Department;
import com.payroll.department.dataobjects.DepartmentDAO;
import com.payroll.designation.dataobjects.Designation;
import com.payroll.designation.dataobjects.DesignationDAO;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.headInfo.dataobjects.HeadInfo;
import com.payroll.headInfo.dataobjects.HeadInfoDAO;

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
					+ "(select dept.departmantName from Department dept where dept.departmentId = (select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = e.employeeId)),"
					+ "(select h.headName from HeadInfo h where h.headId = (select eMas.headInfo.headId from EmpHeadInfo eMas where eMas.employee.employeeId = e.employeeId)),"
					+ "(select desg.designationName from Designation desg where desg.designationId = "
					+ "(select eDesg.designation.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = e.employeeId)), "
					+ "e.addressLine1, e.addressLine2, e.addressLine3, e.gender, e.joiningDate) from Employee e where e.status= ?");		
			
			if(deptId != 0)
				searchCriteria.append(" and e.employeeId = (select eDept.employee.employeeId from EmpDepartment eDept where e.employeeId = eDept.employee.employeeId and eDept.department.departmentId = ?)");
			if(headId != 0){
				searchCriteria.append(" and e.employeeId = (select eMas.employee.employeeId from EmpHeadInfo eMas where e.employeeId = eMas.employee.employeeId and eMas.headInfo.headId = ?)");
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
			if(!employeeList.isEmpty())
				System.out.println("Size:"+employeeList.size());
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
					+ "(select eDept.employeeId from EmpDepartment eDept where eDept.employee.employeeId=e.employeeId and eDept.department.departmentId = ? ) and "
					+ "e.employeeId = (select eDesg.employee.employeeId from EmpDesignation eDesg where eDesg.employeeId=e.employeeId and "
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
	
	public Employee getById(int empId){
		Session session = null;
		Employee employee = null;
		try{
			String queryString = " from Employee e where e.employeeId = ? and e.status = ?";
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			employee = (Employee)(!(query.list().isEmpty()) ? query.list().get(0) : null);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employee;
	}
	
	public EmployeeVO getEmployeeById(int empId){
		//Session session = null;
		EmployeeVO employee = null;
		try{
			//String queryString = " from Employee e where e.employeeId = ?";
			String queryString = " select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName,"
					+ " e.email, e.phone, e.pan, e.adharNo, e.dob, (select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = e.employeeId), "
					+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = e.employeeId), "
					+ "(select eDesg.designation.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = e.employeeId and eDesg.lastWokingDate is null), "
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
			String queryString = " from EmpDesignation ed where ed.employee.employeeId = ? and ed.employee.designationId = ? and ed.status = ?";
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
	
	public List<EmployeeVO> getEmployeesByDesgId(int desgId){
		List<EmployeeVO> empList = null;
		Session session = null;
		try{
			String queryString = "select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName) from Employee e "
					+ "where e.status = ? and e.employeeId in (select eDesg.employee.employeeId from EmpDesignation eDesg where eDesg.designation.designationId = ?)";
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			query.setParameter(1, desgId);
			empList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return empList;
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
					Query queryED = session.createQuery("Update EmpDesignation eDesg set eDesg.status = ?, eDesg.rowUpdDate = ? where eDesg.employee.employeeId = ? and eDesg.designation.designationId = ?");
					queryED.setParameter(0, "S");
					queryED.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryED.setParameter(2, empId);
					queryED.setParameter(3, empDB.getDesignationId());
					updated = queryED.executeUpdate();
					Query queryEDp = session.createQuery("Update EmpDepartment eDept set eDept.status = ?, eDept.rowUpdDate = ? where eDept.employee.employeeId = ? and eDept.department.departmentId = ?");
					queryEDp.setParameter(0, "S");
					queryEDp.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryEDp.setParameter(2, empId);
					queryEDp.setParameter(3, empDB.getDepartmentId());
					updated = queryEDp.executeUpdate();
					Query queryEM = session.createQuery("Update EmpHeadInfo eMas set eMas.status = ?, eMas.rowUpdDate = ? where eMas.employee.employeeId = ? ");
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
			/*Department dept = new DepartmentDAO().getDepartmentById(emp.getDepartmentId());
			HeadInfo headInfo = new HeadInfoDAO().getHeadInfoById(emp.getHeadId());
			Designation designation = new DesignationDAO().getDesignationById(emp.getDesignationId());*/
			Department dept = (Department)session.load(Department.class, emp.getDepartmentId());
			HeadInfo headInfo = (HeadInfo)session.load(HeadInfo.class, emp.getHeadId());
			Designation designation =(Designation)session.load(Designation.class, emp.getDesignationId());
			if(emp.getEmployeeId() != 0) {
				EmployeeVO empDB = getEmployeeById(emp.getEmployeeId());
				/*if(empDB.getDepartmentId() != emp.getDepartmentId()){
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
				//emp.setEmployeeId(getMaxEmpId(session));
				emp.setStatus("A");
				emp.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
				session.save(emp);
				//Inserting Employee Department:
				EmpDepartment empDept = new EmpDepartment();
				empDept.setStartDate(new Date());
				empDept.setStatus("A");
				empDept.setDepartment(dept);
				empDept.setEmployee(emp);
				empDept.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.save(empDept);
				//Inserting Employee Head:
				EmpHeadInfo empHead = new EmpHeadInfo();
				empHead.setStartDate(new Date());
				empHead.setStatus("A");
				empHead.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				empHead.setEmployee(emp);
				empHead.setHeadInfo(headInfo);
				session.save(empHead);
				//Inserting Employee Designation:
				EmpDesignation empDesg = new EmpDesignation();
				//empDesg.setDesignationId(emp.getDesignationId());
				//empDesg.setEmpId(emp.getEmployeeId());
				empDesg.setStartDate(new Date());
				empDesg.setStatus("A");
				empDesg.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				empDesg.setEmployee(emp);
				empDesg.setDesignation(designation);
				session.save(empDesg);
				
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
