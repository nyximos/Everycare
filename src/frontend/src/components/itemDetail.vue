<template>
  <div class="content">
    <div class="view">
      <div class="view_div">

        <div class="con01">
          <div class="con0101">
            <!-- <span class="icon03" id="btn_alert" @click="update">수정하기</span>			<span class="file"> -->
            <span style=" width:100px; height:100px;  overflow:hidden;">
              <img :src="'https://localhost:8080/api/images/'+ careTargetImages " class="main_img img_sample">
            </span>


            <div class="layer01" id="layer_alert">
            </div>
            <span class="name">
              <span class="name01">{{ caretarget }}
              </span>
              <span class="sex">
                {{ gender }} <span class="bar01">|</span> {{ height }} cm <span class="bar01">|</span> {{ weight }} kg
                <!-- <span class="icon01 icon01_2">✓ 인증완료</span> -->
              </span>

            </span>
          </div>

          <div class="con0103">
            <span class="span01">
              <span class="tit01">제목</span>
              <span class="con010301">{{ title }}</span>
              <span class="bar01"></span>
            </span>
            <span class="span01 cen">
              <span class="tit01">주소</span>
              <span class="con010301">{{ address }} {{ detailAddress }}</span>
              <span class="bar01"></span>
            </span>
            <span class="span01">
              <span class="tit01">희망활동</span>
              <span class="con010301">{{ activity }}</span>
            </span>
          </div>

        </div>

        <div class="con02">
          <h3 class="tit_top">상세정보
          </h3>




          <span class="tit_mid">채용정보
          </span>
          <div class="con0202">
            <span class="head">희망시터성별</span>
            <span class="con">{{ sitterSex }}&nbsp;</span>
            <span class="head">희망날짜</span>
            <span class="con">{{ startDay }} ~ {{ endDay }}&nbsp;</span>
            <span class="head">급여</span>
            <span class="con">시급 : {{ pay }}원&nbsp;</span>

          </div>

          <span class="tit_mid">상세요강
          </span>
          <div class="con0202">

            <span class="head">스케줄</span>
            <span class="con">총 활동시간 : {{ start1 }} ~ {{ end1 }} </span>
            <div class="c0">
            <div class="c1">
              <p v-for="(r,index) in activity123" :key="index">
                {{r.name}}
              </p>
            </div>
            
            
            <div class="c2">
              <p v-for="(m,index) in member1" :key="index">
                {{m.requirement}}
              </p>
            </div>
            </div>



            <span class="head">추가사항</span>
            <span class="con"><span>{{ comment }}</span>&nbsp;</span>
          </div>
          <v-btn color="primary" @click="application">신청</v-btn>
          <v-btn @click="back">취소</v-btn>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'itemDetail',
  mounted() {
    this.$http
      .get('/api/dashboard/caresitter/contracts', {
        withCredentials: true
      })
      .then((res) => {
        console.log(res)
        console.log(res.data.body)
        this.id = res.data.body.id
      }).catch(err => {
        alert(err);
        console.log(err);
      })
    const id = Number(this.$route.params.contentId);
    this.$http
      .get(`/api/recruitions/recruition/${id}`, {
        withCredentials: true
      })
      .then((res) => {
        console.log(res.data.bdo)
        this.activity123 = res.data.body.activityClassificationsDTO
        console.log(this.activity123)
        this.member1= res.data.body.activityInformationDTO

        this.member = res.data.body.member
        this.title = res.data.body.title
        this.comment = res.data.body.comment
        this.day = res.data.body.desiredDayWeek
        this.sitterSex = res.data.body.desiredCareSitterGender
        this.startDay = res.data.body.startDate
        this.endDay = res.data.body.endDate
        this.startTime = res.data.body.desiredStartTime
        this.endTime = res.data.body.desiredEndTime
        this.pay = res.data.body.pay
        this.caretarget = res.data.body.careTarget.name
        this.gender = res.data.body.careTarget.gender
        this.birth = res.data.body.careTarget.birth
        this.weight = res.data.body.careTarget.weight
        this.height = res.data.body.careTarget.height
        this.address = res.data.body.careTarget.address
        this.detailAddress = res.data.body.careTarget.detailAddress
        this.careTargetSchedule = res.data.body.careTargetScheduleListDTO
        this.activity = res.data.body.careTargetScheduleListDTO.name
        this.start1 = res.data.body.careTargetScheduleListDTO.startTime
        this.end1 = res.data.body.careTargetScheduleListDTO.endTime
        this.requirement1 = res.data.body.activityClassificationsDTO
        
        // this.end1 = res.data.body.activityInformationDTO[0].endTime
        // this.requirement1 = res.data.body.activityInformationDTO[0].requirement
        // this.start1 = res.data.body.activityInformationDTO[0].startTime
        // this.end1 = res.data.body.activityInformationDTO[0].endTime
        // this.requirement1 = res.data.body.activityInformationDTO[0].requirement
        // this.start2 = res.data.body.activityInformationDTO[1].startTime
        // this.end2 = res.data.body.activityInformationDTO[1].endTime
        // this.requirement2 = res.data.body.activityInformationDTO[1].requirement
        // this.start3 = res.data.body.activityInformationDTO[2].startTime
        // this.end3 = res.data.body.activityInformationDTO[2].endTime
        // this.requirement3 = res.data.body.activityInformationDTO[2].requirement
        this.careTargetImages = res.data.body.careTargetImages
        console.log(this.careTargetImages)

      })
      .catch((err) => {
        console.log(err)
        
      })
  },
  data() {
    return {
      title: this.title,
      caretarget: this.caretarget,
      careTargetSchedule: this.careTargetSchedule,
      pickSchedule: this.pickSchedule,
      schedulelist: [],
      startDay: this.startDay,
      endDay: this.endDay,
      startTime: this.startTime,
      endTime: this.endTime,
      day: [],
      sitterSex: [],
      paytimepick: this.paytimepick,
      pay: this.pay,
      comment: this.comment,
      id: this.id,
      careTargetImages: this.careTargetImages,
      requirement: this.requirement1,
      // member1:this.member1
    }
  },
  methods: {

    application() {
      // this.$router.push({name:'jobapplication', params:{contractId:id}})

      // console.log(this.id)
      // this.$router.push({ name: 'CaretargetsDetail', params: { caretargetsId: p.id }})
      const id = Number(this.$route.params.contentId);
      // console
      this.$http
        .post(`/api/recruitions/${id}/contracts`, {
          withCredentials: true
        })
        .then((res) => {
          console.log(res);
          // console.log(res.data.body)
          // this.profiles = res.data.body

        }).catch(err => {
          alert(err);
          console.log(err);
        })
         alert('계약이 신청 되었습니다.')
                
       this.$router.push({ path: '/recruitions' })
       location.reload();
      // alert('계약신청 되었습니다.')

      // this.$router.push({ path: '/recruitions' })
      // location.reload();
      // location.href = '/';
    },
    back() {
      this.$router.push({
        path: '/recruitions'
      })
    }
  },

}

