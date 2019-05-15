define('orlov/hello', ['orlov/aui', 'bitbucket-plugin-layout-pull-request'], function (AJS, handlers) {

    'use strict';

    return {
        onReady: function () {
            handlers.registerHandler('hello-tab', /^[^?#]*servlet\/hello/, {
                load: function (el) {
                    AJS.messages.info(el, {body: 'Hello!', closeable: false});
                },
                unload: function (el) {
                    AJS.$(el).empty();
                }
            });
        }
    };
});

AJS.$(document).ready(require('orlov/hello').onReady);