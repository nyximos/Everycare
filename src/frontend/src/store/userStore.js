export default {
    namespaced: true,
    state: {
        id: '',
        name: '',
        role: '',
        careSitterId: '',
        storeId: '',

    },
    mutations: {
        userInfo(state, userinfo) {
            state.id = userinfo.username;
            state.name = userinfo.username;
        },
        userData(state, userData) {
            state.careSitterId = userData.caresitterId;
            state.storeId = userData.storeId;
        },
        clearUsername(state) {
            state.id = '';
            state.careSitterId = '';
            state.storeId = '';
        },
    },
};
