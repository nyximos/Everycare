export default {
    namespaced: true,
    state: {
        id: '',
        name: '',
    },
    mutations: {
        caretarget(state, caretarget) {
            state.id = caretarget.id;
            state.name = caretarget.name;
        },
        // clearUsername(state) {
        //     state.id = '';
        //     state.careSitterId = '';
        //     state.storeId = '';
        // },
    },
};
