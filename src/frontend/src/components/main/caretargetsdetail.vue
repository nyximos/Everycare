<template>
    <div class="content">
        <div class="view">
            <div class="view_div">
                <div class="con01">
                    <div class="con0101">
                        <!-- <span class="icon03" id="btn_alert" @click="update">수정하기</span>			<span class="file"> -->
                        <span style="display:inline-block; width:200px; height:200px; border-radius:200px; overflow:hidden;">
                            <img :src="'https://localhost:8086/api/images/' + this.attachFiles" class="main_img img_sample" />
                        </span>

                        <div class="layer01" id="layer_alert"></div>
                        <span class="name">
                            <span class="name01">{{ name }} </span>
                            <span class="sex">
                                {{ gender }} <span class="bar01">|</span> {{ birth }} <span class="bar01">|</span>
                                <!-- <span class="icon01 icon01_2">✓ 인증완료</span> -->
                            </span>
                        </span>
                    </div>

                    <div class="con0103">
                        <span class="span01">
                            <span class="tit01">케어타입</span>
                            <span class="con010301">{{ careType }}</span>
                            <span class="bar01"></span>
                        </span>
                        <span class="span01 cen">
                            <span class="tit01">주소</span>
                            <span class="con010301">{{ address }} {{ detailedAddress }}</span>
                            <span class="bar01"></span>
                        </span>
                        <span class="span01">
                            <span class="tit01">장기요양등급</span>
                            <span class="con010301">{{ longTermCareGrade }}등급</span>
                        </span>
                    </div>
                </div>

                <div class="con02">
                    <h3 class="tit_top">상세정보</h3>

                    <span class="tit_mid">신체정보 </span>
                    <div class="con0202">
                        <span class="head">키</span>
                        <span class="con">{{ height }}cm&nbsp;</span>
                        <span class="head">몸무게</span>
                        <span class="con">{{ weight }}kg&nbsp;</span>
                        <span class="head">코로나검사여부 </span>
                        <span class="con">{{ coronaTest }}&nbsp;</span>
                    </div>

                    <span class="tit_mid">근무조건 </span>
                    <div class="con0202">
                        <span class="head">반려동물여부</span>
                        <span class="con">{{ pet }}&nbsp;</span>
                        <span class="head">CCTV동의</span>
                        <span class="con">{{ isCctvAgreement }} &nbsp;</span>
                        <span class="head">특이사항</span>
                        <span class="con"
                            ><span>{{ comment }}</span
                            >&nbsp;</span
                        >
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- <div class="resume-view">
    <div id="Contents">
		<div class="resumeWrap">
				<div class="resumeType1">
				<div class="resumeType1-inner">
					<div id="ResumeBaseInfo" class="resumeView">
						<h2 class="hide">기본정보</h2>
						<div class="photoArea">
							<span class="photo">
								<a href="#ResumeBaseInfo" class="image" style="position:static;left:0;bottom:0;display:block;width:auto;height:auto;padding:0;border:0 none;background:none">
									<img id="divProfile" :src="'https://localhost:8086/api/images/'+this.attachFiles" alt="사진">
									
								</a>
							</span>
						</div>
						
							<ul class="infoList">
								<li class="name"><strong>{{name}}</strong> <em>{{gender}}</em>  ·{{birth}} <br></li>
                               
								<li class="address"><span class="info-title">키</span>{{height}} <span class="star"></span></li>
								<li class="contact">
								<span class="info-title">몸무게</span>{{weight}}
								</li>
								<li class="mail"><span class="info-title">주소</span><span class="star">{{address}} {{detailedAddress}}</span><span class="star"></span></li>
							</ul>
					</div>
					<div id="ResumeCareer" class="resumeView">
						<h2>정보</h2>
						<div class="infoArea first"> 	
							<div class="nameArea">
								<dt class="title">케어유형:{{careType}}	</dt>
								
							</div>
							<div class="nameArea">
								<dt class="title">반려동물 유무:{{pet}}	</dt>
								
							</div>
							<div class="nameArea">
								<dt class="title">CCTV 동의여부:{{isCctvAgreement}}	</dt>

							</div> 	
							<dl class="infoDetail"> 		
								<dt class="title">백신접종: <span>{{coronaTest}}</span></dt> 
								
								
								<dd class="kind">
									<ul>
										
									</ul> 		
								</dd> 	
							</dl> 
						</div> 
					</div>
					
					<div id="ResumePR" class="resumeView">
						<h2>특이사항</h2>
						<div class="ResumeOpenBox"><span class="lockIcon"></span><p class="first">{{comment}}</p></div>
					</div>
                    
				</div>
			</div>
			</div>
        </div>
		<v-btn class="ma-2" outlined color="indigo" @click="clickme">돌아가기</v-btn>
		
		</div> -->
</template>

