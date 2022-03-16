import Vue from 'vue';
import VueRouter from 'vue-router';

import Match from '../pages/Match'
import Profile from '../pages/Profile'

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
         path:'/match',
         name:'match',
         component:Match
    },
    {
        path:'/profile',
        name:'profile',
        component:Profile
    }
];

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router;
