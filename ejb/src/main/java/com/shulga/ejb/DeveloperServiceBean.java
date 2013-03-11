package com.shulga.ejb;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.shulga.annotation.WstLogger;
import com.shulga.common.Notifier;
import com.shulga.common.ValidationException;
import com.shulga.ejb.interfaces.DeveloperServiceRemote;
import com.shulga.model.Developer;
import com.shulga.persistance.DeveloperPL;

@Stateless
public class DeveloperServiceBean implements DeveloperServiceRemote {
	@Inject
	private DeveloperPL devPL;
	@Inject
	private Notifier notifier;
	@Inject
	@WstLogger
	private Logger logger;

	@Override
	public Long create(Developer dev) throws ValidationException {
		if (!getList(dev).isEmpty()) {
			throw new ValidationException("The name already exists");
		}
		notifier.sendMessage("Hello");
		Long id = devPL.create(dev);
		return id;
	}

	@Override
	public void update(Developer dev) {
		devPL.update(dev);
	}

	@Override
	public void delete(Long id) {
		devPL.delete(id);
	}

	@Override
	public Developer get(Long id) {
		Developer developer = devPL.get(id);
		return developer;
	}

	@Override
	public List<Developer> getList(Developer qbe) {
		List<Developer> devs = devPL.getListByQBE(qbe);
		return devs;
	}

}
