package us.puter.park.repository.dao;

import org.springframework.stereotype.Repository;
import us.puter.park.domain.entity.Menu;

import java.util.List;

@Repository
public class MenuDao extends BaseDao {

	/**
	 * 메뉴 목록 갯수
	 * @return
	 */
	public int getMenuListCount(Menu menu) {

		return sqlSession.selectOne("getMenuListCount", menu);
	}

	/**
	 * 메뉴 목록
	 * @return
	 */
	public List<Menu> getMenuList() {

		return sqlSession.selectList("getMenuList");
	}

}
