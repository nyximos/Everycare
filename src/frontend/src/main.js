import Vue from 'vue';
import vueMoment from 'vue-moment'
import { Datetime } from 'vue-datetime'
import 'vue-datetime/dist/vue-datetime.css'
import App from '@/App.vue';
import vuetify from '@/plugins/vuetify';
import router from '@/router/index.js';
import store from '@/store/index.js';
import global from '@/global/index.js';
import Alert from '@/plugins/Alert.js';
import axios from 'axios';
import vueResource from 'vue-resource';
import jquery from 'jquery'


global.import();

Vue.config.productionTip = false

axios.defaults.baseURL = 'http://localhost:8086';
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
axios.defaults.headers.post['Content-Type'] = 'application/json';


Vue.prototype.$axios = axios;
Vue.use(vueResource);
Vue.use(Alert);
Vue.use(vueMoment);
Vue.use(Datetime);
Vue.config.productionTip = false;



new Vue({
    vuetify,
    router,
    store,
    jquery,
    render: h => h(App)
}).$mount('#app');
