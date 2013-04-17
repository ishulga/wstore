package com.shulga.persistance.infinispan;

import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.inject.Alternative;

import com.shulga.model.User;
import com.shulga.persistance.interfaces.UserPL;

@Alternative
public class UserCache extends GenericCache<User> implements UserPL {

	public UserCache() {
		super(User.class);
	}

	public User getByLogin(String login) {
		for (User user : cache.values()) {
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public Collection<User> getAll(User user) {

//		// get the search manager from the cache:
//		SearchManager searchManager = org.infinispan.query.Search
//				.getSearchManager(cache);
//
//		// you could make the queries via Lucene APIs, or use some helpers:
//		QueryBuilder queryBuilder = searchManager.buildQueryBuilderForClass(
//				Book.class).get();
//
//		// the queryBuilder has a nice fluent API which guides you through all
//		// options.
//		// this has some knowledge about your object, for example which
//		// Analyzers
//		// need to be applied, but the output is a failry standard Lucene Query.
//		org.apache.lucene.search.Query luceneQuery = queryBuilder.phrase()
//				.onField("name").andField("lastname")
//				.sentence("")
//				.createQuery();
//
//		// the query API itself accepts any Lucene Query, and on top of that
//		// you can restrict the result to selected class types:
//		List<Object> query = searchManager.getQuery(luceneQuery, User.class).list();
//		Collection<User> list = new ArrayList<User>();
//		for (Object object : query) {
//			list.add((User)object);
//		}

		return new ArrayList();
	}

	@Override
	public Collection<User> getAll() {
		return cache.values();
	}

}
