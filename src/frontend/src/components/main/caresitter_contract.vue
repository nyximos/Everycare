<template>
  <div>
    <br><br><br>
    <div v-if="!contract.length">신청된 계약서가 없습니다.</div>
      <v-card
      v-for="(c,index) in contract" :key="index"
    class="mx-auto"
    max-width="344"
  >
    <v-card-text>
      <div>아이디:{{c.id}}</div>
      <p class="text-h6 text--primary">
        제목:{{c.title}}
      </p>
      급여:<span>{{c.amount}}</span>
      <div class="text--primary">
        날짜:{{c.startDate}}~{{c.endDate}}
      </div>
    </v-card-text>
    <v-card-actions>
      <v-btn
        text
        color="success"
        @click="move(c)"
      >
        상세보기
      </v-btn>
    </v-card-actions>
  </v-card>
  <br><br><br><br><br><br><br><br><br><br>
  </div>
</template>

<script>
export default {
    data(){
        return{
            contract:[]
        }
    },
    mounted(){
        this.$http
        .get('/api/dashboard/caresitter/contracts',{
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
        move(c){
            this.$router.push({name:'contract_detail',params:{contractId:c.id}})
        }
    }
}
</script>

<style>
  .mx-auto{
    margin-bottom: 50px;
  }
</style>