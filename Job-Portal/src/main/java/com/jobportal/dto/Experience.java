package com.jobportal.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
	private String title;
	private String company;
	private String location;
	private String description;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Boolean working;
}
