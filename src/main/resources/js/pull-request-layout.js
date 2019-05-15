define('bitbucket-plugin-layout-pull-request', ['require'], function (require) {
    try {
        return require('bitbucket/internal/layout/pull-request');
    } catch (ignore) {
        // bitbucket 6
        return require('bitbucket/internal/layout/pull-request/pull-request');
    }
});