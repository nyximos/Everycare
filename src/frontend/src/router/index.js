import Vue from 'vue';
import VueRouter from 'vue-router';

import Match from '../pages/Match'
import Profile from '../pages/Profile'
import Careprofile from '../pages/Careprofile'
import Carepeople from '../pages/Carepeople'

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
    },
    {
        path:'/careprofile',
        name:'careprofile',
        component:Careprofile
    },
    {
        path:'/carepeople',
        name:'carepeople',
        component:Carepeople
    }
];

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router;
