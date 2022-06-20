<template>
    <div>
       <div v-if="!question.length">문의글이 없습니다</div>
       <div v-else>Question({{question.length}})
       <hr>
       <v-container>
        <div class="text-end">
          <v-btn @click="goNewQna">문의 등록</v-btn>
        </div>
        <v-card v-for="item in question" :key="item.index" class="mt-4" @click="QnaDetail(item)">
            <v-card-title>{{item.title}}</v-card-title>
            
            <v-card-text>
                <div class="text-end">
                    {{item.createdAt.slice(0,10)}}
                </div>
                <div>
                    {{item.content}}
                </div>
            </v-card-text>
        </v-card>
          <div class="text-center">
    <v-pagination
      class="mt-3"
      v-model="curPageNum"
      :length="numOfPages"
    ></v-pagination>
  </div>
        </v-container>
    </div>
    </div>
</template>

<script>
export default {
mounted() {
    const id = Number(this.$route.params.contentId);
    this.$http.get(`/api/store/products/${id}/qna`,{
        withCredentials:true
      })
		.then((res)=>{
        console.log(res.data);
        this.question=res.data.body
        
      }).catch(err =>{
		console.log(err);
	})
},
data(){
    return{
        question:[],
        dataPerPage:4,
        curPageNum:1,
    }
},
methods:{
  QnaDetail(item){
    this.$router.push({
      name:'QnaDetail',
      params: {
        contentId: item.id
      }
    })
  },
goNewQna(){
  this.$router.push({
    name:'QnaNew'
  })
}
},
    computed: {
      startOffset() {
        return ((this.curPageNum - 1) * this.dataPerPage);
      },
      endOffset() {
        return (this.startOffset + this.dataPerPage);
      },   
      numOfPages() {
        return Math.ceil(this.question.length / this.dataPerPage);
      },
      calData() {
        return this.question.slice(this.startOffset, this.endOffset)
      },
    }
}
</script>

<style>

</style>