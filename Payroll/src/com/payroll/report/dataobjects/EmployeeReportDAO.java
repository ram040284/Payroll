package com.payroll.report.dataobjects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.payroll.HibernateConnection;
import com.payroll.Utils;
import com.payroll.employee.salary.dataobjects.SalaryDAO;
import com.payroll.employee.salary.vo.SalaryVO;
import com.payroll.report.vo.EmpAllowanceReportVO;
import com.payroll.report.vo.EmployeeReportVO;

// Referenced classes of package com.payroll.report.vo:
//            EmployeeReportVO, EmpAllowanceReportVO

public class EmployeeReportDAO
{

    Session session;

    public EmployeeReportDAO()
    {
        session = null;
    }

    public List getEmployees(int deptId, int headId, String name)
    {
        List employeeList;
        Session session;
        employeeList = null;
        session = null;
        try
        {
            StringBuffer searchCriteria = new StringBuffer("");
            searchCriteria.append(" select new com.payroll.report.vo.EmployeeReportVO(e.employeeId, e.firstName, e." +
"lastName, e.middleName, e.email, e.phone, e.pan, e.adharNo, e.dob,(select dept.d" +
"epartmantName from Department dept where dept.departmentId = (select eDept.depar" +
"tment.departmentId from EmpDepartment eDept where eDept.employee.employeeId = e." +
"employeeId)),(select h.headName from HeadInfo h where h.headId = (select eMas.he" +
"adInfo.headId from EmpHeadInfo eMas where eMas.employee.employeeId = e.employeeI" +
"d)),(select desg.designationName from Designation desg where desg.designationId " +
"= (select eDesg.designation.designationId from EmpDesignation eDesg where eDesg." +
"employee.employeeId = e.employeeId)), e.addressLine1, e.addressLine2, e.addressL" +
"ine3, e.gender, e.joiningDate) from Employee e where e.status= ?"
);
            if(deptId != 0)
            {
                searchCriteria.append(" and e.employeeId = (select eDept.employee.employeeId from EmpDepartment eDept w" +
"here e.employeeId = eDept.employee.employeeId and eDept.department.departmentId " +
"= ?)"
);
            }
            if(headId != 0)
            {
                searchCriteria.append(" and e.employeeId = (select eMas.employee.employeeId from EmpHeadInfo eMas where" +
" e.employeeId = eMas.employee.employeeId and eMas.headInfo.headId = ?)"
);
            }
            if(!Utils.isEmpty(name))
            {
                searchCriteria.append(" and (e.firstName like :fname or e.middleName like :mname or e.lastName like :ln" +
"ame)"
);
            }
            session = HibernateConnection.getSessionFactory().openSession();
            Query query = session.createQuery(searchCriteria.toString());
            int i = 0;
            query.setParameter(i++, "A");
            if(deptId != 0)
            {
                query.setParameter(i++, Integer.valueOf(deptId));
            }
            if(headId != 0)
            {
                query.setParameter(i++, Integer.valueOf(headId));
            }
            if(!Utils.isEmpty(name))
            {
                query.setParameter("fname", (new StringBuilder("%")).append(name).append("%").toString());
                query.setParameter("mname", (new StringBuilder("%")).append(name).append("%").toString());
                query.setParameter("lname", (new StringBuilder("%")).append(name).append("%").toString());
            }
            employeeList = query.list();
            EmployeeReportVO employeeVo;
            for(Iterator iterator = employeeList.iterator(); iterator.hasNext(); employeeVo.setSalaryVo(getEmpSalary(employeeVo.getEmployeeId())))
            {
                employeeVo = (EmployeeReportVO)iterator.next();
                SalaryDAO dao = new SalaryDAO();
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        HibernateConnection.closeSession(session);
        return employeeList;
    }

    public SalaryVO getEmpSalary(int empId)
    {
        SalaryVO salVO;
        Session session;
        salVO = null;
        session = null;
        try
        {
            String queryString = " select new com.payroll.employee.salary.vo.SalaryVO(s.employee.employeeId, '',''" +
", s.year, s.basic, s.gradePay, s.scalePay, s.scaleInc) from Salary s where s.emp" +
"loyee.employeeId = ? and s.status = ?"
;
            session = HibernateConnection.getSessionFactory().openSession();
            Query query = session.createQuery(queryString);
            query.setParameter(0, Integer.valueOf(empId));
            query.setParameter(1, "A");
            salVO = (SalaryVO)(query.list().isEmpty() ? null : query.list().get(0));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
			HibernateConnection.closeSession(session);
		}
        return salVO;
    }

    public List getEmpSalaryReport(int deptId, int headId)
    {
        Session session;
        List empSalList;
        SalaryVO salVO = null;
        session = null;
        empSalList = new ArrayList();
        try
        {
        	String queryString = "SELECT empMaster.EMP_ID, EMP_FNAME, EMP_MNAME, EMP_LNAME, CCA, WASHING_ALLOWANCE, CONV_ALLOWANCE, NON_PRACT_ALLOWANCE, UNIFORM_ALLOWANCE, " + 
        			"FAMILY_PLANNING_ALLOWANCE, CYCLE_ALLOWANCE, HRA_FLAG , (SELECT dept.DEPT_NAME FROM DEPT_MASTER dept " + 
        			"WHERE dept.DEPT_ID = empDept.DEPT_ID) as DeptName,  (SELECT deptHead.HEAD_NAME FROM dept_cost_head_master deptHead " +
        			"WHERE deptHead.HEAD_ID = empHead.HEAD_ID) as HeadName, (SELECT desg.DESG_NAME FROM desg_master desg " +
        			"WHERE desg.DESG_ID = empDesg.DESG_ID) as Designation FROM EMP_MASTER empMaster " +
        		//	"LEFT OUTER JOIN  EMP_SAL_MASTER empSal ON empMaster.EMP_ID =empSal.EMP_ID " +
        			"LEFT OUTER JOIN  EMP_ALLOWANCES_MASTER empAllowance ON empMaster.EMP_ID =empAllowance.EMP_ID " +
        		//	"LEFT OUTER JOIN  EMP_LEAVE_MASTER empLeave  ON empMaster.EMP_ID =empLeave.EMP_ID " +
        			"LEFT OUTER JOIN  emp_dept_details empDept ON empMaster.EMP_ID =empDept.EMP_ID " +
        			"LEFT OUTER JOIN  emp_cost_head_details empHead ON empMaster.EMP_ID=empHead.EMP_ID " +
        			"LEFT OUTER JOIN  emp_desg_details empDesg ON empMaster.EMP_ID =empDesg.EMP_ID ";
        			
            if(deptId != 0 || headId != 0)
            {
                queryString = (new StringBuilder(String.valueOf(queryString))).append(" WHERE ").toString();
            }
            if(deptId != 0)
            {
                queryString = (new StringBuilder(String.valueOf(queryString))).append(" empMaster.EMP_ID =empDept.EMP_ID AND empDept.DEPT_ID = ? ").toString();
            }
            if(headId != 0)
            {
                queryString = (new StringBuilder(String.valueOf(queryString))).append(" AND empMaster.EMP_ID =empHead.EMP_ID AND empHead.HEAD_ID = ? ").toString();
            }
            queryString = (new StringBuilder(String.valueOf(queryString))).append(" GROUP BY empMaster.EMP_ID;").toString();
            session = HibernateConnection.getSessionFactory().openSession();
            Query query = session.createSQLQuery(queryString);
            if(deptId != 0)
            {
                query.setParameter(0, Integer.valueOf(deptId));
            }
            if(headId != 0)
            {
                query.setParameter(1, Integer.valueOf(headId));
            }
            List rows = query.list();
            System.out.println((new StringBuilder("rows size:")).append(rows.size()).toString());
            EmpAllowanceReportVO empSalVo;
            for(Iterator iterator = rows.iterator(); iterator.hasNext(); empSalList.add(empSalVo))
            {
                Object row[] = (Object[])iterator.next();
                empSalVo = new EmpAllowanceReportVO(row[0] != null ? ((Integer)row[0]).intValue() : 0,
                		(String)row[1], (String)row[2], (String)row[3], 
                		row[4] != null ? ((Double)row[4]).doubleValue() : 0.0D, 
                		row[5] != null ? ((Double)row[5]).doubleValue() : 0.0D, 
                		row[6] != null ? ((Double)row[6]).doubleValue() : 0.0D, 
                		row[7] != null ? ((Double)row[7]).doubleValue() : 0.0D, 
                		row[8] != null ? ((Double)row[8]).doubleValue() : 0.0D, 
                		row[9] != null ? ((Double)row[9]).doubleValue() : 0.0D, 
                		row[10] != null ? ((Double)row[10]).doubleValue() : 0.0D, 
                		row[11] != null ? ((Boolean)row[11]).booleanValue() : false, 
                		(String)row[12], (String)row[13], (String)row[14]);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
			HibernateConnection.closeSession(session);
		}
        return empSalList;
    }
}