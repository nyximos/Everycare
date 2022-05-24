<template>
  <div class="sc-component">
      <li class="sche_list" v-for="(postItem, i) in postItems" :key="i">
            <v-card 
            class="mx-auto" 
            max-width="300" 
            >
            <v-card-text>
                {{postItem.requirement}}
                </v-card-text>
                <v-card-actions>
                 <v-btn x-small color="primary" @click="update(postItem)" dark> 수정 </v-btn>
                <v-btn x-small color="error" dark @click="delet(postItem)"> 삭제 </v-btn>
           </v-card-actions>
           </v-card>
        </li>
          <v-btn
            class="back_btn"
            color="grey"
            dark
            @click="back_pg"
          >
            뒤로
          </v-btn>  
    <v-row justify="center">
        
      <v-dialog
        v-model="dialogPg"
        @click:outside="closeDialog"
        width="500"
        scrollable
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            class="sc-add-btn"
            color="#69f0ae"
            dark
            v-bind="attrs"
            v-on="on"
          >
            활동 추가
          </v-btn>
        </template>
        <v-card>
          <v-card-title>활동 등록</v-card-title>
          <v-divider></v-divider>
          <v-card-text style="height: 300px">
            <v-card-text class="v-time-picker">
              <div>
          <v-row>
          <v-col cols="4">시작시간</v-col>
          <v-col cols="6">
            <input type="time"
            v-model="startTime"
            name="StartTime"
            id="StartTime"></v-col>
          </v-row>
        </div>
        <div>
          <v-row>
          <v-col cols="4">종료시간</v-col>
          <v-col cols="6">
            <input type="time" 
            v-model="endTime"
            name="EndTime"
            id="EndTime"></v-col>
          </v-row>
        </div>
            </v-card-text>
            <v-container fluid>
              <v-textarea
                v-model="requirement"
                required
                label="요구사항"
              ></v-textarea>
              <v-select name="cate" id="cate" v-model="category" :items="cate_level1" label="카테고리" item-text="name" item-value="value" @change="catego($event)"></v-select>
              <v-select name="cate1" id="cate1" v-model="category1" :items="cate_level2" label="카테고리" item-text="name" item-value="value" @change="catego1($event)" v-if="select1" required></v-select>
              <v-select name="cate2" id="cate2" v-model="category2" :items="cate_level3" label="카테고리" item-text="name" item-value="value" v-if="select2" required></v-select>
           
            <!-- <select class="" >
              <option v-for="(c,i) in ccc" :key="i">
                <p v-if="c.level=='1'">
                  {{c.name}}
                  {{c.name}}
                </p>
              </option>
            </select> -->
            </v-container>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-btn color="blue darken-1" text @click="closeDialog">
              닫기
            </v-btn>
            <v-btn color="blue darken-1" text @click="dialogsav"> 저장 </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
    
  </div>
</template>

