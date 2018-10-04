//package com.kcb.hrms.payroll.dao;
package com.payroll.hrms.payroll.dao;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.payroll.HibernateConnection;
import com.payroll.designation.dataobjects.Designation;
import com.payroll.employee.advances.dataobjects.EmpAdvances;
import com.payroll.employee.allowance.dataobjects.EmpAllowance;
import com.payroll.employee.allowance.dataobjects.EmpAllowanceDAO;
import com.payroll.employee.allowance.vo.EmployeeAllowances;
import com.payroll.employee.arrears.dataobjects.EmpArrearDAO;
import com.payroll.employee.arrears.dataobjects.EmpArrears;
import com.payroll.employee.bank.vo.BankVO;
import com.payroll.employee.dataobjects.EmpDesignation;
import com.payroll.employee.deductions.dao.EmpFixedDeductionsDAO;
import com.payroll.employee.deductions.dao.EmpVarDeductionsDAO;
import com.payroll.employee.deductions.dataobjects.EmployeeFixedDeductions;
import com.payroll.employee.deductions.dataobjects.EmployeeVarDeductions;
import com.payroll.employee.leave.dataobjects.LeaveRequest;
import com.payroll.employee.lic.dataobjects.EmpLic;
import com.payroll.employee.lic.dataobjects.EmpLicDAO;
import com.payroll.employee.lic.vo.EmployeeLIC;
import com.payroll.employee.salary.dataobjects.Salary;
import com.payroll.employee.vo.EmployeeVO;
/*import com.kcb.hrms.payroll.dataobjects.EmployeePayroll;
import com.kcb.hrms.payroll.dataobjects.EmployeePayrollDTO;*/
import com.payroll.hrms.payroll.dataobjects.EmployeePayroll;
import com.payroll.overtime.dataobjects.Overtime;

/**
 * Created by rajendra on 12/8/17.
 */
public class EmployeePayrollDAO {
	private Session session = null;
	private String employeeId;
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
	
	private double incomeTax;
	
