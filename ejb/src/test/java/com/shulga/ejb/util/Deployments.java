/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other
 * contributors as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a full listing of
 * individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.shulga.ejb.util;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;

public final class Deployments {

	private Deployments() {
	}

	public static WebArchive baseDeployment() {
	   MavenDependencyResolver resolver = DependencyResolvers.use(
			   MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
	   
      return ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "com.shulga")
              .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
              .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
              .addAsWebInfResource("test-ds.xml", "test-ds.xml").addAsLibraries(
            		  resolver.artifact("org.infinispan:infinispan-core")
            		  .artifact("org.hibernate:hibernate-search-infinispan")
            		  .resolveAsFiles());
		// .addAsLibraries(
		// DependencyResolvers.use(MavenDependencyResolver.class)
		// .loadReposFromPom("pom.xml")
		// .artifact("javax.cache:cache-api")
		// .artifact("org.infinispan:infinispan-cdi")
		// .resolveAs(GenericArchive.class));
   }
}
