// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.daiyida.api.domain.bankcard;

import com.daiyida.api.domain.bankcard.BankCard;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Version;

privileged aspect BankCard_Roo_Jpa_Entity {
    
    declare @type: BankCard: @Entity;
    
    @Version
    @Column(name = "version")
    private Integer BankCard.version;
    
    public Integer BankCard.getVersion() {
        return this.version;
    }
    
    public void BankCard.setVersion(Integer version) {
        this.version = version;
    }
    
}
