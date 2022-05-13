import Vue from 'vue';
import VueRouter from 'vue-router';


import Carepeople from '../pages/Carepeople';
import Alarm from '../pages/Alarm';

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
        path: '/adminpage',
        name: 'adminpage',
        component: () => import('@/pages/Adminpage.vue')
    },
    {
        path: '/mypage',
        name: 'mypage',
        component: () => import('@/pages/Mypage.vue')
    },
    {
        path: '/certification/:caresitterId',
        name: 'certification',
        component: () => import('@/pages/Certification.vue')
    },
    
    {
        path: '/admin_report',
        name: 'admin_report',
        component: () => import('@/pages/Adminpage_report.vue')
    },
    {
        path: '/signup',
        name: 'signup',
        component: () => import('@/pages/Signup.vue')
   },
   {
       path: '/calendar',
       name: 'calendar',
       component:() => import('@/pages/Calendar.vue')
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
        path: '/dashboard/careprofile1/:profileId',
        name: 'careprofile1',
        component: () => import('@/pages/Careprofile1.vue')
    },
    {
        path: '/caresitterprofile_delete',
        name: 'caresitterprofile_delete',
        component: () => import('@/pages/Caresitterprofile.vue')
    },
    {
        path: '/careprofilecreate',
        name: 'careprofilecreate',
        component: () => import('@/pages/Careprofilecreate.vue')
    },
    {
        path: '/profile_update/:caresitterId',
        name: 'profile_update',
        component: () => import('@/pages/Profile_update.vue')
    },
    // {
    //     path: '/profile_update/:profileId',
    //     name: 'profile_update',
    //     component: () => import('@/pages/Profile_update.vue')
    // },
    {
        path: '/addprofile2/:caresitterId',
        name: 'addprofile2',
        // props:true,
        component: () => import('@/components/main/addprofile2.vue')
    },
    {
        path: '/caresitterprofile/:caresitterId',
        name: 'caresitterprofile',
        // props:true,
        component: () => import('@/components/main/caresitterprofile.vue')
    },
    {
        path: '/dashboard/careprofile2',
        name: 'careprofile2',
        component: () => import('@/pages/Careprofile2.vue')
    },
    {
        path: '/contract',
        name: 'contract',
        component: () => import('@/pages/Contract.vue')
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
        // props:true,
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
    // {
    //     path: '/profile2',
    //     name: 'profile2',
    //     component: Profile2
    // },
    {
        path: '/test',
        name: 'Test',
        component: () => import('@/pages/Test.vue')
    },
    {
        path: '/caretargets',
        name: 'Caretargets',
        component: () => import('@/pages/Caretargets.vue')
    },
];

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router;
