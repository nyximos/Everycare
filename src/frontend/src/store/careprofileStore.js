export default {
    namespaced: true,
    state: {
        image: '',
        name: '',
        row: '',
        age: '',
        textarea: '',
        hopelocation: '',
        hopeday: '',
        hopetime: '',
        pay: '',
        hopetype: '',
        hopeage: '',
        vaccine: '',
        cctv: '',
        certification: ''
    },
    mutations: {
        carePro(state, caredata) {
            state.name = caredata.name;
            state.age = caredata.age;
            state.row = caredata.row;
            state.textarea = caredata.textarea;
        },
        carePro1(state, caredata) {
            state.hopelocation = caredata.hopelocation;
            state.hopetime = caredata.hopetime;
            state.hopeday = caredata.hopeday;
            state.pay = caredata.pay;
        },
        carePro2(state, caredata) {
            state.hopetype = caredata.hopetype;
            state.hopeage = caredata.hopeage;
            state.vaccine = caredata.vaccine;
            state.cctv = caredata.cctv;
            state.certification = caredata.certification;
        },
    },
    actions: {},
    getters: {}
};
