package com.revature.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ReimbursementService;
import com.revature.employee.FinanceManager;
import com.revature.employee.GenericEmployee;
import com.revature.exceptions.ManagerApprovingOwnRequestException;
import com.revature.exceptions.NoPendingReimbursmentException;
import com.revature.exceptions.NoReibursmentForIdException;
import com.revature.exceptions.NoReimbursementForEmployeeException;
import com.revature.exceptions.ReimbursmentApprovalException;
import com.revature.exceptions.ReimbursmentDenialException;
import com.revature.exceptions.ReimbursmentSubmissionException;
import com.revature.reimbursement.Category;
import com.revature.reimbursement.Reimbursment;

public class ReburService {
	private ReburService(){}
	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
	
	public static String submitManagerRebur(HttpServletRequest request, HttpServletResponse response) {
		FinanceManager man = (FinanceManager)request.getSession().getAttribute("authorizedUser");
		String cat = request.getParameter("category");
		String amount = request.getParameter("amount");
		Reimbursment rebur = new Reimbursment(Category.stringToCat(cat), Integer.parseInt(amount),man.getId());
		try {
			ReimbursementService.submitReimbursment(rebur);
			String rebursementJson = new Gson().toJson(ReimbursementService.getReimbursmentForEmployee(man.getUsername()));
			request.getSession().setAttribute("reimbursements", rebursementJson);
			String pendingJson = new Gson().toJson(ReimbursementService.getPendingReimbursemnts());
			request.getSession().setAttribute("pendingReimbursements", pendingJson);
		} catch (ReimbursmentSubmissionException rse) {
			logger.error(rse.getMessage(), rse);
		} catch (NoReimbursementForEmployeeException nrfee) {
			logger.error(nrfee.getMessage(), nrfee);
		} catch (NoPendingReimbursmentException npre) {
			logger.error(npre.getMessage(), npre);
		}
		return"/viewManagerReimburstment.jsp";
	}
	public static String submitEmployeeRebur(HttpServletRequest request, HttpServletResponse response) {
		GenericEmployee emp = (GenericEmployee)request.getSession().getAttribute("authorizedUser");
		String cat = request.getParameter("category");
		String amount = request.getParameter("amount");
		Reimbursment rebur = new Reimbursment(Category.stringToCat(cat), Integer.parseInt(amount),emp.getId());
		try {
			ReimbursementService.submitReimbursment(rebur);
			String rebursementJson = new Gson().toJson(ReimbursementService.getReimbursmentForEmployee(emp.getUsername()));
			request.getSession().setAttribute("reimbursements", rebursementJson);
			String pendingJson = new Gson().toJson(ReimbursementService.getPendingReimbursemnts());
			request.getSession().setAttribute("pendingReimbursements", pendingJson);
		} catch (ReimbursmentSubmissionException rse) {
			logger.error(rse.getMessage(), rse);
		} catch (NoReimbursementForEmployeeException nrfee) {
			logger.error(nrfee.getMessage(), nrfee);
		} catch (NoPendingReimbursmentException npre) {
			logger.error(npre.getMessage(), npre);
		}
		return"/viewEmployeeReimburstment.jsp";
	}

	public static String approve(HttpServletRequest request, HttpServletResponse responce) throws ManagerApprovingOwnRequestException {
		FinanceManager man = (FinanceManager)request.getSession().getAttribute("authorizedUser");
		int appId = man.getId();
		int reburId = Integer.parseInt(request.getParameter("id"));
		int subId;
		try {
			subId = ReimbursementService.getReimbursmentById(reburId).getSumbitterId();
			if(subId != appId) {
				ReimbursementService.approveReimbursment(appId,reburId);
				String pendingJson = new Gson().toJson(ReimbursementService.getPendingReimbursemnts());
				request.getSession().setAttribute("pendingReimbursements", pendingJson);
				return"/approveReimburstment.jsp";
		} }catch (NoReibursmentForIdException nrfie) {
			logger.error(nrfie.getMessage(), nrfie);
		} catch (ReimbursmentApprovalException rae) {
			logger.error(rae.getMessage(), rae);
		} catch (NoPendingReimbursmentException npre) {
			logger.error(npre.getMessage(), npre);
		}
		
		throw new ManagerApprovingOwnRequestException();
	}
	public static String deny(HttpServletRequest request, HttpServletResponse responce) throws ManagerApprovingOwnRequestException {
		FinanceManager man = (FinanceManager)request.getSession().getAttribute("authorizedUser");
		int appId = man.getId();
		int reburId = Integer.parseInt(request.getParameter("id"));
		int subId;
		try {
			subId = ReimbursementService.getReimbursmentById(reburId).getSumbitterId();
			if(subId != appId) {
				ReimbursementService.denyReimbursment(appId,reburId);
				String pendingJson = new Gson().toJson(ReimbursementService.getPendingReimbursemnts());
				request.getSession().setAttribute("pendingReimbursements", pendingJson);
				return"/approveReimburstment.jsp";
		}} catch (NoReibursmentForIdException nrfie) {
			logger.error(nrfie.getMessage(), nrfie);
		} catch (ReimbursmentDenialException rde) {
			logger.error(rde.getMessage(), rde);
		} catch (NoPendingReimbursmentException npre) {
			logger.error(npre.getMessage(), npre);
		}
		throw new ManagerApprovingOwnRequestException();
	}
}
