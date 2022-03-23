import User from '@/api/User';
import router from '@/router';

export default {
    namespaced: true,
    state: {
        user: {
            name: ''
        }
    },
    getters: {
        get_user: state => state.user
    },
    mutations: {
        set_user(state, value) {
            state.user.name = value.name;
        },
        delete_user(state) {
            state.user = {
                name: ''
            };
        }
    },
    actions: {
        login({ dispatch }, data) {
            User.requestLogin(
                data,
                () => {
                    dispatch('getUserInfo');
                    router.push({ name: 'Main' });
                },
                err => {
                    console.log(err);
                }
            );
        },
        logout({ commit }) {
            localStorage.removeItem('jwt-access-token');
            commit('delete_user');
            router.push({ name: 'Main' });
        }
    }
};
