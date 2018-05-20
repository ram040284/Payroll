//package com.kcb.hrms.payroll.dao;
package com.payroll.hrms.payroll.dao;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.payroll.HibernateConnection;
import com.payroll.TestData;
import com.payroll.employee.advances.dataobjects.EmpAdvances;
import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.allowance.dataobjects.EmpAllowanceDAO;
import com.payroll.employee.allowance.vo.EmployeeAllowances;
import com.payroll.employee.bank.vo.BankVO;
import com.payroll.employee.deductions.dao.EmpFixedDeductionsDAO;
import com.payroll.employee.deductions.dao.EmpVarDeductionsDAO;
//import com.payroll.employee.deductions.dataobjects.EmpDeductionDetails;
import com.payroll.employee.deductions.dataobjects.EmpDeductions;
import com.payroll.employee.deductions.dataobjects.EmployeeFixedDeductions;
import com.payroll.employee.deductions.dataobjects.EmployeeVarDeductions;
import com.payroll.employee.leave.dataobjects.Leave;
import com.payroll.employee.leave.dataobjects.LeaveRequest;
import com.payroll.employee.lic.dataobjects.EmpLic;
import com.payroll.employee.lic.dataobjects.EmpLicDAO;
import com.payroll.employee.lic.vo.EmployeeLIC;
import com.payroll.employee.pf.dataobjects.EmpPf;
import com.payroll.employee.qtr.dataobjects.EmpQuarters;
import com.payroll.employee.salary.dataobjects.Salary;
import com.payroll.employee.vo.EmployeeVO;
/*import com.kcb.hrms.payroll.dataobjects.EmployeePayroll;
import com.kcb.hrms.payroll.dataobjects.EmployeePayrollDTO;*/
import com.payroll.hrms.payroll.dataobjects.EmployeePayroll;
import com.payroll.hrms.payroll.dataobjects.EmployeePayrollDTO;
import com.payroll.hrms.payroll.service.EmployeePayrollService;
import com.payroll.overtime.dataobjects.Overtime;
/**
 * Created by rajendra on 12/8/17.
 */
public class EmployeePayrollDAO {
	private Session session = null;
	private int employeeId; 
	private Date date;
	
