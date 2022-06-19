<template>
<div class="container">
  <label class="control-label">케어대상인 목록&nbsp;<br class="visible-xs"><span class="sub-text text-muted">(<i class="text-primary">1</i>/9)</span><i ng-show="vm.isRequiredOptionalImages" class="icon-must ng-hide" aria-label="필수항목"></i></label>
<ul class="ul01">
  <div class="dropZone" id="dropZone" style="width: 300px; height: 410px; border-style: solid; border-color: black; ">
                
                </div>
<li class="li01" v-for="(p,index) in profiles.slice(0,2)" :key="index">
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
      :src="'https://localhost:8086/api/images/'+p.imageDTOs[0].storeFileName"
      >
    </v-img>

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
      @click="schedule(p)"
      outlined
      color="indigo"
    >
      스케줄 관리
    </v-btn>

        
      </v-chip-group>
    </v-card-text>

  </v-card>
  
  </li>

</ul>


                
                
<v-btn class="ma-2" outlined color="indigo" @click="clickme">돌아가기</v-btn>

</div>
</template>

<script>
export default {
  data() {
    return {
      profiles: [],
      id: this.$route.params.caretargetsId,
      selection:'',
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
  },
   schedule(p) {
      this.$router.push({ name: 'schedule', params: { caretargetsId: p.id }})
    },
  clickme(){
    location.href = '/';
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
.dropZone {
  display: inline-block;
  margin: -16px;
}
ul {
  
  text-align: center;
}
v-chip-group{
text-align: center;
}
li {
display: inline-block;
list-style:none; 

}
.ma-2{
  float: right
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