package org.mule.extension.mule.github.internal.operation.pull;

import org.mule.extension.mule.github.internal.connection.MuleGitHubConnection;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

public class UpdatePullRequestOperation {

    @MediaType(MediaType.APPLICATION_JSON)
    public String UpdatePullRequestOperation(
            @Connection MuleGitHubConnection connection,
            @DisplayName("Repository Owner") @Placement(order = 1) String owner,
            @DisplayName("Repository Name") @Placement(order = 2) String repoName,
            @DisplayName("Pull Request Number") @Placement(order = 3) String pullNumber,
            @DisplayName("Title") @Placement(order = 4) String title,
            @DisplayName("Body") @Placement(order = 5) String body,
            @DisplayName("State") @Placement(order = 6) String state,
            @DisplayName("Base") @Placement(order = 7) String base,
            @DisplayName("Mantainer Can Modify") @Placement(order = 8) boolean mantainerCanModify
    ){
        return connection.getMuleGitHubService()
                .patchPullRequest(owner, repoName, pullNumber, title, body, state, base, mantainerCanModify);
    }
}