	@SuppressWarnings("unchecked")
	public EmployeePayroll loadPayrollInfo(String employeeId, byte handicapFlag, Date date, int billType){
		//System.out.println("loading payroll info...");
		//System.out.println("loadPayrollInfo employeeId " + employeeId);
    	Salary salary = null;
		
		EmpAdvances empAdvances = null;
		//EmpLic empLic = null;
		List<Overtime> overtimeList = null;
		List<LeaveRequest> leaves = null;
		EmployeePayroll empPayroll = null;
		BankVO bankVo = null;
		EmpDesignation empDesignation;
    	try{
    		this.employeeId = employeeId;
			session = HibernateConnection.getSessionFactory().openSession();
			salary = (Salary)getObjectByEmpId(" from Salary s where s.employee.employeeId = ? and s.status = ?");
			empDesignation = (EmpDesignation) getObjectByEmpId(" from EmpDesignation d where d.employeeId = ? and d.status = ?");
			EmployeeAllowances employeeAllowances = new EmpAllowanceDAO().getEmployeeAllowances(employeeId);
			
			//get arrears list using emp_id
			List<EmpArrears> empArrearsList = new EmpArrearDAO().getEmpArrearList(employeeId);
			
			// Employee Fixed Deductions details
			EmployeeFixedDeductions employeeFixedDeductions = new EmpFixedDeductionsDAO()
					.getEmployeeFixedDeductions(employeeId);
	    	
	    	// Employee Monthly Variable Deductions details
			EmployeeVarDeductions employeeVarDeductions = new EmpVarDeductionsDAO()
					.getEmpVarDeductionsNew(employeeId);
	    	
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
	    	
			overtimeList = (List) getObjectsByEmpId(date,
					" from Overtime o where o.employee.employeeId = ? and o.status = ? and o.overtimeDate >= ?");
			this.date = null;
			leaves = (List)getObjectsByEmpId(" from LeaveRequest l where l.employee.employeeId = ? and l.status = ?");
			empAdvances = (EmpAdvances) getObjectByEmpId(
					" from EmpAdvances a where a.employee.employeeId = ? and a.status = ?");
			bankVo = (BankVO) getObjectByEmpId(
					"select new com.payroll.employee.bank.vo.BankVO(b.bankDetails.bankId, b.bankDetails.bankName, b.accountNo) "
   					+ "from EmpBank b where b.employee.employeeId = ? and b.status = ?");
			double overtimeAmount = com.payroll.hrms.payroll.service.EmployeePayrollService.overtimeHours(overtimeList);
			
			//TODO: Prasad: Needs to be retrieved from respective module
//			double abcenties = com.payroll.hrms.payroll.service.EmployeePayrollService.getabcenties(leaves);
			
			// This need to check 
			double festAdvanceRecovery = (empAdvances != null) ? empAdvances.getInstallAmount() : 0;
			double bankLoanRecovery = 0;
//			double cpfRecovery = 0;
			
			Calendar cal = Calendar.getInstance();
    		cal.setTime(date); 
    		int month = cal.get(Calendar.MONTH);
    		System.out.println("empArrears size " + empArrearsList.size());
    		
    		double arrearsTotPay = 0;
    		double arrearsTotDed = 0;
    		
			double arrearsPay = 0; 
			double arrearsDedu = 0;
			
			double otherPay = 0;
			double otherDedu = 0;
			
    		for (Iterator<EmpArrears> iterator = empArrearsList.iterator(); iterator.hasNext();) {
    			EmpArrears arrears = (EmpArrears)iterator.next();
    			
    			Date arrearDate = arrears.getRowUpdatedDate();
    			cal = Calendar.getInstance();
        		cal.setTime(arrearDate); 
        		int arrearMonth = cal.get(Calendar.MONTH);
        		
        		if (arrearMonth == month) {
        			
        			if (arrears.getArrearsType().equals("Rent")) {
        				arrearsPay = arrears.getArrearsPay(); 
        				arrearsDedu = arrears.getArrearsDeductions();
        				
        			} else if(arrears.getArrearsType().equals("AfkRent")) {
        				arrearsPay = arrears.getArrearsPay(); 
        				arrearsDedu = arrears.getArrearsDeductions();
        				
        			} else if(arrears.getArrearsType().equals("Other")) {
        				otherPay = arrears.getArrearsPay(); 
        				otherDedu = arrears.getArrearsDeductions();
        				
        			} else if(arrears.getArrearsType().equals("Misc")) {
        				arrearsPay = arrears.getArrearsPay(); 
        				arrearsDedu = arrears.getArrearsDeductions();
        			}
        			arrearsTotPay += arrearsPay;
        			arrearsDedu+= arrearsDedu;
				}
    		}
			
			if (billType == 1) {
				// Other pay need to add from Arrears
	   			empPayroll = new EmployeePayroll(this.employeeId, handicapFlag, salary.getBasic(), salary.getGradePay(), salary.getScalePay(), salary.getScaleCode(), employeeAllowances.getOtherAllowance(),
	   					employeeAllowances.getCca(), employeeAllowances.getCycleAlwance(), employeeAllowances.getOtherAllowance(), employeeAllowances.getFamilyPlanAlwance(),employeeAllowances.getNonPracAwance(),
	   					employeeAllowances.getWashingAlwance(), employeeAllowances.getUniformAlwance(), employeeAllowances.getHraFlag(),employeeAllowances.getPFFlag(), employeeAllowances.getTaFlag(), employeeAllowances.gettAllowance(),
	   					employeeFixedDeductions.getUnionFee(), employeeFixedDeductions.getKssUnionFee(), employeeFixedDeductions.getRent(), employeeFixedDeductions.getElectricityRecovery(), employeeFixedDeductions.getCourtRecovery(),
	   					employeeFixedDeductions.getGis(), employeeVarDeductions.getAfkRent(), employeeVarDeductions.getPfLoanRecovery(), employeeVarDeductions.getOtherDeductions(),
	   					employeeVarDeductions.getSociety(), employeeVarDeductions.getIncomeTax(), licTotalInstallmentAmt, festAdvanceRecovery , bankLoanRecovery, employeeVarDeductions.getAbsenties(), overtimeAmount, bankVo.getBankName(), 
	   					bankVo.getAccountNo(), bankVo.getBankId(), salary.getIncrementDate(), salary.getIncrementAmount(), month, employeeFixedDeductions.getApfAcpf(), otherPay, otherDedu);
	   			
			} else if (billType == 2) {
				empPayroll = new EmployeePayroll(salary.getBasic(), salary.getGradePay(), salary.getScalePay(), salary.getScaleCode(),
						salary.getEmpAbsentDays(), salary.getEmpPresentDays(), bankVo.getAccountNo(), bankVo.getEmployeeId(), billType, 
						empDesignation.getDesignation().getDesignationId());
			} else if (billType == 3) {
				empPayroll = new EmployeePayroll(salary.getBasic(), salary.getGradePay(), salary.getScalePay(), salary.getScaleCode(),
						salary.getEmpAbsentDays(), salary.getEmpPresentDays(), bankVo.getAccountNo(), bankVo.getEmployeeId(), billType, 
						empDesignation.getDesignation().getDesignationId());
			}

   		}catch(Exception e){
			e.printStackTrace();
		}finally {
			HibernateConnection.closeSession(session);
		}
        return empPayroll;
    }
   
