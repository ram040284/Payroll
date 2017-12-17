package com.payroll.employee.leave.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.payroll.Utils;
import com.payroll.employee.leave.dataobjects.Leave;
import com.payroll.employee.leave.dataobjects.LeaveDAO;
import com.payroll.employee.leave.vo.LeaveVO;

public class LeaveService {
	
	public List<LeaveVO> getLeaves(){
		//return new LeaveDAO().getLeaves();
		return getEmpLeaves(new LeaveDAO().getLeaves());
	}
	
	public List<LeaveVO> getLeaves(int deptId, int headId, String empName){
		//return new LeaveDAO().getLeaves();
		return getEmpLeaves(new LeaveDAO().getLeaves(deptId, headId, empName));
	}
	
	public String addUpdateLeave(LeaveVO leaveVO){
		return new LeaveDAO().addUpdateLeave(copyProperties(leaveVO));
	}
	
	public LeaveVO getLeaveByIde(int empId){
		//return new LeaveDAO().getEmpLeave(empId);
		LeaveVO empLeave = null;
		List<LeaveVO> listVO = new LeaveDAO().getEmpLeave(empId);
		LeaveVO leaveDBVO = null;
		if(!listVO.isEmpty())
		leaveDBVO = listVO.get(0);
		List<LeaveVO> leaveList = getEmpLeaves(new LeaveDAO().getEmpLeave(empId));
		if(!leaveList.isEmpty() && leaveDBVO !=null){
			LeaveVO empLeaveVO = leaveList.get(0);
			System.out.println("EmpID:"+leaveDBVO.getEmployeeId());
			System.out.println("Dept ID:"+leaveDBVO.getDepartmentId());
			System.out.println("DesgId:"+leaveDBVO.getDesignationId());
			System.out.println("headId:"+leaveDBVO.getHeadId());
			empLeave = new LeaveVO(leaveDBVO.getEmployeeId(), leaveDBVO.getDepartmentId(), leaveDBVO.getDesignationId(), 
					leaveDBVO.getHeadId(), empLeaveVO.getSickLeaves(), empLeaveVO.getCasualLeaves(), 
					empLeaveVO.getPaidLeaves(), empLeaveVO.getLeaveBalance(), empLeaveVO.getLeaveIds());
		}
		return empLeave;
	}
	
	private List<Leave> copyProperties(LeaveVO leaveVO){
		
		List<Leave> leaveList = new ArrayList<Leave>();
		int slId =0, plId=0, clId = 0;
		if(!Utils.isEmpty(leaveVO.getLeaveIds())){
			String[] ids = leaveVO.getLeaveIds().split(",");
			if(ids.length > 0){
				for (int i = 0; i < ids.length; i++) {
					String idText =  ids[i];
					if(!Utils.isEmpty(idText)){
						if(idText.contains(":")){
							if(idText.contains("SL"))
								slId = Integer.parseInt(idText.split(":")[1]);
							if(idText.contains("PL"))
								plId = Integer.parseInt(idText.split(":")[1]);
							if(idText.contains("CL"))
								clId = Integer.parseInt(idText.split(":")[1]);
						}
					}
				}
			}
		}
		if(leaveVO.getCasualLeaveInp() != 0){
			Leave leave = new Leave();
			leave.setEmployeeId(leaveVO.getEmployeeId());
			leave.setLeaveType("Casual Leave");
			leave.setNoOfLeaves(leaveVO.getCasualLeaveInp());
			leave.setLeaveId(clId);
			leaveList.add(leave);
		}
		if(leaveVO.getSickLeaveInp() != 0){
			Leave leave = new Leave();
			leave.setEmployeeId(leaveVO.getEmployeeId());
			leave.setLeaveType("Sick Leave");
			leave.setNoOfLeaves(leaveVO.getSickLeaveInp());
			leave.setLeaveId(slId);
			leaveList.add(leave);
		}
		if(leaveVO.getPaidLeaveInp() != 0){
			Leave leave = new Leave();
			leave.setEmployeeId(leaveVO.getEmployeeId());
			leave.setLeaveType("Paid Vacation");
			leave.setNoOfLeaves(leaveVO.getPaidLeaveInp());
			leave.setLeaveId(plId);
			leaveList.add(leave);
		}
		return leaveList;
	}
	
	public String deleteLeave(int empId){
		return new LeaveDAO().deleteEmpLeave(empId);
	}
	
	public List<LeaveVO> getEmpAvailableLeaves(int empId){
		return getEmpLeaves(new LeaveDAO().getLeavesByEmp(empId));
	}
	
	private List<LeaveVO> getEmpLeaves(List<LeaveVO> leaves){
		List<LeaveVO> empLeaves = new ArrayList<LeaveVO>();
		int sLeave = 0, cLeave = 0, pLeave = 0;
		String empName = null;
		int empId = 0;
		StringBuffer leaveIds = new StringBuffer("");
		for (Iterator iterator = leaves.iterator(); iterator.hasNext();) {
			LeaveVO leave = (LeaveVO) iterator.next();
			if(empId == 0)
				empId = leave.getEmployeeId();
			if(empId != leave.getEmployeeId()){
				System.out.println("empId:"+empId+" -- leaveIds:"+leaveIds.toString());
				empLeaves.add(new LeaveVO(empId, empName, cLeave, pLeave, sLeave, leaveIds.toString()));
				empId = leave.getEmployeeId();
				sLeave = 0; cLeave = 0; pLeave = 0;
				empName = null;
				leaveIds = new StringBuffer("");
			}
			if(leave.getLeaveType() != null){
				if(Utils.isEmpty(empName))
					empName = leave.getFullName();
				if(leave.getLeaveType().equalsIgnoreCase("Sick Leave")){
					sLeave = leave.getLeaveBalance();
					leaveIds.append("SL:");
					leaveIds.append(leave.getLeaveId());
					leaveIds.append(",");
				}
				if(leave.getLeaveType().equalsIgnoreCase("Casual Leave")){
					cLeave = leave.getLeaveBalance();
					leaveIds.append("CL:");
					leaveIds.append(leave.getLeaveId());
					leaveIds.append(",");
				}
				if(leave.getLeaveType().equalsIgnoreCase("Paid Vacation")){
					pLeave = leave.getLeaveBalance();
					leaveIds.append("PL:");
					leaveIds.append(leave.getLeaveId());
					leaveIds.append(",");
				}
			}
		}
		System.out.println("empId:"+empId+" -- leaveIds:"+leaveIds.toString());
		if(empId!=0){
			empLeaves.add(new LeaveVO(empId, empName, cLeave, pLeave, sLeave, leaveIds.toString()));
		}
		return empLeaves;
	}

}
