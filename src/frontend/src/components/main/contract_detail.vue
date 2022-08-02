<template>
  <div id="content">
  <div id="subContentgb">
        <div class="ggViewWrap" id="layer_ggViewWrap">
            <section class="resume-view">
    <div class="resume-field resume-field-title">
        <h2 class="resume-title">계약서</h2>
    </div>
    <article class="resume-field resume-field-personal">
        <div class="photo ">
                <img :src="'https://localhost:8086/api/images/' + image[0].storeFileName"  alt="프로필사진" id="resumeimg" class="pic">
                </div>
        <div class="personal-info">
            <div class="profile">
                <span class="name">
                        <p class="devBtnOpenLoginPopup">{{contract.careTargetDetailDTO.name}}</p>
                </span>
                <span class="gender">
                    {{gender}}
                </span>
                <span class="age">{{contract.careTargetDetailDTO.birth}}</span>
            </div>
            <div class="contact">
                    <ul class="contact-items">
                            <li>
                                <span class="data-head">주소</span>
                                <span class="data-content">{{contract.careTargetDetailDTO.address}} {{contract.careTargetDetailDTO.detailedAddress}}</span>
                            </li>
                        <li>
                            <span class="data-head">키</span>
                            <span class="data-content">
                                <span class="open devBtnOpenLoginPopup">{{contract.careTargetDetailDTO.height}}cm</span>
                            </span>
                        </li>
                            <li>
                                <span class="data-head">몸무게</span>
                                <span class="data-content">
                                        <span class="open devBtnOpenLoginPopup">
                                            {{contract.careTargetDetailDTO.weight}}kg
                                        </span>
                                </span>
                            </li>
                    </ul>
            </div>
        </div>
    </article>
    <input type="hidden" name="isUsePlayCntSave" id="isUsePlayCntSave" value="? 1:0">
    <article class="resume-field resume-field-hope">
        <div class="field-head">
            <h3 class="heading">개인정보</h3>
        </div>
        <div class="field-content">
            <ul>
                <li class="data-item">
                    <div class="data-head">케어타입</div>
                    <div class="data-content">
                        <p>{{contract.careTargetDetailDTO.careType}}</p>
                    </div>
                </li>
                <li class="data-item">
                    <div class="data-head">정보</div>
                    <div class="data-content">
                        <ul class="data-list hope-job-list">
                                <li>
                                    펫: {{pet}}
                                </li>
                                <li>
                                    백신: {{vaccinated}}
                                </li>
                                <li>
                                    CCTV: {{cctvAgreement}}
                                </li>
                                <li>
                                    케어등급: {{contract.careTargetDetailDTO.longTermCareGrade}}등급
                                </li>
                        </ul>
                    </div>
                </li>
                <li class="data-item">
                    <div class="data-head">Comment</div>
                    <div class="data-content">
                        <p>{{contract.careTargetDetailDTO.comment}}</p>
                    </div>
                </li>
            </ul>
        </div>
    </article>
    <article class="resume-field resume-field-license">
        <div class="field-head">
            <h3 class="heading">계약정보</h3>
        </div>
        <div class="field-content">
            <ul>
                    <li class="data-item">
                        <div class="data-head">title</div>
                        <div class="data-content">
                            <p><strong>{{contract.contractJobOfferDTO.title}}</strong></p>
                        </div>
                    </li>
                    <li class="data-item">
                        <div class="data-head">날짜/시간</div>
                        <div class="data-content">
                            <p><strong>{{contract.contractJobOfferDTO.startDate}} ~ {{contract.contractJobOfferDTO.endDate}}</strong></p>
                            <p>{{contract.contractJobOfferDTO.desiredStartTime}} ~ {{contract.contractJobOfferDTO.desiredEndTime}}</p>
                        </div>
                    </li>
                    <li class="data-item">
                        <div class="data-head">pay</div>
                        <div class="data-content">
                            <p><strong>{{contract.contractJobOfferDTO.amount}}원</strong></p>
                            <p>{{contract.contractJobOfferDTO.pay}}원</p>
                        </div>
                    </li>
            </ul>
        </div>
    </article>
            <div class="btn">
                <v-btn class="ma-2" outlined color="indigo" @click="submit">수락</v-btn>
                <router-link to="/dashboard/caresitter/contracts"><v-btn class="ma-2" outlined color="indigo">취소</v-btn></router-link>
            </div>
            </section>
        </div>
    </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            contract:[
            ],
            gender:this.gender,
            cctvAgreement:this.cctvAgreement,
            vaccinated:this.vaccinated,
            image:this.image
        }
    },
    mounted(){
        const id = this.$route.params.contractId;
        this.$http
        .get(`/api/dashboard/caresitter/contracts/${id}`,{
            withCredentials:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.contract = res.data.body;
            this.image = res.data.body.careTargetDetailDTO.imageDTOs
            if(res.data.body.careTargetDetailDTO.gender == "M"){
                this.gender = "남자"
            }else{
                this.gender = "여자"
            }
            if(res.data.body.careTargetDetailDTO.coronaTest == 0){
                this.vaccinated = "1차접종완료"
            }else if(res.data.body.careTargetDetailDTO.coronaTest ==1){
                this.vaccinated = "추가접종완료"
            }else if(res.data.body.careTargetDetailDTO.coronaTest == 2){
                this.vaccinated = "미접종"
            } 

            if(res.data.body.careTargetDetailDTO.isCctvAgreement == 0){
            this.cctvAgreement = "O"
            }else{
            this.cctvAgreement = "X"
            }
            if(res.data.body.careTargetDetailDTO.pet == 0){
                this.pet = "O"
            }else{
                this.pet = "X"
            }
        }).catch(err=>{
            console.log(err);
        })
    },

    methods:{
        submit(){
            if(confirm("수락하시겠습니까??") ==true){
            const id = this.$route.params.contractId;
            console.log(id);
            this.$http
            .post(`/api/dashboard/caresitter/contracts/${id}`,{

            }).then((res)=>{
                console.log(res);
                alert("수락완료");
                this.$router.push({ path: '/Main' })
            }).catch(err=>{
                console.log(err);
            })
            }else{
                return false;
            }
        },
    }
}
</script>