<script>
export default {
    name: 'CaretargetsDetail',
    mounted() {
        const id = this.$route.params.caretargetsId;
        this.$http
            .get(`/api/dashboard/caretargets/${id}`, {
                withCredentials: true
            })

            .then(res => {
                console.log(res.data.body);
                this.name = res.data.body.name;
                this.gender = res.data.body.gender;
                this.birth = res.data.body.birth;
                this.height = res.data.body.height;
                this.weight = res.data.body.weight;
                this.zipcode = res.data.body.zipcode;
                this.address = res.data.body.address;
                this.detailedAddress = res.data.body.detailedAddress;
                this.longTermCareGrade = res.data.body.longTermCareGrade;
                this.comment = res.data.body.comment;
                this.pet = res.data.body.pet;
                this.isCctvAgreement = res.data.body.isCctvAgreement;
                this.careType = res.data.body.careType;
                this.coronaTest = res.data.body.coronaTest;

                // this.attachFiles = res.data.body.attachFiles[0].storeFileName
                this.attachFiles = res.data.body.imgDTOs.storeFileName;
                this.detailImg = res.data.body.imagesDTOs;
            })
            .catch(err => {
                console.log(err);
            });
    },
    data() {
        return {
            name: this.name,
            gender: this.gender,
            birth: this.birth,
            height: this.height,
            weight: this.weight,
            zipcode: this.zipcode,
            address: this.address,
            detailedAddress: this.detailedAddress,
            longTermCareGrade: this.longTermCareGrade,
            comment: this.comment,
            pet: this.pet,
            isCctvAgreement: this.isCctvAgreement,
            careType: this.careType,
            coronaTest: this.coronaTest,
            attachFiles: null,
            img: this.img
        };
    },
    methods: {
        clickme() {
            location.href = '/caretargets';
        }
        // update(){
        //       this.$router.push({
        //         path: '/dashboard/caretargets/1/update'
        //       })

        // }
    }
};
// data(){
// 	return{
//         caretargetsdetail:{

//         }
// 		 profile:{}
// 	}
// },
// created(){
// 	 var id = this.$route.params.id;
//     this.$http.get('/api/dashboard/caretargets/{id}')
//     .tehn((res)=>{
//     this.caretargetsdetail =res.data[0]
//     })
// this.$http.get('/api/dashboard/caresitter/${id}')
// .then((res)=>{
// 	this.profile = res.data[0]

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
</script>

<style>
.content {
    width: 100%;
    position: relative;
}
@media (max-width: 410px) {
    .content {
        font-size: 0.9em;
    }
}
@media (max-width: 320px) {
    .content {
        font-size: 0.8em;
    }
}
.icon03 {
    display: inline-block;
    padding: 7px 7px 7px 7px;
    box-sizing: border-box;
    position: absolute;
    font-size: 1em;
    font-weight: 400;
    font-size: 0.7em;
    border: 0;
    border-radius: 10px;
    color: #ffffff;
    background-color: #2c786c;
    right: 0;
    float: right;
    margin: 0 10px 0 0;
}
.content .view {
    width: 100%;
    max-width: 670px;
    min-height: 800px;
    background-color: #ffffff;
    position: relative;
    margin: 0 auto;
    border-left: 1px solid #dedede;
    border-right: 1px solid #dedede;
}
.content .view .view_div {
    width: 100%;
    max-width: 670px;
    padding: 30px 0 0 0;
    box-sizing: border-box;
    position: relative;
}
.content .view .view_div h3.tit {
    width: 100%;
    padding: 0 15px 0 15px;
    box-sizing: border-box;
    position: relative;
    font-size: 1.3em;
    font-weight: 500;
    color: #333333;
}
.content .view .view_div h3.tit .btn01 {
    display: inline-block;
    padding: 7px 7px 7px 7px;
    box-sizing: border-box;
    position: absolute;
    font-size: 1em;
    font-weight: 400;
    font-size: 0.7em;
    border: 0;
    border-radius: 10px;
    color: #ffffff;
    background-color: #2c786c;
    right: 0;
    float: right;
    margin: 0 10px 0 0;
}

.content .view .view_div h3.tit_top {
    width: 100%;
    padding: 10px 0 10px 0;
    box-sizing: border-box;
    position: relative;
    font-size: 1.2em;
    font-weight: 400;
    color: #333333;
}
.content .view .view_div h3.tit_top .edit_date {
    display: inline-block;
    position: absolute;
    right: 0;
    text-align: center;
    margin: 0 0 0 0;
    font-size: 0.7em;
    letter-spacing: 0;
    color: #797979;
    padding: 5px 0 0 0;
}
.content .view .view_div .con01 {
    width: 100%;
    position: relative;
    margin: 0 auto;
    padding: 0 0 30px 0;
    box-sizing: border-box;
    background-color: #ffffff;
    color: #373737;
    float: left;
}
.content .view .view_div .con01 span.tit_mid {
    width: 100%;
    display: inline-block;
    padding: 15px 15px 15px 15px;
    box-sizing: border-box;
    position: relative;
    font-size: 1em;
    font-weight: 400;
    color: #333333;
    border-top: 1px solid #dedede;
    border-left: 1px solid #dedede;
    border-right: 1px solid #dedede;
    border-radius: 10px 10px 0 0;
    background-color: #f4f4f4;
}
.content .view .view_div .con01 .con0101 {
    width: 100%;
    position: relative;
    margin: 0 auto 0 auto;
    box-sizing: border-box;
    background-color: #ffffff;
    color: #373737;
    overflow: hidden;
    line-height: 1.3em;
    text-align: center;
}
.content .view .view_div .con01 .con0101 .file {
    display: inline-block;
    width: 200px;
    height: 200px;
    border: 0;
    margin: 0 auto;
    box-sizing: border-box;
    position: relative;
    overflow: hidden;
    border-radius: 200px;
}
.content .view .view_div .con01 .con0101 .file .main_img {
    position: absolute;
    left: 50%;
    top: 50%;
    height: 100%;
    width: auto;
    -webkit-transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
}
.content .view .view_div .con01 .con0101 .file .main_img.img_sample {
    height: 200px;
}
.content .view .view_div .con01 .con0101 .file .horizontal {
    width: auto;
    height: 100%;
}
.content .view .view_div .con01 .con0101 .file .vertical {
    width: 100%;
    height: auto;
}

