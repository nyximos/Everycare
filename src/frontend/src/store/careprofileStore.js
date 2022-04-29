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
        submit(){
            const userIntro={
                preferredType:this.preferredType,
                hopefulRegion:this.hopefulRegion,
                desiredDayWeek:this.desiredDayWeek,
                activityTime:this.activityTime,
                desiredHourlyWage:this.desiredHourlyWage,
                desiredMonthlyWage:this.desiredMonthlyWage,
                cctvAgreement:this.cctvAgreement,
                vaccination:this.vaccination,
                introduction:this.introduction,
                careSitterImages:this.image,

            }
            console.log(userIntro)
            this.$http
            .post('/api/dashboard/caresitter',userIntro,{
                withCredentials:true
            })
            .then(res=>{
                console.log(res);
            }).catch(err=>{
                console.log(err)
            })
        }
        // requestuserData({commit}){
        // axios.post('/api/dashboard/caresitter')
        // .then(response=>{
        //     commit()
        // })
        // }
    },
    getters: {}
};