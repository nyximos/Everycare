<template>
<div class="container">
<ul class="ul01">
<li class="li01" v-for="(p,index) in profiles" :key="index">
  <v-card
    :loading="loading"
    class="mx-auto my-12"
    max-width="300"
  >
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-img
      height="250"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
      
    ></v-img>{{p.attachFiles}}

    <v-card-title text-align="center">{{p.name}}님</v-card-title>

    <v-card-text>
      <v-chip-group
        v-model="selection"
        active-class="deep-purple accent-4 white--text"
        column
      >
        <v-btn color="error" @click="drop(p)">
      삭제
    </v-btn>
    <v-btn 
      tile
      color="success"
      @click="update(p)"
    >
      
      수정
    </v-btn>
    <v-btn @click="move(p)">보기</v-btn>
        <v-btn
      
      outlined
      color="indigo"
    >
      스케줄 관리
    </v-btn>

        
      </v-chip-group>
    </v-card-text>

  </v-card>
  </li>
  <li>
    <a href="#" class="_3vyujQUKFF">케어대상인을 등록해주세요</a>
  </li>
</ul>


</div>
</template>

<script>
export default {
  data() {
    return {
      profiles: [],
      //  id: this.$route.params.caretargetsId
    }
  },
  methods: {
    move(p) {
      this.$router.push({ name: 'CaretargetsDetail', params: { caretargetsId: p.id }})
    },
    update(p) {
      this.$router.push({ name: 'TargetsUpdate', params: { caretargetsId: p.id }})
    },
    drop(p){
    this.$router.push({ params: { caretargetsId: p.id }})
    this.$http.delete(`/api/dashboard/caretargets/${p.id}`,{
      withCredentials: true
    })
    .then((res)=> {
      console.log(res)
    }).catch((err)=>{
      console.log(err)
    })
  }

  },
  
  mounted() {
    this.$http
    .get('/api/dashboard/caretargets', {
      withCredentials: true
    })
      .then((res) => {
          console.log(res.data.body)
          this.profiles = res.data.body
  }).catch(err => {
    alert(err);
    console.log(err);
  })
}
}
</script>

<style scoped>
v-chip-group{
text-align: center;
}
li {
display: inline-block;
list-style:none; 

}


.li01{
  
  width: 400px;
  height: 400px;
}

._3vyujQUKFF {
    display: inline-block;
    width: 300px;
    height: 410px;
    text-align: center;
    border: 1px dashed #666;
    background-color: #fff;
    font-size: 17px;
    font-weight: bold;
    color: #222;
}

</style>