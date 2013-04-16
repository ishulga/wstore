package com.shulga.persistance.infinispan;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.infinispan.Cache;
import org.infinispan.manager.DefaultCacheManager;

import com.shulga.model.Cachable;
import com.shulga.model.HasId;
import com.shulga.persistance.annotations.DefaultCache;


public class GenericCache<T extends Serializable & HasId & Cachable> {

    @Inject
    @DefaultCache
    private DefaultCacheManager cacheManager;
    protected Cache<String, T> cache;
    private Class<T> type;

    public GenericCache(Class<T> type) {
        this.type=type;
    }
    
    @PostConstruct
    public void postConstruct(){
        cache = cacheManager.getCache(type.getName());
    }

    public Long create(T obj) {
    	obj.setId(Counter.next());
        cache.put(Counter.getStr(), obj);
        return Counter.get();
    }

    public void delete(String id) {
        cache.remove(id);
    }

    public void update(T obj) {
        cache.put(obj.getKey(), obj);
    }

    public T get(String id) {
        return cache.get(id);
    }

}