.content .view .view_div .con01 .con0101 .file .auto {
    width: auto;
    height: auto;
}
.content .view .view_div .con01 .con0101 .icon01 {
    display: inline-block;
    padding: 5px 10px 5px 10px;
    background-color: #ffffff;
    color: #f8b400;
    border: 1px solid #f8b400;
    position: absolute;
    border-radius: 30px;
    left: 10px;
    top: 10px;
    font-size: 0.8em;
}
.content .view .view_div .con01 .con0101 .icon01_2 {
    position: relative;
    padding: 1px 5px 1px 5px;
    margin: 0 0 0 15px;
    left: 0;
    top: 0;
    font-size: 0.6em;
}
.content .view .view_div .con01 .con0101 .icon02 {
    display: inline-block;
    padding: 5px 10px 5px 10px;
    background-color: #ffffff;
    color: #2c786c;
    border: 1px solid #2c786c;
    position: absolute;
    border-radius: 30px;
    left: 93px;
    top: 10px;
    font-size: 0.8em;
}
.content .view .view_div .con01 .con0101 .icon02_2 {
    position: relative;
    padding: 1px 5px 1px 5px;
    margin: 0 0 0 0;
    left: 0;
    top: 0;
    font-size: 0.6em;
}

.content .view .view_div .con01 .con0101 .icon03 {
    display: inline-block;
    padding: 5px 10px 5px 10px;
    background-color: #ffffff;
    color: #474747;
    border: 1px solid #cccccc;
    position: absolute;
    border-radius: 35px;
    height: 32px;
    box-sizing: border-box;
    right: 15px;
    top: 0;
    font-size: 0.6em;
    z-index: 10;
}
.content .view .view_div .con01 .con0101 .icon03 .img_alert {
    height: 12px;
    margin: 0 3px 0 0;
}

.content .view .view_div .con01 .con0101 .insurance {
    display: inline-block;
    width: calc(100% - 30px);
    box-sizing: border-box;
    position: relative;
    background-color: #f8b4000f;
    font-size: 0.9em;
    margin: 15px;
    color: #9a9a9a;
    text-align: center;
    padding: 15px;
    border: 1px solid #f8b400;
    border-radius: 10px;
    letter-spacing: 0;
    line-height: 1.7em;
}
.content .view .view_div .con01 .con0101 .insurance .date01 {
    display: inline-block;
    color: red;
    font-size: 1.2em;
}
.content .view .view_div .con01 .con0101 .subject {
    display: inline-block;
    width: 100%;
    box-sizing: border-box;
    position: relative;
    overflow: hidden;
    padding: 15px 0 0 15px;
}
.content .view .view_div .con01 .con0101 .name {
    display: inline-block;
    width: 100%;
    box-sizing: border-box;
    position: relative;
    overflow: hidden;
    font-size: 1.2em;
    padding: 10px 15px 0 15px;
    color: #333333;
    letter-spacing: 0;
}
.content .view .view_div .con01 .con0101 .name .list01 {
    display: inline-block;
    position: relative;
    overflow: hidden;
    word-break: keep-all;
    margin: 10px 0 0 0;
    width: 100%;
    text-align: center;
}
.content .view .view_div .con01 .con0101 .name .name01 {
    display: inline-block;
    float: left;
    margin: 0 0 0 0;
    width: 100%;
    text-align: center;
}
.content .view .view_div .con01 .con0101 .name .list01 .span01 {
    display: inline-block;
    background-color: #f4f4f4;
    font-size: 0.58em;
    color: #373737;
    padding: 5px 5px 5px 5px;
    letter-spacing: 0;
    margin: 5px 5px 0 0;
    border-radius: 10px;
}
.content .view .view_div .con01 .con0101 .name .list01 .span01 .percent01 {
    display: inline-block;
    color: red;
}
.content .view .view_div .con01 .con0101 .sex {
    display: inline-block;
    width: 100%;
    position: relative;
    font-size: 0.75em;
    letter-spacing: 0;
    padding: 5px 0 0 0;
    font-weight: 300;
    text-align: center;
}
.content .view .view_div .con01 .con0101 .option01 {
    display: inline-block;
    position: relative;
    overflow: hidden;
    float: left;
    font-size: 0.58em;
    color: #ffffff;
    background-color: #ff7979;
    padding: 5px 5px 5px 5px;
    vertical-align: middle;
    word-break: keep-all;
    margin: 5px 5px 0 0;
    border-radius: 10px;
}
.content .view .view_div .con01 .con0101 .sex .bar01 {
    display: inline-block;
    color: #e4e4e4;
    font-size: 0.8em;
    letter-spacing: 0;
}
.content .view .view_div .con01 .con0101 .bar_star {
    display: inline-block;
    position: relative;
    width: 95px;
    height: 18px;
    box-sizing: border-box;
    background-color: #dedede;
    margin: 10px 0 0 0;
    vertical-align: top;
}
.content .view .view_div .con01 .con0101 .bar_star .bar01 {
    position: absolute;
    left: 0;
    top: 0;
    display: inline-block;
    width: 100%;
    height: 18px;
    background: url('/images/common/bg_star_bar01.png') no-repeat;
    background-size: 100% 100%;
}
.content .view .view_div .con01 .con0101 .sex .icon_star {
    display: inline-block;
    color: #e4e4e4;
    font-size: 1.2em;
    letter-spacing: 0;
    margin: 0 10px 0 0;
}

