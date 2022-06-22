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

    v-if="n.storeFileName ==null"

    src="@/assets/note.png"
    height="200px"
    width="200px"
    style="margin:0 auto;"
    ></v-img>

    <v-img 
      v-else
      :src="'https://localhost:8086/api/images/' + n.storeFileName"
      width="250px"
      height="250px"
      style="margin:0 auto;"
    ></v-img>
    <!-- <v-card-title>
        <h4 class="write">날짜:{{n.date}}</h4>
    </v-card-title> -->
    <v-card-title>
      <!-- <h5 class="write">케어시터:</h5> -->
    </v-card-title>
    <v-card-title>
      <h6 class="write">시작일/종료일</h6>
      <h5 class="write">{{n.startTime.slice(0,10)}} ~ {{n.endTime.slice(0,10)}}</h5>

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


        })
        .catch((err)=>{
            console.log(err);
        })
    },
    methods:{    
        detail(n){


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


    .title{
        width: 100%
    }
    .title h2{
        text-align: center;

        padding: 30px;

    }
    .v-card__title{
        padding: 5px;
        margin-top: 10px;
    }
    
    .v-card__title .write{
        margin: 0 auto;
    }

    .table{
    font-size :15px;
    border-collapse: collapse;
    width: 1200px;
    margin-top:20px;
    border: 1px solid gray;    
    text-align: center;
}

.thead td {
    background: #B2B2B2;
    height: 60px;
    font-size: 20px;
}

td{
    height: 40px;
    border-bottom:1px solid black;
}


</style>