
package org.mule.extension.mule.github.internal.service.models.getpull.response;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Base {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("ref")
    @Expose
    private String ref;
    @SerializedName("sha")
    @Expose
    private String sha;
    @SerializedName("user")
    @Expose
    private User__2 user;
    @SerializedName("repo")
    @Expose
    private Repo__1 repo;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public User__2 getUser() {
        return user;
    }

    public void setUser(User__2 user) {
        this.user = user;
    }

    public Repo__1 getRepo() {
        return repo;
    }

    public void setRepo(Repo__1 repo) {
        this.repo = repo;
    }

}
