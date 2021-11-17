package org.mule.extension.mule.github;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.mule.functional.junit4.MuleArtifactFunctionalTestCase;
import org.junit.Test;

public class MuleGitHubOperationsTestCase extends MuleArtifactFunctionalTestCase {

  /**
   * Specifies the mule config xml with the flows that are going to be executed in the tests, this file lives in the test resources.
   */
  @Override
  protected String getConfigFile() {
    return "test-mule-config.xml";
  }

  @Test
  public void executeGetPullRequestsOperation() throws Exception {
    String payloadValue = ((String) flowRunner("getPullRequestsFlow").run()
            .getMessage()
            .getPayload()
            .getValue());
    assertThat(payloadValue, is("Repo: test - State: public - Head: head - Base: test - Sort: desc - Direction: test - PerPage: 100 - Page: 1"));
  }

  @Test
  public void executeUpdatePullRequestOperation() throws Exception {
    String payloadValue = ((String) flowRunner("updatePullRequestFlow")
            .run()
            .getMessage()
            .getPayload()
            .getValue());
    assertThat(payloadValue, is("Owner: doe - Repo: test - Pull Number: 123 - Title: title - Body: body - State: public - Base: test - Mantainer: true"));
  }
}
