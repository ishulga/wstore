package com.shulga.ejb;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.shulga.common.ServiceValidationException;
import com.shulga.ejb.interfaces.ItemServiceRemote;
import com.shulga.model.Item;
import com.shulga.persistance.hibernate.ItemDAO;

@Stateless
public class ItemServiceBean implements ItemServiceRemote {
    // TODO make it work with interface, using cdi
    @Inject
    private ItemDAO itemPL;

    @Override
    public Long create(Item item) throws ServiceValidationException {
        return itemPL.create(item);
    }

    @Override
    public void update(Item item) {
        itemPL.update(item);
    }

    @Override
    public void delete(Long id) {
        // TODO use a common style for all war clients
        itemPL.delete(id.toString());
    }

    @Override
    public Item get(Long id) {
        // TODO use a common style for all war clients
        return itemPL.get(id.toString());
    }
    
    @Override
    public Collection<Item> getAll(Item item) {
        return itemPL.getAll(item);
    }

    @Override
    public Item getByLogin(String login) {
        return itemPL.getByLogin(login);
    }

	@Override
	public Collection<Item> getAll() {
		return itemPL.getAll();
	}

}
