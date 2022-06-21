<template>
  <div class="content">
      <v-card class="mx-auto" max-width="500" v-for="(c,index) in contract" :key="index">
        <h5>계약서:{{index+1}}</h5>
        <v-img :src="'https://localhost:8086/api/images/'+ c.careSitterStoreFileName" height="250px" width="300px" style="margin:0 auto;"></v-img>
      <v-card-title>
        시터 이름:{{c.careSitterName}} <br>
        amount:{{c.amount}}
      </v-card-title>
      <v-card-subtitle>
        날짜: {{c.day}}<br>
        계약 날짜:{{c.payDateTime.slice(0,10)}}
      </v-card-subtitle>
    <v-card-actions>
      <div style="margin:0 auto;">
        <v-btn color="#69f0ae" text @click="note(c)">노트</v-btn>
      </div>
    </v-card-actions>
  </v-card>
    
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
        const targetId = this.$route.params.targetId;
        this.$http
        .get(`/api/member/caretargets/${targetId}/contracts/complition`,{

            withCredential:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.contract = res.data.body
        })
        .catch((err)=>{
            console.log(err);
        })
    },
    methods:{
        note(c){        
            this.$router.push({ name: 'complete_membernote', params: { contractId: c.id, targetId:this.$route.params.targetId }})
        }
    }
}
</script>

<style>
  .content{
    padding: 40px;
    display: flex;
  }
</style>