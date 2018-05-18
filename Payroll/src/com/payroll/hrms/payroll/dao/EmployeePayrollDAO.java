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
import com.payroll.employee.bank.vo.BankVO;
//import com.payroll.employee.deductions.dataobjects.EmpDeductionDetails;
import com.payroll.employee.deductions.dataobjects.EmpDeductions;
import com.payroll.employee.leave.dataobjects.Leave;
import com.payroll.employee.lic.dataobjects.EmpLic;
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
	private int deptId;
	private int month;
	private Date date;
	private double gradePay;
	private double basicPay;
	private double da;
	private double hra;
	private double cca;
	private double ta;
	private double npa;
	private double wa;
	private double ca;
	private double uniformAlw;
	private double familyPlaningAlw;
	private double totallw;
	private double otAmt;
	private double others;
	private double rent;
	private double afkRent;
	private double absentDed;
	private double festAdvRcry;
	private double pt;
	private double lic;
	private double socity;
	private double gis;
	private double bankLoanRcry;
	private double vlr;
	private double pfsCpf;
	private double apfAcf;
	private double pfLoanRcry;
	private double cpfRcry;
	private double incomTax;
	private double unionFee;
	private double elecRcry;
	private double courtRcry;
	private double otherDeducs;
	private double misc;
	
    @SuppressWarnings("unchecked")
	public EmployeePayroll loadPayrollInfo(int employeeId, Date date){
    	Salary salary = null;
		EmpPf empPf = null;
		EmpQuarters empQtr = null;
		EmpAllowance alowances = null;
		EmpDeductions empDeductions = null;
		//EmpDeductionDetails empDeductDetails = null;
		EmpAdvances empAdvances = null;
		EmpLic empLic = null;
		List<Overtime> overtimeList = null;
		List<Leave> leaves = null;
		EmployeePayrollDTO payrollDTO = new EmployeePayrollDTO();
		EmployeePayroll empPayroll = null;
		BankVO bankVo = null;
    	try{
    		this.employeeId = employeeId;
			session = HibernateConnection.getSessionFactory().openSession();
			salary = (Salary)getObjectByEmpId(" from Salary s where s.employee.employeeId = ? and s.status = ?");
			empPf = (EmpPf)getObjectByEmpId(" from EmpPf p where p.employee.employeeId = ? and p.status = ?");
			empQtr = (EmpQuarters)getObjectByEmpId(" from EmpQuarters q where q.employee.employeeId = ? and q.status= ?");
			//empDeductions = (EmpDeductions)getObjectByEmpId(" from EmpDeductions d where d.employee.employeeId = ? and d.status = ?");
			alowances = (EmpAllowance)getObjectByEmpId(" from EmpAllowance a where a.employee.employeeId = ? and a.status = ?");
			overtimeList = (List)getObjectsByEmpId(date, " from Overtime o where o.employee.employeeId = ? and o.status = ? and o.overtimeDate >= ?");
			this.date = null;
			leaves = (List)getObjectsByEmpId(" from Leave l where l.employee.employeeId = ? and l.status = ?");
			empLic = (EmpLic)getObjectByEmpId(" from EmpLic l where l.employee.employeeId = ? and l.status = ?");
   			empAdvances = (EmpAdvances)getObjectByEmpId(" from EmpAdvances a where a.employee.employeeId = ? and a.status = ?");
   			//empDeductDetails = (EmpDeductionDetails)getObjectByEmpId(" from EmpDeductionDetails d where d.employee.employeeId = ? and d.status = ?");
   			bankVo = (BankVO)getObjectByEmpId("select new com.payroll.employee.bank.vo.BankVO(b.bankDetails.bankId, b.bankDetails.bankName, b.accountNo) "
   					+ "from EmpBank b where b.employee.employeeId = ? and b.status = ?");		
			
   			if(salary == null){
   				salary = TestData.getSalary(employeeId);
   			}
   			if(empPf == null)
   				empPf = TestData.getEmpPf(employeeId);
   			if(empLic == null)
   				empLic = TestData.getEmpLic(employeeId);
   			if(alowances== null)
   				alowances = TestData.getAllowance(employeeId);
   			if(bankVo == null)
   				bankVo = TestData.getBAnkDetails(employeeId);
   			//if(empDeductDetails == null)
   			//	empDeductDetails = TestData.getEmpDeductDtls(employeeId);
   			
   		/*	empPayroll = new EmployeePayroll(salary.getBasic(), salary.getGradePay(), salary.getScalePay()+"", salary.getScaleCode(),
   				//	empDeductDetails.getAfkRent(), empPf.getPfLoneRecAmt(), empDeductDetails.getUnionFee(),empDeductDetails.getElectRecovery(),
   				//	empDeductDetails.getCourtRecovery(),empDeductDetails.getOtherDeductions(), empDeductDetails.getSociety(),
					alowances.getCca(), empLic.getInstlmtAmt(),alowances.getFamilyPlanAlwance(), alowances.getNonPracAwance(), 
					alowances.getWashingAlwance(), alowances.getUniformAlwance(), 
					alowances.getHraFlag(), 0 , 0, 0, 0, empPf.getPfsCpfCntrbn(), EmployeePayrollService.getAbsenties(leaves),
					EmployeePayrollService.overtimeHours(overtimeList), bankVo.getBankName(), bankVo.getAccountNo(), bankVo.getBankId());
	*/
			//payrollDTO.set
    	}catch(Exception e){
			e.printStackTrace();
		}finally {
			HibernateConnection.closeSession(session);
		}
        return empPayroll;
    }
    
    private StringBuffer getSearchCriteria(String condition){
    	StringBuffer searchCriteria = new StringBuffer("(select eDept.employee.employeeId from EmpDepartment eDept where ");
    	searchCriteria.append(condition);
    	searchCriteria.append("= eDept.employee.employeeId and eDept.department.departmentId = ?)");
    	return searchCriteria;
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
     * @param date
     * @param queryString
     * @return
     */
    private List getObjectsByDeptId(Date date, String queryString){
    	this.date = date;
    	return getObjectsByDeptId(queryString);
    }
    
    private List getObjectsByDeptId(String queryString){
    	Query query = session.createQuery(queryString);
		query.setParameter(0, deptId);
		query.setParameter(1, "A");
		if(date != null)
			query.setParameter(2, date);
		return query.list();
	}
    
    private void loadBasicGradePay(List<Salary> slaryList){
    	for (Iterator iterator = slaryList.iterator(); iterator.hasNext();) {
			Salary salary = (Salary) iterator.next();
			basicPay += salary.getBasic();
			gradePay += salary.getGradePay();
		}
    }
    private void loadAllowances(List<EmpAllowance> allowanceList){
    	for (Iterator iterator = allowanceList.iterator(); iterator.hasNext();) {
    		EmpAllowance empAllowance = (EmpAllowance) iterator.next();
			cca += empAllowance.getCca();
			uniformAlw += empAllowance.getUniformAlwance();
			familyPlaningAlw += empAllowance.getFamilyPlanAlwance();
			npa += empAllowance.getNonPracAwance();
			wa+=empAllowance.getWashingAlwance();
			others+= empAllowance.getCycleAlwance();
		}
    }
    
    private void loadPFDetails(List<EmpPf> empPfList){
    	for (Iterator iterator = empPfList.iterator(); iterator.hasNext();) {
    		EmpPf empPf = (EmpPf) iterator.next();
			pfsCpf += empPf.getPfsCpfCntrbn();
			apfAcf += empPf.getApfAcpfCntrbn();
			cpfRcry += empPf.getCfLoneRecAmt();
			pfLoanRcry += empPf.getPfLoneRecAmt();
		}
    }
    
    private void loadLicDetails(List<EmpLic> empLicList){
    	for (Iterator iterator = empLicList.iterator(); iterator.hasNext();) {
    		EmpLic empLic = (EmpLic) iterator.next();
			lic += empLic.getInstlmtAmt();
		}
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