.content .view .view_div .con01 .con0102 {
    width: 100%;
    position: relative;
    margin: 0 auto 0 auto;
    box-sizing: border-box;
    background-color: #ffffff;
    color: #373737;
    line-height: 1.3em;
    text-align: left;
    padding: 25px 15px 0 15px;
}
.content .view .view_div .con01 .con0102 .btn01 {
    position: relative;
    display: inline-block;
    padding: 10px 0 10px 0;
    background-color: #ffffff;
    border: 1px solid #dedede;
    color: #797979;
    font-size: 1em;
    width: 33%;
    box-sizing: border-box;
    border-radius: 10px;
    float: left;
    cursor: pointer;
}
.content .view .view_div .con01 .con0102 .btn01.on {
    background-color: #f8b400;
    border: 1px solid #f8b400;
    color: #ffffff;
}
.content .view .view_div .con01 .con0102 .btn02 {
    position: relative;
    display: inline-block;
    padding: 10px 15px 10px 15px;
    background-color: #2c786c;
    border: 1px solid #2c786c;
    color: #ffffff;
    font-size: 1em;
    width: 65%;
    box-sizing: border-box;
    margin: 0 0 0 0;
    border-radius: 10px;
    font-weight: 500;
    float: right;
    cursor: pointer;
}
.content .view .view_div .con01 .con0102 .btn02.ready {
    background-color: #ffffff;
    color: #2c786c;
    border: 1px solid #2c786c;
}

.content .view .view_div .layer01 {
    width: 100%;
    position: relative;
    display: none;
}
.content .view .view_div .layer01 .layer0101 {
    position: fixed;
    border-radius: 10px;
    width: 300px;
    margin: 0 auto;
    left: calc(100% / 2 - 150px);
    top: calc(100% / 2 - 150px);
    border: 1px solid #dedede;
    background-color: #ffffff;
    z-index: 22;
    padding: 20px;
    box-sizing: border-box;
    box-shadow: 3px 3px 10px 2px #797979;
    text-align: center;
}
.content .view .view_div .layer01 .layer0101 .text01 {
    display: inline-block;
    width: 100%;
    text-align: center;
    padding: 20px 0 10px 0;
    box-sizing: border-box;
}
.content .view .view_div .layer01 .layer0101 .label01 {
    display: inline-block;
    width: 100%;
    text-align: center;
    text-align: left;
    padding: 10px;
    background-color: #f4f4f4;
    box-sizing: border-box;
    border-top: 1px solid #dedede;
    border-bottom: 1px solid #dedede;
}
.content .view .view_div .layer01 .layer0101 .label01 .list01 {
    display: inline-block;
    width: 100%;
    text-align: left;
    box-sizing: border-box;
    padding: 0 0 12px 0;
    font-size: 0.9em;
}
.content .view .view_div .layer01 .layer0101 .text02 {
    background-color: #77777712;
    display: inline-block;
    margin: 0 auto;
    text-align: center;
    padding: 5px;
    margin: 10px 0 20px 0;
    font-size: 0.9em;
    color: #777777;
}
.content .view .view_div .layer01 .layer0101 .text02 strong {
    color: red;
}
.content .view .view_div .layer01 .layer0101 .btn0101 {
    border-top: 1px solid #dedede;
    padding: 20px 0 0 0;
    display: inline-block;
    width: 100%;
    text-align: center;
}
.content .view .view_div .layer01 .layer0101 .btn0101 .btn03 {
    background-color: #f4f4f4;
    border: 1px solid #dedede;
    border-radius: 5px;
    padding: 5px 10px 5px 10px;
    font-weight: 300;
    margin: 0 5px 0 0;
}
.content .view .view_div .layer01 .layer0101 .btn0101 .btn04 {
    background-color: #2c786c;
    border: 1px solid #2c786c;
    color: #ffffff;
    border-radius: 5px;
    padding: 5px 10px 5px 10px;
    font-weight: 500;
    margin: 0 0 0 5px;
}

