package org.mule.extension.mule.github.internal.service;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import org.mule.extension.mule.github.internal.connection.MuleGitHubConnection;
import com.mulesoft.extensions.request.builder.RequestBuilder;
import org.mule.extension.mule.github.internal.service.models.GitHubPatchPullRequestModel;
import org.mule.extension.mule.github.internal.service.models.getpull.response.GitHubGetPullResponseModel;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.mule.runtime.api.metadata.MediaType.APPLICATION_JSON;

public class MuleGitHubService {
    private MuleGitHubConnection connection;
    private String gitHubPAT;
    private final String rootUrl = "https://api.github.com/repos";

    public MuleGitHubService(MuleGitHubConnection connection, String gitHubPAT) {
        this.connection = connection;
        this.gitHubPAT = gitHubPAT;
    }

    public List<GitHubGetPullResponseModel> getPullRequests(String owner, String repoName, String state, String head, String base, String sort, String direction,
                                                      int per_age, int page) throws Exception {
        String url = String.format("%s/%s/%s/pulls", this.rootUrl, owner, repoName);

        try {
            String response = RequestBuilder.get(connection.getHttpClient(),url)
                    .queryParam("state",state)
                    .queryParam("head",head)
                    .queryParam("base",base)
                    .queryParam("sort",sort)
                    .queryParam("direction",direction)
                    .queryParam("per_age",per_age)
                    .queryParam("page",page)
                    .header("Authorization","token " + gitHubPAT)
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
                    .execute();

            return new GsonBuilder().create()
                    .fromJson(response, new TypeToken<List<GitHubGetPullResponseModel>>(){}.getType());

        } catch (Exception e){
            throw new Exception("Error: failed to retrieve the PRs (" + e.getMessage() + ")");
        }
    }

    public String patchPullRequest(String owner, String repoName, String pullNumber, String title, String body, String state, String base, boolean mantainerCanModify){
        GitHubPatchPullRequestModel model = new GitHubPatchPullRequestModel(title, body, state, base, mantainerCanModify);
        String url = String.format("%s/%s/%s/pulls/%s", this.rootUrl, owner, repoName, pullNumber);

        try {
            return RequestBuilder.patch(connection.getHttpClient(), url)
                    .entity(model)
                    .execute();
        } catch (Exception e){
            return "Error: failed to update pull requests (" + e.getMessage() + ")";
        }
    }
}
