import Vue from 'vue';
import VueRouter from 'vue-router';


import Carepeople from '../pages/Carepeople';
import Alarm from '../pages/Alarm';
import Profile2 from '../pages/Profile2';

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
        path: '/dashboard/careprofile',
        name: 'careprofile',
        component: () => import('@/pages/Careprofile.vue')
    },
    {
        path: '/dashboard/careprofile1',
        name: 'careprofile1',
        component: () => import('@/pages/Careprofile1.vue')
    },
    {
        path: '/dashboard/careprofile2',
        name: 'careprofile2',
        component: () => import('@/pages/Careprofile2.vue')
    },
    // {
    //     path: '/recruitions',
    //     name: 'Recruitions',
    //     component: () => import('@/pages/Recruitions.vue')
    // },
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/pages/Login.vue'),
        reEnter: (to, from, next) => {
            const token = localStorage.getItem('jwt-access-token');
            if (token !== null) next({ name: 'Main' });
            else next();
        }
    },
    {
        path: '/caresitters',
        name: 'caresitters',
        component: () => import('@/pages/Caresitters.vue')
    },
    {
        path: '/works',
        name: 'FindWork',
        component: () => import('@/pages/FindWork.vue'),
        reEnter: (to, from, next) => {
            const token = localStorage.getItem('jwt-access-token');
            if (token !== null) next({ name: 'Main' });
            else next();
        }
    },
    // {
    //     path: '/dashboard/caresitter',
    //     name: '케어시터 프로필생성',
    //     component: () => import('@/pages/Careprofile.vue')
    // },
    {
        path: '/carepeople',
        name: 'carepeople',
        component: Carepeople
    },
    {
        path: '/alarm',
        name: 'alarm',
        component: Alarm
    },
    {
        path: '/profile2',
        name: 'profile2',
        component: Profile2
    },
    {
        path: '/test',
        name: 'Test',
        component: () => import('@/pages/Test.vue')
    }
];

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router;
