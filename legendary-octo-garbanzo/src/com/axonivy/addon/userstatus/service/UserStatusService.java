package com.axonivy.addon.userstatus.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.axonivy.addon.userstatus.common.StatusIcon;
import com.axonivy.addon.userstatus.enums.UserStatusExpiration;
import com.axonivy.addon.userstatus.persistence.domain.UserStatus;

import ch.ivyteam.ivy.environment.Ivy;

public class UserStatusService {
	private static final String FONT_AWESOME_ICONS_CSV_FILE_NAME = "FontAwesomeIcons.csv";
	private static final String SEMICOLON_DELIMITER = ";";
	
	private static Map<String, List<StatusIcon>> mapStatusIconsByTab;


	public static Map<String, List<StatusIcon>> getStatusIconsByTab() {
		if(mapStatusIconsByTab == null) {
			mapStatusIconsByTab = getListStatusIcons().stream()
					.filter(s -> StringUtils.isNotBlank(s.getTabName()))
					.collect(Collectors.groupingBy(StatusIcon::getTabName));
		}
		return mapStatusIconsByTab;
	}

	private static List<StatusIcon> getListStatusIcons() {
		List<StatusIcon> records = new ArrayList<>();
		
		try (Scanner scanner = new Scanner(UserStatusService.class.getClassLoader().getResourceAsStream(FONT_AWESOME_ICONS_CSV_FILE_NAME));) {
			boolean isHeaderSkipped = false;
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				if(isHeaderSkipped) {
					records.add(getStatusIconFromLine(line));
				}
				isHeaderSkipped = true;
			}
		}
		return records;
	}
	
	private static StatusIcon getStatusIconFromLine(String line) {
		StatusIcon icon = new StatusIcon();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(SEMICOLON_DELIMITER);
			
			for(int i = 0; i < 3; i++) {
				if(i == 0 && rowScanner.hasNext()) {
					icon.setClassName(rowScanner.next());
				}
				if(i == 1 && rowScanner.hasNext()) {
					icon.setTabName(rowScanner.next());
				}
				if(i == 2 && rowScanner.hasNext()) {
					int beginIndex = icon.getClassName().length() + icon.getTabName().length() + 2;
					icon.setLabels(line.substring(beginIndex).split(SEMICOLON_DELIMITER));
				}
			}
			
		}
		return icon;
	}

	public static UserStatus getUserStatus() {
		UserStatus result = Ivy.repo().search(UserStatus.class)
				.textField("userName")
				.isEqualToIgnoringCase(Ivy.session().getSessionUserName())
				.execute()
				.getFirst();
		return result;
	}

	public static void saveUserStatus(String statusIcon, String statusText, UserStatusExpiration statusExpiration) {
		UserStatus userStatus = getUserStatus();
		String userName = Ivy.session().getSessionUserName();
		LocalDateTime now = LocalDateTime.now();
		if(userStatus == null) {
			userStatus = new UserStatus();
			userStatus.setCreatedBy(userName);
			userStatus.setCreatedOn(now);
			userStatus.setUserName(userName);
		}
		userStatus.setUpdatedBy(userName);
		userStatus.setUpdatedOn(now);
		userStatus.setStatusExpiration(statusExpiration);
		userStatus.setStatusIcon(statusIcon);
		userStatus.setStatusText(statusText);
		
		Ivy.repo().save(userStatus);
	}
	
	public static void deleteUserStatus() {
		UserStatus userStatus = getUserStatus();
		if(userStatus != null) {
			Ivy.repo().delete(userStatus);
		}
	}
	
}
