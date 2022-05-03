<template>
<div>
  <v-card>
    <v-card-title>{{listItem.title}}</v-card-title>
    <v-card-text>
      <p>작성자 : {{listItem.id}}</p>
      <p>시작일 : {{listItem.startDate}}</p>
      <p>종료일 : {{listItem.endDate}}</p>
      <p>급여 : {{listItem.pay}}</p>
      <p>설명 : {{listItem.comment}}</p>
      <v-btn @click="detail">상세보기</v-btn>
    </v-card-text>
  </v-card>
  <!-- <v-card>
    <v-text>
      <v-btn>클릭</v-btn>
    </v-text>
  </v-card> -->
</div>  
</template>

<script>
export default {
  name: 'componentWriteDetail',
  props:{
    listItem:{
      required: true
    }
  },
  data(){
    return{
    }
  },
  methods:{
    detail(){
      this.$emit('detail', this.listItem.id);
      // const user = {
      //   user : this.userId, //신청하는 자신의 id
      //   schedul : this.schedul, //신청하는 사람이 작성한 스케줄
      //   receive : this.subuser //신청 받는 사람의 아이디
      // }
      // axios.post('/api/user/invite', user)
      // .then((result)=>{
      //   if(result == "ok") {
      //     alert('신청완료')
      //   }else{
      //     alert('실패')
      //   }
      // }) 
  }
 }
}
</script>

<style>

</style>