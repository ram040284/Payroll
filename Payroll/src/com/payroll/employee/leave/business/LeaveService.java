package com.payroll.employee.leave.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.payroll.Utils;
import com.payroll.employee.leave.dataobjects.Leave;
import com.payroll.employee.leave.dataobjects.LeaveDAO;
import com.payroll.employee.leave.vo.LeaveVO;
public class LeaveService {
	public static final String LEAVE_TYPES[] = {"Casual Leave", "Sick Leave", "Half Paid Leave", 
			"Earned Leave", "Maternity Leave", "Paternity Leave", "Extraordinary Leave"};
	public static final String SHORT_LEAVE_TYPES[] = {"CL", "SL", "PL", "EL", "MTL", "PTL", "EXL"};
	int empId;
	public List<LeaveVO> getLeaves(){
		return getEmpLeaves(new LeaveDAO().getLeaves());
	}
	
	public List<LeaveVO> getLeaves(int deptId, int headId, String empName){
		return getEmpLeaves(new LeaveDAO().getLeaves(deptId, headId, empName));
	}
	
	public String addUpdateLeave(LeaveVO leaveVO){
		return new LeaveDAO().addUpdateLeave(copyProperties(leaveVO));
	}
	
	public LeaveVO getLeaveByIde(int empId){
		LeaveVO empLeave = null;
		List<LeaveVO> listVO = new LeaveDAO().getEmpLeave(empId);
		LeaveVO leaveDBVO = null;
		if(!listVO.isEmpty())
		leaveDBVO = listVO.get(0);
		List<LeaveVO> leaveList = getEmpLeaves(new LeaveDAO().getEmpLeave(empId));
		if(!leaveList.isEmpty() && leaveDBVO !=null){
			LeaveVO empLeaveVO = leaveList.get(0);
			empLeave = new LeaveVO(leaveDBVO.getEmployeeId(), leaveDBVO.getDepartmentId(), leaveDBVO.getDesignationId(), 
					leaveDBVO.getHeadId(), empLeaveVO.getSickLeaves(), empLeaveVO.getCasualLeaves(), 
					empLeaveVO.getPaidLeaves(), empLeaveVO.getEarnLeave(), empLeaveVO.getMaternityLeave(), 
					empLeaveVO.getPaternityLeave(), empLeaveVO.getExtraLeave(), empLeaveVO.getLeaveBalance(), empLeaveVO.getLeaveIds());
		}
		return empLeave;
	}
	
