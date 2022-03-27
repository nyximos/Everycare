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
        path: '/caresitter',
        name: 'FindCareSitter',
        component: () => import('@/pages/FindCareSitter.vue'),
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
