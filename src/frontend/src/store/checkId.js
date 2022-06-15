export default {
    namespaced: true,
    state: {
        chkid: ''
    },
    mutations: {
        checkIds(state, id){
            state.chkid=id
        }
    },
};
