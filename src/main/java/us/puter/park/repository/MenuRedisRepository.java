package us.puter.park.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import us.puter.park.domain.entity.Menu;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class MenuRedisRepository {

	private final RedisTemplate<String, Object> redisTemplate;

	private final String KEY = "menuList";

	/**
	 * DB에서 가져온 menuList를 redis에 저장
	 * @param menuList
	 */
	public void cacheMenuList(List<Menu> menuList) {
		redisTemplate.opsForValue().set(KEY, menuList);
		redisTemplate.expire(KEY, 60, TimeUnit.MINUTES);
	}

	/**
	 * redis에 저장된 menuList 조회
	 * @return menuList
	 */
	public List<Menu> findMenuList() {
		return (List<Menu>) redisTemplate.opsForValue().get(KEY);
	}

}
