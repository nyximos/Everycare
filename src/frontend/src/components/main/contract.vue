<template>
<div>
  <div class="box">
    <br><br>
      <v-card class="mx-auto" height="230" width="400" v-for="(j,index) in joboffer" v-bind:key="index" @click="choose(j)">
    <v-card-text>
      <div>희망시간: {{j.endTime}}</div>
      <p class="text-h6 text--primary">
        {{j.title}}
      </p>
      <div class="text--primary">
        <h6>이름: {{j.careTargetName}}</h6>
        <h6>시작일: {{j.startDate}}</h6>
        <h6>종료일: {{j.endDate}}</h6>
        <h6>스케줄: {{j.scheduleName}}</h6>
      </div>
    </v-card-text>
	<!-- <v-btn class="ma-2" outlined color="indigo" @click="choose">선택</v-btn> -->
  </v-card>
  </div>
  <br><br><br><br><br><br><br><br>  
</div>
</template>

<script>
// import { param } from 'jquery';
// import 'url-search-params-polyfill';

export default {
  data(){
    return{
      joboffer:[

      ],
      caresitterId:this.$store.state.careprofileStore.id,
    }
  },
  mounted(){
    const id = this.$store.state.userStore.careSitterId;
    console.log(id)
    this.$http
    .get(`/api/caresitters/${id}/recruitions`,{
      withCredentials:true
    })
    .then((res)=>{
      this.joboffer = res.data.body
      console.log(res.data.body);
    }).catch(err =>{
      console.log(err)
    })
    
  },
  methods:{
    choose(j){
      if(confirm("정말로 신청하시겠습니까??") ==true){
        const id =this.$store.state.careprofileStore.id;
        const jobId = j.id;
        console.log(id);
        console.log(jobId);
        this.$http
        .post(`/api/caresitters/${id}/contracts?jobOfferId=${jobId}&careSitterId=${id}`,
        ).then((res)=>{
          console.log(res);
          alert("신청이 완료되었습니다.")
          this.$router.push({ path: '/Main' })
        }).catch(err=>{
          console.log(err)
        })
      }else{
        return false;
      }
    }
  }
}
</script>

<style>
  .box{
    display: flex;
    padding: 10px; 
  }
  .mx-auto{
    margin:20px;
  }
  .text--primary{
    margin-bottom:10px;
  }
</style>
