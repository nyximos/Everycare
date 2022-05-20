export default{
    namespaced: true,
    state: {
        id:'',
        name:'',
        role:''
    },
    mutations:{
        userInfo(state, userinfo){
            state.id=userinfo.username
         },
         clearUsername(state){
            state.id='';
         }
    }
}