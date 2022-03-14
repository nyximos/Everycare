import Vue from 'vue';
import App from '@/App.vue';
import vuetify from '@/plugins/vuetify';
import router from '@/router/index.js';
import store from '@/store/index.js';
import global from '@/global/index.js';
import Alert from '@/plugins/Alert.js';
global.import();

Vue.use(Alert);
Vue.config.productionTip = false;

new Vue({
    vuetify,
    router,
    store,
    render: h => h(App)
}).$mount('#app');
