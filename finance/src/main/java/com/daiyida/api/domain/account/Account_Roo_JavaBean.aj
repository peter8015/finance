// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.daiyida.api.domain.account;

import com.daiyida.api.domain.account.Account;
import com.daiyida.api.domain.role.Role;
import java.util.Date;
import java.util.Set;

privileged aspect Account_Roo_JavaBean {
    
    public String Account.getId() {
        return this.id;
    }
    
    public void Account.setId(String id) {
        this.id = id;
    }
    
    public String Account.getUserName() {
        return this.userName;
    }
    
    public void Account.setUserName(String userName) {
        this.userName = userName;
    }
    
    public String Account.getPhoneNum() {
        return this.phoneNum;
    }
    
    public void Account.setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    public String Account.getPassword() {
        return this.password;
    }
    
    public void Account.setPassword(String password) {
        this.password = password;
    }
    
    public int Account.getStatus() {
        return this.status;
    }
    
    public void Account.setStatus(int status) {
        this.status = status;
    }
    
    public String Account.getRealName() {
        return this.realName;
    }
    
    public void Account.setRealName(String realName) {
        this.realName = realName;
    }
    
    public Date Account.getCreateTime() {
        return this.createTime;
    }
    
    public void Account.setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Set<Role> Account.getRoles() {
        return this.roles;
    }
    
    public void Account.setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
}
