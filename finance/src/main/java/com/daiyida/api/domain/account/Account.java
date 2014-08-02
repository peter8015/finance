package com.daiyida.api.domain.account;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import com.daiyida.api.domain.role.Role;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name = "t_account")
@RooJson
public class Account {
	
	
	@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id=UUID.randomUUID().toString();
	
	
	 /**
     */
	 @NotNull
	    @Size(max = 45)
	    private String userName;

 

    /**
     */
    @NotNull
    @Size(max = 45)
    private String phoneNum;

   
    /**
     */
    @NotNull
    private String password;

    /**
     */
    @NotNull
    private int status;

  

    /**
     */
    @NotNull
    private String realName;
    
    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createTime;

    /**
     */
    @ManyToMany(cascade = CascadeType.PERSIST, targetEntity = Role.class)
    private Set<Role> roles = new HashSet<Role>();

    public static List<Account> findUsersByLoginName(String loginName) {
        //String jpaQuery = "SELECT o FROM Users o where o.loginName ='"+loginName+"'";
        Query query = entityManager().createQuery("select distinct(u) from Account u left join fetch u.roles where u.userName = :userName");
        return query.setParameter("userName", loginName).getResultList();
    }
    /*
     *验证用户名
     */
    public static List<Account>  findUserByName(String name) {
    	String  queryString="SELECT o FROM Account o where o.userName=:userName or o.phoneNum=:userName";
    	return entityManager().createQuery(queryString,  Account.class).setParameter("userName", name).getResultList();     		
    }
    /*
     * 登陆
     */
    public static List<Account>  login(String name,String pwd) {
    	String  queryString="SELECT o FROM Account o where (o.userName=:userName or o.phoneNum=:userName) and o.password=:pwd";
        return  entityManager().createQuery(queryString, Account.class).setParameter("userName", name).setParameter("pwd",pwd).getResultList();
    }
    /*
     * 验证用户是否存在
     */
    public static long  checkUser(String name) {
    	String  queryString="SELECT COUNT(o) FROM Account o where o.userName=:userName";
    	return entityManager().createQuery(queryString,  Long.class).setParameter("userName", name).getSingleResult();     		
    }
    
    /*
     * 验证手机号是否存在
     */
    public static long  checkPhone(String name) {
    	String  queryString="SELECT COUNT(o) FROM Account o where o.phoneNum=:phone";
    	return entityManager().createQuery(queryString,  Long.class).setParameter("phone", name).getSingleResult();     		
    }
    
    /*
     * 验证用户名密码是否一致
     */
    public static long  checkUserPhone(String name,String phone) {
    	String  queryString="SELECT COUNT(o) FROM Account o where o.phoneNum=:phone and  o.userName=:userName";
    	return entityManager().createQuery(queryString,  Long.class).setParameter("phone", name).setParameter("userName",name).getSingleResult();     		
    }

    
    /*
     *根据用户名查找Account
     */
    public static List<Account>  findAccountByName(String name) {
    	String  queryString="SELECT o FROM Account o where o.userName=:userName ";
    	return entityManager().createQuery(queryString,  Account.class).setParameter("userName", name).getResultList();     		
    }

    
    public static Account findAccountByName_Phone(String userName,String phoneNum){
    	String jpaQuery = "SELECT o FROM Account o where o.userName=:userName AND o.phoneNum=:phoneNum";
    	List<Account> entities = entityManager().createQuery(jpaQuery, Account.class).setParameter("userName", userName).setParameter("phoneNum", phoneNum).getResultList();
    	return entities.isEmpty()?null:entities.get(0);   
    }


	
}
