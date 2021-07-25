package com.axonivy.addon.userstatus.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.StringUtils;

import com.axonivy.addon.userstatus.common.StatusIcon;
import com.axonivy.addon.userstatus.enums.UserStatusExpiration;
import com.axonivy.addon.userstatus.persistence.domain.UserStatus;
import com.axonivy.addon.userstatus.service.UserStatusService;


@ManagedBean
@ViewScoped
public class UserStatusBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2950082024823314576L;

	private static final String DEFAULT_STATUS_ICON = "fa-smile-o";
	
	private UserStatus userStatus;
	
	private String statusIcon = DEFAULT_STATUS_ICON;
	private String statusText = "";
	private UserStatusExpiration statusExpiration;
	private Map<String, List<StatusIcon>> statusIconsByTab = new HashMap<>();

	private UserStatusExpiration[] listStatusExpirations = UserStatusExpiration.values();
	private Map<String, String> mapSuggestions = new HashMap<>(4);
	
	
	@PostConstruct
	public void init() {
		statusIconsByTab = UserStatusService.getStatusIconsByTab();
		initUserStatus();
		populateSuggestions();
	}

	private void initUserStatus() {
		userStatus = UserStatusService.getUserStatus();
		if(userStatus == null || userStatus.isStatusExpired()) {
			userStatus = new UserStatus();
			userStatus.setStatusIcon(DEFAULT_STATUS_ICON);
		}
		updateUserStatusPropertie();
	}
	
	private void populateSuggestions() {
		mapSuggestions.put("fa-tree", "On vacation");
		mapSuggestions.put("fa-home", "Working from home");
		mapSuggestions.put("fa-frown-o", "Out sick");
		mapSuggestions.put("fa-ban", "Focusing");
	}

	public void saveUserStatus() {
		UserStatusService.saveUserStatus(statusIcon, statusText, statusExpiration);
		initUserStatus();
	}

	public void clearUserStatus() {
		UserStatusService.deleteUserStatus();
		initUserStatus();
	}

	public void setSuggestion(Map.Entry<String, String> suggestion) {
		statusIcon = suggestion.getKey();
		statusText = suggestion.getValue();
	}

	public void updateUserStatusPropertie() {
		statusIcon = userStatus.getStatusIcon();
		statusText = userStatus.getStatusText();
		statusExpiration = userStatus.getStatusExpiration();
	}

	public String abbreviate(String value, int maxWidth) {
		return StringUtils.abbreviate(value, maxWidth);
	}


	public UserStatus getUserStatus() {
		return userStatus;
	}


	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}


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


	public Map<String, List<StatusIcon>> getStatusIconsByTab() {
		return statusIconsByTab;
	}


	public UserStatusExpiration[] getListStatusExpirations() {
		return listStatusExpirations;
	}


	public Map<String, String> getMapSuggestions() {
		return mapSuggestions;
	}

	
}
