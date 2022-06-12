<template>
    <div class="content">
        <div class="head">
            <div class="title">
                <h2> 스케줄</h2>
                <div>
                    <span class="tit"><h4>케어대상인: OOO</h4></span>
                    <v-btn class="ma-2" outlined color="indigo" @click="end">활동 종료</v-btn>
                </div>
            </div>
        <div class="table">
        <table  style="margin:0 auto;" class="content-table" >
            <thead>
                <tr class="head">
                    <th>Id</th>
                    <th>시간</th>
                    <th>스케줄명</th>
                    <th>요구사항</th>
                    <th>사진</th>
                    <th>활동</th>
                    <th>건강</th>
                </tr>
            </thead>
            <tbody v-for="( s ,index) in schedule" :key="index">
                <tr>
                    <td>{{ s.id }}</td>
                    <td>{{s.startTime}}</td>
                    <td>{{s.name}}</td>
                    <td>{{s.requirement}}</td>
                    <td v-if="schedule.storeFileName=''"><img :src="'https://localhost:8086/api/images/' + s.storeFileName"></td>
                    <td v-else @click="add(s)">사진</td>
                    <td @click="activity(s)">활동</td>
                    <td @click="health(s)">등록</td>
                </tr>
            </tbody>
        </table>
        </div>
        <br><br><br><br><br><br><br><br>
        </div>
        <!-- 건강 -->
         <v-dialog v-model="Dialog02" max-width="500px" @click:outside="closeDialog" @keydown.esc="closeDialog">
         <v-card style="background:#f8f8f8;">
          <v-card-text style="max-height: 550px; padding-bottom:0px;">
            <v-card-text>
                <h2 class="title01">건강기록</h2>
            </v-card-text>            
          </v-card-text>

          <v-select type="text" width="500px"
          v-model="health1"
    :items="['신체기능', '식사기능', '인지기능', '배변변화']"
    label="건강분류"
    
  >
    <template v-slot:item="{ item, attrs, on }">
      <v-list-item
        v-bind="attrs"
        v-on="on"
        
      >
        <v-list-item-title
          :id="attrs['aria-labelledby']"
          v-text="item"
        ></v-list-item-title>
      </v-list-item>
    </template>
    
  </v-select>
  <v-select
  type="text"
  v-model="healthstatus"
    :items="['좋음', '보통', '나쁨']"
    label="건강상태"
    
  >
  <!-- <v-select
  type="text"
  v-model="healthstatus"
    :items="healthstatus1"
    item-text="name"
        item-value="value"
    label="건강상태"> -->
    
    
    <template v-slot:item="{ item, attrs, on }">
      <v-list-item
        v-bind="attrs"
        v-on="on"
        
      >
        <v-list-item-title
          :id="attrs['aria-labelledby']"
          v-text="item"
        ></v-list-item-title>
      </v-list-item>
    </template>
  </v-select>

          <v-col cols="12" md="8" style="margin:0 auto;">
        <v-textarea
          solo
          name="input-7-4"
          label="내용을 입력해주세요"
          v-model="content2"
        ></v-textarea>
      </v-col>
          <v-divider style="margin:auto;"></v-divider>
          <v-card-actions>
            <div style="margin:auto;">
            <v-btn class="ma-2" outlined color="indigo" @click="addmit2">등록</v-btn>
            <v-btn class="ma-2" outlined color="indigo" @click="closeDialog">닫기</v-btn>
            </div>
          </v-card-actions>
        </v-card>
    </v-dialog>

        <!-- 사진 -->
        <div class="modal1">
         <v-dialog v-model="Dialog" max-width="500px" @click:outside="closeDialog" @keydown.esc="closeDialog">
         <v-card>
          <v-card-text style="max-height: 550px">
            <v-card-text>
                <h2 class="title01">{{this.$store.state.carenoteStore.name}}</h2>
            </v-card-text>
            <div class="img">   
                <label for="uppic">
                    <v-img :src="avatar" width="150px" height="150px" alt="사진없음" style="margin:0 auto; padding:20px; margin-top:20px" class="img-avatar" id="preview" />
                </label> 
            </div>
            <v-container fluid class="btn">
                 <input type="file" name="avatar" id="uppic"  accept="image/gif,image/gif,image/jpg,image/png" @change="changeImage($event);" ref="avatarInput" class="uppic" multiple="multiple">
            </v-container>
            
          </v-card-text>
          <v-divider style="margin:auto;"></v-divider>
          <v-card-actions>
            <div style="margin:auto;">
            <v-btn class="ma-2" outlined color="indigo" @click="submit">등록</v-btn>
            <v-btn class="ma-2" outlined color="indigo" @click="closeDialog">닫기</v-btn>
            </div>
          </v-card-actions>
        </v-card>
    </v-dialog>

     <!-- 활동  -->
    <v-dialog v-model="Dialog01" max-width="500px" @click:outside="closeDialog" @keydown.esc="closeDialog">
         <v-card style="background:#f8f8f8;">
          <v-card-text style="max-height: 550px; padding-bottom:0px;">
            <v-card-text>
                <h2 class="title01">{{this.$store.state.carenoteStore.name}}</h2>
            </v-card-text>            
          </v-card-text>
          <v-col cols="12" md="8" style="margin:0 auto;">
        <v-textarea
          solo
          name="input-7-4"
          label="내용을 입력해주세요"
          v-model="content"
        ></v-textarea>
      </v-col>
          <v-divider style="margin:auto;"></v-divider>
          <v-card-actions>
            <div style="margin:auto;">
            <v-btn class="ma-2" outlined color="indigo" @click="addmit">등록</v-btn>
            <v-btn class="ma-2" outlined color="indigo" @click="closeDialog">닫기</v-btn>
            </div>
          </v-card-actions>
        </v-card>
    </v-dialog>
  </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            schedule:[],
            Dialog:false,
            Dialog01:false,
            Dialog02:false,
            avatar:require('@/assets/writing.png'),
            file:this.file,
            content:'',
            content2:'',
            health1:'',
            healthstatus:'',
            
        }
    },
    mounted(){
        const id = this.$route.params.contentId;
        this.$http
        .get(`/api/carenotes/${id}/schedules`,{
            withCredentail:true
        })
        .then((res)=>{
            // console.log(res.data.body);
            this.schedule= res.data.body.activityInformationDTOs
            console.log(this.schedule)
            // console.log("데이터" + res);
        })
        .catch((err)=>{
            console.log(err);
        })
        this.$http
        .get(`/api/carenote/${id}/health-records`, {
            withCredentail:true
        })
        .then((res)=>{
            console.log(res.data.body);
    
            
            // console.log("데이터" + res);
        })
        .catch((err)=>{
            console.log(err);
        })
    },
    methods:{
        closeDialog(){
            this.Dialog = false;
            this.Dialog01 = false;
            this.Dialog02 = false;
        },
        add(s){
            this.Dialog = true;
            const caretarget ={
                id : s.id,
                name : s.name
            };
            this.$store.commit('carenoteStore/caretarget', caretarget);
        },
        submit(){
            const carenoteId = this.$route.params.contentId;
            const activityId = this.$store.state.carenoteStore.id;
            
            
            var formData = new FormData();
            formData.append('attachFile', this.file);

            this.$http
            .patch(`/api/carenote/${carenoteId}/schedules/${activityId}/photo`,formData,{
                withCredentail:true
            })
            .then((res)=>{
                console.log(res);
            })
            .catch((err)=>{
                console.log(err);
            })
        },
        health(s){
            this.Dialog02 = true
            const caretarget ={
                id : s.id,
                name : s.name
            };
            this.$store.commit('carenoteStore/caretarget', caretarget);
        },
        activity(s){
            this.Dialog01 = true
            const caretarget ={
                id : s.id,
                name : s.name
            };
            this.$store.commit('carenoteStore/caretarget', caretarget);
        },
        changeImage(e) {
         document.getElementById("uppic").click();   
        
         var file = e.target.files[0]
        //  console.log(file)
         this.file = file
         var reader = new FileReader()
         var that = this
         reader.readAsDataURL(file)
         reader.onload = function(e) {
           that.avatar = this.result
         }
       },
       addmit(){
            const carenoteId = this.$route.params.contentId;
            const activityId = this.$store.state.carenoteStore.id;

            var formData = new FormData();
            formData.append('content', this.content);

           this.$http
           .patch(`/api/carenote/${carenoteId}/schedules/${activityId}/content`, formData,{
               withCredentail:true
           })
           .then((res)=>{
               console.log(res);
           })
           .catch((err)=>{
               console.log(err);
           })
       },
       addmit2(){
            const carenoteId = this.$route.params.contentId;
            

            var formData = new FormData();
            
            formData.append('detailComment', this.content2);
            formData.append('healthClassificationId', this.health);
            formData.append('healthStatus', this.healthstatus);
           this.$http
           .post(`/api/carenote/${carenoteId}/health-records`, formData,{
               withCredentail:true
           })
           .then((res)=>{
               console.log(res);
           })
           .catch((err)=>{
               console.log(err);
           })
       },
       end(){
           const carenoteId = this.$route.params.contentId;
           console.log(carenoteId);
           this.$http
           .patch(`/api/carenotes/${carenoteId}/schedules/complition`,{
               withCredentail:true
           })
           .then((res)=>{
               console.log(res)
           })
           .catch((err)=>{
               console.log(err)
           })
       }
    }
}
</script>

