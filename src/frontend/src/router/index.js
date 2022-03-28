import Vue from 'vue';
import VueRouter from 'vue-router';

import Caresitters from '../pages/Caresitters'
import Profile from '../pages/Profile'
import Careprofile from '../pages/Careprofile'
import Careprofile1 from '../pages/Careprofile1'
import Careprofile2 from '../pages/Careprofile2'
import Carepeople from '../pages/Carepeople'
import Alarm from '../pages/Alarm'
import Profile2 from '../pages/Profile2'

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
         path:'/caresitters',
         name:'caresitters',
         component:Caresitters
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
    },
    {
        path:'/careprofile1',
        name:'careprofile1',
        component:Careprofile1
    },
    {
        path:'/careprofile2',
        name:'careprofile2',
        component:Careprofile2
    },
    {
        path:'/alarm',
        name:'alarm',
        component:Alarm
    },
    {
        path:'/profile2',
        name:'profile2',
        component:Profile2
    }
];

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router;
