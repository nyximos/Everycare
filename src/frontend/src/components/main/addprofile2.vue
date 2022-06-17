<template>
   <div class="resume-view">
    <div id="Contents">
      <div class="resumeWrap">
            <div class="resumeType1">
            <div class="resumeType1-inner">
               <div id="ResumeBaseInfo" class="resumeView">
                  <h2 class="hide">기본정보</h2>
                  <div v-for="(b,index) in badge" :key="index">
                     <img :src="'https://localhost:8086/api/images/' + b.storeFileName">
                  </div>
                  <div class="photoArea">
                     <span class="photo">
                        <!-- <div v-if="this.attachFile == ' ' ">  -->
								<a href="#ResumeBaseInfo" class="image" style="position:static;left:0;bottom:0;display:block;width:auto;height:auto;padding:0;border:0 none;background:none">
									<img :src="'https://localhost:8086/api/images/' + detail.attachFiles[0].storeFileName" width="90" height="120" alt="프로필사진" id="per_pic">		
								</a>
								<!-- </div> -->
                     </span>
                  </div>
                     <ul class="infoList">
                        <li class="name"><strong>{{name}}</strong> <em>{{birth}}</em> · ( {{gender}} ) <br></li>
                        <li class="address"><span class="info-title">주소</span><span class="star">{{address}}</span></li>
                        <li class="contact"> 
                        <span class="info-title">연락처</span>{{phone}}
                         </li>
                        <li class="mail"><span class="info-title">이메일</span><span class="star">{{email}}</span></li>
                     </ul>
               </div>
               <div id="ResumeCareer" class="resumeView">
                  <h2>정보</h2>
                  <div class="infoArea first">    
                     <div class="nameArea">
                        <dt class="title">CCTV 동의여부:{{detail.cctvAgreement}}</dt>
                     </div>    
                     <dl class="infoDetail">       
                        <dt class="title">백신접종:{{detail.is_vaccinated}} <span></span></dt> 
                        
                        <dt class="title">자격증:<span class="certi" v-for="(c,index) in certification" :key="index">{{c.name}}</span></dt> 
                        <dd class="kind">
                           <ul>
                              
                           </ul>       
                        </dd>    
                     </dl> 
                  </div> 
               </div>
               <div id="ResumeTerms" class="resumeView">
                  <h2>희망근무</h2>
                  <table class="resumeView-table" summary="근무기간, 근무요일, 근무형태">
                     <colgroup>
                        <col width="33.3%">
                        <col width="33.3%">
                        <col width="33.3%">
                     </colgroup>
                     <caption></caption>
                     <thead class="hide">
                        <tr>
                           <th>근무요일</th>
                           <th>근무시간</th>
                           <th>근무급여</th>
                        </tr>
                     </thead>
                     <tbody>
                        <tr>
                           <td>
                              <dl class="item">
                                 <dt class="title">{{detail.desiredDayWeek}}</dt>
                              </dl>
                           </td>
                           <td>
                              <dl class="item">
                                 <dt class="title">{{detail.activityTime}}</dt>
                              </dl>
                           </td>
                           <td>
                              <dl class="item">
                                 <!-- <dt class="title">{{$store.state.careprofileStore.pay}}</dt> -->
                                 <dt class="title">시급:{{detail.hourlyWage}}</dt>
                                 <dt class="title">월급:{{detail.monthlyWage}}</dt>
                              </dl>
                           </td>
                        </tr>
                     </tbody>
                  </table>
                  <ul class="fullList">
                     <li>
                        <span class="title">희망근무지</span>
                        <p class="result">
                           {{detail.hopefulRegion}} 
                        </p>
                     </li>
                     <li>
                        <span class="title">희망업직종</span>
                        <p class="result">{{detail.preferredType}}</p>
                     </li>
                  </ul>
               </div>
               <div id="ResumePR" class="resumeView">
                  <h2>자기소개서</h2>
                  <div class="ResumeOpenBox"><span class="lockIcon">{{detail.introduction}}</span><p class="first"></p></div>
               </div>
                    <div id="#" class="resumeView">
                        <h2>후기</h2>
                  <div class="ResumeOpenBox"><span class="lockIcon" v-for="(r,index) in review" :key="index">{{r.member.name}}:{{r.comment}}<br></span><p class="first"></p></div>
                    </div>
                    <div id="#" class="resumeView">
                        <h2>활동내역</h2>
                    </div>
            </div>
            <br><br><br><br><br><br><br><br>
            <div style="text-align:center;">
            <v-btn class="ma-2" outlined color="indigo" @click="contract">계약</v-btn>
                <router-link to="/caresitters"><v-btn class="ma-2" outlined color="indigo">취소</v-btn></router-link>
            </div>
         </div>
         </div>
        </div>
      </div>