</script>

<style>
.c0{

}
.c1{
/*  float:left;  */
width: 30%;
display: flex;
flex-direction: row;
justify-content: center;
}

.c2{
/*  float: right; */
margin-left:10px;
width: 30%;
}
.content {
  width: 100%;
  position: relative;
}

@media (max-width:410px) {
  .content {
    font-size: 0.9em;
  }
}

@media (max-width:320px) {
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
  color: #FFFFFF;
  background-color: #2c786c;
  right: 0;
  float: right;
  margin: 0 10px 0 0;
}

.content .view {
  width: 100%;
  max-width: 670px;
  min-height: 800px;
  background-color: #FFFFFF;
  position: relative;
  margin: 0 auto;
  border-left: 1px solid #DEDEDE;
  border-right: 1px solid #DEDEDE;
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
  color: #FFFFFF;
  background-color: #2c786c;
  right: 0;
  float: right;
  margin: 0 10px 0 0
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
  background-color: #FFFFFF;
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
  border-top: 1px solid #DEDEDE;
  border-left: 1px solid #DEDEDE;
  border-right: 1px solid #DEDEDE;
  border-radius: 10px 10px 0 0;
  background-color: #F4F4F4
}

.content .view .view_div .con01 .con0101 {
  width: 100%;
  position: relative;
  margin: 0 auto 0 auto;
  box-sizing: border-box;
  background-color: #FFFFFF;
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
  background-color: #FFFFFF;
  color: #f8b400;
  border: 1px solid #f8b400;
  position: absolute;
  border-radius: 30px;
  left: 10px;
  top: 10px;
  font-size: 0.8em
}

