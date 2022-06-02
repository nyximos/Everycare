export default {
    namespaced: true,
    state: {
        id: '',
        name: '',
        categoryId:''
    },
    mutations: {
        caretarget(state, caretarget) {
            state.id = caretarget.id;
            state.name = caretarget.name;
            state.categoryId = caretarget.categoryId;
        },
        // clearUsername(state) {
        //     state.id = '';
        //     state.careSitterId = '';
        //     state.storeId = '';
        // },
    },
};
