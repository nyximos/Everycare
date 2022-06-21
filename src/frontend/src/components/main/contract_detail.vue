<template>
    <div class="profile-page">
        <br><br>
    <div class="wrapper">
        
        <div class="inputBox">
            <h3>계약서</h3>
            <br>
        <ul>
            <li><h2>케어대상인 정보</h2></li>
            <li>아이디:{{contract.careTargetDetailDTO.id}}</li>
            <li>이름:{{contract.careTargetDetailDTO.name}}</li>
        <li>주소:{{contract.careTargetDetailDTO.address}}</li>
            <li>상세주소:{{contract.careTargetDetailDTO.detailedAddress}}</li>
           <li>생년월일:{{contract.careTargetDetailDTO.birth}}</li>
          <li>성별:{{contract.careTargetDetailDTO.gender}}</li>
          <li>키:{{contract.careTargetDetailDTO.height}}</li>
          <li>몸무게:{{contract.careTargetDetailDTO.weight}}</li>
           <li>타입:{{contract.careTargetDetailDTO.careType}}</li>
          <li>comment:{{contract.careTargetDetailDTO.comment}}</li>
          <li>백신: {{contract.careTargetDetailDTO.coronaTest}}</li>
          <li>cctv:{{contract.careTargetDetailDTO.isCctvAgreement}}</li>
          <li>케어등급:{{contract.careTargetDetailDTO.longTermCareGrade}}</li>
          <li>펫:{{contract.careTargetDetailDTO.pet}}</li>
          <li>zipcode:{{contract.careTargetDetailDTO.zipcode}}</li>
            <br><br><br>
          <li><h2>계약정보</h2></li>
          <li>title:{{contract.contractJobOfferDTO.title}}</li>
          <li>아이디:{{contract.contractJobOfferDTO.id}}</li>
          <li>날짜:{{contract.contractJobOfferDTO.day}}</li>
          <li>시작시간:{{contract.contractJobOfferDTO.desiredStartTime}}</li>
          <li>끝나는시간{{contract.contractJobOfferDTO.desiredEndTime}}</li>
          <li>시작날짜:{{contract.contractJobOfferDTO.startDate}}</li>
          <li>끝나는 날짜{{contract.contractJobOfferDTO.endDate}}</li>
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
        pay(){
            var tossPayments = TossPayments("test_ck_Lex6BJGQOVDGPJNGkJq3W4w2zNbg");
            var orderId = new Date().getTime();
            let customDate = new Date();
            const id = this.$route.params.contractId;
            
            console.log(id);
            console.log(orderId);
            console.log(customDate);
            
            var paymentData = {
                amount:this.contract.contractJobOfferDTO.amount,
                orderId:new Date().getTime(),
                orderName:this.contract.careTargetDetailDTO.name+'계약서',
                customerName:'박토스',
                successUrl: `https://localhost:8086/api/dashboard/contracts/payments?contractId=${id}`,
                failUrl: 'https://localhost:8080/fail'
            };
            // console.log(paymentData)
            tossPayments.requestPayment("카드", paymentData);
        
            // this.$http
            // .get('/api/success',)
        }
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