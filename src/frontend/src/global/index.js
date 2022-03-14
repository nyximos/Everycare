import Vue from 'vue';
const requireComponent = require.context('.', false, /[\w-]+\.vue$/);

const global = {
    import() {
        requireComponent.keys().forEach(fileName => {
            const componentConfig = requireComponent(fileName);
            console.log('fileName', fileName);
            const componentName = fileName.replace(/^\.\//, '').replace(/\.\w+$/, '');
            console.log(componentName);
            Vue.component(componentName, componentConfig.default || componentConfig);
        });
    }
};

export default global;
