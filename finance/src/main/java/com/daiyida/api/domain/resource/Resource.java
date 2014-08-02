package com.daiyida.api.domain.resource;
import java.util.UUID;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "t_resource")
@RooJson
public class Resource {

    /**
     */
    @NotNull
    private String name;

    /**
     */
    @NotNull
    private String url;

	@Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();
}
