package com.payroll.employee.leave.dataobjects;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.Utils;
import com.payroll.employee.contract.EmployeeContractVO;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.leave.business.LeaveService;
import com.payroll.employee.leave.vo.LeaveVO;

public class LeaveDAO {
	private int deptId = 0;
	private int headId = 0;
	private String name; 
	private static SimpleDateFormat inSDF = new SimpleDateFormat("mm/dd/yyyy");
	private static SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");
	public List<com.payroll.employee.leave.vo.LeaveVO> getLeaves(int deptId, int headId, String name){
		this.deptId = deptId;
		this.headId = headId;
		this.name = name;
		return getLeaves();
	}
	public List<com.payroll.employee.leave.vo.LeaveVO> getLeaves(){
		List<com.payroll.employee.leave.vo.LeaveVO> leaves = null;
			Session session = null;
			StringBuffer searchCriteria = new StringBuffer("");
			try{
				searchCriteria.append(" select new com.payroll.employee.leave.vo.LeaveVO(lm.employee.employeeId, "
						+"lm.employee.firstName, lm.employee.lastName, "
						+ "MAX(CASE WHEN lm.leaveType.id = 1 THEN lm.leaveBalance END) as casualLeaveBal, "
						+ "MAX(CASE WHEN lm.leaveType.id = 2 THEN lm.leaveBalance END) as sickLeaveBal, "
						+ "MAX(CASE WHEN lm.leaveType.id = 3 THEN lm.leaveBalance END) as halfPaidLeaveBal, "
						+ "MAX(CASE WHEN lm.leaveType.id = 4 THEN lm.leaveBalance END) as earnLeaveBal, "
						+ "MAX(CASE WHEN lm.leaveType.id = 5 THEN lm.leaveBalance END) as maternityLeaveBal, "
						+ "MAX(CASE WHEN lm.leaveType.id = 6 THEN lm.leaveBalance END) as paternitiLeaveBal, "
						+ "MAX(CASE WHEN lm.leaveType.id = 7 THEN lm.leaveBalance END) as extraOrdLeaveBal, "
						+ "MAX(CASE WHEN lm.leaveType.id = 8 THEN lm.leaveBalance END) as childCareLeaveBal, "
						+ "MAX(CASE WHEN lm.leaveType.id = 9 THEN lm.leaveBalance END) as extraOrdLeaveWithoutMediBal) "
						+ " from Leave lm where lm.status = ? ");
				if(deptId != 0)
					searchCriteria.append(" and lm.employee.employeeId = (select eDept.employee.employeeId from EmpDepartment eDept where lm.employee.employeeId = eDept.employee.employeeId and eDept.department.departmentId = ? and eDept.status = 'A')");
				if(headId != 0){
					searchCriteria.append(" and lm.employee.employeeId = (select eMas.employee.employeeId from EmpHeadInfo eMas where lm.employee.employeeId = eMas.employee.employeeId and eMas.headInfo.headId = ? and eMas.status = 'A')");
				}
				if(!Utils.isEmpty(name))
					searchCriteria.append(" and lm.employee.firstName like :fname or lm.employee.middleName like :mname or lm.employee.lastName like :lname))");
				
				searchCriteria.append(" group by lm.employee.employeeId");
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
				leaves = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return leaves;
	}
	
	public List<LeaveRequestVO> getLeaveRequests(int deptId, int headId, String name){
		this.deptId = deptId;
		this.headId = headId;
		this.name = name;
		return getLeaveRequests();
	}
	public List<LeaveRequestVO> getLeaveRequests(){
			List<LeaveRequestVO> leaves = null;
				Session session = null;
				StringBuffer searchCriteria = new StringBuffer("");
				try{
					//String queryString
					searchCriteria.append(" select new com.payroll.employee.leave.dataobjects.LeaveRequestVO(l.employee.employeeId, l.noOfLeaves, l.fromDate "
							+ ",l.toDate, l.reason, l.employee.lastName, l.employee.firstName, l.leaveType.description) from LeaveRequest l where l.status = ?");
					if(deptId != 0)
						searchCriteria.append(" and l.employee.employeeId = (select eDept.employee.employeeId from EmpDepartment eDept where l.employee.employeeId = eDept.employee.employeeId and eDept.department.departmentId = ? and eDept.status = 'A')");
					if(headId != 0){
						//searchCriteria.append(" and e.employeeId = (select eMas.empId from EmployeeMaster eMas where e.employeeId = eMas.empId and eMas.headId = ?)");
						searchCriteria.append(" and l.employee.employeeId = (select eMas.employee.employeeId from EmpHeadInfo eMas where l.employee.employeeId = eMas.employee.employeeId and eMas.headInfo.headId = ? and eMas.status = 'A')");
					}
					if(!Utils.isEmpty(name))
						//searchCriteria.append(" and l.employee.employeeId = (select e.employeeId from Employee where e.employeeId = l.empId and (e.firstName like :fname or e.middleName like :mname or e.lastName like :lname))");
						searchCriteria.append(" and l.employee.firstName like :fname or l.employee.middleName like :mname or l.employee.lastName like :lname))");
					
					searchCriteria.append(" order by l.employee.employeeId");
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
					leaves = query.list();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					HibernateConnection.closeSession(session);
				}
			
			return leaves;
		}
	
	public List<LeaveVO> getEmpLeave(String empId){
		List<LeaveVO> leaveVOList = null;
		Session session = null;
		try{
			String queryString = " select new com.payroll.employee.leave.vo.LeaveVO(l.employee.employeeId, "+
					"(select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = l.employee.employeeId and eDept.status='A'), "
					+ "(select eDesg.designation.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = l.employee.employeeId "
					+ "and eDesg.status = 'A'), "
					+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = l.employee.employeeId and dh.status='A'), "
					+ "l.leaveId, l.leaveType.id, l.leaveBalance) from Leave l where l.employee.employeeId = ? and l.status = ?";		
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			//leaveVO = (LeaveVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			leaveVOList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return leaveVOList;
	}
	
	public List<LeaveVO> getEmpLeaveByType(String empId, int leaveType){
		List<LeaveVO> leaveVOList = null;
		Session session = null;
		try{
			String queryString = " select new com.payroll.employee.leave.vo.LeaveVO(l.employee.employeeId, "+
					"(select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = l.employee.employeeId and eDept.status='A'), "
					+ "(select eDesg.designation.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = l.employee.employeeId "
					+ "and eDesg.status = 'A'), "
					+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = l.employee.employeeId and dh.status='A'), "
					+ "l.leaveId, l.leaveType.id, l.leaveBalance) from Leave l where l.employee.employeeId = ? and l.status = ? and l.leaveType.id = ?";		
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			query.setParameter(2, leaveType);
			//leaveVO = (LeaveVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			leaveVOList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return leaveVOList;
	}
	
	public Leave getEmpLeave(String empId, int leaveTypeId){
		List<Leave> leaveVOList = null;
		Session session = null;
		try{
			
			String queryString = "from Leave l where l.employee.employeeId = ? and l.status = ? and l.leaveType.id = ?";		
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			query.setParameter(2, leaveTypeId);
			//leaveVO = (LeaveVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			leaveVOList = query.list();
			
			leaveVOList.get(0);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return (leaveVOList.size() > 0) ?leaveVOList.get(0) : null;
	}
	
		private Leave getLeaveById(String employeeId, int leaveId, Session session){
			Leave leave = null;
			try{
				if(session == null || !session.isOpen())
					session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(" from Leave l where l.employee.employeeId = ? and l.leaveType.id = ?");
				query.setParameter(0, employeeId);
				query.setParameter(1, leaveId);
				leave = (Leave)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			}catch(Exception e){
				e.printStackTrace();
			}
			return leave;
		}
		
		public String addLeaveRequest(LeaveRequest leaveRequest) {
			String result = null;
			Session session = null;
			Transaction transaction = null;
			LeaveVO leave = null;
			LeaveRequestVO request = new LeaveService().getEmpLeaveRequestById(leaveRequest.getEmployeeId(), leaveRequest.getLeaveTypes());
			try{
				
				leave = new LeaveService().getLeaveDetailsById(leaveRequest.getEmployeeId(), leaveRequest.getLeaveTypes());
				session = HibernateConnection.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				Employee employee = null;
				
				String fromDate = formatDate(leaveRequest.getFromDate());
				String toDate = formatDate(leaveRequest.getToDate());
				
				
				leaveRequest.setFromDate(fromDate);
				leaveRequest.setToDate(toDate);
				leaveRequest.setLeaveType(leaveRequest.getLeave().getLeaveType());
				leaveRequest.setStatus("A");
				leaveRequest.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				
				
				int newLeavebal = leave.getLeaveBalance() - leaveRequest.getNoOfLeaves();
				if (leaveRequest.getAddUpdate() == 0) {
					session.save(leaveRequest);
					
					Query query = session.createQuery("update Leave l set l.leaveBalance = ? where l.employee.employeeId = ? and l.leaveType.id = ?");
					//leave.setLeaveBalance(newLeavebal);
					
					query.setParameter(0, newLeavebal);
					query.setParameter(1, leaveRequest.getEmployeeId());
					query.setParameter(2, leaveRequest.getLeaveType().getId());
					
					int updated = query.executeUpdate();
					if(updated > 0) {
						result = "Successfully update Employee leave!";
					}
					
				}else {
					
					int oldLeave = 0;
					if (request!=null) {
						oldLeave = request.getNoOfLeaves();
					}
					
					Query leaveQuery = session.createQuery("update Leave l set l.leaveBalance = ? where l.employee.employeeId = ? and l.leaveType.id = ?");
					//leave.setLeaveBalance(newLeavebal);
					
					leaveQuery.setParameter(0, newLeavebal);
					leaveQuery.setParameter(1, leaveRequest.getEmployeeId());
					leaveQuery.setParameter(2, leaveRequest.getLeaveType().getId());
					
					leaveQuery.executeUpdate();
					
					Query query = session.createQuery("update LeaveRequest lr set lr.noOfLeaves = ?, lr.fromDate = ?, lr.toDate = ?, lr.reason = ?, "
							+ "lr.status = ?, lr.rowUpdDate = ? where lr.employee.employeeId = ? and  lr.status = ?");
								
					query.setParameter(0, leaveRequest.getNoOfLeaves()+oldLeave);
					query.setParameter(1, leaveRequest.getFromDate());
					query.setParameter(2, leaveRequest.getToDate());
					query.setParameter(3, leaveRequest.getReason());
					query.setParameter(4, "A");
					query.setParameter(5, leaveRequest.getRowUpdDate());
					
					query.setParameter(6, leaveRequest.getEmployeeId());
					query.setParameter(7, "A");
					
					int updated = query.executeUpdate();
					if(updated > 0)
						result = "Successfully update Employee leave!";
						}
				
				transaction.commit();
				result = "Yes";
			}catch(ConstraintViolationException cv){
				cv.printStackTrace();
				transaction.rollback();
				result = "Selected Leave details are already exist for Employee!";
			}catch(Exception e){
				e.printStackTrace();
				transaction.rollback();
				result = "Unable to Add/Update leave for selected Employee!";
			}finally {
				HibernateConnection.closeSession(session);
			}
			return result;
		}
		
		public static String formatDate(String inDate) {
		    String outDate = "";
		    if (inDate != null) {
		        try {
		            Date date = inSDF.parse(inDate);
		            outDate = outSDF.format(date);
		        } catch (ParseException ex){ 
		        	ex.printStackTrace();
		        }
		    }
		    return outDate;
		  }
		
		public String addUpdateLeave(List<Leave> leaves){
			String result = null;
			Session session = null;
			Transaction transaction = null;
			try{
				session = HibernateConnection.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				Employee employee = null;
				for (Iterator iterator = leaves.iterator(); iterator.hasNext();) {
					Leave leave = (Leave) iterator.next();
					if(employee == null){
						//employee = new EmployeeDAO().getById(leave.getEmployeeId());
						employee = (Employee)session.load(Employee.class, leave.getEmployeeId());
					}
//					System.out.println("addUpdateLeave --> Leave:"+leave);
//					if(leave != null && leave.getLeaveId() !=0){
//						Leave leaveDB = getLeaveById(leave.getEmployeeId(), leave.getLeaveId(), session);
//						leaveDB.setEmployee(employee);
////						leaveDB.setLeaveBalance(leave.getNoOfLeaves() + leaveDB.getLeaveBalance());
//						leaveDB.setStatus("A");
//						leaveDB.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
//						session.update(leaveDB);
//					}else {
						/*if(checkLeaveExist(leave.getEmployeeId(), leave.getLeaveType(), session)){
							result = "Leave type is exist for selected Employee, please select other or update existing!";
							return result;
						}*/
						//int maxId = getMaxLeaveId(session);
						leave.setStatus("A");
						leave.setEmployee(employee);
//						leave.setLeaveBalance(leave.getNoOfLeaves());
						leave.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
					//	leave.setLeaveId(maxId);
						session.save(leave);
//					}
				}
				session.flush();
				transaction.commit();
				result = "Yes";
			}catch(ConstraintViolationException cv){
				cv.printStackTrace();
				transaction.rollback();
				result = "Selected Leave details are already exist for Employee!";
			}catch(Exception e){
				e.printStackTrace();
				transaction.rollback();
				result = "Unable to Add/Update leave for selected Employee!";
			}finally {
				HibernateConnection.closeSession(session);
			}
			return result;
		}
		private boolean checkLeaveExist(String empId, String leaveType, Session session){
			boolean exist = false;
			try{
				if(session == null)
					session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(" from Leave l where l.employee.employeeId = ? and l.leaveType = ? and l.status = ?");
				query.setParameter(0, empId);
				query.setParameter(1, leaveType);
				query.setParameter(2, "A");
				exist = !(query.list().isEmpty()) ? true: false;	
			}catch(Exception e){
				e.printStackTrace();
			}
			return exist;
		}
		private int getMaxLeaveId(Session session){
			int maxId = 0;
			//Session session = null;
			try{
				if(session == null)
					session = HibernateConnection.getSessionFactory().openSession();
				Leave leave = (Leave)session.createQuery("select l from Leave l order by l.leaveId desc").setMaxResults(1).uniqueResult();
				int leaveId = (leave != null) ? leave.getLeaveId() : 0;
				maxId = leaveId + 1;
				System.out.println("maxId:"+maxId);
			}catch(Exception e){
				e.printStackTrace();
				maxId = 0;
			}/*finally {
				HibernateConnection.closeSession(session);
			}*/
			return maxId;
		}
		
		public String deleteEmpLeave(String empId, String leaveId){
			String result = null;
			Session session = null;
			Transaction transaction = null;
			try{
				session = HibernateConnection.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				Query query = session.createQuery("update Leave l set l.status = ?, l.rowUpdDate = ? where l.employee.employeeId = ?");
				query.setParameter(0, "I");
				query.setParameter(1, new Timestamp(System.currentTimeMillis()));
				query.setParameter(2, empId);
				int updated = query.executeUpdate();
				transaction.commit();
				if(updated > 0)
					result = "Successfully deleted Leave!";
			}catch(Exception e){
				e.printStackTrace();
				transaction.rollback();
				result = "Failed deleted Leave!";
			}finally{
				HibernateConnection.closeSession(session);
			}
			return result;
		}
		
		public List<LeaveVO> getLeavesByEmp(String empId){
			List<LeaveVO> leaveList = null;
			Session session = null;
			try{
				session = HibernateConnection.getSessionFactory().openSession();
				String queryString = " select new com.payroll.employee.leave.vo.LeaveVO(l.employee.employeeId, "
						//+ "(select e.firstName from Employee e where e.employeeId = l.empId),"
						//+ " (select e.lastName from Employee e where e.employee.employeeId = l.employee.employeeId), "
						+"l.employee.firstName, l.employee.lastName, "
//						+ "l.leaveId, l.leaveType, l.noOfLeaves, l.leaveBalance) from Leave l where l.status = ? and l.employee.employeeId = ?";		
						+ "l.leaveId, l.leaveType.id, l.leaveBalance) from Leave l where l.status = ? and l.employee.employeeId = ?";	
				
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				query.setParameter(1, empId);
				leaveList = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
			return leaveList;
		}
		
		public List<LeaveType> getLeaveTypeById(int id) {
			List<LeaveType> leaveTypes = new ArrayList<LeaveType>();
			Session session = null;
			try{
				session = HibernateConnection.getSessionFactory().openSession();
				String queryString = " from LeaveType where id=?";		
				Query query = session.createQuery(queryString);
				query.setParameter(0, id);
				leaveTypes = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
			return leaveTypes;
			
		}
		
		public LeaveRequestVO getEmpLeaveRequest(String empId){
			LeaveRequestVO leaveRequestVOs = null;
			Session session = null;
			String queryString;
			try{
				queryString = " select new com.payroll.employee.leave.dataobjects.LeaveRequestVO(l.employee.employeeId, l.noOfLeaves, l.fromDate "
						+ ",l.toDate, l.reason, l.employee.lastName, l.employee.firstName, l.leaveType.description) from LeaveRequest l where l.employee.employeeId = ? and l.status = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				leaveRequestVOs = (LeaveRequestVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
			return leaveRequestVOs;
		}
		
		public List<LeaveType> getLeaveType() {
			List<LeaveType> leaveTypes = new ArrayList<LeaveType>();
			Session session = null;
			try{
				session = HibernateConnection.getSessionFactory().openSession();
				String queryString = " from LeaveType";		
				Query query = session.createQuery(queryString);
				leaveTypes = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
			return leaveTypes;
			
		}
		
		public LeaveRequestVO getEmpLeaveRequestById(String empId, String leaveType){
			LeaveRequestVO leaveRequestVOs = null;
			Session session = null;
			String queryString;
			try{
				queryString = " select new com.payroll.employee.leave.dataobjects.LeaveRequestVO(l.employee.employeeId, l.noOfLeaves, l.fromDate "
						+ ",l.toDate, l.reason, l.employee.lastName, l.employee.firstName, l.leaveType.description) from LeaveRequest l where l.employee.employeeId = ? and l.status = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, empId);
				query.setParameter(1, "A");
				leaveRequestVOs = (LeaveRequestVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
			return leaveRequestVOs;
		}
}
