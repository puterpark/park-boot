package us.puter.park.repository.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	protected SqlSession sqlSession;

}
