package com.payroll.employee.bank.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.bank.dataobjects.BankDetails;
import com.payroll.bank.dataobjects.BankDetailsDAO;
import com.payroll.employee.bank.vo.BankVO;
import com.payroll.employee.dataobjects.Employee;
import com.payroll.employee.dataobjects.EmployeeDAO;
import com.payroll.employee.salary.dataobjects.Salary;

public class EmpBankDAO {
	
	public List<com.payroll.employee.bank.vo.BankVO> getBankList(){
		List<com.payroll.employee.bank.vo.BankVO> bankList = null;
			Session session = null;
			
			try{
				String queryString = " select new com.payroll.employee.bank.vo.BankVO(b.employee.employeeId, "
						//+ "(select e.firstName from Employee e where e.employeeId = b.empId),"
						//+ " (select e.lastName from Employee e where e.employeeId = b.empId), "
						+ "b.employee.firstName, b.employee.lastName, b.bankDetails.bankId, "
						+ "b.bankDetails.bankName, b.bankDetails.ifscCode, b.accountNo) from EmpBank b where b.status = ?";		
				
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				bankList = query.list();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		
		return bankList;
	}
	
	public BankVO getBankByEmpId(int empId){
		BankVO bankVO = null;
		Session session = null;
		
		try{
			String queryString = " select new com.payroll.employee.bank.vo.BankVO(b.employee.employeeId, "
					+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = b.employee.employeeId), "
					//+ "from EmpDepartment eDept where eDept.empId = b.empId)), (select desg.designationId "
					+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = b.employee.employeeId and desg.lastWokingDate is null), "
					+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = b.employee.employeeId and dh.lastWokingDate is null), "
					//+ "(select eDesg.designationId from EmpDesignation eDesg where eDesg.empId = b.empId and eDesg.lastWokingDate is null)), "
					+ "b.bankDetails.bankId, b.bankDetails.bankName, b.bankDetails.ifscCode, b.accountNo) from EmpBank b "
					+ "where b.status = ? and b.employee.employeeId = ?";		
			
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			query.setParameter(1, empId);
			bankVO = (BankVO)(!query.list().isEmpty() ? query.list().get(0) : null);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateConnection.closeSession(session);
		}
		return bankVO;
	}
	
	public String addUpdateBank(EmpBank bank){
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			//EmpBank bankDB = checkEmpBank(bank.getEmployeeId(), session);
			/*Employee employee = new EmployeeDAO().getById(bank.getEmployeeId());
			BankDetails bankDetails = new BankDetailsDAO().getBankDetailsById(bank.getBankId());*/
			Employee employee = (Employee)session.load(Employee.class, bank.getEmployeeId());
			BankDetails bankDetails = (BankDetails)session.load(BankDetails.class, bank.getBankId());
			/*if(bankDB != null){
				if(bank.getAddUpdate() ==0){
					result = "Bank details are exist for employee is exist!";
					return result;
				}
				System.out.println("Update");
				bankDB.setBankName(bank.getBankName());
				bankDB.setAccountNo(bank.getAccountNo());
				bankDB.setIfscCode(bank.getIfscCode());
				bankDB.setStatus("A");
				bankDB.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.refresh(bankDB);
			}else {
				System.out.println("Add");
				bank.setStatus("A");
				bank.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
				session.save(bank);
			}*/
			bank.setEmployee(employee);
			bank.setBankDetails(bankDetails);
			bank.setStatus("A");
			bank.setRowUpdDate(new Timestamp(System.currentTimeMillis()));
			if(bank.getAddUpdate() == 0)
				session.save(bank);
			else
				session.update(bank);
			transaction.commit();
			result = "Yes";
		}catch(ConstraintViolationException cv){
			cv.printStackTrace();
			transaction.rollback();
			result = "Bank details are already exist for selected Employee!";
		}catch(Exception e){
			e.printStackTrace();
			transaction.rollback();
			result = "Failed to Add/Update Employee Bank!";
		}finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}

	private EmpBank checkEmpBank(int empId, Session session){
		EmpBank bank = null;
		try{
			if(session == null)
				session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("select b from EmpBank b where b.empId = ? and b.status = ?");
			//.setMaxResults(1).uniqueResult();
			query.setParameter(0, empId);
			query.setParameter(1, "A");
			if(query.list() !=null && !query.list().isEmpty() )
				bank = (EmpBank)query.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			session.clear();
		}
		
		return bank;
	}
	
	public String deleteEmpBank(int empId){
		String result = null;
		Session session = null;
		try{
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery("update EmpBank b set b.status = ?, b.rowUpdDate = ? where b.employee.employeeId = ?");
			query.setParameter(0, "S");
			query.setParameter(1, new Date());
			query.setParameter(2, empId);
			int updated = query.executeUpdate();
			if(updated > 0)
				result = "Successfully deleted Bank Details!";
		}catch(Exception e){
			e.printStackTrace();
			result = "Failed to delete Bank details!";
		}finally{
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
}
