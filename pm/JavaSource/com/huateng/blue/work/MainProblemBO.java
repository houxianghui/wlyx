package com.huateng.blue.work;

import java.util.List;

import com.abc.logic.IbatisBO;

public class MainProblemBO extends IbatisBO {

	@Override
	public void update(Object obj) throws Exception {

	}

	@Override
	public void insert(Object obj) throws Exception {

	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		return dao.queryForListByGenerate(namespace, new MainProblemExample());
	}

	@Override
	public void delete(Object obj) throws Exception {

	}
	public void batchUpdate(List<MainProblem> l){
		dao.deleteByGenerateWithCondition(namespace, new MainProblemExample());
		for(MainProblem m :l){
			dao.insertByGenerate(namespace, m);
		}
	}

}
