package com.payroll.employee.leave.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.payroll.Utils;
import com.payroll.department.dataobjects.Department;
import com.payroll.department.dataobjects.DepartmentDAO;
import com.payroll.employee.dataobjects.EmployeeDAO;
import com.payroll.employee.leave.dataobjects.Leave;
import com.payroll.employee.leave.dataobjects.LeaveDAO;
import com.payroll.employee.leave.dataobjects.LeaveRequest;
import com.payroll.employee.leave.dataobjects.LeaveRequestVO;
import com.payroll.employee.leave.dataobjects.LeaveType;
import com.payroll.employee.leave.vo.LeaveVO;
public class LeaveService {
	public static final String LEAVE_TYPES[] = {"Casual Leave", "Sick Leave", "Half Paid Leave", 
			"Earned Leave", "Maternity Leave", "Paternity Leave", "Extraordinary Leave", "Child Care Leave", "Extraordinary Leave Without Medical"};
	public static final String SHORT_LEAVE_TYPES[] = {"CL", "SL", "PL", "EL", "MTL", "PTL", "EXL", "CCL","EXLM"};
	String empId;
	
	public Map getLeaveTypes(String gender) {
		Map leaveMap = new LinkedHashMap();
		for (int i=0; i < LEAVE_TYPES.length; i++) {
			if (gender.equalsIgnoreCase("female")) {
				if (SHORT_LEAVE_TYPES[i].equalsIgnoreCase("PL")) {
					continue;
				}
			} else if (gender.equalsIgnoreCase("male")) {
				if (SHORT_LEAVE_TYPES[i].equalsIgnoreCase("MTL")) {
					continue;
				}
			}
			leaveMap.put(SHORT_LEAVE_TYPES[i], LEAVE_TYPES[i]);
		}
		return leaveMap;
	}
	
	public Map getLeaveBalance(LeaveVO leave) {
		Map leaveBalanceMap = new LinkedHashMap();
		for (int i=0; i < LEAVE_TYPES.length; i++) {
			if (leave.getEmployee().getGender().equalsIgnoreCase("female")) {
				if (SHORT_LEAVE_TYPES[i].equalsIgnoreCase("PL")) {
					continue;
				}
			} else if (leave.getEmployee().getGender().equalsIgnoreCase("male")) {
				if (SHORT_LEAVE_TYPES[i].equalsIgnoreCase("MTL")) {
					continue;
				}
			}

			switch (SHORT_LEAVE_TYPES[i]) {
			case "CL": if (leave.getCasualLeaves()!=0) leaveBalanceMap.put(LEAVE_TYPES[i], leave.getCasualLeaves()); break;
			case "SL": if (leave.getSickLeaves()!=0) leaveBalanceMap.put(LEAVE_TYPES[i], leave.getSickLeaves()); break;
			case "PL": if (leave.getPaidLeaves()!=0) leaveBalanceMap.put(LEAVE_TYPES[i], leave.getPaidLeaves()); break; 
			case "EL": if (leave.getEarnLeave()!=0) leaveBalanceMap.put(LEAVE_TYPES[i], leave.getEarnLeave()); break; 
			case "MTL": if (leave.getMaternityLeave()!=0) leaveBalanceMap.put(LEAVE_TYPES[i], leave.getMaternityLeave()); break; 
			case "PTL": if (leave.getPaternityLeave()!=0) leaveBalanceMap.put(LEAVE_TYPES[i], leave.getPaternityLeave()); break;
			case "EXL": if (leave.getExtraLeave()!=0) leaveBalanceMap.put(LEAVE_TYPES[i], leave.getExtraLeave()); break; 
			case "CCL": if (leave.getchildLeaves()!=0) leaveBalanceMap.put(LEAVE_TYPES[i], leave.getchildLeaves()); break; 
			case "EXLM": if (leave.getExtraOrdinaryLeaveWithoutMedi()!=0) leaveBalanceMap.put(LEAVE_TYPES[i], leave.getExtraOrdinaryLeaveWithoutMedi());
			}
		}
		return leaveBalanceMap;
	}
	public List<LeaveVO> getLeaves(){
		return getEmpLeaves(new LeaveDAO().getLeaves());
	}
	
	public List<LeaveVO> getLeavesData(int deptId, int headId, String empName){
		return new LeaveDAO().getLeaves(deptId, headId, empName);
	}
	
