package com.dian.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dian.bean.VoteOption;
import com.dian.dao.VoteOptionDao;
import com.dian.util.DBConnection;

public class VoteOptionDaoImpl implements VoteOptionDao {

	@Override
	public void addVoteOption(VoteOption voteOption) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String addSQL = "insert into tb_voteoption(voteOptionName,voteID,ticketNum) values(?,?,?)";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(addSQL); // 获得预处理对象并赋值
			pstmt.setString(1, voteOption.getVoteOptionName()); // 设置投票选项名称
			pstmt.setInt(2, voteOption.getVoteID()); // 设置投票ID
			pstmt.setInt(3, voteOption.getTicketNum()); // 设置投票数
			pstmt.executeUpdate(); // 执行添加
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		}
	}

	@Override
	public void updateVoteOption(VoteOption voteOption) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String deleteSQL = "update tb_voteoption set ticketNum = ? where voteOptionID = ?";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(deleteSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, voteOption.getTicketNum()); // 设置票数
			pstmt.setInt(2, voteOption.getVoteOptionID());
			pstmt.executeUpdate(); // 执行删除
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		}
	}

	@Override
	public void deleteVoteOption(int voteOptionID) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String deleteSQL = "delete from tb_voteoption where voteOptionID=?";
		PreparedStatement pstmt = null; // 声明预处理对象
		try {
			pstmt = conn.prepareStatement(deleteSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, voteOptionID); // 设置投票编号
			pstmt.executeUpdate(); // 执行删除
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(pstmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		}
	}

	@Override
	public List<VoteOption> findVoteOptionByVoteID(int voteID) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String findByIDSQL = "select * from tb_voteoption where voteID = ?";// 查询SQL语句
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		List<VoteOption> voteOptions = new ArrayList<VoteOption>();
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, voteID);
			rs = pstmt.executeQuery(); // 执行查询
			while (rs.next()) {
				VoteOption voteOption = new VoteOption();
				voteOption.setVoteOptionID(rs.getInt(1));
				voteOption.setVoteID(rs.getInt(2));
				voteOption.setVoteOptionName(rs.getString(3));
				voteOption.setTicketNum(rs.getInt(4));
				voteOptions.add(voteOption);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs); // 关闭结果集对象
			DBConnection.close(pstmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		}
		return voteOptions;
	}

	@Override
	public VoteOption findVoteOptionById(int voteOptionID) {
		Connection conn = DBConnection.getConnection(); // 获得连接对象
		String findByIDSQL = "select * from tb_voteoption where voteOptionID = ?";// 查询SQL语句
		PreparedStatement pstmt = null; // 声明预处理对象
		ResultSet rs = null;
		VoteOption voteOption = null;
		try {
			pstmt = conn.prepareStatement(findByIDSQL); // 获得预处理对象并赋值
			pstmt.setInt(1, voteOptionID);
			rs = pstmt.executeQuery(); // 执行查询
			if (rs.next()) {
				voteOption = new VoteOption();
				voteOption.setVoteOptionID(rs.getInt(1));
				voteOption.setVoteID(rs.getInt(2));
				voteOption.setVoteOptionName(rs.getString(3));
				voteOption.setTicketNum(rs.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.close(rs); // 关闭结果集对象
			DBConnection.close(pstmt); // 关闭预处理对象
			DBConnection.close(conn); // 关闭连接对象
		}
		return voteOption;
	}

}