.content .view .view_div .layer01 .layer0101 .text03 {
    display: inline-block;
    width: 100%;
    text-align: center;
    margin: 20px 0 0 0;
    padding: 10px 0 10px 0;
    box-sizing: border-box;
}
.content .view .view_div .layer01 .layer0101 .text04 {
    width: 100%;
    background-color: #ffffff;
    display: inline-block;
    margin: 0 auto;
    text-align: center;
    padding: 0 0 0 0;
    margin: 10px 0 20px 0;
    font-size: 0.9em;
    color: #777777;
    box-sizing: border-box;
}
.content .view .view_div .layer01 .layer0101 .text04 .textarea01 {
    width: 100%;
    padding: 10px;
    line-height: 1.5em;
    font-size: 1;
    letter-spacing: 0;
    box-sizing: border-box;
    border: 1px solid #ffffff;
    border-top: 1px solid #dedede;
    border-bottom: 1px solid #dedede;
    background-color: #f4f4f4;
}
.content .view .view_div .layer01 .layer0101 .text04 .textarea01::placeholder {
    color: #a9a9a9;
}

.content .view .view_div .con01 .con0103 {
    width: 100%;
    position: relative;
    margin: 0 auto 0 auto;
    box-sizing: border-box;
    background-color: #ffffff;
    color: #373737;
    overflow: hidden;
    line-height: 1.3em;
    text-align: left;
    padding: 10px 15px 0 15px;
    overflow: hidden;
}
.content .view .view_div .con01 .con0103 .span01 {
    display: inline-block;
    width: 30%;
    padding: 15px 0 15px 0;
    box-sizing: border-box;
    float: left;
    text-align: center;
    border-top: 1px solid #dedede;
    border-bottom: 1px solid #dedede;
    line-height: 1.5em;
    letter-spacing: 0;
}
.content .view .view_div .con01 .con0103 .span01.cen {
    width: 40%;
}
.content .view .view_div .con01 .con0103 .span01 .tit01 {
    display: inline-block;
    width: 100%;
    box-sizing: border-box;
    text-align: center;
    font-size: 0.9em;
    color: #999999;
    font-weight: 300;
    letter-spacing: -0.05em;
}
.content .view .view_div .con01 .con0103 .span01 .con010301 {
    display: inline-block;
    width: 100%;
    box-sizing: border-box;
    text-align: center;
    font-size: 0.9em;
    letter-spacing: -0.05em;
    color: #373737;
    font-weight: 400;
}
.content .view .view_div .con01 .con0103 .span01 .bar01 {
    position: absolute;
    width: 1px;
    height: 40px;
    background: #dedede;
    display: inline-block;
    margin: 40px 0 0 0;
    top: 0;
}

.content .view .view_div .con02 {
    width: 100%;
    position: relative;
    margin: 0 auto;
    padding: 15px 15px 15px 15px;
    box-sizing: border-box;
    background-color: #ffffff;
    color: #373737;
    overflow: hidden;
}
.content .view .view_div .con02 span.tit_mid {
    width: 100%;
    display: inline-block;
    padding: 15px 15px 15px 15px;
    box-sizing: border-box;
    position: relative;
    font-size: 1em;
    font-weight: 400;
    color: #333333;
    border-top: 1px solid #dedede;
    border-left: 1px solid #dedede;
    border-right: 1px solid #dedede;
    border-radius: 10px 10px 0 0;
    background-color: #f4f4f4;
}
.content .view .view_div .con02 span.tit_mid .btn01 {
    display: inline-block;
    padding: 7px 7px 7px 7px;
    box-sizing: border-box;
    position: absolute;
    font-size: 1em;
    font-weight: 400;
    font-size: 0.85em;
    border: 0;
    border-radius: 10px;
    color: #2c786c;
    border: 1px solid #2c786c;
    background-color: #ffffff;
    right: 0;
    float: right;
    margin: -5px 10px 0 0;
}
.content .view .view_div .con02 .con0201 {
    width: 100%;
    position: relative;
    margin: 0 auto 15px auto;
    padding: 15px 15px 15px 15px;
    box-sizing: border-box;
    border: 1px solid #dedede;
    border-radius: 0 0 10px 10px;
    background-color: #ffffff;
    color: #373737;
    overflow: hidden;
    line-height: 1.3em;
}
.content .view .view_div .con02 .con0201 .text01 {
    width: 100%;
    position: relative;
    margin: 10px auto 0 auto;
    padding: 10px 10px 10px 10px;
    text-align: center;
    box-sizing: border-box;
    background-color: #f4f4f4;
    color: #373737;
    overflow: hidden;
    line-height: 1.3em;
}
.content .view .view_div .con02 .con0202 {
    width: 100%;
    position: relative;
    margin: 0 auto 15px auto;
    padding: 15px 15px 15px 15px;
    box-sizing: border-box;
    border: 1px solid #dedede;
    border-radius: 0 0 10px 10px;
    background-color: #ffffff;
    color: #373737;
    overflow: hidden;
    line-height: 1.3em;
}

