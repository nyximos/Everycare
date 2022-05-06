<template>
<v-card flat>
        <v-row class="mt-5">
            <v-text-field
            v-model="title"
            placeholder="제목을 입력하세요"
            outlined
          ></v-text-field>{{title}}
        </v-row>
         <v-row>
          <v-col cols="6">
      <v-select
      v-model="caretarget"
      :items="caretargetlist"
      item-text="id"
      chips
      label="케어대상인 선택">
    </v-select>
          </v-col>
          <v-col
            cols="6">
            <v-btn 
            v-if="showbtn"
            @click="buttonClick"
            :disabled="!lockSchedulebtn"
            >스케줄 선택</v-btn>
          <v-select
          v-if="showSchedule"
          v-model="pickSchedule"
          :items="schedulelist"
          item-text="id"
          chips
        ></v-select>
          </v-col>
        </v-row>
        <v-row>
            <v-col cols="4">
              <v-subheader>시작일</v-subheader>
           <input type="date" v-model="startDay">
         </v-col>
         <v-col cols="4">
           <v-subheader>종료일</v-subheader>
           <input type="date" v-model="endDay">
         </v-col>
    </v-row>
          <v-row>
            <v-col cols="4">
             <v-subheader>요일 선택</v-subheader>
            </v-col>
            <v-col cols="8">
            <v-select
            v-model="day"
            :items="days"
            attach
            chips
            multiple>
          </v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="4">
              <input type="time"
              v-model="startTime">
            </v-col>
            <v-col cols="4">
              <input type="time"
              v-model="endTime">
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="4">
              <v-subheader>희망 케어시터 성별</v-subheader> 
            </v-col>
            <v-col cols="8">
              <v-radio-group
             v-model="sitterSex"
            mandatory
            row>
            <v-radio
              label="남성"
              value="M">
              </v-radio>
            <v-radio
              label="여성"
              value="W">
              </v-radio>
          </v-radio-group>
            </v-col>
          </v-row>
    <v-row>
          <v-col
            cols="4"
          >
           <v-radio-group
             v-model="paytimepick"
            mandatory
            row>
            <v-radio
              label="시급"
              value="hourpay">
              </v-radio>
            <v-radio
              label="월급"
              value="monthpay">
              </v-radio>
          </v-radio-group>
          </v-col>
          <v-col
            cols="8"
          >
            <v-text-field
              v-model="pay"
              color="blue darken-2"
              label="희망 시/월급을 입력하세요"
              required
            ></v-text-field>
          </v-col>
          </v-row>
          <v-col cols="12">
            <v-textarea
              v-model="comment"
              label="남기고싶은 말 (optional)"
            >
            </v-textarea>
          </v-col>
      <v-btn
          text
          color="primary"
          @click="edit"
        >
          edit
        </v-btn>
        <v-btn
          text
          color="primary"
          @click="drop"
        >
          삭제
        </v-btn>
  </v-card>
</template>

<script>
export default {
    mounted() {
    const id = Number(this.$route.params.id);
    this.$http
      .get(`/api/recruitions/new`,{
        withCredentials:true
      })
		.then((res)=>{
        console.log(res.data);
        this.caretargetlist=res.data.body
      }).catch(err =>{
		alert(err);
		console.log(err);
	})
    this.$http
    .get(`/api/recruitions/recruition/${id}`,{
        withCredentials:true
    })
    .then((res)=>{
        console.log(res);
        this.comment = res.data.body.comment
        this.sitterSex = res.data.body.desiredCareSitterGender
        this.title = res.data.body.title
        this.startTime = res.data.body.desiredStartTime
        this.endTime = res.data.body.desiredEndTime
        this.endDay = res.data.body.endDate
        this.startDay = res.data.body.startDate
        this.pay = res.data.body.pay
    })
    .catch((err) => {
        console.log(err);
    })
    },
    data(){
        return{
        title: this.title,
        caretarget:this.caretarget,
        caretargetlist:[],
        pickSchedule: this.pickSchedule,
        schedulelist:[],
        startDay: this.startDay,
        endDay: this.endDay,
        startTime: this.startTime,
        endTime: this.endTime,
        day: [],
        days: ['월', '화', '수', '목', '금', '토', '일'],
        sitterSex: [],
        paytimepick: this.paytimepick,
        pay: this.pay,
        comment: this.comment,
        showbtn: true,
        showSchedule: false,
        btnLock: false
        }
    },
    methods:{
        edit(){
            const id = Number(this.$route.params.id);
            var formData = new FormData();
            formData.append('title',this.title);
            formData.append('careTarget', this.caretarget);
            formData.append('careTargetSchedule', this.pickSchedule);
            formData.append('startDate', this.startDay)
            formData.append('endDate',this.endDay);
            formData.append('desiredDayWeek', this.day.toString());
            formData.append('desiredCareSitterGender', this.sitterSex);
            formData.append('pay', this.pay);
            formData.append('comment',this.comment);
            formData.append('desiredStartTime', this.startTime);
            formData.append('desiredEndTime', this.endTime);
            this.$http
            .patch(`/api/recruitions/recruition${id}`, formData,{
            withCredentials:true
            })
            .then(res => {
            console.log(res);
            })
            .catch(err => {
            console.log(err);
            console.log(typeof this.endDay)
            });
        },
    drop(){
        const id = Number(this.$route.params.id);
        this.$http
        .delete(`/api/recruitions/recruition/${id}`,{
        withCredentials: true
        })
        .then((res)=> {
            console.log(res)
        }).catch((err)=>{
            console.log(err)
        })
    },
    buttonClick(){
    var formData = new FormData()
    formData.append('id', this.caretarget);
    this.$http
    .post('/api/recruitions/schedules',formData,{
    withCredentials:true
    })
	.then((res)=>{
    console.log(res.data);
    this.schedulelist=res.data.body
    })
    .catch(err =>{
	console.log(err);
	})
    this.showbtn=false,
    this.showSchedule=true
      }
    },
    computed:{
        lockSchedulebtn(){
        return (
        this.caretarget
      )},
    }
    
}
</script>

<style>

</style>
