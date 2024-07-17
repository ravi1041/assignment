package com.artiscien.assignment.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artiscien.assignment.entity.ApplicationHealthInfo;
import com.artiscien.assignment.repository.ApplicationHealthInfoRepository;

@RestController
public class ApplicationHealthInfoController {

	@Autowired
	public ApplicationHealthInfoRepository applicationHealthInfoRepository;

	@GetMapping("/application-health")
	public ResponseEntity<?> getApplicationHealthInfo(@RequestParam("startDate") String startDateStr,
			@RequestParam("endDate") String endDateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime startDate = LocalDateTime.parse(startDateStr, formatter);
        LocalDateTime endDate = LocalDateTime.parse(endDateStr, formatter);
		List<ApplicationHealthInfo> records = applicationHealthInfoRepository.getApplicationHealthInfo(startDate,
				endDate);
		return ResponseEntity.ok(records);
	}

}
