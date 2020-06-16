package com.sachin.userservice;

import com.sachin.commons.util.EntityUtils;
import com.sachin.userservice.domain.Permission;
import com.sachin.userservice.model.PermissionModel;

public class PermissionCopyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PermissionModel model = new PermissionModel("user:read");
		Permission permission = new Permission();
		new EntityUtils<PermissionModel, Permission>().copyProperties(model, permission);
		System.out.println(permission.getAuthority());
	}

}
