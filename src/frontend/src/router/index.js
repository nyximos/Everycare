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
        component: () => import('@/pages/Login.vue'),
        reEnter: (to, from, next) => {
            const token = localStorage.getItem('jwt-access-token');
            if (token !== null) next({ name: 'Main' });
            else next();
        }
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
    
    {
        path: '/caresitters',
        name: 'caresitters',
        component: () => import('@/pages/Caresitters.vue')
    },
    
    {
        path: '/dashboard/caresitter',
        name: 'careprofile',
        component: () => import('@/pages/Careprofile.vue')
    },
    {
        path: '/dashboard/caresitter1',
        name: 'careprofile1',
        component: () => import('@/pages/Careprofile1.vue')
    },
    {
        path: '/dashboard/caresitter2',
        name: 'careprofile2',
        component: () => import('@/pages/Careprofile2.vue')
    },
    {
        path: '/carepeople',
        name: 'carepeople',
        component:() => import('@/pages/Carepeople.vue')
    },
    {
        path: '/profile2',
        name: 'profile2',
        component:() => import('@/pages/Profile2.vue')
    },
    {
        path: '/test',
        name: 'Test',
        component: () => import('@/pages/Test.vue')
    },
    {
        path: '/registration',
        name: 'registration',
        component: () => import('@/pages/Registration.vue')
    },
    {
        path: '/picture',
        name: 'picture',
        component: () => import('@/pages/Picture.vue')
    },
];

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router;
