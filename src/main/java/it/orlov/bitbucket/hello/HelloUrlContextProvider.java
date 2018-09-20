package it.orlov.bitbucket.hello;

import com.atlassian.bitbucket.nav.NavBuilder;
import com.atlassian.bitbucket.pull.PullRequest;
import com.atlassian.bitbucket.repository.Repository;
import com.atlassian.plugin.PluginParseException;
import com.atlassian.plugin.web.ContextProvider;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class HelloUrlContextProvider implements ContextProvider {

    private NavBuilder navBuilder;

    public HelloUrlContextProvider(NavBuilder navBuilder) {
        this.navBuilder = navBuilder;
    }

    @Override
    public void init(Map<String, String> params) throws PluginParseException {
    }

    @Override
    public Map<String, Object> getContextMap(Map<String, Object> context) {
        Repository repository = (Repository) context.get("repository");
        PullRequest pullRequest = (PullRequest) context.get("pullRequest");

        String commentsUrl = navBuilder.pluginServlets()
                .path("hello",
                        String.valueOf(repository.getId()),
                        String.valueOf(pullRequest.getId()))
                .buildRelNoContext();

        return ImmutableMap.of("helloUrl", commentsUrl);
    }
}