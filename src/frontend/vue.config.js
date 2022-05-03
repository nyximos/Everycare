const fs = require('fs');

module.exports = {
    outputDir: '../main/resources/static',
    indexPath: '../static/index.html',
    devServer: {
        https: {
            key: fs.readFileSync('./localhost+2-key.pem'),
            cert: fs.readFileSync('./localhost+2.pem')
        },
        public: 'https://localhost:8087/',
        proxy: {
            '/api': {
                target: 'https://localhost:8086/',
                changeOrigin: true
            }
        }
    },
    transpileDependencies: ['vuetify']
};
