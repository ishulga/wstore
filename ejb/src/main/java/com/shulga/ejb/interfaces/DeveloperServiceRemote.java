package com.shulga.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.shulga.common.ValidationException;
import com.shulga.model.Developer;

@Local
public interface DeveloperServiceRemote {
	
	Long create(Developer dev) throws ValidationException;

    void update(Developer dev);

    void delete(Long id);

    Developer get(Long id);

	List<Developer> getList(Developer qbe);
}