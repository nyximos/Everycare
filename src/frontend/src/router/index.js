import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);
const routes = [
    {
        path: '*',
        redirect: '/'
    },
    {
        path: '/',
        name: 'Main',
        component: () => import('@/pages/Main.vue')
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/pages/Login.vue')
    }
];

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router;
