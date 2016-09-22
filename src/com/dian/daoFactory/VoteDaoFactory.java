package com.dian.daoFactory;

import com.dian.dao.VoteDao;
import com.dian.daoImpl.VoteDaoImpl;

public class VoteDaoFactory {
	public static VoteDao getVoteDaoInstance(){	//工厂方法，用来返回DAO实现类实例
		return new VoteDaoImpl();						//返回DAO实现类实例
	}
}