	public List<LeaveRequestVO> getLeaveRequests(int deptId, int headId, String empName){
		return new LeaveDAO().getLeaveRequests(deptId, headId, empName);
	}
	
	public LeaveRequestVO getEmpLeaveRequest(String employeeId){
		return new LeaveDAO().getEmpLeaveRequest(employeeId);
	}
	
	public LeaveRequestVO getEmpLeaveRequestById(String employeeId, String leaveType){
		return new LeaveDAO().getEmpLeaveRequestById(employeeId, leaveType);
	}
	
	public String addUpdateLeave(LeaveVO leaveVO){
		return new LeaveDAO().addUpdateLeave(copyProperties(leaveVO));
	}
	
	public String addLeaveRequest(LeaveRequest leaveRequest){
		
		int leaveTypeId = 0;
		
		if (leaveRequest.getLeaveTypes().equals("Casual Leave")) {
			leaveTypeId = 1;
		} else if (leaveRequest.getLeaveTypes().equals("Sick Leave")) {
			leaveTypeId = 2;
		} else if (leaveRequest.getLeaveTypes().equals("Half Paid Leave")) {
			leaveTypeId = 3;
		} else if (leaveRequest.getLeaveTypes().equals("Earned Leave")) {
			leaveTypeId = 4;
		} else if (leaveRequest.getLeaveTypes().equals("Maternity Leave")) {
			leaveTypeId = 5;
		} else if (leaveRequest.getLeaveTypes().equals("Paternity Leave")) {
			leaveTypeId = 6;
		} else if (leaveRequest.getLeaveTypes().equals("Extraordinary Leave")) {
			leaveTypeId = 7;
		}else if (leaveRequest.getLeaveTypes().equals("Child Care leave")) {
			leaveTypeId = 8;
		}else if (leaveRequest.getLeaveTypes().equals("Extraordinary Leave Without Medical")) {
			leaveTypeId = 9;
		}
		
		List<LeaveType> leaveTypes = new LeaveDAO().getLeaveTypeById(leaveTypeId);
		LeaveType retrievedLeaveType = null;
		if (!leaveTypes.isEmpty()) {
			retrievedLeaveType = leaveTypes.get(0);
		}
		leaveRequest.setLeaveType(retrievedLeaveType);
		
		return new LeaveDAO().addLeaveRequest(leaveRequest);
	}
	
	public LeaveVO getLeaveByIde(String empId){
		LeaveVO empLeave = null;
		List<LeaveVO> listVO = new LeaveDAO().getEmpLeave(empId);
		LeaveVO leaveDBVO = null;
		int sickLeave = 0;
		int casualLeave = 0;
		int childCareLeave = 0;
		int paidLeave = 0;
		int earnLeave = 0;
		int maturityLeave = 0;
		int paternityLeave = 0;
		int extraLeave = 0;
		int extraWithoutMedLeave = 0;
		LeaveVO empLeaveVO = null;
		if(!listVO.isEmpty())
		for (int i = 0; i < listVO.size(); i++) {
			leaveDBVO = listVO.get(i);
			List<LeaveVO> leaveList = getEmpLeaves(new LeaveDAO().getEmpLeave(empId));
			if(!leaveList.isEmpty() && leaveDBVO !=null){
				empLeaveVO = leaveList.get(i);
				if (empLeaveVO.getSickLeaves()!=0) {
					sickLeave = empLeaveVO.getSickLeaves();
				} 
				if (empLeaveVO.getCasualLeaves()!=0) {
					casualLeave = empLeaveVO.getCasualLeaves();
				}
				if (empLeaveVO.getchildLeaves()!=0) {
					childCareLeave = empLeaveVO.getchildLeaves();
				}
				if (empLeaveVO.getPaidLeaves()!=0) {
					paidLeave = empLeaveVO.getPaidLeaves();
				}
				if (empLeaveVO.getEarnLeave()!=0) {
					earnLeave = empLeaveVO.getEarnLeave();
				}
				if (empLeaveVO.getMaternityLeave()!=0) {
					maturityLeave = empLeaveVO.getMaternityLeave();
				}
				if (empLeaveVO.getPaternityLeave()!=0) {
					paternityLeave = empLeaveVO.getPaternityLeave();
				}
				if (empLeaveVO.getExtraLeave()!=0) {
					extraLeave = empLeaveVO.getExtraLeave();
				}
				if (empLeaveVO.getExtraOrdinaryLeaveWithoutMedi()!=0) {
					extraWithoutMedLeave = empLeaveVO.getExtraOrdinaryLeaveWithoutMedi();
				}
			}
		}
		
		empLeave = new LeaveVO(leaveDBVO.getEmployeeId(), leaveDBVO.getDepartmentId(), leaveDBVO.getDesignationId(), 
				leaveDBVO.getHeadId(), sickLeave, casualLeave, childCareLeave, 
				paidLeave, earnLeave, maturityLeave, 
				paternityLeave, extraLeave, empLeaveVO.getLeaveBalance(), empLeaveVO.getLeaveIds(), extraWithoutMedLeave);
		
		return empLeave;
	}
	
