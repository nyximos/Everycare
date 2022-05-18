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
      </div>
    </v-card-text>
	<!-- <v-btn class="ma-2" outlined color="indigo" @click="choose">선택</v-btn> -->
  </v-card>
  <br><br><br><br><br><br><br><br>
  </div>
</template>

<script>
export default {
  data(){
    return{
      joboffer:[

      ],
      // props:[
      //   'caresitterId'
      // ],
      caresitterId:this.$store.state.careprofileStore.caresitterId,
    }
  },
  mounted(){
    const id = this.$route.params.caresitterId;
    console.log(id)
    this.$http
    .get(`/api/caresitters/${id}/recruitions`,{
      withCredentials:true
    })
    .then((res)=>{
      this.joboffer = res.data.body
      console.log(res.data.body)
    }).catch(err =>{
      console.log(err)
    })
    
  },
  methods:{
    choose(j){
      const id = this.caresitterId;
      const careSitterId = this.$store.state.careprofileStore.caresitterId;
      const jobId = j.id;
      console.log(careSitterId);
      console.log(jobId);
      this.$http
      .post(`/api/caresitters/${id}/contracts`,
      {params: {careSitterId:careSitterId}},
      {params:{jobOfferId: jobId}},
      {withCredentials:true}
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