package com.payroll.employee.leave.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.payroll.HibernateConnection;
import com.payroll.Utils;
import com.payroll.employee.leave.vo.LeaveVO;
import com.payroll.employee.salary.dataobjects.Salary;
import com.payroll.employee.salary.vo.SalaryVO;
import com.payroll.headInfo.dataobjects.HeadInfo;

public class LeaveDAO {
	private int deptId;
	private int headId = 0;
	private String name; 
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
					//String queryString
					searchCriteria.append(" select new com.payroll.employee.leave.vo.LeaveVO(l.empId, (select e.firstName from Employee e where e.employeeId = l.empId),"
							+ " (select e.lastName from Employee e where e.employeeId = l.empId), "
							+ "l.leaveId, l.leaveType, l.noOfLeaves, l.leaveBalance) from Leave l where l.status = ?");
					if(deptId != 0)
						searchCriteria.append(" and l.empId = (select eDept.empId from EmployeeMaster eDept where l.empId = eDept.empId and eDept.departmentId = ?)");
					if(headId != 0){
						//searchCriteria.append(" and e.employeeId = (select eMas.empId from EmployeeMaster eMas where e.employeeId = eMas.empId and eMas.headId = ?)");
						searchCriteria.append(" and l.empId = (select eMas.empId from EmployeeMaster eMas where l.empId = eMas.empId and eMas.headId = ?)");
					}
					if(!Utils.isEmpty(name))
						searchCriteria.append(" and l.empId = (select e.employeeId from Employee where e.employeeId = l.empId and (e.firstName like :fname or e.middleName like :mname or e.lastName like :lname))");
					
					searchCriteria.append(" order by l.empId");
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
	public List<LeaveVO> getEmpLeave(int empId){
		List<LeaveVO> leaveVOList = null;
		Session session = null;
		try{
			String queryString = " select new com.payroll.employee.leave.vo.LeaveVO(l.empId, "+
					"(select dept.departmentId from Department dept where dept.departmentId = (select eDept.departmentId "
					+ "from EmpDepartment eDept where eDept.empId = l.empId)), (select desg.designationId "
					+ "from Designation desg where desg.designationId = (select eDesg.designationId from EmpDesignation eDesg "
					+ "where eDesg.empId = l.empId and eDesg.lastWokingDate is null)), "
					+ "(select dh.headId from DesignationHead dh where dh.designationId = "
					+ "(select eDesg.designationId from EmpDesignation eDesg where eDesg.empId = l.empId and eDesg.lastWokingDate is null)), "
					+ "l.leaveId, l.leaveType, l.noOfLeaves, l.leaveBalance) from Leave l where l.empId = ?";		
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, empId);
			//leaveVO = (LeaveVO)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			leaveVOList = query.list();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return leaveVOList;
	}
	
		private Leave getLeaveById(int leaveId, Session session){
			Leave leave = null;
			try{
				if(session == null || !session.isOpen())
					session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(" from Leave l where l.leaveId = ?");
				query.setParameter(0, leaveId);
				leave = (Leave)(!(query.list().isEmpty()) ? query.list().get(0) : null);
			}catch(Exception e){
				e.printStackTrace();
			}
			return leave;
		}
		public String addUpdateLeave(List<Leave> leaves){
			String result = null;
			Session session = null;
			Transaction transaction = null;
			try{
				session = HibernateConnection.getSessionFactory().openSession();
				transaction = session.beginTransaction();
				for (Iterator iterator = leaves.iterator(); iterator.hasNext();) {
					Leave leave = (Leave) iterator.next();
					System.out.println("addUpdateLeave --> Leave:"+leave);
					if(leave != null && leave.getLeaveId() !=0){
						Leave leaveDB = getLeaveById(leave.getLeaveId(), session);
						leaveDB.setLeaveBalance(leave.getNoOfLeaves() + leaveDB.getLeaveBalance());
						leaveDB.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
						session.update(leaveDB);
					}else {
						if(checkLeaveExist(leave.getEmpId(), leave.getLeaveType(), session)){
							result = "Leave type is exist for selected Employee, please select other or update existing!";
							return result;
						}
						int maxId = getMaxLeaveId(session);
						leave.setStatus("A");
						leave.setLeaveBalance(leave.getNoOfLeaves());
						leave.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
						leave.setLeaveId(maxId);
						session.save(leave);
					}
				}
				
				transaction.commit();
				result = "Yes";
			}catch(Exception e){
				e.printStackTrace();
				transaction.rollback();
				result = "Unable to Add/Update leave for selected Employee!";
			}finally {
				HibernateConnection.closeSession(session);
			}
			return result;
		}
		private boolean checkLeaveExist(int empId, String leaveType, Session session){
			boolean exist = false;
			try{
				if(session == null)
					session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(" from Leave l where l.empId = ? and l.leaveType = ? and l.status = ?");
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
		
		public String deleteEmpLeave(int empId){
			String result = null;
			Session session = null;
			try{
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery("update Leave l set l.status = ?, l.rowUpdDate = ? where l.empId = ?");
				query.setParameter(0, "S");
				query.setParameter(1, new Date());
				query.setParameter(2, empId);
				int updated = query.executeUpdate();
				if(updated > 0)
					result = "Successfully deleted Leave!";
			}catch(Exception e){
				e.printStackTrace();
				result = "Failed deleted Leave!";
			}finally{
				HibernateConnection.closeSession(session);
			}
			return result;
		}
		
		public List<LeaveVO> getLeavesByEmp(int empId){
			List<LeaveVO> leaveList = null;
			Session session = null;
			try{
				session = HibernateConnection.getSessionFactory().openSession();
				String queryString = " select new com.payroll.employee.leave.vo.LeaveVO(l.empId, (select e.firstName from Employee e where e.employeeId = l.empId),"
						+ " (select e.lastName from Employee e where e.employeeId = l.empId), "
						+ "l.leaveId, l.leaveType, l.noOfLeaves, l.leaveBalance) from Leave l where l.status = ? and l.empId = ?";		
				
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
}
