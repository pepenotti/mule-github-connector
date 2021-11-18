package org.mule.extension.mule.github.internal.connection;

import org.mule.connectors.commons.template.connection.ConnectorConnection;
import org.mule.extension.mule.github.internal.errors.MuleGithubErrorType;
import org.mule.extension.mule.github.internal.service.IMuleGitHubService;
import org.mule.extension.mule.github.internal.service.MuleGitHubService;
import org.mule.runtime.extension.api.exception.ModuleException;
import org.mule.runtime.http.api.client.HttpClient;

/**
 * This class represents an extension connection just as example (there is no real connection with anything here c:).
 */
public class MuleGitHubConnection implements ConnectorConnection {
    private static final String NULL_CONNECTION_INSTANCE = "Null connection instance";
    private HttpClient httpClient;
    private IMuleGitHubService muleGitHubService;

    public MuleGitHubConnection(HttpClient httpClient, IMuleGitHubService muleGitHubService){
        this.httpClient = httpClient;
        this.muleGitHubService = muleGitHubService;
    }

    public HttpClient getHttpClient() { return this.httpClient; }

    public IMuleGitHubService getMuleGitHubService() { return this.muleGitHubService; }

    @Override
    public void disconnect() {
        this.httpClient = null;
    }

    @Override
    public void validate() {
        if(getHttpClient() == null) {
            throw new ModuleException(NULL_CONNECTION_INSTANCE, MuleGithubErrorType.INVALID_CONNECTION);
        }
    }
}
