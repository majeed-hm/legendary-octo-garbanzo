package com.axonivy.addon.userstatus.persistence.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.axonivy.addon.userstatus.enums.UserStatusExpiration;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserStatus {
	protected String createdBy;
	
	protected LocalDateTime createdOn;

	protected String updatedBy;
	
	protected LocalDateTime updatedOn;
	
	private String statusIcon;
	
	private String statusText;

	private UserStatusExpiration statusExpiration;
	
	private String userName;


	public String getStatusIcon() {
		return statusIcon;
	}

	public void setStatusIcon(String statusIcon) {
		this.statusIcon = statusIcon;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public UserStatusExpiration getStatusExpiration() {
		return statusExpiration;
	}

	public void setStatusExpiration(UserStatusExpiration statusExpiration) {
		this.statusExpiration = statusExpiration;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	@JsonIgnore
	public boolean isStatusExpired() {
		boolean isStatusExpired = false;
		if(statusExpiration != null && statusExpiration != UserStatusExpiration.NEVER) {
			LocalDateTime now = LocalDateTime.now();
			LocalDate today = LocalDate.now();
			if(statusExpiration == UserStatusExpiration.IN_30_MINUTES) {
				isStatusExpired = now.isAfter(updatedOn.plusMinutes(30));
			}
			else if(statusExpiration == UserStatusExpiration.IN_1_HOUR) {
				isStatusExpired = now.isAfter(updatedOn.plusHours(1));
			}
			else if(statusExpiration == UserStatusExpiration.IN_4_HOURS) {
				isStatusExpired = now.isAfter(updatedOn.plusHours(4));
			}
			else if(statusExpiration == UserStatusExpiration.END_OF_THE_DAY) {
				isStatusExpired = now.isAfter(today.atTime(LocalTime.MAX));
			}
			else if(statusExpiration == UserStatusExpiration.END_OF_THE_WEEK) {
				isStatusExpired = now.isAfter(today.with(DayOfWeek.SUNDAY).atTime(LocalTime.MAX));
			}
		}
		return isStatusExpired;
	}

}

