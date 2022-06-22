export default {
    namespaced: true,
    state: {
        id: '',
        name: '',
        categoryId:'',
        contractId:''
    },
    mutations: {
        caretarget(state, caretarget) {
            state.id = caretarget.id;
            state.name = caretarget.name;
            state.categoryId = caretarget.categoryId;
            state.contractId = caretarget.contractId;
        },
    },
};
