// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.daiyida.api.domain.capital;

import com.daiyida.api.domain.capital.Capital;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Capital_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Capital.entityManager;
    
    public static final List<String> Capital.fieldNames4OrderClauseFilter = java.util.Arrays.asList("id", "password", "userId", "fund", "status");
    
    public static final EntityManager Capital.entityManager() {
        EntityManager em = new Capital().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Capital.countCapitals() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Capital o", Long.class).getSingleResult();
    }
    
    public static List<Capital> Capital.findAllCapitals() {
        return entityManager().createQuery("SELECT o FROM Capital o", Capital.class).getResultList();
    }
    
    public static List<Capital> Capital.findAllCapitals(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Capital o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Capital.class).getResultList();
    }
    
    public static Capital Capital.findCapital(String id) {
        if (id == null || id.length() == 0) return null;
        return entityManager().find(Capital.class, id);
    }
    
    public static List<Capital> Capital.findCapitalEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Capital o", Capital.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Capital> Capital.findCapitalEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Capital o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Capital.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Capital.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Capital.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Capital attached = Capital.findCapital(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Capital.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Capital.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Capital Capital.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Capital merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
