<template>
<div>
  <v-card falt>
    <v-container fluid>
        <v-row class="mt-5">
            <h2>채용정보</h2>
        </v-row>
        <v-row>
            <v-col cols="4">
                <!-- <h2>{{product.member}}</h2> -->
            </v-col>
            <v-col cols="8">
                <v-subheader>
                    <!-- 돌봄정보 <span>{{product.caretarget}}</span> -->
                </v-subheader>
            </v-col>
        </v-row>
        <hr class="mt-5 mb-5">
        <v-row>
            <h2>상세정보</h2>
        </v-row>
        <v-row>
            <!-- <v-subheader>희망분야</v-subheader> <span>{{product.caretype}}</span>
            <v-subheader>희망 시작/종료일</v-subheader><span>{{product.dates}}</span>
            <v-subheader>희망급여</v-subheader><span>{{product.pay}}</span>
            <v-subheader>희망성별</v-subheader><span>{{product.sittersex}}</span>
            <v-subheader>희망 요일</v-subheader><span>{{product.day}}</span> -->
            <!-- <v-subheader>희망 시간</v-subheader><span>dfsdfa</span> -->
        </v-row>
        <div class="text-center">
          <v-btn
          @click="submit">
          신청
          </v-btn>
          <v-btn :to="{name: 'Job_list'}">
            이전
            </v-btn>
        </div>
        
    </v-container>
  </v-card>
</div>  
</template>

<script>
// import axios from 'axios'
export default {
  name: 'componentWriteDetail',
  // props:{
  //   postId:{
  //     type: Number,
  //     required: true
  //   }
  // },
  data(){
    return{
      product: null
    }
  },
  // mounted() {
  //   const url = `/writedetail/${this.postId}`
  //   axios.get(url)
  //   .then(res => {
  //     this.product = res.data
  //   })
  //   .catch(error => {
  //     console.log(error)
  //   })
  // },
  methods:{
    submit(){
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