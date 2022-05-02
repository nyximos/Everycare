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
								<a href="#ResumeBaseInfo" class="image" style="position:static;left:0;bottom:0;display:block;width:auto;height:auto;padding:0;border:0 none;background:none">
									<img id="divProfile" src="//image.alba.kr/person/profile_no_female.png" alt="사진">
								</a>
							</span>
						</div>
						
							<ul class="infoList">
								<li class="name"><strong>{{$store.state.careprofileStore.name}}</strong> <em>{{$store.state.careprofileStore.sex}}</em>  ·{{$store.state.careprofileStore.age}}(년생) <br></li>
								<li class="address"><span class="info-title">주소</span>경남 거제시 <span class="star">******</span></li>
								<li class="contact">
								<span class="info-title">연락처</span>010-****-****
								</li>
								<li class="mail"><span class="info-title">이메일</span><span class="star">****</span>@<span class="star">이메일.com</span></li>
							</ul>
					</div>
					<div id="ResumeCareer" class="resumeView">
						<h2>정보</h2>
						<div class="infoArea first"> 	
							<div class="nameArea">
								<dt class="title">CCTV 동의여부:{{$store.state.careprofileStore.cctvAgreement}}	</dt>
								<!-- <p class="date">CCTV 동의여부:{{this.$store.state.careprofileStore.cctv}}</p> -->
							</div> 	
							<dl class="infoDetail"> 		
								<dt class="title">백신접종: <span>{{$store.state.careprofileStore.vaccination}}</span></dt> 
								
								<dt class="title">자격증</dt> 
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
							<caption>희망근무지</caption>
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
											<dt class="title">{{$store.state.careprofileStore.desiredDayWeek}}</dt>
										</dl>
									</td>
									<td>
										<dl class="item">
											<dt class="title">{{$store.state.careprofileStore.activityTime}}</dt>
										</dl>
									</td>
									<td>
										<dl class="item">
											<dt class="title">{{$store.state.careprofileStore.pay}}</dt>
											<dd>{{$store.state.careprofileStore.desiredHourlyWage}}</dd>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
						<ul class="fullList">
							<li>
								<span class="title">희망근무지</span>
								<p class="result">
									1순위:{{$store.state.careprofileStore.hopeloc1}}&nbsp;&nbsp;&nbsp; 
									2순위:{{$store.state.careprofileStore.hopeloc2}}&nbsp;&nbsp;&nbsp;
									3순위:{{$store.state.careprofileStore.hopeloc3}}
								</p>
						
							</li>
							<li>
								<span class="title">희망업직종</span>
								<span class="result">{{$store.state.careprofileStore.hopetype}}</span>
							</li>
						</ul>
					</div>
					<div id="ResumePR" class="resumeView">
						<h2>자기소개서</h2>
						<div class="ResumeOpenBox"><span class="lockIcon"></span><p class="first">{{$store.state.careprofileStore.introduction}}</p></div>
					</div>
                    <div id="#" class="resumeView">
                        <h2>후기</h2>
                    </div>
                    <div id="#" class="resumeView">
                        <h2>활동내역</h2>
                    </div>
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
			profile:{}
		}
	},
		created(){
			// var id = this.$route.params.id;
			this.$http.get('/api/dashboard/caresitter/${id}')
			.then((res)=>{
				this.profile = res.data[0]
			})
		
	}
	// created:function(){
	// 	var id = this.$route.params.id;
	// 	this.$http.get('/api/dashboard/caresitter/${id}')
	// 	.then((response) =>{
	// 		this.profile = response.data
	// 	}).catch(err =>{
	// 		alert(err);
	// 		console.log(err);
	// 	})
	// }
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