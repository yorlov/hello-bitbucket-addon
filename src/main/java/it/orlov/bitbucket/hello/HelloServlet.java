package it.orlov.bitbucket.hello;

import com.atlassian.bitbucket.pull.PullRequest;
import com.atlassian.bitbucket.pull.PullRequestService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.soy.renderer.SoyTemplateRenderer;
import com.google.common.collect.ImmutableMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static javax.servlet.http.HttpServletResponse.*;

public class HelloServlet extends HttpServlet {

    private SoyTemplateRenderer soyTemplateRenderer;
    private PullRequestService pullRequestService;

    public HelloServlet(@ComponentImport SoyTemplateRenderer soyTemplateRenderer, PullRequestService pullRequestService) {
        this.soyTemplateRenderer = soyTemplateRenderer;
        this.pullRequestService = pullRequestService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] params = request.getPathInfo().substring(1).split("/");
        int repositoryId = Integer.parseInt(params[0]);
        long pullRequestId = Long.parseLong(params[1]);

        PullRequest pullRequest = pullRequestService.getById(repositoryId, pullRequestId);

        if (pullRequest == null) {
            response.sendError(SC_NOT_FOUND);
            return;
        }

        response.setContentType("text/html;charset=UTF-8");

        soyTemplateRenderer.render(
                response.getWriter(),
                "it.orlov.bitbucket.hello:hello-templates",
                "hello.page",
                ImmutableMap.of("pullRequest", pullRequest)
        );
    }
}