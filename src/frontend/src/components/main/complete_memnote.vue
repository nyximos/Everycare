<template>
  <div class="content">
      <div class="title">
        <h2>완료된 케어노트</h2>
      </div>
      <v-card
    class="mx-auto"
    max-width="300"
    v-for="(n,index) in note" :key="index">
    <v-img 
    v-if="n.storename==''"
      src="'https://localhost:8086/api/images/' + n.storeName"
      height="250px"
    ></v-img>
    <v-img 
    v-else
    src="@/assets/note.png"
    height="200px"
    width="200px"
    style="margin:0 auto;"
    ></v-img>
    <v-card-title>
        <h4 class="write">{{n.date}}</h4>
    </v-card-title>
    <v-card-title>
      <h5 class="write">케어시터:</h5>
    </v-card-title>
    <v-card-title>
      <h5 class="write" format="yyyy-MM-dd HH:mm:ss">{{n.startTime}} ~ {{n.endTime}}</h5>
    </v-card-title>
    <v-card-actions>
      <v-btn
        color="#69f0ae"
        width="100%"
        text
        @click="detail(n)"
      >
        스케줄 관리
      </v-btn>

      <v-spacer></v-spacer>
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
        const contractId = this.$route.params.contentId
        // const carenotes =
        this.$http
        .get(`/api/member/caretargets/${caretargetId}/contracts/complition/${contractId}/${carenotes}`,{
            withCredential:true
        }).then((res)=>{
            console.log(res)
            this.note = res.data.body
        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        notedetail(c){
            this.$router.push({name:'complete_membernote_detail',params:{}})
        }
    }
}
</script>

<style>

</style>