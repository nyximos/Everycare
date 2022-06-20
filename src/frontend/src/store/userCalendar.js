export default {
    namespaced: true,
    state: {
        scheid: '',
        
    },
    mutations: {
        memsch(state, scheData){
            state.scheid = scheData.id
        }
    },
};
