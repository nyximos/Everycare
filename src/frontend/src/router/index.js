import Vue from 'vue';
import VueRouter from 'vue-router';

import Caresitters from '../pages/Caresitters';
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
        path: '/order',
        name: 'Order',
        component: () => import('@/components/order.vue')
    },
    {
        path: '/payment',
        name: 'Payment',
        component: () => import('@/components/productPayment.vue')
    },
    {   
        // 관리자 자격증 조회 화면
        path: '/admin/caresitter-certifications',
        name: 'adminpage_certi',
        component: () => import('@/pages/Adminpage_certi.vue')
    },
    {
        // 관리자 자격증 상세/승인
        path: '/admin/caresitter-certifications/:contentId',
        name: 'certification_detail',
        component: () => import('@/components/main/certification_detail.vue')
    },
    {   
        //자격증 등록화면
        path: '/dashboard/caresitter/:caresitterId/certifications/new',
        name: 'certification',
        component: () => import('@/pages/Certification.vue')
    },
    {   
        // 회원 마이페이지
        path: '/dashboard',
        name: 'mypage',
        component: () => import('@/pages/Mypage.vue')
    },
    {   
        // 구직글 조회
        path: '/caresitters',
        name: 'caresitters',
        component: () => import('@/pages/Caresitters.vue')
    },
    {
        // 케어시터 프로필 등록
        path: '/dashboard/caresitter',
        name: 'careprofilecreate',
        component: () => import('@/pages/Careprofilecreate.vue')
    },
    {   
        // 구직글 케어시터 프로필 상세조회
        path: '/caresitters/:caresitterId',
        name: 'addprofile2',
        props:true,
        component: () => import('@/components/main/addprofile2.vue')
    },
    {   
        // 계약서
        path: '/contract/:caresitterId',
        name: 'contract',
        component: () => import('@/pages/Contract.vue')
    },
    {   
        //마이페이지 케어시터프로필 조회
        path:'/mypage_profile/:caresitterId',
        name:'mypage_profile',
        component: ()=> import('@/components/main/mypage_profile')
    },
    {   
        // 프로필 수정
        path: '/profile_update/:caresitterId',
        name: 'profile_update',
        component: () => import('@/pages/Profile_update.vue')
    },
    {   
        //마이페이지 자격증 조회
        path:'/mypage_certi/:caresitterId',
        name:'mypage_certi',
        component: ()=> import('@/components/main/mypage_certi')
    },
    {
        path: '/admin_report',
        name: 'admin_report',
        component: () => import('@/pages/Adminpage_report.vue')
    },
    {
        path: '/wish',
        name: 'Wish',
        component: () => import('@/pages/Wish.vue')
    },
    {
        path: '/cart',
        name: 'Cart',
        component: () => import('@/pages/Cart.vue')
    },
    {

        path: '/joboffer/:id/editwrite',
        name: 'editwrite',
        component: () => import('@/components/writeEdit.vue')
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
    path: '/stores/product/:contentId',
    name: 'prodDetail',
    component: () => import('@/components/prodDetail.vue')
    },
    {
        path:'/stores',
        name:'storeMain',
        component: ()=> import ('@/pages/StoreHome.vue')
    },
    {
        path: '/createstore',
        name: 'createStore',
        component: () => import('@/pages/Storesubmit.vue')
    },
    {
        path: '/stores/:id',
        name: 'storeEdit',
        component: () => import('@/pages/StoreInfoEdit.vue')
    },
    
    {
        path: '/joblist',
        name: 'Job_list',
        component: () => import('@/pages/Job_list.vue')
    },
    {
        path: '/careprofile1',
        name: 'careprofile1',
        component: () => import('@/pages/Careprofile1.vue')
    },
    {
        path: '/careprofile2',
        name: 'careprofile2',
        component: () => import('@/pages/Careprofile2.vue')
    },
    {
        path: '/joblist/detail/:contentId',
        name: 'detail',
        component: () => import('@/components/itemDetail.vue')
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
        component: Caresitters
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
    //     path: '/profile',
    //     name: 'profile',
    //     component: Profile
    // },
    {
        path: '/dashboard/caresitter',
        name: '케어시터 프로필생성',
        component: () => import('@/pages/Careprofile.vue')
    },
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
    {

        path: '/caretargetsdetail/:caretargetsId',
        name: 'CaretargetsDetail',
        component: () => import('@/components/main/caretargetsdetail.vue')
    },
    // {
    //     path: '/targets_update/:caretargetsId',
    //     name: 'targets_update',
    //     component: () => import('@/pages/Targets_update.vue')
    // },
    {
        path: '/caretargets_update/:caretargetsId',
        name: 'TargetsUpdate',
        // component: () => import('@/pages/Targets_update.vue')
        component: () => import('@/components/main/caretargets_update.vue')
    },
    
    {
        path: '/adminpage',
        name: 'adminpage',
        component: () => import('@/pages/Adminpage.vue')
    },
    
]

const router = new VueRouter({
    mode: 'history',
    routes
});

export default router;
