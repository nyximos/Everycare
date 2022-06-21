<template>
    <div class="profile-page">
        <br><br>
    <div class="wrapper">
            <h3>계약서</h3>
        
        <div class="inputBox">
            <br>
        <ul>
            <li><h2>케어대상인 정보</h2></li>
            <!-- <li>아이디: {{contract.careTargetDetailDTO.id}}</li> -->
            <li>이름: {{contract.careTargetDetailDTO.name}}</li>
            <li>주소: {{contract.careTargetDetailDTO.address}}</li>
            <li>상세주소: {{contract.careTargetDetailDTO.detailedAddress}}</li>
            <li>생년월일: {{contract.careTargetDetailDTO.birth}}</li>
            <li>성별: {{gender}}</li>
            <li>키: {{contract.careTargetDetailDTO.height}}cm</li>
            <li>몸무게: {{contract.careTargetDetailDTO.weight}}kg</li>
            <li>타입: {{contract.careTargetDetailDTO.careType}}</li>
            <li>comment: {{contract.careTargetDetailDTO.comment}}</li>
            <li>백신: {{vaccinated}}</li>
            <li>cctv: {{cctvAgreement}}</li>
            <li>케어등급: {{contract.careTargetDetailDTO.longTermCareGrade}}등급</li>
            <li>펫: {{pet}}</li>
            <li>zipcode: {{contract.careTargetDetailDTO.zipcode}}</li>
            <br><br><br>
            <li><h2>계약정보</h2></li>
            <li>title: {{contract.contractJobOfferDTO.title}}</li>
            <!-- <li>아이디: {{contract.contractJobOfferDTO.id}}</li> -->
            <li>날짜: {{contract.contractJobOfferDTO.day}}</li>
            <li>시간: {{contract.contractJobOfferDTO.desiredStartTime}} ~ {{contract.contractJobOfferDTO.desiredEndTime}}</li>
            <!-- <li>끝나는시간: {{contract.contractJobOfferDTO.desiredEndTime}}</li> -->
            <li>시작날짜: {{contract.contractJobOfferDTO.startDate}} ~ {{contract.contractJobOfferDTO.endDate}}</li>
            <!-- <li>끝나는 날짜: {{contract.contractJobOfferDTO.endDate}}</li> -->
            <li>금액:{{contract.contractJobOfferDTO.amount}}</li>
            <li>pay:{{contract.contractJobOfferDTO.pay}}</li>
      </ul>  
      <div class="btn">
        <v-btn class="ma-2" outlined color="indigo" @click="submit">수락</v-btn>
        <router-link to="/dashboard/caresitter/contracts"><v-btn class="ma-2" outlined color="indigo">취소</v-btn></router-link>
     </div>
        </div>
  </div>
  </div>
</template>

<script>
export default {
    data(){
        return{
            contract:[
            ],
            gender:this.gender,
            cctvAgreement:this.cctvAgreement,
            vaccinated:this.vaccinated
        }
    },
    mounted(){
        const id = this.$route.params.contractId;
        // console.log(id)
        this.$http
        .get(`/api/dashboard/caresitter/contracts/${id}`,{
            withCredentials:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.contract = res.data.body;

            if(res.data.body.careTargetDetailDTO.gender == "M"){
                this.gender = "남자"
            }else{
                this.gender = "여자"
            }
            if(res.data.body.careTargetDetailDTO.coronaTest == 0){
                this.vaccinated = "1차접종완료"
            }else if(res.data.body.careTargetDetailDTO.coronaTest ==1){
                this.vaccinated = "추가접종완료"
            }else if(res.data.body.careTargetDetailDTO.coronaTest == 2){
                this.vaccinated = "미접종"
            } 

            if(res.data.body.careTargetDetailDTO.isCctvAgreement == 0){
            this.cctvAgreement = "O"
            }else{
            this.cctvAgreement = "X"
            }
            if(res.data.body.careTargetDetailDTO.pet == 0){
                this.pet = "O"
            }else{
                this.pet = "X"
            }
            // this.gender = res.data.body.careTargetDetailDTO.gender
        }).catch(err=>{
            console.log(err);
        })
    },

    methods:{
        submit(){
            if(confirm("수락하시겠습니까??") ==true){
            const id = this.$route.params.contractId;
            console.log(id);
            this.$http
            .post(`/api/dashboard/caresitter/contracts/${id}`,{

            }).then((res)=>{
                console.log(res);
                alert("수락완료");
                this.$router.push({ path: '/Main' })
            }).catch(err=>{
                console.log(err);
            })
            }else{
                return false;
            }
        },
    }
}
</script>

<style>
    li{
        list-style: none;
    }
    .profile-page{
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        background-color: white;
    }
    .wrapper{
        max-width: 500px;
        width: 100%;
        box-shadow: 0 0 5px rgba(0, 0, 0,.10);
        margin: auto;
        background: white;
        margin-top: 50px;
        margin-bottom: 50px;
    }
    .wrapper h2{
        text-align: center;
        font-size: 2rem;
    }
    /* .wrapper .inputBox{
        width: 100%;
        height: 50px;
        margin-bottom: 1.3rem;
    } */
    /* .wrapper .inputBox:last-child{
        margin-bottom: 0;
    } */
    .btn{
        width: 100%;
    }
</style>