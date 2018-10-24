package com.payroll.employee.dataobjects;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.Utils;
import com.payroll.department.dataobjects.Department;
import com.payroll.designation.dataobjects.Designation;
import com.payroll.employee.vo.EmpContactVO;
import com.payroll.employee.vo.EmployeeVO;
import com.payroll.headInfo.dataobjects.HeadInfo;

public class EmployeeDAO {

	Session session = null;
	public List<EmployeeVO> getEmployees(int deptId, int headId, String name, int employeeType){
		List<EmployeeVO> employeeList = null;
		Session session = null;
		try{
			//String queryString = " from Employee";
			StringBuffer searchCriteria = new StringBuffer("");
			searchCriteria.append(" select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName,"
					//+ " e.email, e.phone, "
					+ " e.pan, e.adharNo, e.dob,"
					+ "(select dept.departmantName from Department dept where dept.departmentId = (select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = e.employeeId)),"
					+ "(select h.headName from HeadInfo h where h.headId = (select eMas.headInfo.headId from EmpHeadInfo eMas where eMas.employee.employeeId = e.employeeId)),"
					+ "(select desg.designationName from Designation desg where desg.designationId = "
					+ "(select eDesg.designation.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = e.employeeId)), "
					//+ "e.addressLine1, e.addressLine2, e.addressLine3, e.gender, e.joiningDate) from Employee e where e.status= ?");
					+ "e.gender, e.joiningDate, e.retirementDate, e.handicapFlag, e.employeeType) from Employee e where e.status= ? and e.employeeType = ?");

			
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
			query.setParameter(i++, employeeType);
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
	
	public List<EmployeeVO> getAllEmployees(){
		List<EmployeeVO> empList = null;
		Session session = null;
		try{
			String queryString = "select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName) from Employee e "
					+ "where e.status = ?";
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			empList = query.list();
			if(!empList.isEmpty())
				System.out.println("getAllEmployees Size:"+empList.size());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return empList;
	}
	
	public List<EmployeeVO> getEmployees(int deptId, int desgId){
		List<EmployeeVO> employeeList = null;
		Session session = null;
		
		try{
			//String queryString = " from Employee";
			String queryString = " select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, "
					+ "e.lastName, e.middleName, e.handicapFlag) from Employee e where e.status = ? and e.employeeId = "
					+ "(select eDept.employeeId from EmpDepartment eDept where eDept.employee.employeeId=e.employeeId and eDept.department.departmentId = ? ) and "
					+ "e.employeeId = (select eDesg.employee.employeeId from EmpDesignation eDesg where eDesg.employeeId=e.employeeId and "
					+ " eDesg.designationId = ?)";		
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
	
	public Employee getById(String empId){
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
	
	//To check if employeeId already exists - to decide add or update 
		public boolean existEmpId(String empId) {
			boolean exist = false;
			
			Session session = null;
			//Employee employee = null;
			try{
				String queryString = " from Employee e where e.employeeId = ?";
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				//query.setParameter(1, "A");
				//employee = (Employee)(!(query.list().isEmpty()) ? query.list().get(0) : null);
				if(!(query.list().isEmpty())) {
					exist = true;
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
			
			return exist;
		}
	
	public EmployeeVO getEmployeeById(String empId){
		//Session session = null;
		EmployeeVO employee = null;
		try{
			//String queryString = " from Employee e where e.employeeId = ?";
			String queryString = " select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName,"
					//+ " e.email, e.phone, "
					+ " e.pan, e.adharNo, e.dob, (select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = e.employeeId), "
					+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = e.employeeId), "
					+ "(select eDesg.designation.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = e.employeeId), "
					//+ "e.addressLine1, e.addressLine2, e.addressLine3, "
					+ "e.gender, e.joiningDate, e.retirementDate, e.handicapFlag, e.employeeType) from Employee e where e.employeeId = ? and e.status = ?";		
			if(session == null || !session.isOpen()) 
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			employee = (EmployeeVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			System.out.println("employee:"+employee);
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
//		/System.out.println("****Designation Id " + desgId);
		List<EmployeeVO> empList = null;
		Session session = null;
		try{
			String queryString = "select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName, e.handicapFlag) from Employee e "
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
	
	public List<Employee> getEmployeeByEmployeeId(String id) {
		List<Employee> employees = null;
		Session session = null;
		try{
			String queryString = "from Employee where employeeId = ?";
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, id);
			employees = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employees;
	}
	
	
	private EmployeeVO getEmployeeById(String empId, Session session){
		this.session = session;
		return getEmployeeById(empId);
	}
	
	public boolean deleteEmp(String empId){
		boolean success = false;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			if(!empId.equals("0")){
				Query query = session.createQuery("Update Employee e set e.status = ?, e.rowUpdatedDate = ? where e.employeeId = ?");
				query.setParameter(0, "I");
				query.setParameter(1, new Date());
				query.setParameter(2, empId);
				int updated = query.executeUpdate();
				System.out.println("Deleted:"+updated);
				if(updated == 1){
					EmployeeVO empDB = getEmployeeById(empId, null);
					Query queryED = session.createQuery("Update EmpDesignation eDesg set eDesg.status = ?, eDesg.rowUpdDate = ? where eDesg.employee.employeeId = ? and eDesg.designation.designationId = ?");
					queryED.setParameter(0, "I");
					queryED.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryED.setParameter(2, empId);
					queryED.setParameter(3, empDB.getDesignationId());
					updated = queryED.executeUpdate();
					Query queryEDp = session.createQuery("Update EmpDepartment eDept set eDept.status = ?, eDept.rowUpdDate = ? where eDept.employee.employeeId = ? and eDept.department.departmentId = ?");
					queryEDp.setParameter(0, "I");
					queryEDp.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryEDp.setParameter(2, empId);
					queryEDp.setParameter(3, empDB.getDepartmentId());
					updated = queryEDp.executeUpdate();
					Query queryEM = session.createQuery("Update EmpHeadInfo eMas set eMas.status = ?, eMas.rowUpdDate = ? where eMas.employee.employeeId = ? ");
					queryEM.setParameter(0, "I");
					queryEM.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryEM.setParameter(2, empId);
					updated = queryEM.executeUpdate();
					Query queryLev = session.createQuery("update Leave l set l.status = ?, l.rowUpdDate = ? where l.employee.employeeId = ?");
					queryLev.setParameter(0, "I");
					queryLev.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryLev.setParameter(2, empId);
					updated = queryLev.executeUpdate();
					Query querySal = session.createQuery("update Salary s set s.status = ?, s.rowUpdDate = ? where s.employee.employeeId = ?");
					querySal.setParameter(0, "I");
					querySal.setParameter(1, new Timestamp(System.currentTimeMillis()));
					querySal.setParameter(2, empId);
					updated = querySal.executeUpdate();
					Query queryBank = session.createQuery("update EmpBank b set b.status = ?, b.rowUpdDate = ? where b.employee.employeeId = ?");
					queryBank.setParameter(0, "I");
					queryBank.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryBank.setParameter(2, empId);
					updated = queryBank.executeUpdate();
//					Query queryEQ = session.createQuery("update EmpQuarters q set q.status = ?, q.rowUpdDate = ? where q.employee.employeeId = ?");
//					queryEQ.setParameter(0, "I");
//					queryEQ.setParameter(1, new Date());
//					queryEQ.setParameter(2, empId);
//					updated = queryEQ.executeUpdate();
//					Query queryPf = session.createQuery("update EmpPf p set p.status = ?, p.rowUpdDate = ? where p.empId = ?");
//					queryPf.setParameter(0, "S");
//					queryPf.setParameter(1, new Date());
//					queryPf.setParameter(2, empId);
//					updated = queryPf.executeUpdate();
					Query queryLic = session.createQuery("update EmpLic l set l.status = ?, l.rowUpdDate = ? where l.employee.employeeId = ?");
					queryLic.setParameter(0, "I");
					queryLic.setParameter(1, new Timestamp(System.currentTimeMillis()));
					queryLic.setParameter(2, empId);
					updated = queryLic.executeUpdate();
				}
			}
			session.flush();
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
	
	private String createEmployeeId(Employee emp) {
		String empID="";
		String empPrefix="";
		String joiningDate = "";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		joiningDate = dateFormat.format(emp.getJoiningDate());
		
		if(emp.getEmployeeType() == 1) {
			empPrefix = "";
			empID = empPrefix.concat(joiningDate.substring(6, 10)).concat(joiningDate.substring(3, 5)).concat(emp.getEmployeeId()); 
		}
		if(emp.getEmployeeType() == 2) {
			empPrefix = "C";
			empID = empPrefix.concat(joiningDate.substring(6, 10)).concat(joiningDate.substring(3, 5)).concat(emp.getEmployeeId()); 
		}
		if(emp.getEmployeeType() == 3) {
			empPrefix = "H";
			empID = empPrefix.concat(emp.getEmployeeId()); 
		}
		return empID;
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
			
			// if length of employee id 3 then it is add request else it's an update request
			if(emp.getEmployeeId().length()!=3) {  					
				//EmployeeVO empDB = getEmployeeById(emp.getEmployeeId());
				emp.setStatus("A");
				emp.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
				session.update(emp);				
			}else {
				//emp.setEmployeeId(getMaxEmpId(session));
				String empId = "";
				empId = createEmployeeId(emp);
				emp.setEmployeeId(empId);
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
			session.flush();
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
	
	/*private int getMaxEmpId(Session session){
		int maxEmpId = 0;
		//Session session = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Employee emp = (Employee)session.createQuery("select e from Employee e order by e.employeeId desc").setMaxResults(1).uniqueResult();
			String empId = (emp != null) ? emp.getEmployeeId() : 0;
			maxEmpId = empId + 1;
			System.out.println("maxEmpId:"+maxEmpId);
		}catch(Exception e){
			e.printStackTrace();
			maxEmpId = 0;
		}finally {
			HibernateConnection.closeSession(session);
		}
		return maxEmpId;
	}*/
	public List<EmployeeVO> getEmployeesByDeptId(Integer deptId){
		List<EmployeeVO> empList = null;
		Session session = null;
		try{
			String queryString = "select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName, e.handicapFlag) from Employee e "
					+ "where e.status = ? and e.employeeId in (select eDept.employee.employeeId from EmpDepartment eDept where eDept.department.departmentId = ?)";
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			query.setParameter(1, deptId);
			empList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return empList;
	}
	/**
	 * @param empId
	 * @param deptId
	 * @return
	 */
			
	public EmployeeVO getEmployeeDetailsById(String empId, int deptId){
		//Session session = null;
		EmployeeVO employee = null;
		try{
			String queryString = " select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName,"
					//+ " e.email, e.phone, "
					+ "e.pan, e.adharNo, e.dob, (select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = e.employeeId), " 
					+ "(select eDept.department.departmantName from EmpDepartment eDept where eDept.employee.employeeId = e.employeeId), "
					+ "(select dh.headInfo.headName from EmpHeadInfo dh where dh.employee.employeeId = e.employeeId), "
					+ "(select eDesg.designation.designationName from EmpDesignation eDesg where eDesg.employee.employeeId = e.employeeId), "
					//+ "e.addressLine1, e.addressLine2, e.addressLine3, "
					+ "e.gender, e.joiningDate, e.handicapFlag, e.employeeType) from Employee e where e.employeeId = ? and e.status = ? ";		
			if (deptId != 0) {
				queryString += " and e.employeeId = (select eDept1.employee.employeeId from EmpDepartment eDept1 where eDept1.employee.employeeId=e.employeeId and eDept1.department.departmentId = ? )";	
			}
			if(session == null || !session.isOpen()) 
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			if (deptId != 0) {
				query.setParameter(2, deptId);
			}
			employee = (EmployeeVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employee;
	}
	/**
	 * 
	 * @param empId
	 * @return
	 */
	public EmpContactVO getEmployeeContactDetailsById(String empId){
		Session session = null;
		EmpContactVO empContact = null;
		try{
			EmployeeVO emp = this.getEmployeeDetailsById(empId, 0);
			session = HibernateConnection.getSessionFactory().openSession();
			/*String queryString = " select new com.payroll.employee.vo.EmpContactVO(e.employeeId, ec.empContactId, e.firstName, e.lastName, e.middleName,"
					+ "(select eDept.department.departmantName from EmpDepartment eDept where eDept.employee.employeeId = e.employeeId), "
					+ "(select dh.headInfo.headName from EmpHeadInfo dh where dh.employee.employeeId = e.employeeId), "
					+ "(select eDesg.designation.designationName from EmpDesignation eDesg where eDesg.employee.employeeId = e.employeeId), "
					+ " ec.email, ec.phone, ec.addressLine1, ec.addressLine2, ec.addressLine3, ec.secEmail, ec.secPhone, ec.secAddressLine1, ec.secAddressLine2, ec.secAddressLine3)"
					+ " from EmpContact ec JOIN ec.employee e with e.employeeId = ec.employee.employeeId where e.employeeId = ? and e.status = ? ";	*/	
			
			String queryString = " select new com.payroll.employee.vo.EmpContactVO(ec.employee.employeeId, ec.empContactId, "
					+ " ec.email, ec.phone, ec.addressLine1, ec.addressLine2, ec.addressLine3, ec.secEmail, ec.secPhone, "
					+ " ec.secAddressLine1, ec.secAddressLine2, ec.secAddressLine3, ec.city, ec.state, ec.pin,"
					+ " ec.secCity, ec.secState, ec.secPin)"
					+ " from EmpContact ec where ec.employee.employeeId = ? ";
			
			if(session == null || !session.isOpen()) 
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			
			query.setParameter(0, empId);
			//query.setParameter(1, "A");
			
			empContact = (EmpContactVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			if (empContact == null)
				empContact = new EmpContactVO();
			empContact.setEmployee(emp);
			empContact.setEmployeeId(emp.getEmployeeId());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return empContact;
	}
	
	/**
	 * 
	 * @param empContact
	 * @return
	 */
	public boolean addUpdateEmpContact(EmpContact empContact) {
		boolean result = false;
		Session session = null;
		Transaction transaction = null;
		try{
			Employee employee = this.getById(empContact.getEmployeeId());
			empContact.setEmployee(employee);
			
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			empContact.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
			if (empContact.getEmpContactId() == 0)
				session.save(empContact);
			else 
				session.update(empContact);
			transaction.commit();
			result = true;
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public EmployeeVO getEmployeeServiceBook(String empId){
		//Session session = null;
		EmployeeVO employee = null;
		try{
			//String queryString = " from Employee e where e.employeeId = ?";
			String queryString = " select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName,"
					//+ " e.email, e.phone, "
					+ " e.pan, e.adharNo, e.dob, (select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = e.employeeId), "
					+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = e.employeeId), "
					+ "(select eDesg.designation.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = e.employeeId), "
					//+ "e.addressLine1, e.addressLine2, e.addressLine3, "
					+ "e.gender, e.joiningDate, e.retirementDate, e.handicapFlag, e.employeeType) from Employee e where e.employeeId = ? and e.status = ?";		
			if(session == null || !session.isOpen()) 
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			employee = (EmployeeVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			System.out.println("employee:"+employee);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return employee;
	}
}
