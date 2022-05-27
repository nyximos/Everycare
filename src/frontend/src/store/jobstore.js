export default {
    namespaced: true,
    state: {
        id: '',
    },
    mutations: {
        detailShot(state, userinfo) {
            state.id = userinfo.id;
        },
      
    },
};
