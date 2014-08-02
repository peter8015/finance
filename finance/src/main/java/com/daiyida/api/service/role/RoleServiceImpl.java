package com.daiyida.api.service.role;

import java.util.List;

import com.daiyida.api.domain.role.Role;

public class RoleServiceImpl implements RoleService {

	


	public List<Role> findAllRoles() {
        return Role.findAllRoles();
    }
}
