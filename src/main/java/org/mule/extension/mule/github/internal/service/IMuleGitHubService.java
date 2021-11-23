package org.mule.extension.mule.github.internal.service;

import org.mule.extension.mule.github.internal.service.models.getpull.response.GitHubGetPullResponseModel;

import java.util.List;

public interface IMuleGitHubService {
    List<GitHubGetPullResponseModel> getPullRequests(String owner, String repoName, String state, String head, String base, String sort, String direction,
                                                     int per_age, int page);

    String patchPullRequest(String owner, String repoName, String pullNumber, String title, String body, String state, String base, boolean mantainerCanModify);
}
