export default {
    namespaced: true,
    state: {
        id: '',
        certification_name:'',
        certification_img:'',
    },
    mutations: {
        move(state, userData) {
            state.id = userData.id;
        },
        userData(state,userData){
            state.certification_name = userData.certification_name;
            state.certification_img = userData.certification_img;

        }
        
    },
};