	public LeaveVO getLeaveDetailsById(String empId, String leaveType){
				
		int leaveTypeId = 0;
		
		if (leaveType.equals("Casual Leave")) {
			leaveTypeId = 1;
		} else if (leaveType.equals("Sick Leave")) {
			leaveTypeId = 2;
		} else if (leaveType.equals("Half Paid Leave")) {
			leaveTypeId = 3;
		} else if (leaveType.equals("Earned Leave")) {
			leaveTypeId = 4;
		} else if (leaveType.equals("Maternity Leave")) {
			leaveTypeId = 5;
		} else if (leaveType.equals("Paternity Leave")) {
			leaveTypeId = 6;
		} else if (leaveType.equals("Extraordinary Leave")) {
			leaveTypeId = 7;
		}else if (leaveType.equals("Child Care leave")) {
			leaveTypeId = 8;
		}else if (leaveType.equals("Extraordinary Leave Without Medical")) {
			leaveTypeId = 9;
		}
		
		LeaveVO empLeave = null;
		List<LeaveVO> listVO = new LeaveDAO().getEmpLeaveByType(empId, leaveTypeId);
		LeaveVO leaveDBVO = null;
		if(!listVO.isEmpty())
		leaveDBVO = listVO.get(0);
		List<LeaveVO> leaveList = getEmpLeaves(new LeaveDAO().getEmpLeaveByType(empId, leaveTypeId));
		if(!leaveList.isEmpty() && leaveDBVO !=null){
			LeaveVO empLeaveVO = leaveList.get(0);
			empLeave = new LeaveVO(leaveDBVO.getEmployeeId(), leaveDBVO.getDepartmentId(), leaveDBVO.getDesignationId(), 
					leaveDBVO.getHeadId(), empLeaveVO.getSickLeaves(), empLeaveVO.getCasualLeaves(),empLeaveVO.getchildLeaves(), 
					empLeaveVO.getPaidLeaves(), empLeaveVO.getEarnLeave(), empLeaveVO.getMaternityLeave(), 
					empLeaveVO.getPaternityLeave(), empLeaveVO.getExtraLeave(), empLeaveVO.getLeaveBalance(), empLeaveVO.getLeaveIds(), empLeaveVO.getExtraOrdinaryLeaveWithoutMedi());
		}
		
		if (empLeave != null) {
			empLeave.setEmployee(new EmployeeDAO().getEmployeeDetailsById(empLeave.getEmployeeId(), 0));
		}
		return empLeave;
	}
	
