package org.mule.extension.mule.github.internal.operation.pull;

import org.mule.connectors.commons.template.operation.ConnectorOperations;
import org.mule.extension.mule.github.internal.config.MuleGitHubConfiguration;
import org.mule.extension.mule.github.internal.connection.MuleGitHubConnection;
import org.mule.extension.mule.github.internal.service.MuleGitHubService;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class GetPullRequestsOperation {

    @MediaType(MediaType.APPLICATION_JSON)
    public String getPullRequestOperation(
            @Connection MuleGitHubConnection connection,
            @DisplayName("Repository Owner") @Placement(order = 1) String owner,
            @DisplayName("Repository Name") @Placement(order = 2) String repoName,
            @DisplayName("Pull Request State") @Placement(order = 3) String state,
            @DisplayName("Branch Head") @Placement(order = 4) String head,
            @DisplayName("Base") @Placement(order = 5) String base,
            @DisplayName("Sort") @Placement(order = 6) String sort,
            @DisplayName("Direction") @Placement(order = 7) String direction,
            @DisplayName("Records Per Page") @Placement(order = 8) int per_age,
            @DisplayName("Page Number") @Placement(order = 9) int page
            ){
        return connection.getMuleGitHubService()
                .getPullRequests(owner, repoName, state, head, base, sort, direction, per_age, page);
    }
}