.content .view .view_div .con01 .con0101 .icon01_2 {
  position: relative;
  padding: 1px 5px 1px 5px;
  margin: 0 0 0 15px;
  left: 0;
  top: 0;
  font-size: 0.6em
}

.content .view .view_div .con01 .con0101 .icon02 {
  display: inline-block;
  padding: 5px 10px 5px 10px;
  background-color: #FFFFFF;
  color: #2c786c;
  border: 1px solid #2c786c;
  position: absolute;
  border-radius: 30px;
  left: 93px;
  top: 10px;
  font-size: 0.8em
}

.content .view .view_div .con01 .con0101 .icon02_2 {
  position: relative;
  padding: 1px 5px 1px 5px;
  margin: 0 0 0 0;
  left: 0;
  top: 0;
  font-size: 0.6em
}

.content .view .view_div .con01 .con0101 .icon03 {
  display: inline-block;
  padding: 5px 10px 5px 10px;
  background-color: #FFFFFF;
  color: #474747;
  border: 1px solid #CCCCCC;
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
  background-color: #f8b4000F;
  font-size: 0.9em;
  margin: 15px;
  color: #9A9A9A;
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
  background-color: #F4F4F4;
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
  color: #FFFFFF;
  background-color: #ff7979;
  padding: 5px 5px 5px 5px;
  vertical-align: middle;
  word-break: keep-all;
  margin: 5px 5px 0 0;
  border-radius: 10px;
}

.content .view .view_div .con01 .con0101 .sex .bar01 {
  display: inline-block;
  color: #E4E4E4;
  font-size: 0.8em;
  letter-spacing: 0;
}

.content .view .view_div .con01 .con0101 .bar_star {
  display: inline-block;
  position: relative;
  width: 95px;
  height: 18px;
  box-sizing: border-box;
  background-color: #DEDEDE;
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
  color: #E4E4E4;
  font-size: 1.2em;
  letter-spacing: 0;
  margin: 0 10px 0 0;
}

.content .view .view_div .con01 .con0102 {
  width: 100%;
  position: relative;
  margin: 0 auto 0 auto;
  box-sizing: border-box;
  background-color: #FFFFFF;
  color: #373737;
  line-height: 1.3em;
  text-align: left;
  padding: 25px 15px 0 15px;
}

.content .view .view_div .con01 .con0102 .btn01 {
  position: relative;
  display: inline-block;
  padding: 10px 0 10px 0;
  background-color: #FFFFFF;
  border: 1px solid #DEDEDE;
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
  color: #FFFFFF;
}

.content .view .view_div .con01 .con0102 .btn02 {
  position: relative;
  display: inline-block;
  padding: 10px 15px 10px 15px;
  background-color: #2c786c;
  border: 1px solid #2c786c;
  color: #FFFFFF;
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
  background-color: #FFFFFF;
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
  border: 1px solid #DEDEDE;
  background-color: #FFFFFF;
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
  background-color: #F4F4F4;
  box-sizing: border-box;
  border-top: 1px solid #DEDEDE;
  border-bottom: 1px solid #DEDEDE;
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
  border-top: 1px solid #DEDEDE;
  padding: 20px 0 0 0;
  display: inline-block;
  width: 100%;
  text-align: center;
}

.content .view .view_div .layer01 .layer0101 .btn0101 .btn03 {
  background-color: #F4F4F4;
  border: 1px solid #DEDEDE;
  border-radius: 5px;
  padding: 5px 10px 5px 10px;
  font-weight: 300;
  margin: 0 5px 0 0;
}

.content .view .view_div .layer01 .layer0101 .btn0101 .btn04 {
  background-color: #2c786c;
  border: 1px solid #2c786c;
  color: #FFFFFF;
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
  background-color: #FFFFFF;
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
  border: 1px solid #FFFFFF;
  border-top: 1px solid #DEDEDE;
  border-bottom: 1px solid #DEDEDE;
  background-color: #F4F4F4
}

.content .view .view_div .layer01 .layer0101 .text04 .textarea01::placeholder {
  color: #A9A9A9
}



