<template>
  <div class="content">
    <h1 class="title">완료된 케어노트</h1>
    <v-card class="mx-auto" max-width="344" v-for="(n,index) in note" :key="index">
    <div v-if="storeName=''" class="img_div">
        <v-img src="@/assets/writing.png" width="75%" alt="사진없음"></v-img>
    </div>
    <div v-else class="img_div">
        <img :src="'https://localhost:8086/api/images/' + n.storeName" width="75%"  class="img">
    </div>
    <v-card-title>
      <h2 class="name">{{n.careTargetName}} 님</h2>
    </v-card-title>
    <v-card-subtitle style="margin-top:1px;">
      <h6 class="start">시작시간:{{n.startTime}} ~ 종료시간:{{n.endTime}}</h6>
    </v-card-subtitle>
    <v-card-actions>
        <div class="button" >
            <v-btn class="ma-2" outlined color="indigo" @click="start(n)">스케줄 보기</v-btn>
        </div>
    </v-card-actions>
  </v-card>
  </div>
</template>

<script>
export default {
    data(){
        return{
            note:[]
        }
    },
    mounted(){
        this.$http
        .get('/api/member/carenotes',{
            withCredentail:true
        })
        .then((res)=>{
            console.log(res.data.body)
            this.note = res.data.body
        })
        .catch((err)=>{
            console.log(err)
        })

    },
    methods:{
        start(n){
            this.$router.push({name:'memberschedule' , params:{contentId:n.id}  })
        }
    }
}
</script>

<style>
    .title{
        font-size: 250px;
        padding-bottom: 35px;
        text-align: center;
    }     
    .content{
        width: 100%;
        margin-top: 30px;
        margin-bottom: 100px;
    }
    .name{
        margin: 0 auto;
    }
    .button{
        margin: 0 auto;
    }
    .img{
        margin: 0 auto;
    }
    /* .img_div{
        text-align: center;
        margin: 0 auto;
    } */
    .v-responsive{
        margin: 0 auto;
    }
    .start{
        text-align: center;
    }
</style>