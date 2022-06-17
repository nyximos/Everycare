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
            <div class="review">
               
             <v-chip class="ma-2" color="success" outlined style="width:150px;" @click="healthview()">
                 <h6 class="cate_name">건강기록조회</h6>
             </v-chip>
             <v-chip class="ma-2" color="success" outlined style="width:150px;" @click="health(s)">
                 <h6 class="cate_name">건강기록등록</h6>
             </v-chip>
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
                    
                </tr>
            </tbody>
        </table>
        </div>
        <br><br><br><br><br><br><br><br>
        </div>
        <!-- 건강 조회 -->
         <v-dialog v-model="Dialog03" max-width="1000px" @click:outside="closeDialog" @keydown.esc="closeDialog">
         <v-card style="background:#f8f8f8;">
          <v-card-text style="max-height: 1000px; padding-bottom:0px;">
            <v-card-text>
                <h2 class="title01"></h2>
            </v-card-text>            
          </v-card-text>
          <table class="table custom-table" show-select >
          <thead>
            <tr>  
              
                    <th>건강분류 </th>
                    <th>건강상태</th>
                    <th>건강기록</th>
                    
                
            </tr>
          </thead>
          
             <tbody v-for="(h ,index) in healthRecordDTO" :key="index">
                <tr>
                    <td @click="upadte(h)">{{h.healthClassification}}</td>
                    <td>{{h.healthStatus}}</td>
                    <td>{{h.detailComment}}</td>
                    
                </tr>
            </tbody>

        </table>
          
          <v-divider style="margin:auto;"></v-divider>
          <v-card-actions>
            
          </v-card-actions>
        </v-card>
    </v-dialog>
    <!-- 상세조회 -->
    <v-dialog v-model="Dialog04" max-width="500px" @click:outside="closeDialog" @keydown.esc="closeDialog">
         <v-card style="background:#f8f8f8;">
          <v-card-text style="max-height: 550px; padding-bottom:0px;">
            <v-card-text>
                <h2 class="title01">건강기록수정</h2>
                {{id}}
            </v-card-text>            
          </v-card-text>

          <v-select 
          v-model="health1"
    :items="healthList"
    item-text="name"
    item-value="value"
    label="건강분류"
      
  >
 
    
  </v-select>
  <v-select
  type="text"
  v-model="healthstatus"
    :items="healthstatusList"
    item-text="name"
  item-value="value"
    label="건강상태"
    
  >

 
    
    
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
            <v-btn class="ma-2" outlined color="indigo" @click="addmit3">수정</v-btn>
            <v-btn class="ma-2" outlined color="indigo" @click="closeDialog">닫기</v-btn>
            </div>
          </v-card-actions>
        </v-card>
    </v-dialog>
        <!-- 건강 -->
         <v-dialog v-model="Dialog02" max-width="500px" @click:outside="closeDialog" @keydown.esc="closeDialog">
         <v-card style="background:#f8f8f8;">
          <v-card-text style="max-height: 550px; padding-bottom:0px;">
            <v-card-text>
                <h2 class="title01">건강기록</h2>

            </v-card-text>            
          </v-card-text>

          <v-select 
          v-model="health1"
    :items="healthList"
    item-text="name"
    item-value="value"
    label="건강분류"
      
  >
    
    
  </v-select>
  <v-select
  type="text"
  v-model="healthstatus"
    :items="healthstatusList"
    item-text="name"
  item-value="value"
    label="건강상태"
    
  >
  <!-- <v-select
  type="text"
  v-model="healthstatus"
    :items="healthstatus1"
    item-text="name"
        item-value="value"
    label="건강상태"> -->
    
    
    
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
            Dialog03:false,
            Dialog04:false,
            avatar:require('@/assets/writing.png'),
            file:this.file,
            content:'',
            content2:'',
            health1:'',
            healthstatus:'',
            healthList:[
                 { name: '신체기능', value: '1'},
                { name: '식사기능', value: '2'},
                { name: '인지기능', value: '3'}
            ],
             healthstatusList:[
                 { name: '좋음', value: 'GOOD'},
                { name: '보통', value: 'MAINTENANCE'},
                { name: '나쁨', value: 'BAD'}
            ],
            healthRecordDTO:[],
            id:this.id
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
            // console.log(this.schedule)
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
            // console.log(res.data.body);
            this.healthRecordDTO = res.data.body
            console.log(this.healthRecordDTO);
            console.log(this.healthRecordDTO[0].id);
            
            /*
            for(let i = 0; i< Object.keys(res.data.body).length; i++){
                console.log("기록"+res.data.body[i].id)
            //  formData.append('id', res.data.body[0].id);
            this.id = res.data.body[i].id
            console.log(this.id)
           } 
           */
            
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
            this.Dialog03 = false;
            this.Dialog04 = false
        },
        add(s){
            this.Dialog = true;
            console.log(s.id);
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
        upadte(h){
            this.Dialog04 = true
            this.id = h.id
            console.log(id)
        },
        health(s){
            this.Dialog02 = true
            const caretarget ={
                id : s.id,
                name : s.name
            };
            this.$store.commit('carenoteStore/caretarget', caretarget);
        },
        healthview(){
            this.Dialog03 = true
            
        
        },
        activity(s){
            this.Dialog01 = true
            console.log(s.id);
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
            formData.append('healthClassificationId', this.health1);
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
       addmit3(){
            const carenoteId = this.$route.params.contentId;
            const id = this.id

            var formData = new FormData();
            
            formData.append('detailComment', this.content2);
            formData.append('healthClassificationId', this.health1);
            formData.append('healthStatus', this.healthstatus);
           this.$http
           .patch(`/api/carenote/${carenoteId}/health-records/${id}`, formData,{
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
    .v-chip__content{
        margin: 0 auto;
    }
    .name{
        align-items: left;
    }
    .review{
        padding-left: 20px;
    }
    .main_title{
        text-align: center;
    }
    .cate_name{
        text-align: center;
        margin: 0 auto;
        margin-bottom: 0px;
    }
    h6{
        margin-bottom: 0px;
        text-align: center;
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
        padding: 20px;
    }
    .ma-2{
        width: 200px;
        /* float: right; */
    }
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

.blind {
    visibility: hidden;
    overflow: hidden;
    position: absolute;
    top: 0;
    left: 0;
    width: 0;
    height: 0;
    font-size: 0;
    line-height: 0;
}

label {
    cursor: pointer;
}

button {
    border: none;
    box-shadow: none;
    border-radius: 0;
    overflow: visible;
    background: inherit;
    cursor: pointer;
}

input[type*=radio], button {
    outline: 1px solid transparent;
}

/* Content */
.pop_wrap {
    position: relative;
    margin: 0 auto;
    text-align: left;
    background: #fff;
    width: 560px;
    overflow: hidden;
}

.pop_wrap .pop_header {
    border-bottom: 1px solid rgba(0, 0, 0, 0.08);
    background: #fff;
    position: relative;
}

.pop_wrap .pop_header h1 {
    font-family: AppleSDGothicNeo-Bold, sans-serif;
    line-height: 60px;
    min-height: 60px;
    text-align: center;
    color: #303038;
    font-size: 22px;
    letter-spacing: -0.61px;
}

.pop_wrap .lst_report {
    padding: 14px 30px 19px;
    border-bottom: 1px solid #e6e6ea;
}

.pop_wrap .lst_report .inner {
    display: block;
    min-width: 48px;
}

.pop_wrap .lst_report .report_area {
    padding-bottom: 10px;
}

.pop_wrap .lst_report .report_area:after {
    display: block;
    clear: both;
    content: '';
}

.pop_wrap .lst_report dt, .pop_wrap .lst_report dd {
    padding-top: 10px;
    line-height: 20px;
}

.pop_wrap .lst_report dt {
    float: left;
    font-weight: bold;
    letter-spacing: -1px;
    font-size: 15px;
    letter-spacing: -0.5px;
    color: #929294;
}

.pop_wrap .lst_report .report_cont {
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    word-break: break-all;
}

.pop_wrap .lst_report .report_nick {
    white-space: nowrap;
    text-overflow: ellipsis;
}

.pop_wrap .lst_report dd {
    overflow: hidden;
    position: relative;
    font-size: 15px;
    letter-spacing: -0.5px;
    color: #303038;
    padding-left: 8px;
}

.pop_wrap .lst_report dd:before {
    content: '';
    position: absolute;
    top: 12px;
    left: 1px;
    width: 1px;
    height: 14px;
    margin-right: 8px;
    background-color: #d8d8d8;
}

.pop_wrap .lst_reason {
    padding: 22px 30px 0;
}

.pop_wrap .lst_reason .reason_type {
    margin: -4px 0 8px 0;
}

.pop_wrap .lst_reason .inner_box .list {
    padding: 16px 20px;
    border: solid 1px #e6e6ea;
}

.pop_wrap .lst_reason .inner_box .list:first-child {
    border-radius: 6px 6px 0 0;
}

.pop_wrap .lst_reason .inner_box .list:last-child {
    border-radius: 0 0 6px 6px;
}

.pop_wrap .lst_reason .inner_box .list + li {
    border-top: none;
}

.pop_wrap .lst_reason .reason_title {
    display: block;
    padding-bottom: 11px;
    font-size: 16px;
    font-weight: bold;
    letter-spacing: -0.5px;
    color: #1e1e23;
}

.pop_wrap .lst_reason .list_type .list {
    position: relative;
    font-size: 16px;
    letter-spacing: -0.5px;
    color: #1e1e23;
    line-height: 21px;
    background: #fff;
}

.pop_wrap .lst_reason .list_type .list .type_tit {
    display: inline-block;
    vertical-align: middle;
}

.pop_wrap .lst_reason .list_type .list .lst_tit {
    display: block;
    vertical-align: middle;
    font-weight: normal;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin-right: 20px;
}

.pop_wrap .lst_reason .list_type .list .lst_tit::before {
    content: '';
    display: inline-block;
    vertical-align: middle;
    width: 6px;
    height: 6px;
    border-radius: 10px;
    background-color: #929294;
    margin: 0 13px 1px 0;
}

.pop_wrap .lst_reason .list_type .list .more {
    background-color: transparent;
    position: absolute;
    top: 0;
    right: 0;
    padding: 22px 20px;
}

.pop_wrap .lst_reason .list_type .list .more::after {
    content: '';
    display: block;
    width: 14px;
    height: 8px;
    margin-top: 2px;
    background: url(https://ssl.pstatic.net/static/help/support/sp_ly_help.png) no-repeat -46px -1px;
    background-size: 95px 24px;
}

.pop_wrap .lst_reason .list_type .list.on .answer_area,
.pop_wrap .lst_reason .list_type .list.on .type_notice {
    display: block;
}

.pop_wrap .lst_reason .list_type .list.on + li {
    border-top: none;
}

.pop_wrap .lst_reason .list_type .list.on .more::after {
    background: url(https://ssl.pstatic.net/static/help/support/sp_ly_help.png) no-repeat -60px -1px;
    background-size: 95px 24px;
}

.pop_wrap .lst_reason .list_type .list:first-child .check_area {
    border-radius: 6px 6px 0 0;
    margin-top: 0;
}

.pop_wrap .lst_reason .list_type .list:last-child .check_area {
    border-radius: 0 0 6px 6px;
}

.pop_wrap .lst_reason .list_type .list + li .check_area {
    border-top: none;
}

.pop_wrap .lst_reason .answer_area {
    margin: 7px 0 0 29px;
    color: #929294;
    font-size: 14px;
    display: none;
}

.pop_wrap .lst_reason .list_answer {
    position: relative;
    font-size: 14px;
    line-height: 22px;
    letter-spacing: -0.5px;
    color: #929294;
    padding: 3px 10px 0;
}

.pop_wrap .lst_reason .list_answer:before {
    content: '';
    position: absolute;
    top: 12px;
    left: 0;
    display: inline-block;
    width: 4px;
    height: 4px;
    border-radius: 10px;
    background-color: rgba(146, 146, 148, 0.6);
}

.pop_wrap .lst_reason .lst_noti {
    display: block;
    padding-bottom: 10px;
    font-size: 14px;
    line-height: 22px;
    letter-spacing: -0.5px;
    color: #929294;
}

.pop_wrap .lst_reason .type_notice {
    margin: 10px 30px 0 20px;
    color: #929294;
    font-size: 14px;
    display: none;
}

.pop_wrap .lst_reason .type_notice .link_btn {
    color: #4a88d9;
    text-decoration: underline;
}

.pop_wrap .report_reason {
    position: absolute;
    width: 1px;
    height: 1px;
    padding: 0;
    margin: -1px;
    overflow: hidden;
    clip: rect(0, 0, 0, 0);
    border: 0;
}

.pop_wrap .check_area {
    border: solid 1px #e6e6ea;
    padding: 15px 20px;
}

.pop_wrap .check_area label {
    position: relative;
    padding-left: 33px;
    padding-bottom: 1px;
    margin-right: 30px;
    display: block;
    vertical-align: middle;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}

.pop_wrap .check_area label:before {
    content: '';
    position: absolute;
    left: 0;
    top: 0;
    background: url(https://ssl.pstatic.net/static/help/support/sp_ly_help.png) no-repeat -23px -1px;
    background-size: 95px 24px;
    width: 22px;
    height: 22px;
}

.pop_wrap .report_reason:checked + .check_area {
    border: 1px solid #03c75a !important;
    box-sizing: border-box;
    margin-top: -1px;
}

.pop_wrap .report_reason:checked + .check_area label:before {
    content: '';
    position: absolute;
    left: 0;
    top: 0;
    background: url(https://ssl.pstatic.net/static/help/support/sp_ly_help.png) no-repeat 0 -1px;
    background-size: 95px 24px;
    width: 22px;
    height: 22px;
}

.pop_footer {
    padding: 24px 30px 30px;
}

.pop_footer .btn_submit {
    display: block;
    background-color: #03c75a;
    color: #fff;
    font-size: 18px;
    font-weight: bold;
    width: 100%;
    height: 52px;
    line-height: 52px;
    text-align: center;
    border-radius: 6px;
    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.12);
    border: solid 0.5px rgba(0, 0, 0, 0.1);
}

.btn_close {
    height: 60px;
    padding: 16px 20px;
    position: absolute;
    top: 0;
    right: 0;
    cursor: pointer;
    background-color: transparent;
}
li {
    list-style: none;
}
</style>