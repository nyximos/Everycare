export default {
    namespaced: true,
    state: {
        image: '',
        name: '',
        gender: '',
        age: '',
        introduction: '',
        hopeloc1: '',
        hopeloc2: '',
        hopeloc3: '',
        desiredDayWeek: '',
        starttimepicker: '',
        endtimepicker: '',
        paytype: '',
        desiredHourlyWage: '',
        desiredMonthlyWage:'',
        hopetype:'',
        preferredType:'',
        vaccination:'',
        cctv:'',
        certification:''
    },
    mutations: {
        set_user1(state, userData) {
            state.image = userData.image;
            state.name = userData.name;
            state.gender = userData.gender;
            state.age = userData.age;
            state.introduction = userData.introduction;
        },
        set_user2(state, userData){
            state.hopeloc1 = userData.hopeloc1;
            state.hopeloc2 = userData.hopeloc2;
            state.hopeloc3 = userData.hopeloc3;
            state.hopefulRegion = userData.hopeloc1 + userData.hopeloc2 + userData.hopeloc3;
            state.desiredDayWeek = userData.desiredDayWeek;
            state.activityTime = userData.starttimepicker + userData.endtimepicker;
            state.paytype = userData.paytype;
            state.desiredHourlyWage = userData.desiredHourlyWage;
            state.desiredMonthlyWage = userData.desiredMonthlyWage;
        },
        set_user3(state, userData){
            state.hopetype = userData.hopetype;
            state.preferredType = userData.preferredType;
            state.vaccination = userData.vaccination;
            state.cctvAgreement = userData.cctvAgreement;
            state.certification = userData.certification;
        }
    },
    actions: {
        // submit(){
        //     var formData= new FormData();

        //     formData.append('preferredType', this.preferredType);
        //     formData.append('vaccination', this.vaccination);
        //     formData.append('cctvAgreement', this.cctvAgreement);
        //     formData.append('introduction', this.introduction);
        //     formData.append('desiredDayWeek', this.desiredDayWeek);
        //     formData.append('hopefulRegion', this.hopeloc1);
        //     formData.append('desiredHourlyWage', this.desiredHourlyWage);
        //     formData.append('desiredMonthlyWage', this.desiredMonthlyWage);
        //     formData.append('activityTime', this.activityTime);
        //     // formData.append('activityTime', this.starttimepicker + this.endtimepicker);
        //    for(let i = 0; i< this.attachFiles.length; i++){
        //      formData.append('attachFiles', this.attachFiles[0]);
        //    } this.$http
        //     .post('/api/dashboard/caresitter',formData,{
        //         withCredentials:true
        //     })
        //     .then(res=>{
        //         console.log(res);
        //     }).catch(err=>{
        //         console.log(err)
        //     })
        // }
        // requestuserData({commit}){
        // axios.post('/api/dashboard/caresitter')
        // .then(response=>{
        //     commit()
        // })
        // }
    },
    getters: {}
};