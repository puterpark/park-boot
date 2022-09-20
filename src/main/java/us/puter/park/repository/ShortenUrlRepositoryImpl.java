package us.puter.park.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import us.puter.park.domain.entity.QShortenUrl;
import us.puter.park.domain.entity.QShortenUrlInfo;
import us.puter.park.service.dto.ShortenUrlDto;

import java.util.List;

@RequiredArgsConstructor
public class ShortenUrlRepositoryImpl implements ShortenUrlRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<ShortenUrlDto> findShortenUrlListTop5(Long date) {

		QShortenUrl shortenUrl = QShortenUrl.shortenUrl;
		QShortenUrlInfo shortenUrlInfo = QShortenUrlInfo.shortenUrlInfo;

		return jpaQueryFactory.select(
						Projections.fields(
								ShortenUrlDto.class,
								shortenUrl.shortenUrlUid,
								shortenUrl.shortenUri,
								shortenUrlInfo.redirectCount.sum().as(shortenUrlInfo.redirectCount)
						)
				)
				.from(shortenUrl)
				.innerJoin(shortenUrlInfo).on(shortenUrl.shortenUrlUid.eq(shortenUrlInfo.shortenUrl.shortenUrlUid))
				.where(shortenUrlInfo.regDate.goe(date))
				.groupBy(shortenUrl.shortenUrlUid)
				.orderBy(shortenUrlInfo.redirectCount.sum().desc())
				.limit(5L)
				.fetch();
	}

}
