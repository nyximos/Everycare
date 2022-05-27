<template>
    <div>
        <v-card flat>
    <v-card-text>
      <v-container fluid>
        <v-row class="mt-5">
            <v-text-field
            v-model="title"
            placeholder="제목을 입력하세요"
            outlined
          ></v-text-field>
        </v-row>
        <v-row>
            <v-img id="divProfile" 
            :src="'https://localhost:8086/api/images/'+this.image" 
            alt="사진" width="344" height="200"/>
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
    .get(`/api/faq/${id}`,{
        withCredentials:true
      })
	.then((res)=>{
        console.log(res.data);
        this.title=res.data.body.title,
        this.image=res.data.body.fileName,
        this.comment=res.data.body.content
      }).catch(err =>{
				alert(err);
				console.log(err);
			})
},
data(){
    return{
        title: this.title,
        image: this.image,
        comment: this.comment
    }
},
methods:{
    edit(){
    const id = Number(this.$route.params.contentId);
    var formData = new FormData();
    formData.append('title', this.title);
    formData.append('content', this.comment);

    this.$http
    .patch(`/api/admin/faq`,formaData, {
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
    this.$http.delete(`/api/admin/faq`,{
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