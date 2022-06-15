<template>
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
          text>
          Cancel
        </v-btn>
        <v-spacer></v-spacer>
        
        <v-btn
          :disabled="!formIsValid"
          text
          color="primary"
          @click="submit">
          Add
        </v-btn>        
      </v-card-actions>
      </v-card-text>
  </v-card>    
</template>

<script>
export default {
data(){
    return{
        title:this.title,
        comment: this.comment,
    }
},
methods:{
submit(){
      var formData = new FormData();
      formData.append('title',this.title);
      formData.append('content',this.comment);
      this.$http
      .post('/api/admin/faq', formData,{
       withCredentials:true
      })
     .then(res => {
      console.log(res);
      })
     .catch(err => {
       console.log(err);
    });
    }
},
computed:{
    formIsValid(){
      return (
        this.title &&
        this.comment
      )},
}
}


</script>

<style>

</style>