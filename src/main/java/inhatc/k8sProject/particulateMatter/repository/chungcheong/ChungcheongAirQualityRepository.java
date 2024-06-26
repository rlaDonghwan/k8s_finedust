package inhatc.k8sProject.particulateMatter.repository.chungcheong;

import inhatc.k8sProject.particulateMatter.domain.chungcheong.ChungcheongAirQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChungcheongAirQualityRepository extends JpaRepository<ChungcheongAirQuality, Long> {
    /**
     * 주어진 측정소 이름(stationName)을 기준으로 최신 데이터 시간(dataTime)에 따른
     * 가장 최근의 충청도 대기질 데이터를 찾습니다.
     *
     * @param stationName 측정소 이름
     * @return Optional로 감싸진 충청도 대기질 데이터 (해당 측정소의 가장 최근 데이터)
     */
    Optional<ChungcheongAirQuality> findFirstByStationNameOrderByDataTimeDesc(String stationName);
}
