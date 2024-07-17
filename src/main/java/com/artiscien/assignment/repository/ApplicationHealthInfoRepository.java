package com.artiscien.assignment.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.artiscien.assignment.entity.ApplicationHealthInfo;

@Repository
public interface ApplicationHealthInfoRepository extends JpaRepository<ApplicationHealthInfo, Integer> {

	@Query("FROM ApplicationHealthInfo c WHERE reportTime BETWEEN :fromTime AND :toTime")
	List<ApplicationHealthInfo> getApplicationHealthInfo(@Param("fromTime") LocalDateTime fromTime,
			@Param("toTime") LocalDateTime toTime);

}
