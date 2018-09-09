package com.payroll.employee.arrears.dataobjects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.payroll.HibernateConnection;
import com.payroll.employee.arrears.vo.EmpArrearsVO;
import com.payroll.employee.dataobjects.Employee;

public class EmpArrearDAO {
	public List<com.payroll.employee.arrears.dataobjects.EmpArrears> getArrearsList() {
		List<com.payroll.employee.arrears.dataobjects.EmpArrears> arrearsVOs = null;
		Session session = null;

		try {
			String queryString = " select new com.payroll.employee.arrears.vo.EmpArrearsVO(arrPay.arrearId, arrPay.employee.employeeId, arrPay.arrearsType, arrPay.arrearsPay, arrPay.arrearsDeductions, arrPay.miscPay, "
					+ " arrPay.miscDeductions, arrPay.arrearsPayNote, arrPay.arrearsDeductionNote, arrPay.employee.firstName as fName, arrPay.employee.lastName as lName) from EmpArrears arrPay where arrPay.status = ?";

			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			arrearsVOs = query.list();
			//System.out.println("EMP Arrears Size : " + query.list().size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateConnection.closeSession(session);
		}

		return arrearsVOs;
	}

	public EmpArrearsVO getArrearsByArrearId(int arrearId) {
		//System.out.println("getArrearsByArrearId called... " + arrearId);
		EmpArrearsVO arrearsVO = null;
		Session session = null;

		try {
			String queryString = " select new  com.payroll.employee.arrears.vo.EmpArrearsVO(empArr.arrearId, empArr.employee.employeeId, empArr.arrearsType, empArr.arrearsPay, empArr.arrearsDeductions, empArr.miscPay, "
					+ " empArr.miscDeductions, empArr.arrearsPayNote, empArr.arrearsDeductionNote, "
					+ "(select dept.department.departmentId from EmpDepartment dept where dept.employee.employeeId = empArr.employee.employeeId), "
					+ "(select desg.designation.designationId from EmpDesignation desg where desg.employee.employeeId = empArr.employee.employeeId), "
					+ "(select dh.headInfo.headId from EmpHeadInfo dh where dh.employee.employeeId = empArr.employee.employeeId)) from EmpArrears empArr where empArr.status = ? and empArr.arrearId = ?";

			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(queryString);
			query.setParameter(0, "A");
			query.setParameter(1, arrearId);
			arrearsVO = (EmpArrearsVO) (!query.list().isEmpty() ? query.list().get(0) : null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateConnection.closeSession(session);
		}
		return arrearsVO;
	}

	public String deleteArrearsPay(int arrearId) {
		//FIXME: Review Comments - Chetan: Check for transaction as well as session flush
		String result = null;
		Session session = null;
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			Query query = session.createQuery(
					"update EmpArrears empArr set empArr.status = ?, empArr.rowUpdatedDate = ? where empArr.arrearId = ?");
			query.setParameter(0, "I");
			query.setParameter(1, new Date());
			query.setParameter(2, arrearId);
			int updated = query.executeUpdate();
			if (updated > 0)
				result = "Successfully deleted Arrears Details!";
		} catch (Exception e) {
			e.printStackTrace();
			result = "Failed to delete Arrears details!";
		} finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}

	public String addUpdateArrears(EmpArrears arrears) {
		String result = null;
		Session session = null;
		Transaction transaction = null;
		try {
			//System.out.println("******arrears.getEmployeeId() " + arrears.getEmployeeId());
			session = HibernateConnection.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Employee employee = (Employee) session.load(Employee.class, arrears.getEmployeeId());
			arrears.setEmployee(employee);
			arrears.setStatus("A");
			//arrears.setEmployeeId(arrears.getEmployeeId());
			arrears.setRowUpdatedDate(new Timestamp(System.currentTimeMillis()));
			//System.out.println("arrears.getArrearId()" + arrears.getArrearId());
			//System.out.println("arrears.getAddUpdate()" + arrears.getAddUpdate());
			if (arrears.getAddUpdate() == 0)
				session.save(arrears);
			else
				session.update(arrears);
			transaction.commit();
			result = "Yes";
		} catch (ConstraintViolationException cv) {
			cv.printStackTrace();
			transaction.rollback();
			result = "Arrears details are already exist for selected Employee!";
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			result = "Failed to Add/Update Employee Arrears!";
		} finally {
			HibernateConnection.closeSession(session);
		}
		return result;
	}
	
	public List<com.payroll.employee.arrears.dataobjects.EmpArrears> getEmpArrearList(int empId){
		//System.out.println("Employee id is " + empId);
		List<com.payroll.employee.arrears.dataobjects.EmpArrears> empArrears = null;
		Session session = null;
			try{
				String queryString = "select new com.payroll.employee.arrears.dataobjects.EmpArrears(empArr.employee.employeeId, empArr.arrearId, empArr.arrearsType, empArr.arrearsPay, empArr.arrearsDeductions, empArr.miscPay, " + 
						" empArr.miscDeductions, empArr.arrearsPayNote, empArr.arrearsDeductionNote ) from EmpArrears empArr where empArr.status = ? and empArr.employee.employeeId = ?";			
				session = HibernateConnection.getSessionFactory().openSession();
				Query query = session.createQuery(queryString);
				query.setParameter(0, "A");
				query.setParameter(1, empId);
				empArrears = query.list();
				//empArrears = (List<EmpArrears>) (!(query.list().isEmpty())?query.list().get(0):null);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				HibernateConnection.closeSession(session);
			}
		return empArrears;
	}

}
