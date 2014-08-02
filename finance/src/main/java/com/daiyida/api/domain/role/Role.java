package com.daiyida.api.domain.role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import com.daiyida.api.domain.resource.Resource;

import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "t_role")
@RooJson
public class Role {

    @NotNull
    private String name;

    private Integer sortNumber;

    /**
     */
    @ManyToMany(cascade = CascadeType.PERSIST, targetEntity = Resource.class)
    private Set<Resource> resources = new HashSet<Resource>();

    public static List<Role> findAllRoleses() {
        return entityManager().createQuery("SELECT o FROM Role o left join fetch o.resources", Role.class).getResultList();
    }

	@Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();
}
