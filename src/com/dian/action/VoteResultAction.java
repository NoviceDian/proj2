package com.dian.action;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.dian.bean.Vote;
import com.dian.bean.VoteOption;
import com.dian.dao.VoteDao;
import com.dian.dao.VoteOptionDao;
import com.dian.daoFactory.VoteDaoFactory;
import com.dian.daoFactory.VoteOptionDaoFactory;
import com.opensymphony.xwork2.ActionSupport;

public class VoteResultAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFreeChart chart;
	private int voteID;//投票ID
	
	public int getVoteID() {
		return voteID;
	}

	public void setVoteID(int voteID) {
		this.voteID = voteID;
	}

	public JFreeChart getChart() {
		VoteDao voteDao = VoteDaoFactory.getVoteDaoInstance();//获得VoteDAO实例
		VoteOptionDao voteOptionDao = 
				VoteOptionDaoFactory.getVoteOptionDaoInstance();//获得voteOption实例
		//根据投票ID得到的投票
		Vote vote = voteDao.findVoteById(voteID);
		String voteName = vote.getVoteName();	//得到投票名称
		
		//根据投票ID得到所有的投票选项
		List<VoteOption> voteOptions = voteOptionDao.findVoteOptionByVoteID(voteID);
		
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();//数据源
		
		//设置数据
		for(VoteOption voteOption : voteOptions) {
			dcd.setValue(voteOption.getTicketNum(),"",voteOption.getVoteOptionName());
		}
		//使用工厂类创建柱状图
		 chart = ChartFactory.createBarChart3D(
				voteName,
				"投票选项",
				"投票数", 
				dcd,
				PlotOrientation.VERTICAL ,
				false, 
				true,
				false);
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	
	public String execute() throws Exception {
		return SUCCESS;
	}
}
