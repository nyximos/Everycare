<template>
<div class="list_wrap">
   <label class="control-label">케어대상인 목록&nbsp;<br class="visible-xs"><span class="sort01">
		<v-btn @click="gocreate" >
          <v-icon left>
            mdi-pencil
          </v-icon>
            케어 대상인 등록
        </v-btn>
		</span></label>
   
   <br/>
   <br/>
   
   <br/>
   
            <ul>
                <li class="item item1" v-for="(p,index) in profiles" :key="index" >
                    <div class="image">사진</div>
                    <div class="cont">
                        <strong>{{p.name}}님</strong>
                        
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
    <hr>
        <v-btn
      @click="schedule(p)"
      outlined
      color="indigo"
    >
      스케줄 관리
    </v-btn>
                    </div>
                </li>
  
            </ul>
        </div>
<!-- <div class="container">
  <label class="control-label">케어대상인 목록&nbsp;<br class="visible-xs"><span class="sub-text text-muted"><i class="text-primary"></i></span><i ng-show="vm.isRequiredOptionalImages" class="icon-must ng-hide" aria-label="필수항목"></i></label>
  
<ul class="ul01">
  
<li class="li01" v-for="(p,index) in profiles" :key="index" >
  <v-card 
    
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
      :src="'https://localhost:8086/api/images/'+p.imageDTOs.storeFileName"
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
<div class="dropZone" id="dropZone" style="width: 300px; height: 410px; border-style: solid; border-color: black; ">
                 <router-link :to="{name:'carepeople'}"><div id="fileDragDesc" > 케어대상인을 등록 해주세요. </div></router-link> 
                </div>
</ul>


                
                
<v-btn class="ma-2" outlined color="indigo" @click="clickme">돌아가기</v-btn>

</div> -->
</template>

<script>
export default {
  data() {
    return {
      profiles: [],
      id: this.$route.params.caretargetsId,
      selection:'',
      imgfile:this.imgfile
    }
  },
  methods: {
    gocreate(){
          this.$router.push({
            path: '/carepeople'
          })
        },
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
          this.imgfile = res.data.body.imgDTOs
          
  }).catch(err => {
    alert(err);
    console.log(err);
  })
}
}
</script>

<style scoped>
* {
    margin: 0;
    padding: 0;
}
ul, li {
    list-style: none;
}
a {
    text-decoration: none;
    color: inherit;
}
.list_wrap {
    width: 1000px;
    margin: 0 auto;
    padding: 100px;
   
    padding: 0;
    text-align: center;
    box-sizing: border-box;
    position: relative;
}

.list_wrap ul {
    font-size: 0;
}
.list_wrap .item {
    display: inline-block;
    width: 30%;
    margin-top: 40px;
    margin-left: 5%;
    box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.2);
}
.list_wrap .item:nth-child(-n+3) {
    margin-top: 0;
}
.list_wrap .item:nth-child(3n-2) {
    margin-left: 0;
}
.list_wrap .item .image {
    width: 100%;
    height: 160px;
    background-color: #ddd;
    background-repeat: no-repeat;
    background-position: 50% 50%;
    background-size: cover;
}










.list_wrap .item .cont {
    padding: 20px;
}
.list_wrap .item strong {
    display: block;
    margin: 0 0 10px 0;
    font-size: 16px;
    letter-spacing: -1px;
}
.list_wrap .item p {
    font-size: 13px;
    letter-spacing: -1px;
}
.list_wrap .item a {
    display: inline-block;
    margin-top: 10px;
    padding: 5px 10px;
    background: #eee;
    font-size: 13px;
    letter-spacing: -1px;
}
.list_wrap .item a:hover {
    background: #325cb2;
    color: #fff;
}
.create{
  float: right;
}
/* #fileDragDesc {
            width: 100%; 
            height: 100%; 
            margin-left: auto; 
            margin-right: auto; 
            padding: 44px; 
            text-align: center; 
            line-height: 685px; 
            
        }
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
} */
</style>