package com.daiyida.api.domain.customer;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(identifierField = "uuid", identifierType = String.class, table = "t_customer")
@RooJson
public class Customer {
	    @Id
	    //@GeneratedValue(strategy=GenerationType.AUTO, generator = "uuid")	
	    @Column(name = "id")
	    private String id=UUID.randomUUID().toString();
	 
	
	    
   
	

	/**
     */
    @NotNull
    private String userId;

    /**
     */
    @NotNull
    private String idCard;

    /**
     */
    @NotNull
    @Size(max = 500)
    private String name;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date birthday;

    /**
     */
    private int sex;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createTime;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date modifyTime;
}
