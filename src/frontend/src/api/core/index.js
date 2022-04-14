import axios from 'axios';

// const instance = axios.create({
//     baseURL: '/api'
// })

// function registerUser(updata) {
//     //const url = '/api/signup';
//     //axios.post('/api/signup', updata)
//     instance.post('/signup', updata)
// }

// function loginUser(userdata){
//     const url = 'http://localhost:8086/login'
//     return axios.post(url, userdata)
//                 .then((res)=>{
//                     console.log(res);
//                 })
// }
// export{ registerUser };

//create an axios instance
const request = axios.create({
    baseURL: process.env.VUE_APP_BASE_URL,
});

// Alter defaults after instance has been created
// request.defaults.headers.common['Authorization'] = 'AUTH_TOKEN';

// request.interceptors.request.use(
//     config => {
//         return config;
//     },
//     error => {
//         console.log(error);
//         return Promise.reject(error);
//     }
// );

// request.interceptors.response.use(
//     response => {
//         const res = response.data;
//         return res;
//     },
//     error => {
//         console.log(error);
//         return Promise.reject(error);
//     }
// );

export default request;