<template>
  <div class="content">
        <div class="head">
            <div class="title">
                <h2 class="main_title"> 스케줄</h2>
                <div class="tit">
                        <span class="name">케어대상인: OOO</span>
                        <span class="name">케어시터: OOO</span>
                        <span class="button"><v-btn depressed>출근/퇴근 정보</v-btn></span>
                        <span class="button"><v-btn depressed @click="information">후기작성 정보</v-btn></span>
                </div>
          
            </div>
            <div class="review">
             <v-chip class="ma-2" color="success" outlined v-for="(c,index) in cate" :key="index" style="width:100px;" @click="write(c)">
                
                 <!-- <v-icon left>mdi-server-plus</v-icon> -->
                <h6 class="cate_name">{{c.name}}</h6>
            </v-chip>
             <v-chip class="ma-2" color="success" outlined style="width:100px;" @click="healthview()">
                
                 <!-- <v-icon left>mdi-server-plus</v-icon> -->
                <h6 class="cate_name">건강기록</h6>
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
                    <td v-else>사진</td>
                    <td>활동</td>
                    
                </tr>
            </tbody>
        </table>
        </div>
        <br><br><br><br><br><br><br><br>
    </div>
    <v-dialog v-model="Dialog" max-width="500px" @click:outside="closeDialog" @keydown.esc="closeDialog">
         <v-card>
          <v-card-text style="max-height: 550px">
            <v-card-text>
                <h2 class="title01">후기작성</h2>
            </v-card-text>
            <v-card-text>
                <div>
                    <span><h5 class="title02">{{this.$store.state.carenoteStore.name}}</h5></span>
                    <span><v-rating v-model="rating" background-color="indigo lighten-3" color="indigo" size="30"></v-rating></span>
                </div>
            </v-card-text>
             <v-col cols="12" md="12">
                <v-textarea solo name="input-7-4" label="내용을 입력하시오" v-model="text"></v-textarea>
            </v-col>
          <!-- <button>💡</button> -->
          
    
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
                    <!-- <th>건강기록</th> -->
                    
                
            </tr>
          </thead>
          
             <tbody v-for="(h ,index) in healthRecordDTO" :key="index">
                <tr>
                    <td @click="upadte(h)">{{h.healthClassification}}</td>
                    <td>{{h.healthStatus}}</td>
                    <!-- <td>{{h.detailComment}}</td> -->
                    
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
                
            </v-card-text>            
          </v-card-text>

          
 
    
 

 
    


          <v-col cols="12" md="8" style="margin:0 auto;">
        <v-textarea
          solo
          name="input-7-4"
          label="내용을 입력해주세요"
          
        ></v-textarea>
      </v-col>
          <v-divider style="margin:auto;"></v-divider>
          <v-card-actions>
            <div style="margin:auto;">
            
            </div>
          </v-card-actions>
        </v-card>
    </v-dialog>
  </div>
    
</template>

<script>
export default {
    data(){
        return{
            schedule:[],
            Dialog:false,
            rating:0,
            text:'',
            cate:[],
            reason:'',
            reportedUserId:'',
            contractId:'',        
            Dialog03:false,
            Dialog04:false,
            healthRecordDTO:[],
        }
    },
    mounted(){
        const id = this.$route.params.contentId;
        this.$http
        .get(`/api/carenotes/${id}/schedules`,{
            withCredentail:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.schedule= res.data.body.activityInformationDTOs
            
            // console.log("데이터" + res);
        })
        .catch((err)=>{
            console.log(err);
        }),
        this.$http
        .get(`/api/carenote/${id}/health-records`,{
            withCredentail:true
        })
        .then((res)=>{
            this.healthRecordDTO = res.data.body
            console.log(this.healthRecordDTO);
            console.log(this.healthRecordDTO[0].id);
            // console.log(res.data.body);
            // this.status= res.data.body.healthstatus
            
            // console.log("데이터" + res);
        })
        .catch((err)=>{
            console.log(err);
        }),
        this.$http
        .get(`/api/carenote/${id}/categories`,{
            withCredentail:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.cate = res.data.body;
        })
        .catch((err)=>{
            console.log(err);
        })
        
        this.$http
        .get(`/api/carenote/${id}/health-records`, {
            withCredentail:true
        })
        .then((res)=>{
            console.log("데이터"+res)
        })
        .catch((err)=>{
            console.log(err);
        })
    },
    methods:{
        report(){
        
        let formData = new FormData()
        formData.append('reason',this.reason);
        formData.append('reportedUserId',this.reportedUserId);
        formData.append('contractId',this.contractId);

        this.$http
        .post('/api/reports/care', formData, {
            withCredentials: true
        })
        .then(res => {
            console.log(res);
        })
        .catch(err => {
            console.log(err);
        })
        },
        upadte(h){
             const carenoteId = this.$route.params.contentId;
            this.Dialog04 = true
            this.id = h.id
            this.$http
        .get(`/api/carenote/${carenoteId}/health-records/${h.id}`,{
            withCredentail:true
        })
        .then((res)=>{
            console.log(res.data.body);
            console.log(res)
            // this.schedule= res.data.body.activityInformationDTOs
            // console.log(this.schedule)
            // console.log("데이터" + res);
        })
        .catch((err)=>{
            console.log(err);
        })
            // console.log(id)
        },
        write(c){
            this.Dialog = true
            const caretarget ={
                categoryId : c.id,
                name : c.name
            };
            this.$store.commit('carenoteStore/caretarget', caretarget);
        },
        healthview(){
            this.Dialog03 = true
            
        
        },
        closeDialog(){
            this.Dialog=false
            this.Dialog03=false;
            this.Dialog04=false;
        },
        submit(){
            const id =this.$route.params.contentId;
            // const activity = this.$store.state.carenoteStore.categoryId;
            // const rating = this.rating
            // const comment = this.text
            // console.log(rating)
            // console.log(comment)
            // console.log(activity);
            var formData = new FormData();
            formData.append('rating', this.rating)
            formData.append('comment', this.text)
            formData.append('activityClassificationId', this.$store.state.carenoteStore.categoryId)
            this.$http
            .post(`/api/carenote/${id}/reviews`,formData,{
                withCredentail:true
            })
            .then((res)=>{
                console.log(res);
            })
            .catch((err)=>{
                console.log(err);
            })
        },
        information(c){
            this.$router.push({name:'review_list' , params:{contentId:this.$route.params.contentId}})
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
body,
p,
h1,
h2,
h3,
h4,
h5,
h6,
ul,
ol,
li,
dl,
dt,
dd,
table,
th,
td,
form,
fieldset,
legend,
input,
textarea,
button,
select {
    margin: 0;
    padding: 0;
}

body,
input,
textarea,
select,
button,
table {
    background: #fff;
    font-family: -apple-system, BlinkMacSystemFont, HelveticaNeue, AppleSDGothicNeo-Regular, sans-serif;
}

img,
fieldset {
    border: 0;
}

ul,
ol {
    list-style: none;
}

em,
address {
    font-style: normal;
}

a {
    text-decoration: none;
}

textarea {
    overflow: auto;
}

legend,
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