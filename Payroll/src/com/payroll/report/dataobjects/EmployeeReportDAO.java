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
import com.payroll.report.vo.EmpSalaryReportVO;
import com.payroll.report.vo.EmployeeReportVO;

// Referenced classes of package com.payroll.report.vo:
//            EmployeeReportVO, EmpSalaryReportVO

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
            String queryString = "SELECT EMP_FNAME, EMP_MNAME, EMP_LNAME, BASIC, GRD_PAY, CA, UFALW, FPALW, TALW, " +
"HRA_FLAG, PF_FLAG, SUM(empLeave.NO_OF_LEAVES) as LWP , (SELECT dept.DEPT_NAME FR" +
"OM PAYROLL_DEV.DEPT_MASTER dept WHERE dept.DEPT_ID = empDept.DEPT_ID) as DeptNam" +
"e,  (SELECT deptHead.HEAD_NAME FROM PAYROLL_DEV.dept_cost_head_master deptHead W" +
"HERE deptHead.HEAD_ID = empHead.HEAD_ID) as HeadName, (SELECT desg.DESG_NAME FRO" +
"M PAYROLL_DEV.desg_master desg WHERE desg.DESG_ID = empDesg.DESG_ID) as Designat" +
"ion FROM PAYROLL_DEV.EMP_MASTER empMaster  LEFT OUTER JOIN  PAYROLL_DEV.EMP_SAL_" +
"MASTER empSal ON empMaster.EMP_ID =empSal.EMP_ID LEFT OUTER JOIN  PAYROLL_DEV.EM" +
"P_ALLOWANCES empAllowance   ON empMaster.EMP_ID =empAllowance.EMP_ID LEFT OUTER " +
"JOIN  PAYROLL_DEV.EMP_LEAVE_MASTER empLeave  ON empMaster.EMP_ID =empLeave.EMP_I" +
"D LEFT OUTER JOIN  PAYROLL_DEV.emp_dept_details empDept    ON empMaster.EMP_ID =" +
"empDept.EMP_ID LEFT OUTER JOIN  PAYROLL_DEV.emp_cost_head_details empHead   ON e" +
"mpMaster.EMP_ID =empHead.EMP_ID LEFT OUTER JOIN  PAYROLL_DEV.emp_desg_details em" +
"pDesg   ON empMaster.EMP_ID =empDesg.EMP_ID "
;
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
            EmpSalaryReportVO empSalVo;
            for(Iterator iterator = rows.iterator(); iterator.hasNext(); empSalList.add(empSalVo))
            {
                Object row[] = (Object[])iterator.next();
                empSalVo = new EmpSalaryReportVO(0, (String)row[0], (String)row[1], (String)row[2], 
                		row[3] != null ? ((Double)row[3]).doubleValue() : 0.0D, 
                		row[4] != null ? ((Double)row[4]).doubleValue() : 0.0D, 
                		row[5] != null ? ((Double)row[5]).doubleValue() : 0.0D, 
                		row[6] != null ? ((Double)row[6]).doubleValue() : 0.0D, 
                		row[7] != null ? ((Double)row[7]).doubleValue() : 0.0D, 
                		row[8] != null ? ((Double)row[8]).doubleValue() : 0.0D, 
                		row[9] != null ? ((Boolean)row[9]).booleanValue() : false, 
                		row[10] != null ? ((Boolean)row[10]).booleanValue() : false, 
                		row[11] != null ? ((BigDecimal)row[11]).intValue() : 0, 
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