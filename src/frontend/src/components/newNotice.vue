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
  <v-file-input
    v-model="file"
    label="File input"
    outlined
    dense
  ></v-file-input>
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
          :to="{name:'Notice'}">
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
     title: this.title,
     file: [],
     comment: this.comment
 }   
},
methods:{
    submit(){
      var date = new Date();
      var year = date.getFullYear();
      var month = date.getMonth();
        month += 1;
        if (month <= 9){
        month = "0" + month;
        }

      var day = date.getDate();
        if (day <= 9){
        day = "0" + month;
        }
        var hours = ('0' + date.getHours()).slice(-2); 
        var minutes = ('0' + date.getMinutes()).slice(-2);
        var seconds = ('0' + date.getSeconds()).slice(-2);
        var miseconds = date.getTime();
      var today = year + '-' + month + '-' + day +' '+ hours + ':' + minutes  + ':' + seconds + '.'+ miseconds;
      console.log(today)

      var formData = new FormData();
      formData.append('id',1);
      formData.append('title',this.title);
      formData.append('content',this.comment);
      formData.append('BoardCategory',1);
    //   formData.append('createdAt',);
    //   formData.append('updatedAt',today);
      formData.append('count',1);
      formData.append('attachFile', this.file);
      formData.append('fileName','배송전문의');
      formData.append('filePath','dsjfahk:dkfja@kdfjal.blcm');
      this.$http
      .post('/api/admin/notice', formData,{
       withCredentials:true
      })
     .then(res => {
      console.log(res);
      })
     .catch(err => {
       console.log(err);
       console.log(this.hi)
    });
    }
},
computed: {
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