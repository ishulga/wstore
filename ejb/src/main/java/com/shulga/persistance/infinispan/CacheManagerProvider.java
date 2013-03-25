package com.shulga.persistance.infinispan;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.util.concurrent.IsolationLevel;

@ApplicationScoped
public class CacheManagerProvider {
    private DefaultCacheManager cacheManager;

    public DefaultCacheManager getCacheManager() {
        if (cacheManager == null) {
            GlobalConfiguration glob = new GlobalConfigurationBuilder().nonClusteredDefault().globalJmxStatistics().disable()
                    .build();
            Configuration loc = new ConfigurationBuilder().jmxStatistics().enable().clustering().cacheMode(CacheMode.LOCAL)
                    .locking().isolationLevel(IsolationLevel.REPEATABLE_READ).eviction().maxEntries(4)
                    .strategy(EvictionStrategy.LIRS).loaders().passivation(false).addFileCacheStore().purgeOnStartup(true)
                    .build(); // Builds the Configuration object
            cacheManager = new DefaultCacheManager(glob, loc, true);
        }
        return cacheManager;
    }

    @PreDestroy
    public void cleanUp() {
        cacheManager.stop();
        cacheManager = null;
    }
}
