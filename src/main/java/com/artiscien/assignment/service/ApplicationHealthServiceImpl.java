package com.artiscien.assignment.service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artiscien.assignment.entity.ApplicationHealthInfo;
import com.artiscien.assignment.repository.ApplicationHealthInfoRepository;

@Service
public class ApplicationHealthServiceImpl implements ApplicationHealthService {
	@Autowired
	private ApplicationHealthInfoRepository applicationHealthInfoRepository;

	public void captureAndSaveSystemHealth() {
		ApplicationHealthInfo systemHealth = ApplicationHealthInfo.builder()
				.reportTime(LocalDateTime.now())
				.cpuUsage(getCpuUsage())
				.diskUsage(getDiskUsage())
				.openFiles(getOpenFileCount()).build();
		applicationHealthInfoRepository.save(systemHealth);
	}

	private double getCpuUsage() {
		OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
		return osBean.getSystemLoadAverage();
	}

	private double getDiskUsage() {
		String path = null;
		String osName = System.getProperty("os.name");
		path = osName.contains("Win") ? "C:" : "/";
		File diskPartition = new File(path);
		double totalSpace = diskPartition.getTotalSpace();
		double freeSpace = diskPartition.getFreeSpace();
		return (totalSpace - freeSpace) / totalSpace * 100;
	}

	private long getOpenFileCount() {
		// OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
		// UnixOperatingSystemMXBean unixBean = (UnixOperatingSystemMXBean) osBean;
		// return unixBean.getOpenFileDescriptorCount();
		return 5;
	}

}
