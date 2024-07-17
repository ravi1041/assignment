"# assignment" 

# Appication Health Metrics Spring Boot Application

This Spring Boot application monitors system health metrics every 10 minutes. It utilizes Spring Security for access and refresh keys and employs Spring AOP for logging and metrics.

## Features

- Monitors system health metrics at regular intervals (every 5 minutes).
- Uses Spring Security for secure access and refresh tokens.
- Implements Spring AOP for logging and capturing metrics.


## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- MySQL 
	For Databse/Schema and Credentials info please refer application.properties
	
	  Table creation Query :
	  
	  CREATE TABLE  `assignment`.`application_health_info` (
	  `id` int(10) unsigned NOT NULL DEFAULT '0',
	  `cpu_usage` double DEFAULT NULL,
	  `disk_usage` double DEFAULT NULL,
	  `open_files` bigint DEFAULT NULL,
	  `report_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	  PRIMARY KEY (`id`)
	  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
	

### Installation

1. Clone the repository:

   git clone https://github.com/ravi1041/assignment.git
   
### Build Steps
- [Build the application] mvn clean install
- [Run the application] mvn spring-boot:run


### APIS

#### authenticate API
	api : http://localhost:8081/authenticate
	method : POST
	Request body : 
	{
		"username":"admin",
		"password":"admin"
	}
	
	Response :
	{
		"accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyMTIzNzIyMiwiZXhwIjoxNzIxMzIzNjIyfQ.P4SoMmrgDnb_DX2tRMHCi9HX2p7cA_youkEE4xn_eq0",
		"refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyMTIzNzIyMiwiZXhwIjoxNzIxODQyMDIyfQ.cdbv5vcVPPtZJjTvpDporC1Y_k8GerfDAidSJMoENqI"
	}
	
#### refresh-token API
	api : http://localhost:8081/refresh-token
	method : POST
	Request body : 
	{
		"refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyMTIzOTc0NiwiZXhwIjoxNzIxODQ0NTQ2fQ.ViN6nX-us8rwpMFIYsgQyxcKShH3CnrzOdzlR1HxEH0"
	}
	
	Response :
	{
		"accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyMTIzNzIyMiwiZXhwIjoxNzIxMzIzNjIyfQ.P4SoMmrgDnb_DX2tRMHCi9HX2p7cA_youkEE4xn_eq0",
		"refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyMTIzNzIyMiwiZXhwIjoxNzIxODQyMDIyfQ.cdbv5vcVPPtZJjTvpDporC1Y_k8GerfDAidSJMoENqI"
	}
	
#### application-health API
	api : http://localhost:8081/application-health
	method : GET
	Request Header Param:
		Authorization : Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyMTIzOTg5NSwiZXhwIjoxNzIxMzI2Mjk1fQ.bX0sZWuLTev4bYziV0e4JYxwkKuMSJd72fkbgCqJfEw
	Request param : 
		startDate : 2024-07-17T12:00:00
		endDate : 2024-07-17T12:30:00
	
	
	Response :
	[
		{
			"id": 908,
			"cpuUsage": -1.0,
			"diskUsage": 45.137159667835974,
			"openFiles": 5,
			"reportTime": "2024-07-17T12:00:09"
		},
		{
			"id": 909,
			"cpuUsage": -1.0,
			"diskUsage": 45.1374236237245,
			"openFiles": 5,
			"reportTime": "2024-07-17T12:01:09"
		}
	]


