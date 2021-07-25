package com.axonivy.addon.userstatus.common;

import java.util.Locale;

import ch.ivyteam.ivy.environment.Ivy;

public class StatusIcon {
	private String className = "";
	private String tabName = "";
	private String[] labels;
	
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getTabName() {
		return tabName;
	}
	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	public String[] getLabels() {
		return labels;
	}
	public void setLabels(String[] labels) {
		this.labels = labels;
	}
	
	public String getLabel() {
		String label = className;
		if(labels != null && labels.length > 0) {
			label = labels[0]; // Default is English
			if(labels.length > 1) { // Second is German
				String language = Ivy.session().getContentLocale().getLanguage();
				if(language.equals(new Locale("de").getLanguage())) {
					label = labels[1];
				}
			}
		}
		return label;
	}
}
