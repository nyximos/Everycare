import http from '@/util/http-common.js';

const requestLogin = (data, callback, errorCallback) => {
    http.post('/user/signIn', data)
        .then(res => {
            callback(res);
        })
        .catch(err => {
            errorCallback(err);
        });
};

export default {
    requestLogin
};
