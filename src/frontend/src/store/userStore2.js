export default{
    namespaced: true,
    state: {
        username:'',
    },
    getters:{
       isLogin(state){
           return state.username !== ''
       }
    },
    mutations:{
        sgnIn(state, userdata){
            state.username = userdata.username;
         },
    clearUsername(state){
        state.username = '';
    }
    } 
}