<template>
  <div>
    <br><br><br>
      <v-card
      v-for="(c,index) in contract" :key="index"
    class="mx-auto"
    max-width="344"
  >
    <v-card-text>
      <div>아이디:{{c.id}}</div>
      <p class="text-h4 text--primary">
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
        color="deep-purple accent-4"
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
            // const id = c.id;
            // console.log(id);

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