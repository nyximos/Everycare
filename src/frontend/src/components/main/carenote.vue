<template>
  <div class="content">
    <h1 class="title">당일케어노트</h1>
    <div v-if="!note.length">당일케어노트가 없습니다.</div>
    <v-card v-else class="mx-auto" max-width="344" v-for="(n,index) in note" :key="index">
        <v-img :src="'https://localhost:8086/api/images/' + n.storeName" height="200px" width="200px" alt="사진없음" style="margin:0 auto;"></v-img>
    <v-card-title>
      <h2 class="name">{{n.careTargetName}} 님</h2>
    </v-card-title>
    
    <!-- <v-card-subtitle style="margin-top:1px;">
      시작시간:{{n.startTime}} ~ 종료시간:{{n.endTime}}
    </v-card-subtitle> -->
    <v-card-actions>
        <div class="button">
            <v-btn class="ma-2" outlined color="success" @click="start(n)">케어시작</v-btn>
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
        .get('/api/carenotes',{
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
            const targetData = {
                targetName:n.careTargetName
            }
            this.$store.commit('caretargetStore/targetData', targetData);
            this.$router.push({name:'carenotestart' , params:{contentId:n.id}  })
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
</style>