package com.daiyida.api.domain.bankcard;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name = "t_bank_card")
@RooJson
public class BankCard {

	@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id=UUID.randomUUID().toString();
    /**
     */
    @NotNull
    private String realName;

    /**
     */
    @NotNull
    @Size(max = 45)
    private String bankCardNum;

    /**
     */
    @NotNull
    @Size(max = 45)
    private String idCardNum;

    /**
     */
    @NotNull
    private int status;

    /**
     */
    @NotNull
    private String UserId;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date bindTime;
    
	public static BankCard findBankCardByAccount(String accountId){
		String jpaQuery = "SELECT o FROM BankCard o WHERE o.UserId=:accountId";
		TypedQuery<BankCard>  query = entityManager().createQuery(jpaQuery, BankCard.class);
		List<BankCard> entities = query.setParameter("accountId", accountId).getResultList();
		return entities.isEmpty()?null:entities.get(0);
	}

	
}