	public Leave getLeaveDetailsByLeaveType(String empId, String leaveType){
		
		int leaveTypeId = 0;
		
		if (leaveType.equals("Casual Leave")) {
			leaveTypeId = 1;
		} else if (leaveType.equals("Sick Leave")) {
			leaveTypeId = 2;
		} else if (leaveType.equals("Half Paid Leave")) {
			leaveTypeId = 3;
		} else if (leaveType.equals("Earned Leave")) {
			leaveTypeId = 4;
		} else if (leaveType.equals("Maternity Leave")) {
			leaveTypeId = 5;
		} else if (leaveType.equals("Paternity Leave")) {
			leaveTypeId = 6;
		} else if (leaveType.equals("Extraordinary Leave")) {
			leaveTypeId = 7;
		}else if (leaveType.equals("Child Care leave")) {
			leaveTypeId = 8;
		}else if (leaveType.equals("Extraordinary Leave Without Medical")) {
			leaveTypeId = 9;
		}
		
		Leave listVO = new LeaveDAO().getEmpLeave(empId, leaveTypeId);
		
		return listVO;
	}
	private List<Leave> copyProperties(LeaveVO leaveVO){
		this.empId = leaveVO.getEmployeeId();
		List<Leave> leaveList = new ArrayList<Leave>();
		int slId =0, plId=0, clId = 0, elId=0, ptlId =0, mtlId=0, exlId = 0,cclId=0,exlWithoutMediId = 0;
		System.out.println("In copy properties- leaveIds:"+leaveVO.getLeaveIds());
		if(!Utils.isEmpty(leaveVO.getLeaveIds())){
			String[] ids = leaveVO.getLeaveIds().split(",");
			if(ids.length > 0){
				for (int i = 0; i < ids.length; i++) {
					String idText =  ids[i];
					String idText2 = idText.substring(0, idText.length() - 2);
					//idText = idText.replaceAll(":", "");
					//StringBuilder builder = new StringBuilder(idText);
					//builder.deleteCharAt(idText.length() - 1);
					if(!Utils.isEmpty(idText)){
						if(idText.contains(":")){
							if(idText2.equals(SHORT_LEAVE_TYPES[0]))
								clId = Integer.parseInt(idText.split(":")[1]);
							if(idText2.equals(SHORT_LEAVE_TYPES[1]))
								slId = Integer.parseInt(idText.split(":")[1]);
							if(idText2.equals(SHORT_LEAVE_TYPES[2]))
								plId = Integer.parseInt(idText.split(":")[1]);
							if(idText2.equals(SHORT_LEAVE_TYPES[3]))
								elId = Integer.parseInt(idText.split(":")[1]);
							if(idText2.equals(SHORT_LEAVE_TYPES[4]))
								mtlId = Integer.parseInt(idText.split(":")[1]);
							if(idText2.equals(SHORT_LEAVE_TYPES[5]))
								ptlId = Integer.parseInt(idText.split(":")[1]);
							if(idText2.equals(SHORT_LEAVE_TYPES[6]))
								exlId = Integer.parseInt(idText.split(":")[1]);
							if(idText2.equals(SHORT_LEAVE_TYPES[7]))
								cclId = Integer.parseInt(idText.split(":")[1]);
							if(idText2.equals(SHORT_LEAVE_TYPES[8]))
								exlWithoutMediId = Integer.parseInt(idText.split(":")[1]);
						}
					}
				}
			}
		}
		if(leaveVO.getCasualLeaveInp() != 0){
			leaveList.add(getLeaveObj(LEAVE_TYPES[0], leaveVO.getCasualLeaveInp(), clId));
		}
		if(leaveVO.getSickLeaveInp() != 0){
			leaveList.add(getLeaveObj(LEAVE_TYPES[1], leaveVO.getSickLeaveInp(), slId));
		}
		if(leaveVO.getPaidLeaveInp() != 0){
			leaveList.add(getLeaveObj(LEAVE_TYPES[2], leaveVO.getPaidLeaveInp(), plId));
		}
		if(leaveVO.getEarnLeaveInp() != 0){
			leaveList.add(getLeaveObj(LEAVE_TYPES[3], leaveVO.getEarnLeaveInp(), elId));
		}
		if(leaveVO.getMaternityLeaveInp() != 0){
			leaveList.add(getLeaveObj(LEAVE_TYPES[4], leaveVO.getMaternityLeaveInp(), mtlId));
		}
		if(leaveVO.getPaternityLeaveInp() != 0) {
			leaveList.add(getLeaveObj(LEAVE_TYPES[5], leaveVO.getPaternityLeaveInp(), ptlId));
		}
		if(leaveVO.getExtraLeaveInp() != 0) {
			leaveList.add(getLeaveObj(LEAVE_TYPES[6], leaveVO.getExtraLeaveInp(), exlId));
		}
		if(leaveVO.getChildLeaveInp() != 0) {
			leaveList.add(getLeaveObj(LEAVE_TYPES[7], leaveVO.getChildLeaveInp(), cclId));
		}
		if(leaveVO.getExtraOrdinaryLeaveWithoutMediInp() != 0) {
			leaveList.add(getLeaveObj(LEAVE_TYPES[8], leaveVO.getExtraOrdinaryLeaveWithoutMediInp(), exlWithoutMediId));
		}
		return leaveList;
	}
	
