export default {
    namespaced: true,
    state: {
        image: '나는 이미지!',
        name: '나는바보다22',
        sex: '',
        age: '',
        comment: '',
        hopeloc1: '',
        hopeloc2: '',
        hopeloc3: '',
        hopeday: '',
        starttimepicker: '',
        endtimepicker: '',
        paytype: '',
        pay: '',
        caretype:'',
        ageselect:'',
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
            state.comment = userData.comment;
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
            state.caretype = userData.caretype;
            state.ageselect = userData.ageselect;
            state.vaccine = userData.vaccine;
            state.cctv = userData.cctv;
            state.certification = userData.files;
        }
    },
    actions: {},
    getters: {}
};