    @SuppressWarnings("unchecked")
	public EmployeePayroll loadPayrollInfo(int employeeId, Date date){
    	System.out.println("loadPayrollInfo:");
    	Salary salary = null;
		EmpPf empPf = null;
		
		EmpAdvances empAdvances = null;
		//EmpLic empLic = null;
		List<Overtime> overtimeList = null;
		List<LeaveRequest> leaves = null;
		EmployeePayroll empPayroll = null;
		BankVO bankVo = null;
    	try{
    		this.employeeId = employeeId;
			session = HibernateConnection.getSessionFactory().openSession();
			salary = (Salary)getObjectByEmpId(" from Salary s where s.employee.employeeId = ? and s.status = ?");
			empPf = (EmpPf)getObjectByEmpId(" from EmpPf p where p.employee.employeeId = ? and p.status = ?");
			
			EmployeeAllowances employeeAllowances = new EmpAllowanceDAO().getEmployeeAllowances(employeeId);
			
			// Employee Fixed Deductions details
	    	EmployeeFixedDeductions employeeFixedDeductions = new EmpFixedDeductionsDAO().getEmployeeFixedDeductions(employeeId);
	    	
	    	// Employee Monthly Variable Deductions details
	    	EmployeeVarDeductions employeeVarDeductions = new EmpVarDeductionsDAO().getEmployeeVarDeductions(employeeId);
	    	
	    	// Employee Lic Deductions details
	    	List <EmployeeLIC> listEmployeeLICDeductions = new EmpLicDAO().getEmployeeLicDeductions(employeeId);
	    	double licTotalInstallmentAmt = 0;
	    	if (listEmployeeLICDeductions!=null){
	    		Iterator<EmployeeLIC> listEmpLicIterator = listEmployeeLICDeductions.iterator();
	    		
	    		while (listEmpLicIterator.hasNext()){
	    			EmployeeLIC employeeLIC = listEmpLicIterator.next();
	    			licTotalInstallmentAmt =licTotalInstallmentAmt + employeeLIC.getInstlmtAmt();
	    		}
	    	}
	    	
			overtimeList = (List)getObjectsByEmpId(date, " from Overtime o where o.employee.employeeId = ? and o.status = ? and o.overtimeDate >= ?");
			this.date = null;
			leaves = (List)getObjectsByEmpId(" from LeaveRequest l where l.employee.employeeId = ? and l.status = ?");
			empAdvances = (EmpAdvances)getObjectByEmpId(" from EmpAdvances a where a.employee.employeeId = ? and a.status = ?");
   			bankVo = (BankVO)getObjectByEmpId("select new com.payroll.employee.bank.vo.BankVO(b.bankDetails.bankId, b.bankDetails.bankName, b.accountNo) "
   					+ "from EmpBank b where b.employee.employeeId = ? and b.status = ?");		
			double overtimeAmount = EmployeePayrollService.overtimeHours(overtimeList);
			double abcenties = EmployeePayrollService.getAbsenties(leaves);
			
			// This need to check 
			double festAdvanceRecovery = empAdvances.getInstallAmount();
			double bankLoanRecovery = 0;
			double cpfRecovery = 0;
   			
   			empPayroll = new EmployeePayroll(salary.getBasic(), salary.getGradePay(), salary.getScalePay()+"", salary.getScaleCode(),
   					employeeAllowances.getCca(), employeeAllowances.getFamilyPlanAlwance(),employeeAllowances.getNonPracAwance(),
   					employeeAllowances.getWashingAlwance(), employeeAllowances.getUniformAlwance(), employeeAllowances.getHraFlag(),
   					employeeFixedDeductions.getUnionFee(),employeeFixedDeductions.getElectricityRecovery(), employeeFixedDeductions.getCourtRecovery(),
   					employeeFixedDeductions.getGis(), employeeVarDeductions.getAfkRent(), employeeVarDeductions.getOtherDeductions(),
   					employeeVarDeductions.getSociety(), licTotalInstallmentAmt, empPf.getPfLoneRecAmt(), empPf.getPfsCpfCntrbn(),
   					cpfRecovery, festAdvanceRecovery , bankLoanRecovery, abcenties, overtimeAmount, bankVo.getBankName(), 
   					bankVo.getAccountNo(), bankVo.getBankId());
	
   		}catch(Exception e){
			e.printStackTrace();
		}finally {
			HibernateConnection.closeSession(session);
		}
        return empPayroll;
    }
   
    
    private List getObjectsByEmpId(Date date, String queryString){
    	this.date = date;
    	return getObjectsByEmpId(queryString);
    }
    private Object getObjectByEmpId(String queryString){
    	Object object = null;
    	Query query = session.createQuery(queryString); 
		query.setParameter(0, employeeId);
		query.setParameter(1, "A");
		object = (!(query.list().isEmpty()) ? query.list().get(0) : null);
		return object;
    }
    private List getObjectsByEmpId(String queryString){
    	Query query = session.createQuery(queryString);
		query.setParameter(0, employeeId);
		query.setParameter(1, "A");
		if(date != null)
			query.setParameter(2, date);
		return query.list();
	}
  /**
     * 
     * @param deptId
     * @return
     */
    public List<EmployeeVO> getEmployeesByDept(int deptId){
    	List<EmployeeVO> employeeList = null;
    	try{
    		session = HibernateConnection.getSessionFactory().openSession();
    		String queryString = "select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName,"
					+ " e.pan, e.dob, e.retirementDate, "
					+ "(select dept.departmantName from Department dept where dept.departmentId = (select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = e.employeeId)),"
					+ "(select h.headName from HeadInfo h where h.headId = (select eMas.headInfo.headId from EmpHeadInfo eMas where eMas.employee.employeeId = e.employeeId)),"
					+ "(select desg.designationName from Designation desg where desg.designationId = "
					+ "(select eDesg.designation.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = e.employeeId)), "
					+ "e.joiningDate) from Employee e where e.status = ? and e.employeeId in "
    			+ "(select eDept.employee.employeeId from EmpDepartment eDept where eDept.department.departmentId = ?)";
    		Query query = session.createQuery(queryString);
    		query.setParameter(0, "A");
    		query.setParameter(1, deptId);
    		employeeList = query.list();
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		HibernateConnection.closeSession(session);
    	}
    	return employeeList;
    }

    /**
     * 
     * @param deptId
     * @return
     */
    public List<Integer> getEmployeeIdsByDept(int deptId){
    	List<Integer> employeeList = null;
    	try{
    		session = HibernateConnection.getSessionFactory().openSession();
    		String queryString = "select e.employeeId"
					+" from Employee e where e.status = ? and e.employeeId in "
    			+ "(select eDept.employee.employeeId from EmpDepartment eDept where eDept.department.departmentId = ?)";
    		Query query = session.createQuery(queryString);
    		query.setParameter(0, "A");
    		query.setParameter(1, deptId);
    		employeeList = query.list();
    	}catch(Exception e){ 
    		e.printStackTrace();
    	}finally {
    		HibernateConnection.closeSession(session);
    	}
    	return employeeList;
    }   
}