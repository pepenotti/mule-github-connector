package org.mule.extension.mule.github.internal.extension;

import org.mule.extension.mule.github.internal.config.MuleGitHubConfiguration;
import org.mule.extension.mule.github.internal.connection.providers.MuleGitHubConnectionProvider;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;


/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "mule-github")
@Extension(name = "Mule GitHub")
@Configurations(MuleGitHubConfiguration.class)
@ConnectionProviders(MuleGitHubConnectionProvider.class)
public class MuleGitHubExtension {

}
