package com.dian.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.dian.bean.Vote;
import com.dian.bean.VoteOption;
import com.dian.bean.VoteResult;
import com.dian.dao.VoteDao;
import com.dian.dao.VoteOptionDao;
import com.dian.daoFactory.VoteDaoFactory;
import com.dian.daoFactory.VoteOptionDaoFactory;
import com.dian.util.Page;
import com.opensymphony.xwork2.ActionSupport;

public class ShowVoteAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int currentPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String execute() throws Exception {
		VoteDao voteDao = VoteDaoFactory.getVoteDaoInstance();// 获得VoteDAO实例
		VoteOptionDao voteOptionDao = VoteOptionDaoFactory.getVoteOptionDaoInstance();// 获得voteOption实例
		int totalCount = voteDao.findAllCount();
		Page page = Page.createPage(totalCount);
		page.setPageSize(1);
		page.setCurrentPage(currentPage);
		List<Vote> votes = voteDao.findAllVote(page);
		List<VoteResult> voteResultList = new ArrayList<VoteResult>();
		for (Vote vote : votes) {
			List<VoteOption> voteOptions = voteOptionDao.findVoteOptionByVoteID(vote.getVoteID());
			VoteResult voteResult = new VoteResult();
			voteResult.setVote(vote);
			voteResult.setVoteOptions(voteOptions);
			voteResultList.add(voteResult);
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("voteResultList", voteResultList);
		request.setAttribute("page", page);
		return SUCCESS;
	}
}