.content .view .view_div .con01 .con0103 {
  width: 100%;
  position: relative;
  margin: 0 auto 0 auto;
  box-sizing: border-box;
  background-color: #FFFFFF;
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
  border-top: 1px solid #DEDEDE;
  border-bottom: 1px solid #DEDEDE;
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
  background: #DEDEDE;
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
  background-color: #FFFFFF;
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
  border-top: 1px solid #DEDEDE;
  border-left: 1px solid #DEDEDE;
  border-right: 1px solid #DEDEDE;
  border-radius: 10px 10px 0 0;
  background-color: #F4F4F4
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
  background-color: #FFFFFF;
  right: 0;
  float: right;
  margin: -5px 10px 0 0
}

.content .view .view_div .con02 .con0201 {
  width: 100%;
  position: relative;
  margin: 0 auto 15px auto;
  padding: 15px 15px 15px 15px;
  box-sizing: border-box;
  border: 1px solid #DEDEDE;
  border-radius: 0 0 10px 10px;
  background-color: #FFFFFF;
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
  border: 1px solid #DEDEDE;
  border-radius: 0 0 10px 10px;
  background-color: #FFFFFF;
  color: #373737;
  overflow: hidden;
  line-height: 1.3em;
}

.content .view .view_div .con02 .con0202 .bar01 {
  display: inline-block;
  color: #E4E4E4;
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
  border: 1px solid #DEDEDE;
  border-radius: 0 0 10px 10px;
  background-color: #FFFFFF;
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
  border: 1px solid #DEDEDE;
  border-radius: 0 0 10px 10px;
  background-color: #FFFFFF;
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
  border: 1px solid #DEDEDE;
  border-radius: 0 0 10px 10px;
  background-color: #FFFFFF;
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
  color: #CCCCCC;
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
  color: #FFFFFF;
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
  background-color: #FAFAFA;
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
  color: #AAAAAA;
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
  color: #FFFFFF;
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
  color: #FFFFFF;
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
  border-top: 1px solid #DEDEDE;
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
  border: 1px solid #DEDEDE;
  box-sizing: border-box;
  border-radius: 5px;
  background-color: #FFFFFF;
  box-shadow: 0px 0px 20px #000000;
}

.content .view .view_div .con02 .tab01 {
  width: 100%;
  display: inline-block;
  position: relative;
  box-sizing: border-box;
  padding: 0 0 10px 0
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
  border: 1px solid #DEDEDE;
}

.content .view .view_div .con02 .tab01 .tab0101:nth-child(3) {
  margin: 0;
}

.content .view .view_div .con02 .tab01 .tab0101.on {
  background-color: #2c786c;
  color: #FFFFFF;
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
  border-bottom: 1px solid #EFEFEF;
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
  background-color: #FAFAFA;
  border: 1px solid #EFEFEF;
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
  color: #D4D4D4;
  font-size: 0.7em;
  vertical-align: top;
  padding: 0 5px 0 5px;
}

.content .view .view_div .con02 .con0203 .text020301 {
  color: #9A9A9A
}

.content .view .view_div .con02 .con0203 .star020301 {
  width: calc(100% - 100px);
  display: inline-block;
  position: relative;
  float: left;
  padding: 5px 0 0 0;
}

.content .view .view_div .con02 .con0203 .star020301 .star02030101 {
  color: #5180781f
}

.content .view .view_div .con02 .con0203 .star020301 .star02030101.on {
  color: #2c786c
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
  padding: 0 0 0 0
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
  font-size: 0.8em
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
  font-size: 0.8em
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
  background-color: #FFFFFF;
  color: #2c786c;
  padding: 9px 10px 9px 10px;
  border-radius: 5px;
  border: 1px solid #2c786c;
  box-sizing: border-box;
  margin: 0 0 10px 0;
}

.content .view .view_div .modal_view .form01 .con020101 .select01 .select0101 {
  width: 100%;
  background-color: #FFFFFF;
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
  background-color: #FFFFFF;
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
  color: #FFFFFF;
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
  color: #FFFFFF;
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
  background-color: #F4F4F4;
  color: #474747;
  text-align: center;
  box-sizing: border-box;
  margin: 0 0 0 0
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
  background-color: #F4F4F4;
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
  background-color: #F4F4F4;
  padding: 5px 0 5px 0;
  font-size: 1.1em;
}

.content .view .view_div .con02 span.con_textarea {
  width: 100%;
  line-height: 1.7em;
}
</style>