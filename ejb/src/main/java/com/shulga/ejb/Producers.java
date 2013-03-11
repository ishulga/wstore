package com.shulga.ejb;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import com.shulga.annotation.WstLogger;

public class Producers {

	@Produces
	@WstLogger
	Logger getLogger(InjectionPoint ip) {
		String category = ip.getMember().getDeclaringClass().getName();
		return Logger.getLogger(category);
	}
}
