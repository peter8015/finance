// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.daiyida.api.domain.role;

import com.daiyida.api.domain.resource.Resource;
import com.daiyida.api.domain.role.Role;
import java.util.Set;

privileged aspect Role_Roo_JavaBean {
    
    public String Role.getName() {
        return this.name;
    }
    
    public void Role.setName(String name) {
        this.name = name;
    }
    
    public Integer Role.getSortNumber() {
        return this.sortNumber;
    }
    
    public void Role.setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }
    
    public Set<Resource> Role.getResources() {
        return this.resources;
    }
    
    public void Role.setResources(Set<Resource> resources) {
        this.resources = resources;
    }
    
    public String Role.getId() {
        return this.id;
    }
    
    public void Role.setId(String id) {
        this.id = id;
    }
    
}