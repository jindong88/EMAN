package com.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




//import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dao.DBAccess;
import com.dao.EBookDao;
import com.entity.EBook;

@Repository("eBookDao")
public class EBookDaoImpl implements EBookDao{
	
	private SqlSession sqlSession = DBAccess.getSqlSession();

	public SqlSession getSqlSession() {
		return sqlSession;
	}

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public EBook queryEBookByEid(int eid) {
		return sqlSession.selectOne("selectEBookByEid", eid);
	}

	@Override
	public List<EBook> quertyAllEBook() {
		List<EBook> list = sqlSession.selectList("selectAllEBook");
		return list;
	}

	@Override
	public List<EBook> queryAllEBookLimit(Integer start) {
		if(start < 0 || start == null)
			start = 0;
		Integer size = 20;
		Map<String, Integer> args = new HashMap<String, Integer>();
		args.put("start", start);
		args.put("size", size);
		List<EBook> list = sqlSession.selectList("selectAllEBookLimit", args);
		return list;
	}
	
	@Override
	public List<EBook> queryEBookLimitByClassifyMain(String classifyMain, Integer start) {
		if(start < 0 || start == null)
			start = 0;

		Integer size = 20;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("classifyMain", classifyMain);
		args.put("start", start);
		args.put("size", size);
		List<EBook> list = sqlSession.selectList("selectEBookLimitByClassifyMain",args);
		return list;
	}

	@Override
	public List<EBook> queryEBookByCondition(EBook condition, Integer start, String orderCondition, String order) {
		if(start < 0 || start == null)
			start = 0;
		if(orderCondition == null || orderCondition.equals(""))
			orderCondition = "ratingValue";
		if(order == null || order.equals(""))
			order = "desc";

		Integer size = 20;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("eid", condition.getEid());
		args.put("ISBN", condition.getISBN());
		args.put("ename", condition.getEname());
		args.put("author", condition.getAuthor());
		args.put("translator", condition.getTranslator());
		args.put("publishingHouse", condition.getPublishingHouse());
		args.put("provider", condition.getProvider());
		args.put("classifyMain", condition.getClassifyMain());
		args.put("orderCondition", orderCondition);
		args.put("order", order);
		args.put("start", start);
		args.put("size", size);
		List<EBook> list = sqlSession.selectList("selectEBookByCondition",args);
		return list;
	}

	@Override
	public List<EBook> queryEBookByKeyword(String keyword, Integer start,
			String orderCondition, String order) {
		if(start < 0 || start == null)
			start = 0;
		if(orderCondition == null || orderCondition.equals(""))
			orderCondition = "ename";
		if(order == null || order.equals(""))
			order = "asc";

		Integer size = 20;
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("keyword", keyword);
		args.put("orderCondition", orderCondition);
		args.put("order", order);
		args.put("start", start);
		args.put("size", size);
		List<EBook> list = sqlSession.selectList("selectEBookByKeyword",args);
		return list;
	}

	@Override
	public int queryEBookByKeywordCount(String keyword, String orderCondition,
			String order) {
		if(orderCondition == null || orderCondition.equals(""))
			orderCondition = "ename";
		if(order == null || order.equals(""))
			order = "asc";

		Map<String, Object> args = new HashMap<String, Object>();
		args.put("keyword", keyword);
		args.put("orderCondition", orderCondition);
		args.put("order", order);
		int num = sqlSession.selectOne("selectEBookByKeywordCount",args);
		return num;
	}

}