    private StringBuffer getSearchCriteria(String condition){
		StringBuffer searchCriteria = new StringBuffer(
				"(select eDept.employee.employeeId from EmpDepartment eDept where ");
    	searchCriteria.append(condition);
    	searchCriteria.append("= eDept.employee.employeeId and eDept.department.departmentId = ?)");
    	return searchCriteria;
    }
   
	/*
	 * @SuppressWarnings("unchecked") public EmployeePayrollDTO
	 * loadPayrollByDept(int deptId, Date date){ List<Salary> salaryList = null;
	 * List<EmpPf> empPfList = null; List<EmpQuarters> empQtrList = null;
	 * List<EmpAllowance> alowancesList = null; List<EmpDeductions>
	 * empDeductionsList = null; List<EmpDeductionDetails> empDeductDetailsList =
	 * null; List<EmpAdvances> empAdvancesList = null; List<Overtime> overtimeList =
	 * null; List<Leave> leaves = null; List<EmpLic> empLicList = null;
	 * EmployeePayrollDTO payrollDTO = new EmployeePayrollDTO(); try{ this.deptId =
	 * deptId; String searchCriteria =
	 * " (select eDept.employee.employeeId from EmpDepartment eDept where e.employeeId = eDept.employee.employeeId and eDept.department.departmentId = ?)"
	 * ; session = HibernateConnection.getSessionFactory().openSession(); salaryList
	 * = (List)
	 * getObjectsByDeptId(" from Salary s where s.employee.employeeId = ? and s.status = ?"
	 * );//session.load(Salary.class, employeeId); empPfList = (List)
	 * getObjectsByDeptId(" from EmpPf p where p.employee.employeeId = ? and p.status = ?"
	 * ); empQtrList = (List)
	 * getObjectsByDeptId(" from EmpQuarters q where q.employee.employeeId = ? and q.status= ?"
	 * ); empDeductionsList = (List)
	 * getObjectsByDeptId(" from EmpDeductions d where d.employee.employeeId = ? and d.status = ?"
	 * ); alowancesList = (List)
	 * getObjectsByDeptId(" from EmpAllowance a where a.employee.employeeId = ? and a.status = ?"
	 * ); overtimeList = (List)getObjectsByDeptId(date,
	 * " from Overtime o where o.employee.employeeId = ? and o.status = ? and o.overtimeDate >= ?"
	 * ); leaves = (List)
	 * getObjectsByDeptId(" from Leave l where l.employee.employeeId = ? and l.status = ?"
	 * ); empLicList = (List)
	 * getObjectsByDeptId(" from EmpLic l where l.employee.employeeId = ? and l.status = ?"
	 * ); empAdvancesList = (List)
	 * getObjectsByDeptId(" from EmpAdvances a where a.employee.employeeId = ? and a.status = ?"
	 * ); empDeductDetailsList = (List)
	 * getObjectsByDeptId(" from EmpDeductionDetails d where d.employee.employeeId = ? and d.status = ?"
	 * ); payrollDTO.setBasic(basicPay); payrollDTO.setGradePay(gradePay);
	 * payrollDTO.setListAllowances(EmployeePayrollService.listAllowances(
	 * alowancesList));
	 * payrollDTO.setNoOfAbsentDays(EmployeePayrollService.getAbsenties(leaves));
	 * payrollDTO.setOverTimeHours(EmployeePayrollService.overtimeHours(overtimeList
	 * )); payrollDTO.setHraFlag(alowances.getHraFlag()); // Yet to conform Pf
	 * details if(empPf.getApfAcpfCntrbn() >0 || empPf.getPfLoneRecAmt() > 0 ||
	 * empPf.getPfsCpfCntrbn() > 0) payrollDTO.setPfFlag(true);
	 * payrollDTO.setAdditionalProventFund(empPf.getApfAcpfCntrbn());
	 * payrollDTO.setPfLoanRecovery(empPf.getPfLoneRecAmt());
	 * payrollDTO.setPf0Recovery(empPf.getPfsCpfCntrbn()); //payrollDTO.set
	 * }catch(Exception e){ e.printStackTrace(); }finally {
	 * HibernateConnection.closeSession(session); } return payrollDTO; }
   */ 
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
    
