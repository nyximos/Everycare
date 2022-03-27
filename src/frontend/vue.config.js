module.exports = {
    outputDir: '../main/resources/static',
    indexPath: '../static/index.html',
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8086',
                changeOrigin: true
            }
        }
    },
    transpileDependencies: ['vuetify']
};