<script>
export default {
    data() {
    return {
      postItems: [],
      actstartTime: "",
      startTime:'',
      endTime:'',
      actendTime: "",
      requirement: "",
      tarid: this.$route.params.caretargetsId,
      schid: this.$route.params.scheduleId,
      level: "",
      activityClassificationId: "",
      dialogPg: false,
      select1:false,
      select2:false,
      category:'',
      category1:'',
      category2:'',
      cate_level1:['노인','임산부','아동','환자'],
      cate_grand:['인지활동','정서지원'],
      cate_child1:['등하원','학습','놀이'],
      cate_san1:['신생아돌봄','산후조리'],
      cate_level3:[],
      cate_in:[
            {name:'요리', value:18},
            {name:'옷 개기', value:19},
            {name:'그림', value:20},
            {name:'퍼즐', value:21},
            {name:'화투', value:22}
      ],
      cate_hel:[
            {name:'스마트폰', value:23},
            {name:'인터넷', value:24},
            {name:'고민나눔', value:25},
            {name:'가족소통', value:26}
      ],
      cate_child:[
            {name:'등원', value:27},
            {name:'하원', value:28}
      ],
      cate_study:[{name:'과학', value:29},{name:'영어', value:30},{name:'한글', value:31},{name:'하브루타', value:32}],
      cate_play:[
            {name:'책', value:33},
            {name:'보드게임', value:34},
            {name:'노래', value:35},
            {name:'라디오', value:36},
            {name:'인형', value:37},
            {name:'교구', value:38},
            {name:'블록', value:39},
            {name:'미술', value:40},
            {name:'놀이터', value:41}
      ],
      cate_san:[{name:'신생아 돌봄', value:42}],
      cate_jo:[
            {name:'산후마사지', value:43},
            {name:'산후용 음식준비', value:44},
            {name:'호흡법', value:45},
            {name:'스트레칭', value:46},
            {name:'수유', value:47}
      ],
      cate_par:[{name:'재활운동', value:48},{name:'인지활동', value:49}],
      cate_ga:[
            {name:'쓸기', value:50},
            {name:'닦기', value:51},
            {name:'환기', value:52},
            {name:'먼지털기', value:53},
            {name:'빨래', value:54},
            {name:'식사도움', value:55},
            {name:'장보기', value:56},
            {name:'밥짓기', value:57},
            {name:'설거지', value:58},
            {name:'간식', value:59},
            {name:'다림질', value:60},
      ],
      cate_help:[{name:'이동', value:61},{name:'병원동행', value:62}],
      cate_activity: [
            {name:'세면', value:63},
            {name:'양치', value:64},
            {name:'목욕', value:65},
            {name:'머리 감기기', value:66},
            {name:'식사', value:67},
            {name:'환복', value:68},
            {name:'자세변경', value:69},
            {name:'산책', value:70},
            {name:'운동', value:71},
            {name:'스트레칭', value:72},
            {name:'복약', value:73}
      ],
      // ccc:[

      // ],
      // cate:[
      //   // {name:this.ccc[0].name}
      // ],
    };
  },
  mounted() {
   const id = this.$route.params.caretargetsId;
    const scid = this.$route.params.scheduleId;
    this.$http
      .get(`/api/dashboard/caretargets/${id}/schedules/${scid}`, {
        withCredentials: true,
      })
      .then((res) => {
        console.log(res);
        this.postItems = res.data.body.activityInformationViewDTOs;
      })
      .catch((err) => {
        console.log(err);
      })
    this.$http
    .get('/api/activity-categories',{
      withCredentials:true,
    })
    .then((res)=>{
      console.log(res.data);
      // if(res.data.body.level == 1){
      this.ccc=res.data.body;
      // }
      // console.log(this.ccc)
    })
    .catch((err) => {
        console.log(err);
      })
  },
  
  methods: {
      back_pg(){
        this.$router.push({ name: 'schedule' });
        window.location=document.referrer
      },
    closeDialog() {
      this.$refs.form.reset();
    },
    dialogsav() {
      if(this.category2=='') {
        alert("카테고리를 선택해주세요!");
        return 0;
      }
      const starttime = this.startTime
      const endtime = this.endTime
      console.log(starttime)
      console.log(endtime)
      const id =this.schid
      console.log(id)
      var actformData = new FormData();
      actformData.append("activityClassificationId", this.category2);
      actformData.append("startTime", this.startTime);
      actformData.append("endTime", this.endTime);
      actformData.append("requirement", this.requirement);
    
      this.$http
        .post(
          `/api/dashboard/caretargets/${this.tarid}/schedules/${this.schid}/activities`,
          actformData,
          {
            withCredentials: true,
          }
        )
        .then((res) => {
          console.log(res);
          this.dialogPg = false;
          // this.$router.go();
        })
        .catch((err) => {
          console.log(err);
        });
         
    },
    scheBack() {
        this.$router.push({ name: "schedule" });
      this.$router.go();
    },
    addActive() {
      this.dialogPg = true;
      console.log(this.id);
    },
    dialogcel() {
      this.dialogPg = false;
      this.$refs.form.reset();
    },
    catego(event){
      this.select1=true
      if(event =='노인'){
      this.cate_level2 =  this.cate_grand 
      this.cate_level2.push('가사','일상생활','신체활동'); 
      }else if(event =='아동'){ 
      this.cate_level2 =  this.cate_child1
      this.cate_level2.push('가사','일상생활','신체활동');
      }else if(event =='임산부'){ 
      this.cate_level2 =  this.cate_san1
      this.cate_level2.push('가사','일상생활','신체활동');
      }else if(event =='환자'){
      this.cate_level2 = this.cate_par
      this.cate_level2.push('가사','일상생활','신체활동');
      }
    },
    catego1(event){
      if(event =='학습'){
        this.select2=true
        this.cate_level3 = this.cate_study
      }else if(event == '등하원'){
        this.select2=true
        this.cate_level3 = this.cate_child
      }else if(event == '놀이'){
        this.select2=true
        this.cate_level3 = this.cate_play
      }else if(event =='가사'){
        this.select2=true
        this.cate_level3 = this.cate_ga
      }else if(event =='일상생활'){
        this.select2=true
        this.cate_level3 = this.cate_help
      }else if(event =='신체활동'){
        this.select2=true
        this.cate_level3 = this.cate_activity
      }else if(event =='가사'){
        this.select2=true
        this.cate_level3 = this.cate_ga
      }else if(event =='산후조리'){
        this.select2=true
        this.cate_level3 = this.cate_jo
      }else if(event =='인지활동'){
        this.select2=true
        this.cate_level3 = this.cate_in
      }else if(event =='정서지원'){
        this.select2=true
        this.cate_level3 = this.cate_hel
      }else if(event == '신생아돌봄'){
        this.select2=true
        this.cate_level3 = this.cate_san
      }else{
        this.select2=false
      }
    }
  },
};
</script>

<style>
.sc-component {
  margin: auto;
}

.sc-add-btn {
  position: absolute;
  top: 10%;
  left: 10%;
  width: auto;
}
.mx-auto {
  position: absolute;
  top: 15%;
}
.active_sel {
  position: relative;
  bottom: 20px;
  left: 5px;
}
.act_add {
  position: absolute;
  top: 15%;
  left: 15%;
}
.act_sel {
  position: relative;
  bottom: 20px;
}
.back_btn{
    position: absolute;
    top: 10%;
    left: 90%;
}
</style>
