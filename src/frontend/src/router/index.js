import Vue from 'vue';
import VueRouter from 'vue-router';

// import Caresitters from '../pages/Caresitters';
import Carepeople from '../pages/Carepeople';
import Alarm from '../pages/Alarm';
// import Profile2 from '../pages/Profile2';

Vue.use(VueRouter);
const routes = [
    {
        path: '*',
        redirect: '/',
    },
    {
        path: '/addr',
        component: () => import('@/components/addr.vue'),
    },
    {
        path: '/',
        name: 'Main',
        component: () => import('@/pages/Main.vue'),
    },
    // {
    //     path: '/notice',
    //     name: 'notice',
    //     component: () => import('@/pages/Notice.vue'),
    // },
    {
        path: '/newNotice',
        name: 'newNotice',
        component: () => import('@/components/newNotice.vue'),
    },
    // 상품 등록
    {
        path: '/store/account/{id}/products/new',
        name: 'ProdCreate',
        component: () => import('@/components/prodCreate.vue'),
    },
    // 상품 관리
    {
        path: '/store/account/:contentId/products',
        name: 'prodEdit',
        component: () => import('@/components/prodEdit.vue'),
    },
    {
            path: '/prodcreate',
        name: 'ProdCreate',
        component: () => import('@/components/prodCreate.vue'),
    },
    {
        path: '/prodedit/:contentId',
        name: 'ProdEdit',
        component: () => import('@/components/prodEdit.vue'),
    },
    {
        path: '/dashboard/cart',
        name: 'Cart',
        component: () => import('@/components/cart.vue'),
    },
    {
        path: '/payment',
        name: 'Payment',
        component: () => import('@/components/productPayment.vue'),
    },
    {
        // 관리자 자격증 조회 화면
        path: '/admin/caresitter-certifications',
        name: 'adminpage_certi',
        component: () => import('@/pages/Adminpage_certi.vue'),
    },
    {
        // 관리자 자격증 상세/승인
        path: '/admin/caresitter-certifications/:contentId',
        name: 'certification_detail',
        component: () => import('@/components/main/certification_detail.vue'),
    },
    {
        //자격증 등록화면
        path: '/dashboard/caresitter/:caresitterId/certifications/new',
        name: 'certification',
        component: () => import('@/pages/Certification.vue'),
    },
    {
        // 회원 마이페이지
        path: '/dashboard',
        name: 'mypage',
        component: () => import('@/pages/Mypage.vue'),
    },
    {
        // 구직글 조회
        path: '/caresitters',
        name: 'caresitters',
        component: () => import('@/pages/Caresitters.vue'),
    },
    {
        // 케어시터 프로필 등록
        path: '/dashboard/caresitter',
        name: 'careprofilecreate',
        component: () => import('@/pages/Careprofilecreate.vue'),
    },
    {
        // 구직글 케어시터 프로필 상세조회
        path: '/caresitters/:caresitterId',
        name: 'addprofile2',
        props: true,
        component: () => import('@/components/main/addprofile2.vue'),
    },
    {
        // 계약서
        path: '/contract/:caresitterId',
        name: 'contract',
        component: () => import('@/pages/Contract.vue'),
    },
    {
        //마이페이지 케어시터프로필 조회
        path: '/mypage_profile/:caresitterId',
        name: 'mypage_profile',
        component: () => import('@/components/main/mypage_profile'),
    },
    {
        // 프로필 수정
        path: '/profile_update/:caresitterId',
        name: 'profile_update',
        component: () => import('@/pages/Profile_update.vue'),
    },
    {
        //마이페이지 자격증 조회
        path: '/mypage_certi/:caresitterId',
        name: 'mypage_certi',
        component: () => import('@/components/main/mypage_certi'),
    },
    {
        // 마이페이지 계약서 조회
        path: '/caresitter_contract/:caresitterId',
        name: 'caresitter_contract',
        component: () => import('@/pages/Caresitter_contract'),
    },
    {
        //마이페이지 계약서 상세조회
        path: '/contract_detail/:contractId',
        name: 'contract_detail',
        component: () => import('@/components/main/contract_detail'),
    },
    {
        //케어 승낙한 계약서
        path: '/lastcontract',
        name: 'lastcontract',
        component: () => import('@/pages/Lastcontract'),
    },
    {
        path: '/admin_report',
        name: 'admin_report',
        component: () => import('@/pages/Adminpage_report.vue'),
    },
    {
        path: '/Test1',
        name: 'Test1',
        component: () => import('@/pages/Test1.vue'),
    },
    {
        path: '/joboffer/:id/editwrite',
        name: 'editwrite',
        component: () => import('@/components/writeEdit.vue'),
    },
    {
        path: '/signup',
        name: 'signup',
        component: () => import('@/pages/Signup.vue'),
    },
    // {
    //     path: '/calendar',
    //     name: 'calendar',
    //     component: () => import('@/pages/Calendar.vue'),
    //     path: '/stores/product/:contentId',
    //     name: 'prodDetail',
    //     component: () => import('@/components/prodDetail.vue'),
    // },
    // {
    //     path: '/careprofile',
    //     name: 'careprofile',
    //     component: () => import('@/pages/Careprofile.vue'),
    // },
    
    // 케어스토어 메인
    {
        path: '/store',
        name: 'storeMain',
        component: () => import('@/pages/StoreHome.vue'),
    },
    // 케어스토어 등록
    {
        path: '/store/new',
        name: 'createStore',
        component: () => import('@/pages/Storesubmit.vue'),
    },
    // 케어스토어 수정
    {
        path: '/store/account/:contentId/update',
        name: 'storeEdit',
        component: () => import('@/pages/StoreInfoEdit.vue'),
    },
    // 케어스토어 상품 전체 조회 (스토어멤버)
    {
        path: '/Mystore/:contentId',
        name: 'storeMain',
        component: () => import('@/pages/MyStore.vue'),
    },
    // 상품 등록
    {
        path: '/store/account/:contentId/products/new',
        name: 'ProdCreate',
        component: () => import('@/components/prodCreate.vue'),
    },
    // 상품 관리
    {
        path: '/store/account/:contentId/products',
        name: 'ProdEdit',
        component: () => import('@/components/prodEdit.vue'),
    },
    // 상품 상세 조회
    {
        path: '/store/account/:contentId/products/:contentId',
        name: 'prodDetail',
        component: () => import('@/components/prodDetail.vue'),
    },
    // 장바구니,주문
    {
        path: '/dashboard/cart',
        name: 'Cart',
        component: () => import('@/components/cart.vue'),
    },
    // 찜 목록
    {
        path: '/dashboard/wishlist',
        name: 'Wish',
        component: () => import('@/pages/Wish.vue'),
    },
    // 상품결제
    {
        path: '/store/ordersheet/payment',
        name: 'Payment',
        component: () => import('@/components/productPayment.vue'),
    },
    // 관리자 케어스토어 목록
    {
        path: '/admin/stores',
        name: 'adminStores',
        component: () => import('@/pages/AdminStores.vue'),
    },
    // 관리자 케어스토어 목록 상세
    {
        path: '/admin/stores/:contentId',
        name: 'adminStoresDetail',
        component: () => import('@/pages/AdminStoresDetail.vue'),
    },
    // 구인글 조회
    {
        path: '/recruitions',
        name: 'Job_list',
        component: () => import('@/pages/Job_list.vue'),
    },
    // 구인글 등록
    {
        path: '/recruitions/new',
        name: 'CreateWrite',
        component: () => import('@/pages/CreateWrite.vue'),
    },
    // 구인글 수정
    {
        path: '/recruitions/:id/update',
        name: 'editwrite',
        component: () => import('@/components/writeEdit.vue'),
    },
    // 구인글 상세
    {
        path: '/recruitions/:contentId',
        name: 'detail',
        component: () => import('@/components/itemDetail.vue')
    },
    // 공지 조회
    {
        path: '/notice',
        name: 'Notice',
        component: () => import('@/pages/Notice.vue'),
    },
    // 공지 등록
    {
        path: '/admin/notice/new',
        name: 'newNotice',
        component: () => import('@/components/newNotice.vue'),
    },
    // 공지 상세조회
    {
        path: '/admin/notice/:contentId',
        name: 'noticeDetail',
        component: () => import('@/components/noticeDetail.vue'),
    },
    // 자주 묻는 질문 조회
    {
        path: '/faq',
        name: 'Faq',
        component: () => import('@/pages/Faq.vue'),
    },
    // 자주 묻는 질문 상세조회, 관리자수정삭제
    {
        path: '/faq/:contentId',
        name: 'FaqDetail',
        component: () => import('@/pages/FaqDetail.vue'),
    },
    // 관리자 자주 묻는 질문 등록
    {
        path: '/admin/faq/new',
        name: 'FapNew',
        component: () => import('@/pages/FaqNew.vue'),
    },
    // 관리자 자주 묻는 질문 등록
    {
        path: '/admin/faq/new',
        name: 'FapNew',
        component: () => import('@/pages/FaqNew.vue'),
    },
    {
        path: '/login',
        name: 'Login',
        component: () => import('@/pages/Login.vue'),
        reEnter: (to, from, next) => {
            const token = localStorage.getItem('jwt-access-token');
            if (token !== null) next({ name: 'Main' });
            else next();
        },
    },
    {
        path: '/works',
        name: 'FindWork',
        component: () => import('@/pages/FindWork.vue'),
        reEnter: (to, from, next) => {
            const token = localStorage.getItem('jwt-access-token');
            if (token !== null) next({ name: 'Main' });
            else next();
        },
    },

    // {
    //     path: '/profile',
    //     name: 'profile',
    //     component: Profile
    // },
    // {
    //     path: '/dashboard/caresitter',
    //     name: '케어시터 프로필생성',
    //     component: () => import('@/pages/Careprofile.vue'),
    // },
    {
        path: '/carepeople',
        name: 'carepeople',
        component: Carepeople,
    },
    {
        path: '/alarm',
        name: 'alarm',
        component: Alarm,
    },
    // {
    //     path: '/profile2',
    //     name: 'profile2',
    //     component: Profile2,
    // },
    {
        path: '/test',
        name: 'Test',
        component: () => import('@/pages/Test.vue'),
    },
    {
        path: '/carenote/caretargets',
        name: 'Caretargets',
        component: () => import('@/pages/Caretargets.vue'),
    },
    {
        path: '/carenote/caretargets:caretargetsId',
        name: 'CaretargetsDetail',
        component: () => import('@/components/main/caretargetsdetail.vue'),
    },
    // {
    //     path: '/targets_update/:caretargetsId',
    //     name: 'targets_update',
    //     component: () => import('@/pages/Targets_update.vue')
    // },
    {
        path: '/dashboard/caretargets/:caretargetsId/update',
        name: 'TargetsUpdate',
        // component: () => import('@/pages/Targets_update.vue')
        component: () => import('@/components/main/caretargets_update.vue'),
    },
    {
        path: '/carenote/caretargets/:caretargetsId/schedules',
        name: 'schedule',
        component:() => import('@/pages/Schedule.vue')
    },
    {
        path: '/dashboard/carenote/caretargets/:caretargetsId/schedules/:scheduleId/update',
        name: 'schupdate',
        component:() => import('@/pages/Schedule_Update.vue')
    },
    {
     path: '/dashboard/carenote/caretargets/:caretargetsId/schedules/:scheduleId/delete',
     name: 'schdelete',
     component:() => import('@/pages/Schedule_Delete.vue')
 },
    {
        path: '/dashboard/carenote/caretargets/:caretargetsId/schedules/:scheduleId/addactive',
        name: 'scheActive',
        component:() => import('@/pages/Schedule_Active.vue')
    },
    {
       path: '/dashboard/memberschedule',
       name: 'memberschedule',
       component:() => import('@/pages/Member_Schedule.vue')
    },
    {
        path: '/dashboard/memberschedule/:memberscheduleId/delete',
        name: 'memberscheduledelete',
        component:() => import('@/pages/Member_Schedule_Delete.vue')
     },
     {
        path: '/dashboard/memberschedule/:memberscheduleId/detail',
        name: 'memberscheduledetail',
        component:() => import('@/pages/Member_Schedule_Detail.vue')
     },
     {
        path: '/dashboard/memberschedule/:memberscheduleId/update',
        name: 'memberscheduleupdate',
        component:() => import('@/pages/Member_Schedule_Update.vue')
     },
    {
        path: '/admin/members',
        name: 'adminpage',
        component: () => import('@/pages/Adminpage.vue'),
    },
    {
        path: '/jobapplication/:contentId',
        name: 'jobapplication',
        component: () => import('@/pages/JobApplication.vue'),
    },
    {
        path: '/mypage_joblist',
        name: 'mypage_joblist',
        component: () => import('@/components/main/mypage_joblist'),
    },

];

const router = new VueRouter({
    mode: 'history',
    routes,
});

export default router;
