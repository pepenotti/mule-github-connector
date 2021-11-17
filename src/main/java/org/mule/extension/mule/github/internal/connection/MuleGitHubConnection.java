package org.mule.extension.mule.github.internal.connection;

import org.mule.connectors.commons.template.connection.ConnectorConnection;
import org.mule.extension.mule.github.internal.errors.MuleGithubErrorType;
import org.mule.extension.mule.github.internal.service.MuleGitHubService;
import org.mule.runtime.extension.api.exception.ModuleException;
import org.mule.runtime.http.api.client.HttpClient;

/**
 * This class represents an extension connection just as example (there is no real connection with anything here c:).
 */
public class MuleGitHubConnection implements ConnectorConnection {
    private static final String NULL_CONNECTION_INSTANCE = "Null connection instance";
    private HttpClient httpClient;
    private MuleGitHubService muleGitHubService;

    public MuleGitHubConnection(HttpClient httpClient, String gitHubPAT){
        this.httpClient = httpClient;
        this.muleGitHubService = new MuleGitHubService(this, gitHubPAT);
    }

    public HttpClient getHttpClient() { return this.httpClient; }

    public MuleGitHubService getMuleGitHubService() { return this.muleGitHubService; }

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
