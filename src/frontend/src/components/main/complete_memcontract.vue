<template>
  <div class="content">
      <v-card class="mx-auto" max-width="500" v-for="(c,index) in contract" :key="index">
        <h5>계약서:{{index+1}}</h5>
        <v-img :src="'https://localhost:8086/api/images/'+ c.careSitterStoreFileName" height="250px" width="300px" style="margin:0 auto;"></v-img>
      <v-card-title>
        시터 이름:{{c.careSitterName}} <br>
        amount:{{c.amount}}
      </v-card-title>
      <v-card-subtitle>
        날짜: {{c.day}}<br>

        계약 날짜:{{c.payDateTime.slice(0,10)}}

      </v-card-subtitle>
    <v-card-actions>
      <div style="margin:0 auto;">
        <v-btn color="#69f0ae" text @click="note(c)">노트</v-btn>

          <v-btn
        class="ma-2"
        color="red"
        dark
        @click="report(c)"
      >
        신고
        <v-icon
          dark
          right
        >
          mdi-cancel
        </v-icon>
      </v-btn>
      </div>
    </v-card-actions>
  </v-card>
    <v-dialog v-model="Dialog01" max-width="560px" @click:outside="closeDialog" @keydown.esc="closeDialog">
    <div class="pop_wrap">
        <div class="pop_header">
            <h1>신고하기</h1>
        </div>
        <div class="pop_container">
            <div class="pop_content">
                <!-- <div class="lst_report">
                    <dl class="report_area">
                        <dt><span class="inner">작성자</span>
                        </dt>
                        <dd class="report_nick">캉밀</dd>
                        <dt><span class="inner">내 용</span>
                        </dt>
                        <dd class="report_cont">스타벅스 프리퀀시 빨강1-하양3 교환 원해요</dd>
                    </dl>
                </div> -->
                <div class="lst_reason"><strong class="reason_title">사유선택</strong>
                    <!---->
                    <div class="list_type">
                        <ul>
                            <li class="list"><input type="radio" name="select" id="0" class="report_reason" v-model="reports" value="케어노트 허위정보 작성.">
                                <div class="check_area"><label for="0">케어노트 허위정보 작성.</label> 
                                </div>
                            </li>
                            <li class="list"><input type="radio" name="select" id="1" class="report_reason" v-model="reports" value="프로필 정보와는 다른 케어태도.">
                                <div class="check_area"><label for="1">프로필 정보와는 다른 케어태도.</label> 
                                </div>
                            </li>
                            <li class="list"><input type="radio" name="select" id="2" class="report_reason" v-model="reports" value="잦은지각.">
                                <div class="check_area"><label for="2">잦은지각.</label> 
                                </div>
                            </li>
                            <li class="list"><input type="radio" name="select" id="3" class="report_reason" v-model="reports" value="케어대상에게 유해한 내용지도.">
                                <div class="check_area"><label for="3">케어대상에게 유해한 내용지도.</label> 
                                </div>
                            </li> 
                        </ul>
                    </div>
                </div>
                
            </div>
        </div>
        <div class="pop_footer"><a href="#" class="btn_submit" @click="report2()">신고하기</a></div> <button type="button"
            class="btn_close" @click="closeDialog"><span class="blind">닫기</span></button>
    </div>
    </v-dialog>

  </div>
  
</template>

<script>
export default {
    data(){
        return{

            contract:[],
            Dialog01:false,
            reports:''

        }
    },
    mounted(){
        const targetId = this.$route.params.targetId;
        this.$http
        .get(`/api/member/caretargets/${targetId}/contracts/complition`,{

            withCredential:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.contract = res.data.body

            
            
            
            

        })
        .catch((err)=>{
            console.log(err);
        })
    },
    methods:{

        closeDialog(){
            
            this.Dialog01 = false;
            
        },
        note(c){        
            this.$router.push({ name: 'complete_membernote', params: { contractId: c.id, targetId:this.$route.params.targetId }})
        },
        report(c){
          this.id = c.id
          this.caresitterid = c.careSitterId
        //   console.log(this.id)
          console.log(this.caresitterid)
          this.Dialog01 = true
        },
        closeDialog(){
          this.Dialog01 = false
        },
        report2(){
            const memberId = this.$store.state.userStore.id;
          let formData = new FormData() 
                formData.append('id',memberId);
                formData.append('reason',this.reports);
                formData.append('reportedCareSitterId',this.caresitterid);
                formData.append('contractId',this.id);
                console.log(this.caresitterid)
                this.$http
    .post('/api/reports/care/sitter',formData, {
    withCredentials: true
    })
     .then(res => {
      console.log(res);
    })
      .catch(err => {
       console.log(err);
    });

        }
    }
}
</script>


<style scoped>

  .content{
    padding: 40px;
    display: flex;
  }

  /* Common */

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
    border: 1px solid #81C784 !important;
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
    background-color: #81C784;
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

.btn_close:after {
    content: '';
    display: block;
    width: 18px;
    height: 18px;
    background: url(https://ssl.pstatic.net/static/help/support/sp_ly_help.png) no-repeat -75px 0;
    background-size: 95px 24px;
}

/*# sourceMappingURL=srp_pc.css.map */

</style>