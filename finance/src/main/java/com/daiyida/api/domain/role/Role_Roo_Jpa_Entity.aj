// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.daiyida.api.domain.role;

import com.daiyida.api.domain.role.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

privileged aspect Role_Roo_Jpa_Entity {
    
    declare @type: Role: @Entity;
    
    declare @type: Role: @Table(name = "t_role");
    
    @Version
    @Column(name = "version")
    private Integer Role.version;
    
    public Integer Role.getVersion() {
        return this.version;
    }
    
    public void Role.setVersion(Integer version) {
        this.version = version;
    }
    
}