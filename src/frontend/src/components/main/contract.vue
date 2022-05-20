<template>
  <div class="box">
    <br><br>
      <v-card class="mx-auto" max-width="344" v-for="(j,index) in joboffer" v-bind:key="index" @click="choose(j)">
    <v-card-text>
      <div>희망시간: <p>{{j.endTime}}</p></div>
      <p class="text-h6 text--primary">
        {{j.title}}
      </p>
      <div class="text--primary">
        이름: {{j.careTargetName}} <br>
        시작일: {{j.startDate}} ~ 종료일: {{j.endDate}}<br>
        스케줄: {{j.scheduleName}}
        {{j.id}}
      </div>
    </v-card-text>
	<!-- <v-btn class="ma-2" outlined color="indigo" @click="choose">선택</v-btn> -->
  </v-card>
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
      caresitterId:this.$store.state.careprofileStore.caresitterId,
    }
  },
  mounted(){
    const id = this.caresitterId;
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
      const searchParams ={
        jobOfferId: j.id,
        careSitterId:this.$store.state.careprofileStore.caresitterId
      }
      const id = this.caresitterId
      // const jobId = j.id;
      // console.log(id)
      // console.log(jobId);
      console.log(searchParams);
      // var params = new URLSearchParams();
      // params.append('careSitterId',id);
      // params.append('jobOfferId', jobId);;
      this.$http
      .post(`/api/caresitters/${id}/contracts`,
      {params:searchParams}
      // {params:{jobOfferId:jobId,careSitterId:id}},
          // {params:{careSitterId:id}},
      // {params: {careSitterId:id},params:{jobOfferId:jobId}},
      // {params:{jobOfferId:jobId},params:{careSitterId:id}},
      // {withCredentials:true}
      ).then((res)=>{
        console.log(res);
      }).catch(err=>{
        console.log(err)
      })
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
</style>