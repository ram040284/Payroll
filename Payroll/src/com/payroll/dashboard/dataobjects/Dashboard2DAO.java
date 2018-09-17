package com.payroll.dashboard.dataobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;

import com.payroll.HibernateConnection;

public class Dashboard2DAO {
	
	public List<PaybillChart> getPaybillChart(String currentMonthDate, String previousMonthDate) {
		
		List<PaybillChart> payBillCharts = new ArrayList<PaybillChart>();
		
		List<String> monthList = Arrays.asList("2018-04-01", "2018-05-01");
		
		Session session = null;
		
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			
			for (String monthDate: monthList) {
				payBillCharts.add((PaybillChart) session.getNamedQuery("paybillChartQuery").setParameter("monthDate", monthDate).list().get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			HibernateConnection.closeSession(session);
		}
			
		return payBillCharts;
	
	}
	
	public List<PaybillPieChart> getPaybillPieChart(String currentMonthDate) {
		
		List<PaybillPieChart> payBillPieCharts = new ArrayList<PaybillPieChart>();
		
		Session session = null;
		
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			List<PaybillPieChart> paybillPieChartList = session.getNamedQuery("paybillPieChartQuery").setParameter("monthDate", currentMonthDate).list();
			
			for (PaybillPieChart pbpc: paybillPieChartList) {
				payBillPieCharts.add(pbpc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			HibernateConnection.closeSession(session);
		}
		
		return payBillPieCharts;
	
	}

	public List<MonthlySummary> getMonthlySummary(String currentMonthDate) {
		
		List<MonthlySummary> monthlySummary = new ArrayList<MonthlySummary>();
		
		Session session = null;
		
		try {
			session = HibernateConnection.getSessionFactory().openSession();
			List<MonthlySummary> monthlySummaryList = session.getNamedQuery("monthlySummaryQuery").setParameter("monthDate", currentMonthDate).list();
			
			for (MonthlySummary ms: monthlySummaryList) {
				monthlySummary.add(ms);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			HibernateConnection.closeSession(session);
		}
		
		return monthlySummary;
	}

}
