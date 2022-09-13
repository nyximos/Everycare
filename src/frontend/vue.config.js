const fs = require('fs');
const path = require('path');

module.exports = {
    outputDir: path.resolve(__dirname, './dist'),
    indexPath: './index.html',
    devServer: {
        overlay: false,
        https: true,
        key: fs.readFileSync('./localhost+2-key.pem'),
        cert: fs.readFileSync('./localhost+2.pem'),
        public: 'https://localhost:8087/',
        proxy: {
            '/api': {
                target: 'https://localhost:8086/',
                changeOrigin: true
            }
        }
    },
    transpileDependencies: ['vuetify'],
    lintOnSave: false
};
