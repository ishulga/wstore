package com.shulga.persistance.hibernate;

import java.util.Collection;
import java.util.List;

import com.shulga.model.Item;
import com.shulga.persistance.interfaces.ItemPL;

public class ItemDAO extends GenericPL<Item> implements ItemPL {

	public ItemDAO() {
		super(Item.class);
	}

	public Item getByLogin(String login) {
		return (Item) em
				.createQuery("select u from Item u where u.login =:login")
				.setParameter("login", login).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> getAll(Item item) {
		return getCriteria(item).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Item> getAll() {
		return getCriteria(new Item()).list();
	}

}
