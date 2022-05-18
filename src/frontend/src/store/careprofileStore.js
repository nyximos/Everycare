export default {
    namespaced: true,
    state: {
        id:''
    },
    mutations: {
        submit(state, userinfo){
            state.id = userinfo.id;
        }
    },
    actions: {
    },
    getters: {}
};