	private List<Leave> copyProperties(LeaveVO leaveVO){
		this.empId = leaveVO.getEmployeeId();
		List<Leave> leaveList = new ArrayList<Leave>();
		int slId =0, plId=0, clId = 0, elId=0, ptlId =0, mtlId=0, exlId = 0;
		if(!Utils.isEmpty(leaveVO.getLeaveIds())){
			String[] ids = leaveVO.getLeaveIds().split(",");
			if(ids.length > 0){
				for (int i = 0; i < ids.length; i++) {
					String idText =  ids[i];
					if(!Utils.isEmpty(idText)){
						if(idText.contains(":")){
							if(idText.contains(SHORT_LEAVE_TYPES[0]))
								clId = Integer.parseInt(idText.split(":")[1]);
							if(idText.contains(SHORT_LEAVE_TYPES[1]))
								slId = Integer.parseInt(idText.split(":")[1]);
							if(idText.contains(SHORT_LEAVE_TYPES[2]))
								plId = Integer.parseInt(idText.split(":")[1]);
							if(idText.contains(SHORT_LEAVE_TYPES[3]))
								elId = Integer.parseInt(idText.split(":")[1]);
							if(idText.contains(SHORT_LEAVE_TYPES[4]))
								mtlId = Integer.parseInt(idText.split(":")[1]);
							if(idText.contains(SHORT_LEAVE_TYPES[5]))
								ptlId = Integer.parseInt(idText.split(":")[1]);
							if(idText.contains(SHORT_LEAVE_TYPES[6]))
								exlId = Integer.parseInt(idText.split(":")[1]);
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
			leaveList.add(getLeaveObj(LEAVE_TYPES[2], leaveVO.getCasualLeaveInp(), plId));
		}
		if(leaveVO.getEarnLeaveInp() != 0){
			leaveList.add(getLeaveObj(LEAVE_TYPES[3], leaveVO.getEarnLeaveInp(), elId));
		}
		if(leaveVO.getMaternityLeaveInp() != 0){
			leaveList.add(getLeaveObj(LEAVE_TYPES[4], leaveVO.getMaternityLeaveInp(), mtlId));
		}
		if(leaveVO.getPaternityLeaveInp() != 0)
			leaveList.add(getLeaveObj(LEAVE_TYPES[5], leaveVO.getPaternityLeaveInp(), ptlId));
		if(leaveVO.getExtraLeaveInp() != 0)
			leaveList.add(getLeaveObj(LEAVE_TYPES[6], leaveVO.getExtraLeaveInp(), exlId));
		
		return leaveList;
	}
	
	private Leave getLeaveObj(String leaveType, int noOfLeaves, int leaveId){
		Leave leave = new Leave();
		leave.setEmployeeId(empId);
		leave.setLeaveType(leaveType);
		leave.setNoOfLeaves(noOfLeaves);
		leave.setLeaveId(leaveId);
		return leave;
	}
	
	public String deleteLeave(int empId){
		return new LeaveDAO().deleteEmpLeave(empId);
	}
	
	public List<LeaveVO> getEmpAvailableLeaves(int empId){
		return getEmpLeaves(new LeaveDAO().getLeavesByEmp(empId));
	}
	
	private List<LeaveVO> getEmpLeaves(List<LeaveVO> leaves){
		List<LeaveVO> empLeaves = new ArrayList<LeaveVO>();
		int sLeave = 0, cLeave = 0, pLeave = 0, eLeave = 0,  mLeave = 0, ptLeave =0, xLeave=0;
		String empName = null;
		int empId = 0;
		StringBuffer leaveIds = new StringBuffer("");
		for (Iterator iterator = leaves.iterator(); iterator.hasNext();) {
			LeaveVO leave = (LeaveVO) iterator.next();
			if(empId == 0)
				empId = leave.getEmployeeId();
			if(empId != leave.getEmployeeId()){
				System.out.println("empId:"+empId+" -- leaveIds:"+leaveIds.toString());
				empLeaves.add(new LeaveVO(empId, empName, cLeave, pLeave, sLeave, 
						eLeave,  mLeave, ptLeave, xLeave, leaveIds.toString()));
				empId = leave.getEmployeeId();
				sLeave = 0; cLeave = 0; pLeave = 0;eLeave = 0;  mLeave = 0; ptLeave =0; xLeave=0;
				empName = null;
				leaveIds = new StringBuffer("");
			}
			if(leave.getLeaveType() != null){
				if(Utils.isEmpty(empName))
					empName = leave.getFullName();
				if(leave.getLeaveType().equalsIgnoreCase(LEAVE_TYPES[1])){
					sLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[1], leave.getLeaveId());
				}
				if(leave.getLeaveType().equalsIgnoreCase(LEAVE_TYPES[0])){
					cLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[0], leave.getLeaveId());
				}
				if(leave.getLeaveType().equalsIgnoreCase(LEAVE_TYPES[2])){
					pLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[2], leave.getLeaveId());
				}
				if(leave.getLeaveType().equalsIgnoreCase(LEAVE_TYPES[3])){
					eLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[3], leave.getLeaveId());
				}
				if(leave.getLeaveType().equalsIgnoreCase(LEAVE_TYPES[4])){
					mLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[4], leave.getLeaveId());
				}
				if(leave.getLeaveType().equalsIgnoreCase(LEAVE_TYPES[5])){
					ptLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[5], leave.getLeaveId());
				}
				if(leave.getLeaveType().equalsIgnoreCase(LEAVE_TYPES[6])){
					xLeave = leave.getLeaveBalance();
					addLeaveIds(leaveIds, SHORT_LEAVE_TYPES[6], leave.getLeaveId());
				}
			}
		}
		System.out.println("empId:"+empId+" -- leaveIds:"+leaveIds.toString());
		if(empId!=0){
			empLeaves.add(new LeaveVO(empId, empName, cLeave, pLeave, sLeave, 
					eLeave,  mLeave, ptLeave, xLeave, leaveIds.toString()));
		}
		return empLeaves;
	}
	
	private void addLeaveIds(StringBuffer leaveIds, String slType, int leaveId){
		leaveIds.append(slType);
		leaveIds.append(leaveId);
		leaveIds.append(",");
	}
}
