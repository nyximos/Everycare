<template>
    <div>
       <div v-if="!reviews.length">글이 없습니다</div>
       <div v-else>Review({{reviews.length}})
       <hr>
       <v-container>
        <v-card v-for="item in reviews" :key="item.index" class="mt-4">
            <v-card-title>{{item.title}}</v-card-title>
            
            <v-card-text>
                <div class="text-end">
                    {{item.createdAt}}
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
    this.$http.get(`/api/store/products/reviews/${id}`,{
        withCredentials:true
      })
		.then((res)=>{
        console.log(res.data);
        this.reviews=res.data.body
        
      }).catch(err =>{
		console.log(err);
	})
},
data(){
    return{
        reviews:[],
        dataPerPage:4,
        curPageNum:1,
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
        return Math.ceil(this.reviews.length / this.dataPerPage);
      },
      calData() {
        return this.reviews.slice(this.startOffset, this.endOffset)
      },
    }
}
</script>

<style>

</style>