</template>

<script>
export default {
   name:'addprofile2',

   mounted(){
      const id =this.$route.params.caresitterId;
      this.$http 
      .get(`/api/caresitters/${id}`,{
         withCredentials:true
      })
      .then((res)=>{
         console.log(res.data.body);
         this.name = res.data.body.memberDTO.name
         this.birth = res.data.body.memberDTO.birth
         this.gender = res.data.body.memberDTO.gender
         this.phone = res.data.body.memberDTO.phone
         this.email = res.data.body.memberDTO.email
         this.address = res.data.body.memberDTO.address
         this.detail = res.data.body;
         this.id = res.data.body.id;
         this.review = res.data.body.careSitterReviews
         this.certification = res.data.body.certification
         this.badge = res.data.body.badge
         this.attachFile = res.data.body.attachFiles
         console.log(this.attachFile)
         }).catch(err=>{
            console.log(err);
         })

   },
   
      data(){
         return{
            props:[
               'caresitterId'
            ],
            certification:[],
            review:[],
            name:this.name,
            birth:this.birth,
            gender:this.gender,
            phone:this.phone,
            address:this.address,
            email:this.email,
            id:this.id,
            badge:this.badge,
            attachFile:this.attachFile,
            // storeFileName:[],
            // certification:this.certification,
            // is_vaccinated:this.is_vaccinated,
            // desiredDayWeek:[this.desiredDayWeek],
            // hourlyWage:this.hourlyWage,
            // monthlyWage:this.monthlyWage,
            // cctvAgreement:this.cctvAgreement,
            // activityTime:[this.activityTime],
            // preferredType:[this.preferredType],
            // introduction:this.introduction,
            // attachFiles:this.attachFiles,
            // img:this.img,
            // hopefulRegion:[this.hopefulRegion],
            detail:[],
      }
   },
   methods:{
      contract(){
            this.$router.push({name:'contract' , params:{caresitterId:this.id}  })
      }
   }
}
</script>

<style>
.ma-2{
    align-items: center;
}
.certi{
   padding: 5px;
}
li{
   list-style: none;
}
.Contents{
   background: #f1f4f8;
   padding: 0 0 80px;
}
.resume-view{
   padding: 94px 0 80px;
   box-sizing: border-box;
   background: #fff;
   font-size: 14px;
   line-height: 1.4;
}

#ResumeBaseInfo {
   z-index:1;
   position:relative;
   width:738px;
   margin-top:0; 
   margin: 0 auto;
   }

#ResumeBaseInfo::after {
   content:" ";
   clear:both;
   display:block
   }

#ResumeBaseInfo .photoArea {
   float:left;
   width:138px;
   margin-right:36px
   }

#ResumeBaseInfo .photoArea .photo {
   position:relative;
   display:inline-block;
   width:112px;
   height:132px;
   padding:1;
   border:1px solid #ddd}
   
#ResumeBaseInfo .photoArea .photo img {
   width:112px;
   height:132px
   }

#ResumeBaseInfo .infoList {
   float:right;
   width:564px
   }

#ResumeBaseInfo .infoList li:first-child {
   padding-left:0;
   margin-bottom:15px;
   border-bottom:1px solid #ebebeb
   }

