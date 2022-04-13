export default {
    namespaced: true,
    state: {
        image: '',
        name: '',
        sex: '',
        age: '',
        intro: '',
        hopeloc1: '',
        hopeloc2: '',
        hopeloc3: '',
        hopeday: '',
        starttimepicker: '',
        endtimepicker: '',
        paytype: '',
        pay: '',
        hopetype:'',
        hopeage:'',
        vaccine:'',
        cctv:'',
        certification:''
    },
    mutations: {
        set_user1(state, userData) {
            state.image = userData.image;
            state.name = userData.name;
            state.sex = userData.sex;
            state.age = userData.age;
            state.intro = userData.intro;
        },
        set_user2(state, userData){
            state.hopeloc1 = userData.hopeloc1;
            state.hopeloc2 = userData.hopeloc2;
            state.hopeloc3 = userData.hopeloc3;
            state.hopeday = userData.hopeday;
            state.starttimepicker = userData.starttimepicker;
            state.endtimepicker = userData.endtimepicker;
            state.paytype = userData.paytype;
            state.pay = userData.pay;
        },
        set_user3(state, userData){
            state.hopetype = userData.hopetype;
            state.hopeage = userData.hopeage;
            state.vaccine = userData.vaccine;
            state.cctv = userData.cctv;
            state.certification = userData.certification;
        }
    },
    actions: {},
    getters: {}
};