	private Leave getLeaveObj(String leaveType, int noOfLeaves, int leaveId){
		Leave leave = new Leave();
		leave.setEmployeeId(empId);
		
		List<LeaveType> leaveTypes = new LeaveDAO().getLeaveTypeById(leaveId);
		LeaveType retrievedLeaveType = null;
		if (!leaveTypes.isEmpty()) {
			retrievedLeaveType = leaveTypes.get(0);
		}
		
		leave.setLeaveType(retrievedLeaveType);
		leave.setLeaveBalance(noOfLeaves);
		leave.setLeaveId(retrievedLeaveType.getId());
		return leave;
	}
	
	public String deleteLeave(String empId, String leaveId){
		return new LeaveDAO().deleteEmpLeave(empId, leaveId);
	}
	
	public List<LeaveVO> getEmpAvailableLeaves(String empId){
		return getEmpLeaves(new LeaveDAO().getLeavesByEmp(empId));
	}
	
	private List<LeaveVO> getEmpLeaves(List<LeaveVO> leaves){
		List<LeaveVO> empLeaves = new ArrayList<LeaveVO>();
		int sLeave = 0, cLeave = 0, pLeave = 0, eLeave = 0,  mLeave = 0, ptLeave =0, xLeave=0,ccLeave=0,xLeaveWithoutMedi=0;
		String empName = null;
		String empId = "0";
		StringBuffer leaveIds = new StringBuffer("");
		for (Iterator iterator = leaves.iterator(); iterator.hasNext();) {
			LeaveVO leave = (LeaveVO) iterator.next();
			if(empId == "0")
				empId = leave.getEmployeeId();
			if(empId != leave.getEmployeeId()){
				System.out.println("empId:"+empId+" -- leaveIds:"+leaveIds.toString());
				empLeaves.add(new LeaveVO(empId, empName, cLeave, ccLeave, pLeave, sLeave, 
						eLeave,  mLeave, ptLeave, xLeave, leaveIds.toString(),xLeaveWithoutMedi));
				empId = leave.getEmployeeId();
				sLeave = 0; cLeave = 0;pLeave = 0;eLeave = 0;  mLeave = 0; ptLeave =0; xLeave=0; ccLeave = 0; 
				empName = null;
				leaveIds = new StringBuffer("");
			}
			if(leave.getLeaveTypeId() != 0){
				if(Utils.isEmpty(empName))
					empName = leave.getFullName();
				if(leave.getLeaveTypeId() == 1){
					cLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[0], leave.getLeaveTypeId());
				}
				if(leave.getLeaveTypeId() == 2){
					sLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[1], leave.getLeaveTypeId());
				}
				if(leave.getLeaveTypeId() == 3){
					pLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[2], leave.getLeaveTypeId());
				}
				if(leave.getLeaveTypeId() == 4){
					eLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[3], leave.getLeaveTypeId());
				}
				if(leave.getLeaveTypeId() == 5){
					mLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[4], leave.getLeaveTypeId());
				}
				if(leave.getLeaveTypeId() == 6){
					ptLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[5], leave.getLeaveTypeId());
				}
				if(leave.getLeaveTypeId() == 7){
					xLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[6], leave.getLeaveTypeId());
				}
				if(leave.getLeaveTypeId() == 8){
					ccLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[7], leave.getLeaveTypeId());
				}
				if(leave.getLeaveTypeId() == 9){
					xLeaveWithoutMedi = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[8], leave.getLeaveTypeId());
				}
			}
		}
		System.out.println("empId:"+empId+" -- leaveIds:"+leaveIds.toString());
		if(empId!="0"){
			empLeaves.add(new LeaveVO(empId, empName, cLeave, ccLeave, pLeave, sLeave, 
					eLeave,  mLeave, ptLeave, xLeave, leaveIds.toString(),xLeaveWithoutMedi));
		}
		return empLeaves;
	}
	
	public List<LeaveType> getLeaveTypes(){
		return new LeaveDAO().getLeaveType();
	}
	
	private void addLeaveIds(StringBuffer leaveIds, String slType, int leaveId){
		leaveIds.append(slType);
		leaveIds.append(":");
		leaveIds.append(leaveId);
		leaveIds.append(",");
	}
}
