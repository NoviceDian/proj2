package com.dian.dao;

import java.util.List;

import com.dian.bean.VoteOption;

public interface VoteOptionDao {
	public void addVoteOption(VoteOption voteOption); // 添加投票选项

	public void updateVoteOption(VoteOption voteOption); // 更新投票选项

	public void deleteVoteOption(int voteOptionID); // 删除投票选项

	public List<VoteOption> findVoteOptionByVoteID(int voteID); // 查询所有投票选项

	public VoteOption findVoteOptionById(int voteOptionID); // 通过ID查询投票选项
}