.content .view .view_div .con02 .con0202 .bar01 {
    display: inline-block;
    color: #e4e4e4;
    font-size: 0.8em;
    letter-spacing: 0;
}

.content .view .view_div .con02 .tab02 {
    display: none;
}
.content .view .view_div .con02 .con0203 {
    width: 100%;
    position: relative;
    margin: 0 auto 15px auto;
    padding: 15px 15px 15px 15px;
    box-sizing: border-box;
    border: 1px solid #dedede;
    border-radius: 0 0 10px 10px;
    background-color: #ffffff;
    color: #373737;
    overflow: hidden;
    line-height: 1.3em;
}
.content .view .view_div .con02 .con0204 {
    width: 100%;
    position: relative;
    margin: 0 auto 15px auto;
    padding: 15px 15px 15px 15px;
    box-sizing: border-box;
    border: 1px solid #dedede;
    border-radius: 0 0 10px 10px;
    background-color: #ffffff;
    color: #373737;
    overflow: hidden;
    line-height: 1.3em;
}
.content .view .view_div .con02 .con0301 {
    width: 100%;
    position: relative;
    margin: 0 auto 15px auto;
    padding: 15px 5px 15px 5px;
    box-sizing: border-box;
    border: 1px solid #dedede;
    border-radius: 0 0 10px 10px;
    background-color: #ffffff;
    color: #373737;
    overflow: hidden;
    line-height: 1.3em;
    text-align: center;
    font-size: 0.9em;
}
.content .view .view_div .con02 .con0301 .text01 {
    display: inline-block;
    position: relative;
    margin: 3px 3px 5px 3px;
    padding: 10px 10px 10px 10px;
    border-radius: 10px;
    text-align: center;
    box-sizing: border-box;
    background-color: #f4f4f4;
    color: #cccccc;
    font-weight: 300;
    overflow: hidden;
    letter-spacing: -0.05em;
    line-height: 1.3em;
    word-break: keep-all;
    text-decoration: line-through;
}
.content .view .view_div .con02 .con0301 .text01.on {
    font-weight: 400;
    background-color: #2c786c;
    color: #ffffff;
    border: 0;
    text-decoration: none;
}

.content .view .view_div .con02 span.head {
    width: 90px;
    position: relative;
    display: inline-block;
    float: left;
    margin: 5px 0 5px 0;
    color: #797979;
    font-size: 0.9em;
}
.content .view .view_div .con02 span.img {
    width: 110px;
    height: 90px;
    position: relative;
    display: inline-block;
    float: left;
    margin: 5px 0 5px 0;
    color: #797979;
    font-size: 0.9em;
    padding: 0 0 0 10px;
    box-sizing: border-box;
}
.content .view .view_div .con02 span.img .img01 {
    width: 70px;
    height: 70px;
    border: 0;
    border-radius: 70px;
    background-color: #fafafa;
    display: inline-block;
    box-sizing: border-box;
}
.content .view .view_div .con02 span.img .img01 img {
    width: 100%;
    height: 100%;
}
.content .view .view_div .con02 span.con {
    width: calc(100% - 110px);
    position: relative;
    display: inline-block;
    margin: 5px 0 5px 0;
    letter-spacing: 0em;
    font-size: 0.9em;
    line-height: 1.5em;
}
.content .view .view_div .con02 span.con2 {
    width: calc(100% - 110px);
    height: 90px;
    position: relative;
    display: inline-block;
    margin: 5px 0 5px 0;
    letter-spacing: 0.05em;
    font-size: 0.9em;
    line-height: 1.4em;
}
.content .view .view_div .con02 span.con2 .text0101 {
    width: 100%;
    display: inline-block;
    font-size: 1em;
    color: #373737;
    text-decoration: underline;
    font-weight: 500;
    padding: 2px 0 0 0;
}
.content .view .view_div .con02 span.con2 .text0102 {
    width: 100%;
    display: inline-block;
    font-size: 0.95em;
    color: #676767;
    padding: 5px 0 0 0;
}
.content .view .view_div .con02 span.con2 .text0103 {
    width: 100%;
    display: inline-block;
    font-size: 0.85em;
    color: #aaaaaa;
    letter-spacing: -0.05em;
    line-height: 1.3em;
}
.content .view .view_div .con02 span.con2 .btn0101 {
    position: absolute;
    right: 10px;
    display: inline-block;
    font-size: 1em;
    padding: 7px 7px 7px 7px;
    border-radius: 10px;
    color: #ffffff;
    background-color: #2c786c;
    border: 0;
    font-weight: 400;
}
.content .view .view_div .con02 span.con2 .btn0102 {
    position: absolute;
    right: 10px;
    display: inline-block;
    font-size: 1em;
    padding: 7px 7px 7px 7px;
    border-radius: 10px;
    color: #ffffff;
    background-color: #f8b400;
    border: 0;
    font-weight: 400;
}

.content .view .view_div .con02 span.con3 {
    width: calc(100% - 90px);
    float: right;
    position: relative;
    display: inline-block;
    margin: 0px 0 10px 0;
    letter-spacing: 0em;
    font-size: 0.8em;
    line-height: 1.5em;
}

