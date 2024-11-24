package com.unreallabss.carrent.domain.criteria;

import com.unreallabss.carrent.domain.user.Role;
import lombok.Data;

@Data
public class RoleCriteria extends Role {
	
	private String keyword;

	private Integer pageNumber;

	private Integer pageSize;

	private String sortProperty;

	private String sortDirection;

}
