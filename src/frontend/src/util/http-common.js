import axios from 'axios';

const storage = localStorage;
axios.defaults.baseURL = '/api';
axios.defaults.headers.common.Accept = 'application/json';
axios.defaults.headers.post['Content-Type'] = 'application/json';
axios.defaults.headers.put['Content-Type'] = 'application/json';

// 요청 인터셉터 추가
const instance = axios.create();

instance.interceptors.request.use(
    function(config) {
        // 요청을 보내기 전에 수행할 일
        // ...
        // 1. 로그인, 회원가입을 위해 요청을 보내는 경우(/user), 토큰 없이 보냄.
        if (config.url.split('/')[3] === 'user' && config.url.split('/')[4] !== 'info') {
            return config;
        }
        // 2. 그 외의 요청에는 항상 토큰을 헤더에 넣어서 보냄.
        else {
            config.headers['Authorization'] = 'Bearer ' + storage.getItem('jwt-access-token');
            return config;
        }
    },
    function(error) {
        // 오류 요청을 보내기전 수행할 일
        // ...
        return Promise.reject(error);
    }
);

// 응답 인터셉터 추가
instance.interceptors.response.use(
    function(response) {
        // 응답 데이터를 가공
        // ...
        if (response.headers['authorization']) {
            storage.setItem('jwt-access-token', response.headers['authorization'].substring(7));
        }
        return response;
    },
    function(error) {
        // 오류 응답을 처리
        // …
        return Promise.reject(error);
    }
);

export default instance;