<style>
    .btn{
        width: 100%;
    } 
    #content{
        background: #f1f4f8;
        padding: 0 0 80px;
        padding-top: 50px;
    }
    #content .resume-view{
        /* padding-bottom: 0px; */
    }
    #content .ggViewWrap{
        position: relative;
    }
    .ggViewWrap{
        width: 960px;
        margin-left: auto;
        margin-right: auto;
    }
    .resume-view{
        padding: 94px 0 80px;
        box-sizing: border-box;
        background: #fff;
        font-size: 14px;
        line-height: 1.4;
    }
    .resume-view .resume-field{
        margin: 80px 129px 0;
    }
    .resume-view .resume-field.resume-field-title{
        position: relative;
        margin-top: 0;
        padding-bottom: 20px;
        border-bottom: 1px solid #e5e7ea;
    }
    .resume-view .resume-field.resume-field-personal{
        margin-top: 40px;
        position: relative;
    }

    article{
        display: block;
    }
    .resume-view .resume-field.resume-field-personal .photo{
        width: 118px;
        height: 150px;
        margin-right: 50px;
        border: 1px solid #e5e7ea;
        box-sizing: border-box;
        border-radius: 8px;
        overflow: hidden;
        position: absolute;
        top: 0;
        left: 0;
    }
    .resume-view .resume-field.resume-field-personal .photo .pic{
        width: 100%;
        transform: scale(1.1);
        transform-origin: center 0;
    }
    img{
        vertical-align: top;
    }
    .resume-view .resume-field.resume-field-personal .photo:not(.hide)+.personal-info{
        padding-left: 154px;
    }
    .resume-view .resume-field.resume-field-personal .profile .name{
        margin-right: 8px;
        font-size: 22px;
        font-weight: 700;
        color: #222;
    }
    .resume-view .resume-field.resume-field-personal .profile .gender ,.age{
        font-size: 14px;
        color: #666;
    }
    .resume-view .resume-field.resume-field-personal .profile>*{
        display: inline-block;
        /* vertical-align: middle; */
    }
    .resume-view .resume-field.resume-field-personal .profile .age:before{
        content: "\B7";
        margin: 0 4px;
    }
    .resume-view .resume-field.resume-field-personal .contact{
        position: relative;
    }
    .resume-view .resume-field.resume-field-personal .contact-items{
        margin-top: 3px;
        padding-left: 0px;
    }
    .resume-view .resume-field.resume-field-personal .contact-items>li{
        height: 28px;
    }
    .resume-view .resume-field.resume-field-personal .contact-items>li .data-head{
        line-height: 26px;
        display: inline-block;
        width: 98px;
        color: #999;
    }
    .resume-view .resume-field .data-content{
        width: 72%;
    }
    ul,li{
        list-style: none;
    }
    .resume-view .resume-field .field-head{
        padding-bottom: 20px;
        font-size: 0;
        border-bottom: 1px solid #222;
    }
    .resume-view .resume-field .heading{
        display: inline-block;
        font-size: 22px;
        font-weight: 700;
        color: #222;
        vertical-align: middle;
    }
    .resume-view .resume-field .data-item{
        padding: 24px 0;
        border-bottom: 1px solid #f1f4f8;
    }
    .resume-view .resume-field .data-head{
        width: 27%;
        color: #666;
    }
    .resume-view .resume-field.resume-field-hope .data-content{
        font-size: 16px;
    }
    .resume-field .data-content,.data-head{
        display: inline-block;
        vertical-align: top;
        word-wrap: break-word;
        word-break: break-all;
    }
    .resume-field .data-content{
        width: 72%;
    }
    .resume-view .resume-field .data-content .data-list{
        margin:  -5px 0;
    }
    .resume-view .resume-field .data-content .data-list.hope-job-list li{
        float: left;
        width: 50%;
    }
    .resume-view .resume-field .data-content .data-list.hope-job-list{
        padding-left: 0px;
    }
</style>