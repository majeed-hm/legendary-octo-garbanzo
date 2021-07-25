package com.axonivy.addon.userstatus.enums;


public enum UserStatusExpiration {
	NEVER,
	IN_30_MINUTES,
	IN_1_HOUR,
	IN_4_HOURS,
	END_OF_THE_DAY,
	END_OF_THE_WEEK;
	;
	
	public String getMessageKey() {
		return "/enums/" + getClass().getName() + "/" + name();
	}
}