.content .view .view_div .con02 .con0201 span.bar01 {
    width: 100%;
    border-top: 1px solid #dedede;
    padding: 10px 0 10px 0;
    display: inline-block;
}
.content .view .view_div .con02 .con0201 .modal_view {
    display: none;
    z-index: 60;
    position: fixed;
    top: 10%;
    width: calc(100% - 60px);
    max-width: 600px;
    border: 1px solid #dedede;
    box-sizing: border-box;
    border-radius: 5px;
    background-color: #ffffff;
    box-shadow: 0px 0px 20px #000000;
}

.content .view .view_div .con02 .tab01 {
    width: 100%;
    display: inline-block;
    position: relative;
    box-sizing: border-box;
    padding: 0 0 10px 0;
}
.content .view .view_div .con02 .tab01 .tab0101 {
    width: calc(100% / 3 - 8px);
    margin: 0 12px 0 0;
    display: inline-block;
    position: relative;
    box-sizing: border-box;
    padding: 10px;
    font-weight: 300;
    font-size: 0.8em;
    letter-spacing: 0;
    background-color: #5180781f;
    color: #474747;
    text-align: center;
    border-radius: 10px;
    float: left;
    border: 1px solid #dedede;
}
.content .view .view_div .con02 .tab01 .tab0101:nth-child(3) {
    margin: 0;
}
.content .view .view_div .con02 .tab01 .tab0101.on {
    background-color: #2c786c;
    color: #ffffff;
    border: 1px solid #2c786c;
    font-weight: 400;
}

.content .view .view_div .con02 .con0203 .con020301 {
    width: 100%;
    position: relative;
    box-sizing: border-box;
    border: 0;
    line-height: 1.5em;
    font-size: 0.8em;
    letter-spacing: 0;
    overflow: hidden;
    float: left;
    color: #373737;
    text-align: left;
    padding: 0 0 20px 0;
    margin: 0 0 20px 0;
    border-bottom: 1px solid #efefef;
}
.content .view .view_div .con02 .con0203 .con020301:nth-last-child(1) {
    border-bottom: 0;
    padding: 0 0 10px 0;
    margin: 0 0 10px 0;
}

.content .view .view_div .con02 .con0203 .con020303 {
    width: 100%;
    position: relative;
    box-sizing: border-box;
    border: 0;
    line-height: 1.3em;
    font-size: 0.8em;
    letter-spacing: 0;
    overflow: hidden;
    float: left;
    color: #373737;
    line-height: 1.2em;
    text-align: center;
}

.content .view .view_div .con02 .con0203 .img020301 {
    position: relative;
    width: 60px;
    height: 60px;
    border-radius: 60px;
    overflow: hidden;
    box-sizing: border-box;
    float: left;
    background-color: #fafafa;
    border: 1px solid #efefef;
    margin: 5px 30px 0 10px;
    float: left;
}
.content .view .view_div .con02 .con0203 .img020301 img {
    position: absolute;
    left: 50%;
    top: 50%;
    height: 100%;
    width: auto;
    -webkit-transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
}
.content .view .view_div .con02 .con0203 .img020301 .horizontal {
    width: auto;
    height: 100%;
}
.content .view .view_div .con02 .con0203 .img020301 .vertical {
    width: 100%;
    height: auto;
}
.content .view .view_div .con02 .con0203 .img020301 .auto {
    width: auto;
    height: auto;
}

.content .view .view_div .con02 .con0203 .name020301 {
    width: calc(100% - 100px);
    display: inline-block;
    position: relative;
    float: left;
}
.content .view .view_div .con02 .con0203 .bar020301 {
    color: #d4d4d4;
    font-size: 0.7em;
    vertical-align: top;
    padding: 0 5px 0 5px;
}
.content .view .view_div .con02 .con0203 .text020301 {
    color: #9a9a9a;
}
.content .view .view_div .con02 .con0203 .star020301 {
    width: calc(100% - 100px);
    display: inline-block;
    position: relative;
    float: left;
    padding: 5px 0 0 0;
}
.content .view .view_div .con02 .con0203 .star020301 .star02030101 {
    color: #5180781f;
}
.content .view .view_div .con02 .con0203 .star020301 .star02030101.on {
    color: #2c786c;
}

.content .view .view_div .con02 .con0203 .date020301 {
    color: #676767;
    font-size: 0.9em;
}
.content .view .view_div .con02 .con0203 .text020302 {
    width: calc(100% - 100px);
    display: inline-block;
    position: relative;
    float: left;
    padding: 5px 0 0 0;
}

.content .view .view_div .con02 .con0203 .con020302 {
    width: calc(100% - 100px);
    margin: 10px 0 0 100px;
    position: relative;
    box-sizing: border-box;
    border: 0;
    line-height: 1.3em;
    font-size: 0.8em;
    letter-spacing: 0;
    overflow: hidden;
    float: left;
    color: #676767;
    padding: 10px;
    background-color: #5180781f;
}

.content .view .view_div .modal_view .form01 {
    display: inline-block;
    width: 100%;
    padding: 20px;
    box-sizing: border-box;
}

