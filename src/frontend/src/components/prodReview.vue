<template>
    <div>
       <div v-if="!reviews.length">글이 없습니다</div>
       <div v-else>Review({{reviews.length}})
       <hr>
       <v-container>
        <v-card v-for="item in calData" :key="item.index" class="mt-4" @click="detail(item)">
          <v-rating
            class="text-start"
            :value="item.rating"
            background-color="yellow darken-1"
            color="yellow darken-1"
            large
          ></v-rating>
          <v-card-text>
          <v-img id="divProfile" :src="'https://localhost:8086/api/images/'+item.fileName" 
                  class="mb-4"  alt="사진" width="80" height="80"/>
            <h6 class="fw-bold text-black text-start">
              {{item.title}}
              </h6>
                <div class="text-end">
                    {{item.createdAt.slice(0,10)}}
                    <v-btn icon>
                      <v-icon> mdi-cancel</v-icon>
                    </v-btn>
                </div>
                <div class="text-start">
                    {{item.content}}
                </div>
                
            </v-card-text>
        </v-card>
          <div class="text-center">
            <v-pagination
            class="mt-3"
      v-model="curPageNum"
      :length="numOfPages"
      circle
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
        // this.rating= res.data.body
        
      }).catch(err =>{
		console.log(err);
	})
},
data(){
    return{
        // rating: this.rating,
        reviews:[],
        dataPerPage:2,
        curPageNum:1,
    }
},
methods:{
  detail(item){
    this.$router.push({
      name:'ReviewDetail',
      params: {
        contentId: item.id
      }
    })
  },
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