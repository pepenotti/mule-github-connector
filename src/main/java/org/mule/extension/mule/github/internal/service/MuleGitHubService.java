package org.mule.extension.mule.github.internal.service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.mulesoft.extensions.request.builder.RequestBuilder;
import org.mule.extension.mule.github.internal.service.models.GitHubPatchPullRequestModel;
import org.mule.extension.mule.github.internal.service.models.getpull.response.GitHubGetPullResponseModel;
import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.http.api.client.HttpClient;

import java.lang.reflect.Type;
import java.util.List;

import static org.mule.runtime.api.metadata.MediaType.APPLICATION_JSON;

public class MuleGitHubService implements IMuleGitHubService {
    private HttpClient httpClient;
    private String gitHubPAT;
    private final String rootUrl = "https://api.github.com/repos";

    public MuleGitHubService(HttpClient httpClient, String gitHubPAT) {
        this.httpClient = httpClient;
        this.gitHubPAT = gitHubPAT;
    }

    public List<GitHubGetPullResponseModel> getPullRequests(String owner, String repoName, String state,
                                                            String head, String base, String sort,
                                                            String direction, int per_age, int page){
        String url = String.format("%s/%s/%s/pulls", this.rootUrl, owner, repoName);

        try {
            String result =  RequestBuilder.get(this.httpClient,url)
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
            return new Gson().fromJson(result, new TypeToken<List<GitHubGetPullResponseModel>>(){}.getType());
        } catch (Exception e){
            return null;
        }
    }

    public String patchPullRequest(String owner, String repoName, String pullNumber,
                                   String title, String body, String state,
                                   String base, boolean mantainerCanModify) {
        GitHubPatchPullRequestModel model = new GitHubPatchPullRequestModel(title, body, state, base, mantainerCanModify);
        String url = String.format("%s/%s/%s/pulls/%s", this.rootUrl, owner, repoName, pullNumber);

        try {
            return RequestBuilder.patch(this.httpClient, url)
                    .entity(model)
                    .header("Authorization","token " + gitHubPAT)
                    .accept(APPLICATION_JSON)
                    .contentType(APPLICATION_JSON)
                    .execute();
        } catch (Exception e){
            return "Error: failed to update pull requests (" + url + ") (" + e.getMessage() + ")";
        }
    }
}
