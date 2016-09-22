package com.dian.action;

import com.dian.bean.Vote;
import com.dian.bean.VoteOption;
import com.dian.dao.VoteDao;
import com.dian.dao.VoteOptionDao;
import com.dian.daoFactory.VoteDaoFactory;
import com.dian.daoFactory.VoteOptionDaoFactory;
import com.opensymphony.xwork2.ActionSupport;

public class AddVoteAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int channel;		// 封装channel参数
	private String voteName;	// 封装voteName参数
	private String[] voteOption;// 封装voteOption参数

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public String getVoteName() {
		return voteName;
	}

	public void setVoteName(String voteName) {
		this.voteName = voteName;
	}

	public String[] getVoteOption() {
		return voteOption;
	}

	public void setVoteOption(String[] voteOption) {
		this.voteOption = voteOption;
	}

	public String execute() throws Exception {
		VoteDao voteDao = VoteDaoFactory.getVoteDaoInstance();//获得VoteDAO实例
		VoteOptionDao voteOptionDao = 
				VoteOptionDaoFactory.getVoteOptionDaoInstance();//获得voteOption实例
		//首先保存投票，然后再保存投票选项
		Vote vote = new Vote();
		vote.setChannelID(channel);
		vote.setVoteName(voteName);
		voteDao.addVote(vote);
		//查询投票ID
		int voteID = voteDao.findVoteByName(voteName).getVoteID();
		//保存投票选项
		for(String voteOptionName : voteOption) {
			VoteOption vp = new VoteOption();
			vp.setVoteID(voteID);
			vp.setVoteOptionName(voteOptionName);
			voteOptionDao.addVoteOption(vp);
		}
		return SUCCESS;
	}

}
