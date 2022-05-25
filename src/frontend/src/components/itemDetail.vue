<template>
<v-container fluid>
    <h1>상세정보</h1>
        <v-row class="mt-5">
            <h5 class="fw-bold">{{title}}</h5>
        </v-row>
        <v-row>
          <v-col cols="4">
              <label>케어대상인</label>
              </v-col>
              <v-col col cols="4">
                <p>{{caretarget}}</p>
              </v-col>
        </v-row>
          <v-row>
          <v-col cols="4">
              <label>스케줄</label>
              </v-col>
              <v-col col cols="4">
                <p>{{careTargetSchedule}}</p>
              </v-col>
        </v-row>
        <v-row>
            <v-col cols="4">
              <label>시작일</label>
              </v-col>
              <v-col col cols="4">
                <p>{{startDay}}</p>
              </v-col>
        </v-row>
        <v-row>
         <v-col cols="4">
              <label>종료일</label>
              </v-col>
              <v-col col cols="4">
                <p>{{endDay}}</p>
              </v-col>
        </v-row>
          <v-row>
            <v-col cols="4">
             <label>요일</label>
            </v-col>
            <v-col cols="8">
            <p>{{day}}</p>
            </v-col>
          </v-row>
          <v-row>
              <v-col cols="4">
             <label>시작시간</label>
            </v-col>
            <v-col cols="4">
              <p>{{startTime}}</p>
            </v-col>
          </v-row>
          <v-row>
              <v-col cols="4">
             <label>종료시간</label>
            </v-col>
            <v-col cols="4">
              <p>{{endTime}}</p>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="4">
              <label>희망 케어시터 성별</label> 
            </v-col>
            <v-col cols="4">
              <p>{{sitterSex}}</p>
            </v-col>
          </v-row>
    <v-row>
        <v-col cols="4">
             <label>급여</label>
            </v-col>
          <v-col cols="8">
            <p>{{pay}}</p>
          </v-col>
          </v-row>
          <v-row>
          <v-col cols="4">
             <label>설명</label>
            </v-col>
          <v-col cols="8">
            <p>{{comment}} </p>
          </v-col>
          </v-row>
          <v-btn color="primary" @click="application">신청</v-btn>
          <v-btn @click="back">취소</v-btn>
      </v-container>
</template>

<script>

export default {
name:'itemDetail',
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
    .get(`/api/recruitions/recruition/${id}`,{
        withCredentials: true
    })
    .then((res)=>{
        console.log(res)
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
        this.careTargetSchedule = res.data.body.careTargetScheduleListDTO
    })
    .catch((err)=>{
        console.log(err)
    })
},
data(){
    return{
        title: this.title,
        caretarget:this.caretarget,
        careTargetSchedule:this.careTargetSchedule,
        pickSchedule: this.pickSchedule,
        schedulelist:[],
        startDay: this.startDay,
        endDay: this.endDay,
        startTime: this.startTime,
        endTime: this.endTime,
        day: [],
        sitterSex: [],
        paytimepick: this.paytimepick,
        pay: this.pay,
        comment: this.comment,
        id: this.$route.params.contentId,
    }
},
methods:{

application(){
  
  // this.$router.push({ name: 'CaretargetsDetail', params: { caretargetsId: p.id }})
  const id = this.id
  console.log(id);
  // console
   this.$http
    .post(`/api/recruitions/${id}/contracts`, {
      withCredentials: true  
    })
    // this.$router.push({name:'jobapplication', params:{contentId:id}})
      .then((res) => {
        console.log(res);
          // console.log(res.data.body)
          // this.profiles = res.data.body
          
  }).catch(err => {
    alert(err);
    console.log(err);
  })

  // location.href = '/';
},
back(){
    this.$router.push({
        path:'/joblist'
    })
}
},

}

</script>

<style>

</style>