#ResumeBaseInfo .infoList li {
   position:relative;
   min-height:32px;
   padding:0 0 0 90px;
   line-height:32px;
   font-size:15px;color:#222
   }

#ResumeBaseInfo .infoList li .info-title {
   position:absolute;
   left:0;
   top:0;
   font-size:15px;
   font-weight:bold;color:#999
   }

#ResumeBaseInfo .infoList li.name {
   padding-top:0;
   font-size:16px;
   color:#666
   }

#ResumeBaseInfo .infoList li.name strong {
   margin-right:6px;
   font-size:24px;
   color:#222
   }

#ResumeBaseInfo .infoList li.name em {
   color:#666
   }

#ResumeBaseInfo .infoList li.name a {
   position:relative;
   top:11px;
   display:inline-block;
   margin-left:10px;
   vertical-align:top
   }
   
#ResumeBaseInfo .infoList li.contact {
   z-index:1;
   position:relative;
   letter-spacing:0
   }

#ResumeBaseInfo .infoList li.contact strong {
   color:#888
   }

#ResumeBaseInfo .infoList li.mail {
   letter-spacing:0;
   word-break:break-all
   }

.resumeView{
    position: relative;
    width: 738px;
    margin: 40px auto 0;
    word-break: break-all;
}
.resumeType1 h2{
    padding-bottom: 10px;
    font-size: 22px;
    font-weight: bold;
    border-bottom: 2px solid #222;
}
.resumeType1 h2 .desc{
    margin-left: 10px;
    font-size: 16px;
    font-weight: normal;
}
#ResumeTerms ul.fullList li span.title{
    float: left;
    width: 110px;
    font-weight: bold;
    line-height: 1.5;
}


#ResumeTerms .resumeView-table {
   overflow:hidden;
   width:100%;
   padding:20px 0;
   border-bottom:1px solid #ebebeb
   }

#ResumeTerms .resumeView-table td {
   border-left:1px solid #ebebeb
   }

#ResumeTerms .resumeView-table td:first-child {
   border-left:0 none
   }

#ResumeTerms .resumeView-table td .item {
   min-height:46px;
   padding:0 10px;
   font-size:15px;
   text-align:center
   }
   
#ResumeTerms .resumeView-table td .item dt.title {
   padding-bottom:10px;
   font-size:14px;
   color:#999
   }

#ResumeTerms ul.fullList {
   padding:12px 0 17px;
   border-bottom:1px solid #ebebeb
   }

#ResumeTerms ul.fullList li {
   overflow:hidden;
   padding:3px 0 3px 20px;
   font-size:14px;
   text-align:left
   }

#ResumeTerms ul.fullList li span.title {
   float:left;
   width:110px;
   font-weight:bold;
   line-height:1.5;
   }
   

#ResumeTerms ul.fullList li p.result {
   overflow:hidden;
   display:block;
   font-size:20px;
   line-height:1.5
   }

#ResumeTerms ul.fullList li span.result a {
   color:#333;
   text-decoration:none
   }

#ResumeCareer .infoDetail {
   width:550px;
   padding-top:0
   }

/* 이력서보기(type1) - 자격증 */
#ResumeLicense .subTitle li {width:346px}
#ResumeLicense .subTitle li.first {width:335px}
#ResumeLicense .infoArea {overflow:visible;margin:0}
#ResumeLicense .infoArea .nameArea {overflow:visible;float:none;width:738px;margin:0}
#ResumeLicense .infoArea .nameArea::after {content:"";display:block;clear:both}
#ResumeLicense .infoArea .nameArea p.name {float:left;width:369px;font-size:16px;font-weight:bold;text-align:center;color:#222}
#ResumeLicense .infoArea .nameArea p.date {overflow:hidden;width:369px;margin:0;font-size:15px;text-align:center;color:#222}

#ResumePR {
   padding-bottom:20px;
   border-bottom:1px solid #ebebeb
   }


</style>