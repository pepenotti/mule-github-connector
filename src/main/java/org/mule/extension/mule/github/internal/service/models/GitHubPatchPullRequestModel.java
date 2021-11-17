package org.mule.extension.mule.github.internal.service.models;

public class GitHubPatchPullRequestModel {
    private String title;
    private String body;
    private String state;
    private String base;
    private boolean mantainerCanModify;

    public GitHubPatchPullRequestModel(String title, String body, String state, String base, boolean mantainerCanModify) {
        this.title = title;
        this.body = body;
        this.state = state;
        this.base = base;
        this.mantainerCanModify = mantainerCanModify;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getState() {
        return state;
    }

    public String getBase() {
        return base;
    }

    public boolean isMantainerCanModify() {
        return mantainerCanModify;
    }
}
