package com.shulga.ejb.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.shulga.common.CustomException;
import com.shulga.model.Developer;

@Local
public interface DeveloperEJBRemote {
	
	Long create(Developer dev) throws CustomException;

    void update(Developer dev);

    void delete(Long id);

    Developer get(Long id);

	List<Developer> getList(Developer qbe);
}
