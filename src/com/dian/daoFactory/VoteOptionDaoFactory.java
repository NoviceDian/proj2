package com.dian.daoFactory;

import com.dian.dao.VoteOptionDao;
import com.dian.daoImpl.VoteOptionDaoImpl;

public class VoteOptionDaoFactory {
	public static VoteOptionDao getVoteOptionDaoInstance(){	//工厂方法，用来返回DAO实现类实例
		return new VoteOptionDaoImpl();						//返回DAO实现类实例
	}
}
