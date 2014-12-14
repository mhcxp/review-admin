package io.github.jzdeveloper.review.admin.config;

import static com.google.common.base.Preconditions.checkArgument;
import io.dropwizard.Bundle;
import io.dropwizard.servlets.assets.AssetServlet;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;

/**
 * 代码主要来源于官方的assets项目
 */
public class PublicResourceBundle implements Bundle {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PublicResourceBundle.class);

	private static final String DEFAULT_ASSETS_NAME = "public";
	private static final String DEFAULT_INDEX_FILE = "app/index.html";
	private static final String DEFAULT_PATH = "/public";

	private final String resourcePath;
	private final String uriPath;
	private final String indexFile;
	private final String assetsName;

	public PublicResourceBundle() {
		this(DEFAULT_PATH, DEFAULT_PATH, DEFAULT_INDEX_FILE,
				DEFAULT_ASSETS_NAME);
	}

	public PublicResourceBundle(String path) {
		this(path, path, DEFAULT_INDEX_FILE, DEFAULT_ASSETS_NAME);
	}

	public PublicResourceBundle(String resourcePath, String uriPath) {
		this(resourcePath, uriPath, DEFAULT_INDEX_FILE, DEFAULT_ASSETS_NAME);
	}

	public PublicResourceBundle(String resourcePath, String uriPath,
			String indexFile) {
		this(resourcePath, uriPath, indexFile, DEFAULT_ASSETS_NAME);
	}

	public PublicResourceBundle(String resourcePath, String uriPath,
			String indexFile, String assetsName) {
		checkArgument(resourcePath.startsWith("/"),
				"%s is not an absolute path", resourcePath);
		checkArgument(!"/".equals(resourcePath), "%s is the classpath root",
				resourcePath);
		this.resourcePath = resourcePath.endsWith("/") ? resourcePath
				: (resourcePath + '/');
		this.uriPath = uriPath.endsWith("/") ? uriPath : (uriPath + '/');
		this.indexFile = indexFile;
		this.assetsName = assetsName;
	}

	@Override
	public void initialize(Bootstrap<?> bootstrap) {
		// nothing doing
	}

	@Override
	public void run(Environment environment) {
		LOGGER.info("Registering AssetBundle with name: {} for path {}",
				assetsName, uriPath + '*');
		environment.servlets().addServlet(assetsName, createServlet())
				.addMapping(uriPath + '*');
	}

	private AssetServlet createServlet() {
		return new AssetServlet(resourcePath, uriPath, indexFile,
				Charsets.UTF_8);
	}

}