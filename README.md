# Mule GitHub Extension [droppped]

This connector was done with the idea to learn how Mule Connectors work, and how to create one from scratch.

The connector has two operations to:

- Get pull requests from a repository
- Update a pull request from a repository

The idea was to create a flow that would retrevie all the pull request from a repository using GitHub REST API. Then it will update the title adding the text `[WIP]` to those PRs that were labeled as `wip`.

Add this dependency to your application pom.xml

```
<groupId>org.mule.github</groupId>
<artifactId>mule-github-connector</artifactId>
<version>0.1.10</version>
<classifier>mule-plugin</classifier>
```
