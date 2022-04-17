export default{
    namespaced: true,
    state: {
        id:'',
        password:'',
        name:'',
        birth:'',
        gender:'',
        phone:'',
        email:'',
        zipcode:'',
        address:'',
        detailedAddress:'',
        storeId: ''
    },
    mutations:{
        sgnUp(state, updata){
            state.id = updata.id;
            state.password = updata.password;
            state.name = updata.name;
            state.birth = updata.birth;
            state.gender = updata.gender;
            state.phone = updata.phone;
            state.email = updata.email;
            state.zipcode = updata.zipcode;
            state.address = updata.address;
            state.detailedAddress = updata.detailedAddress;
            state.storeId = updata.storeId
         }
    }
}