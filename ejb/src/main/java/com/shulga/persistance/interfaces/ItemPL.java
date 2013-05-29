package com.shulga.persistance.interfaces;

import java.util.Collection;

import com.shulga.common.ServiceValidationException;
import com.shulga.model.Item;

public interface ItemPL {

    Long create(Item item) throws ServiceValidationException;

    void update(Item item);

    void delete(String id);

    Item get(String id);

    Item getByLogin(String login);
    
    Collection<Item> getAll(Item item);
    
    Collection<Item> getAll();
}
