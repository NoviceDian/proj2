package com.dian.action;

import java.util.List;

import com.dian.bean.VoteOption;
import com.dian.dao.VoteDao;
import com.dian.dao.VoteOptionDao;
import com.dian.daoFactory.VoteDaoFactory;
import com.dian.daoFactory.VoteOptionDaoFactory;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteVoteAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int voteID;

	public int getVoteID() {
		return voteID;
	}

	public void setVoteID(int voteID) {
		this.voteID = voteID;
	}

	public String execute() throws Exception {
		VoteDao voteDao = VoteDaoFactory.getVoteDaoInstance();//获得VoteDAO实例
		VoteOptionDao voteOptionDao = 
				VoteOptionDaoFactory.getVoteOptionDaoInstance();//获得voteOption实例
		//通过该投票ID查找该投票下的所有投票选项
		List<VoteOption> voteOptions = voteOptionDao.findVoteOptionByVoteID(voteID);
		//循环进行删除
		for(VoteOption voteOption : voteOptions) {
			voteOptionDao.deleteVoteOption(voteOption.getVoteOptionID());
		}
		//再删除该投票
		voteDao.deleteVote(voteID);
		return SUCCESS;
	}
}
