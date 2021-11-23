package org.mule.extension.mule.github.internal.operation.pull;

import com.google.gson.Gson;
import org.mule.extension.mule.github.internal.connection.MuleGitHubConnection;
import org.mule.extension.mule.github.internal.service.models.getpull.response.GitHubGetPullResponseModel;
import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.runtime.streaming.PagingProvider;

import java.util.Collections;
import java.util.List;

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
        return new Gson().toJson(connection.getMuleGitHubService()
                .getPullRequests(owner, repoName, state, head, base, sort, direction, per_age, page));
    }

    public PagingProvider<MuleGitHubConnection, GitHubGetPullResponseModel> getPagedPullRequestOperation(
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
        return new PagingProvider<MuleGitHubConnection, GitHubGetPullResponseModel>(){
            private int pageNumber = 1;

            @Override
            public List<GitHubGetPullResponseModel> getPage(MuleGitHubConnection connection) {
                List<GitHubGetPullResponseModel> prList = connection.getMuleGitHubService()
                        .getPullRequests(owner, repoName, state, head, base, sort, direction, per_age, pageNumber);

                pageNumber++;

                return prList == null ? Collections.emptyList() : prList;
            }

            @Override
            public java.util.Optional<Integer> getTotalResults(MuleGitHubConnection connection) {
                return java.util.Optional.empty();
            }

            @Override
            public void close(MuleGitHubConnection connection) throws MuleException {
                connection.disconnect();
            }
        };
    }
}
