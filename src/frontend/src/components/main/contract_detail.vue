<template>
  <div>
      <ul>  
          <li><h2>케어대상인 정보</h2></li>
          주소: <li>{{contract.careTargetDetailDTO.address}}</li>
          생년월일: <li>{{contract.careTargetDetailDTO.birth}}</li>
          타입: <li>{{contract.careTargetDetailDTO.careType}}</li>
          <li>{{contract.careTargetDetailDTO.comment}}</li>
          백신: <li>{{contract.careTargetDetailDTO.coronaTest}}</li>
          <li>{{contract.careTargetDetailDTO.detailedAddress}}</li>
          <li>{{contract.careTargetDetailDTO.gender}}</li>
          <li>{{contract.careTargetDetailDTO.height}}</li>
          <li>{{contract.careTargetDetailDTO.id}}</li>
          <li>{{contract.careTargetDetailDTO.isCctvAgreement}}</li>
          <li>{{contract.careTargetDetailDTO.longTermCareGrade}}</li>
          <li>{{contract.careTargetDetailDTO.name}}</li>
          <li>{{contract.careTargetDetailDTO.pet}}</li>
          <li>{{contract.careTargetDetailDTO.weight}}</li>
          <li>{{contract.careTargetDetailDTO.zipcode}}</li>
            <br><br><br>
          <li><h2>계약정보</h2></li>
          <li>{{contract.contractJobOfferDTO.amount}}</li>
          <li>{{contract.contractJobOfferDTO.day}}</li>
          <li>{{contract.contractJobOfferDTO.desiredEndTime}}</li>
          <li>{{contract.contractJobOfferDTO.desiredStartTime}}</li>
          <li>{{contract.contractJobOfferDTO.endDate}}</li>
          <li>{{contract.contractJobOfferDTO.id}}</li>
          <li>{{contract.contractJobOfferDTO.pay}}</li>
          <li>{{contract.contractJobOfferDTO.startDate}}</li>
          <li>{{contract.contractJobOfferDTO.title}}</li>
      </ul>  
      <v-btn class="ma-2" outlined color="indigo" @click="submit">결제</v-btn>
  </div>
</template>

<script>
export default {
    data(){
        return{
            // target:[
            // ],
            // id:this.address,
            // address:this.address,
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
            console.log(this.contract)
            // this.target = res.data.body.careTargetDetailDTO;
            // this.address = res.data.body.careTargetDetailDTO.address;
            // console.log(this.address);
            // console.log(res.data.body.careTargetDetailDTO);

        }).catch(err=>{
            console.log(err);
        })
    },
    methods:{
        submit(){
            const id = this.$route.params.contractId;
            console.log(id);
            this.$http
            .post(`/api/dashboard/caresitter/contracts/${id}`,{

            }).then((res)=>{
                console.log(res);
            }).catch(err=>{
                console.log(err);
            })
        }
    }
}
</script>

<style>

</style>