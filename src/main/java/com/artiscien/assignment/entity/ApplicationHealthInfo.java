package com.artiscien.assignment.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "application_health_info")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationHealthInfo {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	@Column(name="cpu_usage")
    private double cpuUsage;
	@Column(name="disk_usage")
    private double diskUsage;
	@Column(name="open_files")
    private long openFiles;
	@Column(name="report_time")
    private LocalDateTime reportTime;
}
