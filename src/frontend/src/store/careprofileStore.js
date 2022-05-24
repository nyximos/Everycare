export default {
    namespaced: true,
    state: {
        id: ''
    },
    mutations: {
        move(state, userData) {
            state.id = userData.id;
        },
        
    },
};
