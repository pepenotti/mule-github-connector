package org.mule.extension.mule.github.internal.config;

import org.mule.connectors.commons.template.config.ConnectorConfig;
import org.mule.extension.mule.github.internal.operation.pull.GetPullRequestsOperation;
import org.mule.extension.mule.github.internal.operation.pull.UpdatePullRequestOperation;
import org.mule.runtime.extension.api.annotation.Operations;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(value = { GetPullRequestsOperation.class, UpdatePullRequestOperation.class })
public class MuleGitHubConfiguration implements ConnectorConfig {
}
