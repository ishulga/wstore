package com.shulga.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.persistence.spi.PersistenceUnitTransactionType;

import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.ejb.packaging.PersistenceMetadata;
import org.hibernate.ejb.packaging.PersistenceXmlLoader;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class SchemeGenerator {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		if (args.length != 2 || !args[0].equals("-o")) {
			throw new IllegalArgumentException(
					"Wrong usage. Try adding -o parameter. Example: -o target/ddl/");
		}
		String folder = args[1];
		File parent = new File(folder);
		if (!parent.exists()) {
			parent.mkdirs();
		}
		Ejb3Configuration cfg = new Ejb3Configuration();
		Enumeration<URL> xmls = Thread.currentThread().getContextClassLoader()
				.getResources("META-INF/persistence.xml");
		URL url = xmls.nextElement();
		List<PersistenceMetadata> metadataFiles = PersistenceXmlLoader.deploy(
				url, Collections.EMPTY_MAP, cfg.getHibernateConfiguration()
						.getEntityResolver(),
				PersistenceUnitTransactionType.JTA);
		for (PersistenceMetadata persistenceMetadata : metadataFiles) {
			String unitName = persistenceMetadata.getName();
			persistenceMetadata.setJtaDatasource(null);
			cfg = new Ejb3Configuration();
			cfg.configure(persistenceMetadata, null);
			SchemaExport schemaExport = new SchemaExport(
					cfg.getHibernateConfiguration());
			schemaExport.setDelimiter(";");
			schemaExport.setFormat(true);
			String dropFile = folder + "drop_" + unitName + ".sql";
			System.err.println("Generating: " + dropFile);
			schemaExport.setOutputFile(dropFile);
			schemaExport.execute(true, false, true, false);
			if (!schemaExport.getExceptions().isEmpty()) {
				throw new RuntimeException("Failed to generate ddl.",
						(Throwable) schemaExport.getExceptions().get(0));
			}
			String createFile = folder + "create_" + unitName + ".sql";
			System.err.println("Generating: " + createFile);
			schemaExport.setOutputFile(createFile);
			schemaExport.execute(true, false, false, true);
			if (!schemaExport.getExceptions().isEmpty()) {
				throw new RuntimeException("Failed to generate drop ddl.",
						(Throwable) schemaExport.getExceptions().get(0));
			}
		}

	}

}
