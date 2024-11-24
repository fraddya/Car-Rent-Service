package com.unreallabss.carrent.enums;

public enum AuthorityType {
	
	ADMIN("Admin", "A"),
	CLIENT("Client", "C"),
	SUPER_ADMIN("Super Admin", "SA");

	private String label;
	private String value;

	private AuthorityType(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public static AuthorityType getEnum(String value) {
		for (AuthorityType item : AuthorityType.values()) {
			if (item.getValue().equals(value)) {
				return item;
			}
		}
		return null;
	}

}
