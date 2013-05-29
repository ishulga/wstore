package com.shulga.ejb.interfaces;

import java.util.Collection;

import javax.ejb.Local;

import com.shulga.common.ServiceValidationException;
import com.shulga.model.Item;

@Local
public interface ItemServiceRemote {
	
	Long create(Item item) throws ServiceValidationException;

    void update(Item item);

    void delete(Long id);

    Item get(Long id);

	Collection<Item> getAll(Item item);

	Collection<Item> getAll();

    Item getByLogin(String login);
}
