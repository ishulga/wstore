package com.shulga.persistance.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.shulga.model.HasId;

public class GenericPL<T extends Serializable & HasId> {
	
	@PersistenceContext(unitName="primary")
	protected EntityManager em;
	private Class<T> type;

	public GenericPL(Class<T> type) {
		this.type = type;
	}
	public GenericPL(){}
	
	public Long create(T obj) {
		em.persist(obj);
		return obj.getId();
	}
	
	public void delete(String id){
	    //TODO check if converting type is necessary
		em.remove(em.getReference(type, Long.valueOf(id)));
		em.flush();
	}
	
	public void update(T obj){
		em.merge(obj);
	}
	
	public T get(String id){
		return em.find(type, Long.valueOf(id));
	}
	
	@SuppressWarnings("unchecked")
	public List<T> get(T qbe)
    {
        return getCriteria(qbe).list();
    }
	
	public void flushAndClear(){
		em.flush();
		em.clear();
	}
	
	public Criteria getCriteria(T qbe, String...excludeProperties ){
		Example example = Example.create(qbe).excludeZeroes();
		for (String prop : excludeProperties) {
			example.excludeProperty(prop);
		}
		Session session = (Session) em.getDelegate();
		return session.createCriteria(qbe.getClass()).add(example).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	}
	
	
}
