export default {
    namespaced: true,
    state: {
        actid: '',
    },
    mutations: {
        onEdit(state, userData) {
            state.actid = userData.actId;
        },
        
    },
};
