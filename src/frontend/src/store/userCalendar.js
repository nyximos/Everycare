export default {
    namespaced: true,
    state: {
        id: '',
        
    },
    mutations: {
        memsch(state, postItem){
            state.id = postItem.id
        }
    },
};
