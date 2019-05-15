define('orlov/aui', function () {
    return requirejs._defined['aui'] ? require('aui') : require('@atlassian/aui');
});