<style>
    .content{
        width: 100%;
    }
    .img{
        text-align:center;
        background: #d2d2d2;
        width: 250px;
        height: 200px;
        margin: 0 auto;
    }
    img{
        margin: 0 auto;
        width: 160px;
        height: 150px;
        padding: 20px;
        margin-top: 15px;
        /* background: gray; */
    }
    .head{
        margin: 20px auto;
        width: 1000px;
    }
    .tit{
        text-align: center;
    }
    .ma-2{
        width: 200px;
        /* float: right; */
    }
    .file{
    }
    /* .btn{
    } */
    .table{
        width: 100%;
        padding: 20px;
    }
    .title01{
        text-align: center;
        color: black;
    }
    .content-table{
        border-collapse: collapse;
        margin: 25px 0;
        font-size: 0.9em;
        min-width: 400px;
        width: 100%;
    }
    .content-table thead tr{
        background-color: #009879;
        color: #ffffff;
        text-align: center;
        height: 70px;
    }
    .content-table th,
    .content-table td{
        padding: 12px 15px;
    }

    .content-table tbody tr{
        border-bottom: 1px solid #dddddd;
    }

    .content-table tbody tr:nth-of-trpe(even){
        background-color: #f3f3f3;
    }

    .content-table tbody tr:last-of-type{
        border-bottom: 2px solid #009879;
    }
    .content-table tbody tr td{
        text-align: center;
    }
    .btn{
        align-items: center;
    }
     input[type="file"]{
        position: absolute;
        overflow: hidden;
        clip: rect(0,0,0,0);
    }
</style>