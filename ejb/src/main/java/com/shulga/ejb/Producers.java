package com.shulga.ejb;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.infinispan.manager.DefaultCacheManager;

import com.shulga.annotation.WstLogger;
import com.shulga.persistance.annotations.DefaultCache;
import com.shulga.persistance.infinispan.CacheManagerProvider;

public class Producers {

    @Inject
    CacheManagerProvider cacheProvider;
    

    @Produces
    @WstLogger
    Logger getLogger(InjectionPoint ip) {
        String category = ip.getMember().getDeclaringClass().getName();
        return Logger.getLogger(category);
    }

    @Produces
    @DefaultCache
    DefaultCacheManager getCacheManager() {
        return cacheProvider.getCacheManager();
    }
    

}