.content .view .view_div .modal_view .form01 .area_cert {
    display: block;
    width: 100%;
    padding: 0 0 0 0;
}
.content .view .view_div .modal_view .form01 .area_cert .sub_title {
    font-weight: 400;
}

.content .view .view_div .modal_view .form01 h3.tit02 {
    width: 100%;
    padding: 10px 0 0 0;
    box-sizing: border-box;
    position: relative;
    font-size: 1.3em;
    font-weight: 500;
    color: #333333;
}

.content .view .view_div .modal_view .form01 .text01 {
    width: 100%;
    position: relative;
    margin: 10px auto 0 auto;
    padding: 10px 10px 10px 10px;
    text-align: center;
    box-sizing: border-box;
    background-color: #f4f4f4;
    color: #373737;
    overflow: hidden;
    line-height: 1.3em;
    font-size: 0.8em;
}

.content .view .view_div .modal_view .form01 .text02 {
    display: inline-block;
    position: absolute;
    right: 20px;
    margin: 0 auto 0 auto;
    padding: 5px 0 0 0;
    text-align: center;
    box-sizing: border-box;
    color: #373737;
    overflow: hidden;
    line-height: 1.3em;
    font-weight: 300;
    font-size: 0.8em;
}

.content .view .view_div .modal_view .form01 .con020101 {
    width: 100%;
    position: relative;
    overflow: hidden;
}
.content .view .view_div .modal_view .form01 .con020101 .select01 {
    display: inline-block;
    width: 100%;
}
.content .view .view_div .modal_view .form01 .con020101 .select01 .text0101 {
    width: 100%;
    background-color: #ffffff;
    color: #2c786c;
    padding: 9px 10px 9px 10px;
    border-radius: 5px;
    border: 1px solid #2c786c;
    box-sizing: border-box;
    margin: 0 0 10px 0;
}
.content .view .view_div .modal_view .form01 .con020101 .select01 .select0101 {
    width: 100%;
    background-color: #ffffff;
    color: #2c786c;
    padding: 9px 10px 9px 10px;
    border-radius: 5px;
    border: 1px solid #2c786c;
    box-sizing: border-box;
    margin: 0 0 10px 0;
}
.content .view .view_div .modal_view .form01 .con020101 .file01 {
    display: inline-block;
    width: 100%;
}
.content .view .view_div .modal_view .form01 .con020101 .file01 .file0101 {
    width: 100%;
    background-color: #ffffff;
    color: #2c786c;
    padding: 5px 10px 5px 10px;
    border-radius: 5px;
    border: 1px solid #2c786c;
    box-sizing: border-box;
}
.content .view .view_div .modal_view .form01 .con020101 .btn01 {
    display: inline-block;
    width: 100%;
}
.content .view .view_div .modal_view .form01 .con020101 .btn01 .btn0101 {
    width: 100%;
    background-color: #2c786c;
    color: #ffffff;
    padding: 9px 10px 9px 10px;
    border-radius: 5px;
    border: 0;
    box-sizing: border-box;
}

.content .view .view_div .modal_view .form01 .btn02 {
    display: inline-block;
    width: 100%;
    padding: 10px 0 10px 0;
    text-align: center;
}
.content .view .view_div .modal_view .form01 .btn02 .btn0201 {
    background-color: #2c786c;
    color: #ffffff;
    padding: 9px 30px 9px 30px;
    border-radius: 5px;
    border: 0;
    font-weight: bold;
}

.content .view .view_div .con02 span.con.map {
    width: 100%;
    position: relative;
    display: inline-block;
    margin: 5px 0 5px 0;
    letter-spacing: 0.05em;
    font-size: 0.9em;
}
.content .view .view_div .con02 span.con.map .text01 {
    width: 100%;
    position: relative;
    display: inline-block;
    padding: 10px;
    font-size: 0.9em;
    background-color: #f4f4f4;
    color: #474747;
    text-align: center;
    box-sizing: border-box;
    margin: 0 0 0 0;
}

.content .view .view_div .con02 span.con.map {
    width: 100%;
    position: relative;
    display: inline-block;
    margin: 5px 0 5px 0;
    letter-spacing: 0.05em;
    font-size: 0.9em;
}

.content .view .view_div .con02 span.con.exam {
    width: auto;
    position: relative;
    display: inline-block;
    text-align: right;
    margin: 5px 0 5px 0;
    letter-spacing: 0.05em;
    font-size: 0.7em;
    color: #2c786c;
    background-color: #f4f4f4;
    padding: 3px;
    box-sizing: border-box;
    float: right;
}

.content .view .view_div .con02 span.con .cctv_sub {
    width: auto;
    position: relative;
    display: inline-block;
    word-break: keep-all;
    text-align: right;
    margin: 0 0 0 0;
    letter-spacing: 0em;
    font-size: 0.8em;
    color: #666666;
    box-sizing: border-box;
}

.content .view .view_div .con02 span.con_subject {
    display: inline-block;
    width: 100%;
    text-align: center;
    background-color: #f4f4f4;
    padding: 5px 0 5px 0;
    font-size: 1.1em;
}

.content .view .view_div .con02 span.con_textarea {
    width: 100%;
    line-height: 1.7em;
}
</style>
