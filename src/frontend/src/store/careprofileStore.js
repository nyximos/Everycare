export default {
    namespaced: true,
    state: {
        id:'',
        caresitterId:'',
    },
    mutations: {
        submit(state, userinfo){
            state.id = userinfo.id;
        },
        move(state, userData){
            state.caresitterId = userData.id
        }
    },
    actions: {
    },
    getters: {}
};