<template>
  <div class="content">
      <div class="title">
        <h2 class="subtitle">완료된 케어노트</h2>
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
            note : []
        }
    },
    mounted(){
        const contractId = this.$route.params.contentId;
        this.$http
        .get(`/api/carenote/contracts/completion/${contractId}`,{
            withCredential:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.note = res.data.body
            // this.date = res.data.body.endTime.format('YYYY-MM-DD');
            // console.log(this.date)
            
        })
        .catch((err)=>{
            console.log(err);
        })
    },
    methods:{    
        detail(n){
            // const contract ={
            //     contractId : this.$route.params.contentId,
            // };
            // // console.log(caretarget)
            // this.$store.commit('carenoteStore/contract', contract);
            
        // console.log(c.id);
            this.$router.push({name:'note_detail', params:{noteId:n.id , contractId : this.$route.params.contentId}});
        }
    }
}
</script>

<style>
    .content{
        height: 700px;
        width:100%;
    }
    .subtitle{
      padding-bottom: 60px;
    }
    .title{
        width: 100%
    }
    .title h2{
        text-align: center;
        padding: 70px;
    }
    .v-card__title{
        padding: 5px;
        margin-top: 10px;
    }
    
    .v-card__title .write{
        margin: 0 auto;
    }
</style>