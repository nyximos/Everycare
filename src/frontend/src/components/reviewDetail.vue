<template>
    <div>
        <v-card flat>
    <v-card-text>
        
          <v-img id="divProfile" :src="'https://localhost:8086/api/images/'+this.fileName" 
                  class="mb-4"  alt="사진" width="300" height="300"/>
      <v-container fluid>
        <v-rating
            class="text-start"
            v-model="rating"
            background-color="yellow darken-1"
            color="yellow darken-1"
            large
          ></v-rating>
        <v-row class="mt-5">
            <v-text-field
            v-model="title"
            placeholder="제목을 입력하세요"
            outlined
          ></v-text-field>
        </v-row>
          <v-row>
              <v-textarea
              v-model="comment"
              label="내용을 입력하세요"
              outlined
            >
            </v-textarea>
          </v-row>
      </v-container>
      <v-card-actions>
        <v-btn
          text
          @click="edit"
          color="primary">
          수정
        </v-btn>
        <v-btn
          text
          color="error"
          @click="drop">
          삭제
        </v-btn>
        <v-btn
          text
          @click="back">
          취소
        </v-btn>
        
      </v-card-actions>
      </v-card-text>
  </v-card>  
    </div>
</template>

<script>
export default {
mounted() {
     const id = Number(this.$route.params.contentId);
    this.$http
    .get(`/api/store/products/review/${id}`,{
        withCredentials:true
      })
	.then((res)=>{
        console.log(res.data);
        this.fileName = res.data.body.fileName
        this.rating = res.data.body.rating
        this.title=res.data.body.title
        this.comment=res.data.body.content
        this.id=res.data.body.id
      }).catch(err =>{
				alert(err);
				console.log(err);
			})
},
data(){
    return{
        fileName: this.fileName,
        rating: this.rating,
        id: this.id,
        title: this.title,
        comment: this.comment
    }
},
methods:{
    edit(){
    var formData = new FormData();
    formData.append('rating', this.rating);
    formData.append('id', this.id);
    formData.append('title', this.title);
    formData.append('content', this.comment);
    this.$http
    .patch(`/api/dashboard/orders/products/${this.id}`, formData, {
    withCredentials: true
    })
     .then(res => {
      console.log(res);
    })
      .catch(err => {
       console.log(err);
    });
  },
  drop(){
    var Id = {
           id:this.id,
           }
    this.$http
    .delete(`/api/dashboard/orders/products/${this.id}`, {
      withCredentials: true
    })
    .then((res)=> {
      console.log(res)
    }).catch((err)=>{
      console.log(err)
    })
  },
back(){
    this.$router.push({
        path:'/faq'
    })
}
}
}
</script>

<style>

</style>