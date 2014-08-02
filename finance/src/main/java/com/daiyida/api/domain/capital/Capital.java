package com.daiyida.api.domain.capital;
import java.util.List;
import java.util.UUID;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@RooJavaBean
@RooToString
@RooJpaActiveRecord
@Table(name = "t_capital")
@RooJson
public class Capital {
	
	    @Id
	   // @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "id")
	    private String id=UUID.randomUUID().toString();

    /**
     */
    @NotNull
    private String password;

    /**
     */
    @NotNull
    private String userId;

    /**
     */
    @NotNull
    private Double fund;
	
    /**
     */
    @NotNull
    private int status;
	
	public static List<Capital> findCapitalByUserId(String id) {
		String sql="SELECT o FROM CapitalAccount o where o.userId=:userId";
        return entityManager().createQuery(sql, Capital.class).setParameter("userId", id).getResultList();
    }
	
	
}
