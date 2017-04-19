package eu.europeana.api.client.datasets.commons.config;

import java.io.File;
import java.io.InputStream;

import eu.europeana.api.client.config.ClientConfiguration;
import eu.europeana.api.client.exception.TechnicalRuntimeException;

public class DatasetsConfiguration extends ClientConfiguration implements DatasetAccessConfiguration{

	protected static final String DATASETS_PROPERTIES_FILE = "/datasets.properties";
	private static DatasetsConfiguration singleton;

	/**
	 * Hide the default constructor
	 */
	protected DatasetsConfiguration() {
	}

	/**
	 * Accessor method for the singleton
	 * 
	 * @return
	 */
	public static synchronized DatasetsConfiguration getInstance() {
		if (singleton == null) {
			singleton = new DatasetsConfiguration();
			singleton.loadProperties();
		}
		return singleton;
	}

	/**
	 * Laizy loading of configuration properties
	 */
	public synchronized void loadProperties() {
		try {
			super.loadProperties();
			
			InputStream resourceAsStream = getClass().getResourceAsStream(
					DATASETS_PROPERTIES_FILE);
			if (resourceAsStream != null)
				getProperties().load(resourceAsStream);
			else
				throw new TechnicalRuntimeException(
						"No properties file found in classpath! "
								+ DATASETS_PROPERTIES_FILE);

		} catch (Exception e) {
			throw new TechnicalRuntimeException(
					"Cannot read configuration file: "
							+ DATASETS_PROPERTIES_FILE, e);
		}

	}

	public File getDatasetUrlsFile(String dataset) {
		return null;
	}
}
