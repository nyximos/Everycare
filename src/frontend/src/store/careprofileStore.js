export default {
    namespaced: true,
    state: {
        id: '',
        certification_name:'',
        certification_img:'',
        activityTime:'',
    },
    mutations: {
        move(state, userData) {
            state.id = userData.id;
        },
        userData(state,userData){
            state.certification_name = userData.certification_name;
            state.certification_img = userData.certification_img;
        },
        profile(state,profile){
            state.activityTime = profile.activityTime
        }
        
    },
};
