const requireModule = require.context('.', false, /\.js$/);
const modules = {};

requireModule.keys().forEach(fileName => {
    if (fileName === './index.js') return;
    const moduleName = fileName.replace(/(\.\/|\.js)/g, '');
    module[moduleName] = requireModule(fileName).default;
});

// eslint-disable-next-line
const state = {
    auth: {
        // 상태 'Auth'
        token: null,
        userId: null
    }
};

export default modules;