	/*
	 * private void loadDeductDetails(List<EmpDeductionDetails> empDeductDetList){
	 * for (Iterator iterator = empDeductDetList.iterator(); iterator.hasNext();) {
	 * //EmpDeductionDetails empDeductDet = (EmpDeductionDetails) iterator.next();
	 * /*basicPay += empDeductDet.getAfkRent(); gradePay +=
	 * empDeductDet.getCourtRecovery(); empDeductDet.getElectRecovery();
	 * empDeductDet.getKssUnionRecovery(); empDeductDet.getOtherDeductions();
	 * empDeductDet.getMiscRecovery(); empDeductDet.getSociety();
	 * empDeductDet.getUnionFee(); } }
	 */
    
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
    
    private void loadLicDetails(List<EmpLic> empLicList){
    	for (Iterator iterator = empLicList.iterator(); iterator.hasNext();) {
    		EmpLic empLic = (EmpLic) iterator.next();
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
					+ "e.joiningDate, e.handicapFlag) from Employee e where e.status = ? and e.employeeId in "
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
	public List<EmployeeVO> getActiveEmployeesByDept(int deptId, Date startDate, int billType) {
    	List<EmployeeVO> employeeList = null;
	
		/*
		 * if (billType == 0) { billType = 1; }
		 */
    	try{
    		session = HibernateConnection.getSessionFactory().openSession();
			String queryString = "select new com.payroll.employee.vo.EmployeeVO(e.employeeId, e.firstName, e.lastName, e.middleName, e.employeeType,"
					+ " e.pan, e.dob, e.retirementDate, "
					+ "(select dept.departmantName from Department dept where dept.departmentId = (select eDept.department.departmentId from EmpDepartment eDept where eDept.employee.employeeId = e.employeeId)),"
					+ "(select h.headName from HeadInfo h where h.headId = (select eMas.headInfo.headId from EmpHeadInfo eMas where eMas.employee.employeeId = e.employeeId)),"
					+ "(select desg.designationName from Designation desg where desg.designationId = "
					+ "(select eDesg.designation.designationId from EmpDesignation eDesg where eDesg.employee.employeeId = e.employeeId)), "
					+ "e.joiningDate, e.handicapFlag) from Employee e where e.employeeType = ? and e.status = ? and e.retirementDate > ? and e.employeeId in "
					+ "(select eDept.employee.employeeId from EmpDepartment eDept where eDept.department.departmentId = ?)";
    		Query query = session.createQuery(queryString);
			query.setParameter(0, billType);
			query.setParameter(1, "A");
			query.setParameter(2, startDate);
			query.setParameter(3, deptId);
			//System.out.println("**** getActiveEmployeesByDept Query Count: " + query.list().size());
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
			String queryString = "select e.employeeId" + " from Employee e where e.status = ? and e.employeeId in "
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