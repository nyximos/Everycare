export default {
    namespaced: true,
    state: {
        id:''
    },
    mutations: {
        submit(state, formData){
            state.id = formData.id;
        }
    },
    actions: {
    },
    getters: {}
};