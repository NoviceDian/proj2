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

public class ShowVoteByChannelAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int channelID;
	private int currentPage;

	public int getChannelID() {
		return channelID;
	}

	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String execute() throws Exception {
		VoteDao voteDao = VoteDaoFactory.getVoteDaoInstance();// 获得VoteDAO实例
		VoteOptionDao voteOptionDao = VoteOptionDaoFactory.getVoteOptionDaoInstance();// 获得voteOption实例
		// 获得该频道下的记录数
		int totalCount = voteDao.fintCountByChannel(channelID);
		// 设置分页信息
		Page page = Page.createPage(totalCount);
		page.setPageSize(3);
		page.setCurrentPage(currentPage);
		// 取得该频道下的记录
		List<Vote> votes = voteDao.findVoteByChannel(page, channelID);
		// 存放所有投票和投票选项
		List<VoteResult> voteResultList = new ArrayList<VoteResult>();
		for (Vote vote : votes) {
			// 查询该投票下的所有投票选项
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
