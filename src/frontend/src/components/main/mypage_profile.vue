<template>
    <div class="resume-view">
    <div id="Contents">
		<div class="resumeWrap">
				<div class="resumeType1">
				<div class="resumeType1-inner">
					<div id="ResumeBaseInfo" class="resumeView">
						<h2 class="hide">기본정보</h2>
						<div class="photoArea">
							<span class="photo">
								<!-- <div v-if="details.attachFiles[0]==''">  -->
								<a href="#ResumeBaseInfo" class="image" style="position:static;left:0;bottom:0;display:block;width:auto;height:auto;padding:0;border:0 none;background:none">
									<img :src="'https://localhost:8080/api/images/' + details.attachFiles[0].storeFileName" width="90" height="120" alt="프로필사진" id="per_pic">		
								</a>
								<!-- </div> -->
							</span>
						</div>
							<ul class="infoList">
								<li class="name"><strong>{{details.memberDTO.name}}</strong> <em>{{details.memberDTO.birth}}</em> · ( {{details.memberDTO.gender}} ) <br></li>
								<li class="address"><span class="info-title">주소</span><span class="star">{{details.memberDTO.address}}</span></li>
								<li class="contact"> 
								<span class="info-title">연락처</span>{{details.memberDTO.phone}}
								 </li>
								<li class="mail"><span class="info-title">이메일</span><span class="star">{{details.memberDTO.email}}</span></li>
							</ul>
					</div>
					<div id="ResumeCareer" class="resumeView">
						<h2>정보</h2>
						<div class="infoArea first"> 	
							<div class="nameArea">
								<dt class="title">CCTV 동의여부: {{cctvAgreement}}	</dt>
								<!-- <p class="date">CCTV 동의여부:{{this.$store.state.careprofileStore.cctv}}</p> -->
							</div> 	
							<dl class="infoDetail"> 		
								<dt class="title">백신접종: {{vaccinated}} <span></span></dt> 
                        		<dt class="title">자격증:<span class="certi" v-for="(c,index) in certification" :key="index">{{c.name}}</span></dt> 
									<!-- </div> -->
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
											<dt class="title">{{details.desiredDayWeek}}</dt>
										</dl>
									</td>
									<td>
										<dl class="item">
											<dt class="title">{{details.activityTime}}시간</dt>
										</dl>
									</td>
									<td>
										<dl class="item">
											<!-- <dt class="title">{{$store.state.careprofileStore.pay}}</dt> -->
											<dt class="title">시급:{{details.hourlyWage}}</dt>
											<dt class="title">월급:{{details.monthlyWage}}</dt>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
						<ul class="fullList">
							<li>
								<span class="title">희망근무지</span>
								<p class="result">
									{{details.hopefulRegion}} 
								</p>
							</li>
							<li>
								<span class="title">희망업직종</span>
								<p class="result">{{details.preferredType}}</p>
							</li>
						</ul>
					</div>
					<div id="ResumePR" class="resumeView">
						<h2>자기소개서</h2>
						<div class="ResumeOpenBox"><span class="lockIcon">{{details.introduction}}</span><p class="first"></p></div>
					</div>
				</div>
				<br><br><br><br><br><br><br><br>
                <div style="text-align:center;">
				<v-btn class="ma-2" outlined color="indigo" @click="move">수정하기</v-btn>
				<v-btn class="ma-2" outlined color="indigo" @click="back">뒤로가기</v-btn>
                </div>
			</div>
			</div>
        </div>
    </div>
</template>

<script>
export default {   
    data(){
        return{
            details:[

            ],
			vaccinated:this.vaccinated,
			cctvAgreement:this.cctvAgreement
        }
    },
    mounted(){
        const id = this.$store.state.userStore.careSitterId
        this.$http
        .get(`/api/caresitters/${id}`,{
            withCredentials:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.details = res.data.body;
			if(res.data.body.is_vaccinated == 0){
            this.vaccinated = "1차접종완료"
         	}else if(res.data.body.is_vaccinated ==1){
            this.vaccinated = "추가접종완료"
         	}else if(res.data.body.is_vaccinated == 2){
            this.vaccinated = "미접종"
         	}
         	if(res.data.body.cctvAgreement == 0){
            this.cctvAgreement = "O"
         	}else{
            this.cctvAgreement = "X"
         }
        }).catch(err=>{
            console.log(err);
        })
    },
    methods:{
        move(){
            // const id = this.$store.state.userStore.careSitterId
            this.$router.push({name:'profile_update' , params:{caresitterId:this.$store.state.userStore.careSitterId}  })
        },
		back(){
			this.$router.push({name:'mypage'})
		}
    }

}
</script>

<style>
   
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

.ma-2{
    align-items: center;
}

</style>