export default{
    namespaced: true,
    state: {
        id:'',
        name:'',
        role:'',
        careSitterId:'',
        storeId:'',
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