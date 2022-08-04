<template>
<v-card flat>
      <v-container fluid>
        <v-row class="mt-5">
            <v-text-field
            v-model="title"
            placeholder="제목을 입력하세요"
            outlined
          ></v-text-field>
        </v-row>
         <v-row>
          <v-col cols="6">
      <v-select
      v-model="caretarget"
      :items="caretargetlist"
      item-text="name"
      item-value="id"
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
          item-text="name"
          item-value="id"
          chips
        ></v-select>
          </v-col>
        </v-row>
        <v-row>
    <v-col
      cols="12"
      sm="6"
    >
      <v-date-picker
        v-model="desiredDayWeek"
        multiple
      ></v-date-picker>
    </v-col>
    <v-col
      cols="12"
      sm="6"
    >
      <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        :return-value.sync="desiredDayWeek"
        transition="scale-transition"
        offset-y
        min-width="auto"
      >
        <template v-slot:activator="{ on, attrs }">
          <v-combobox
            v-model="desiredDayWeek"
            multiple
            chips
            small-chips
            label="Multiple picker in menu"
            prepend-icon="mdi-calendar"
            readonly
            v-bind="attrs"
            v-on="on"
          ></v-combobox>
        </template>
        <v-date-picker
          v-model="desiredDayWeek"
          multiple
          no-title
          scrollable
        >
          <v-spacer></v-spacer>
          <v-btn
            text
            color="primary"
            @click="menu = false"
          >
            Cancel
          </v-btn>
          <v-btn
            text
            color="primary"
            @click="$refs.menu.save(dates)"
          >
            OK
          </v-btn>
        </v-date-picker>
      </v-menu>
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
      </v-container>
      <v-card-actions>
        <v-btn
          text
          :to="{name:'Main'}" @click="back()">
          
          Cancel
        </v-btn>
        <v-spacer></v-spacer>
        
        <v-btn

          text
          color="primary"
          @click="submit">
          Register
        </v-btn>
        
      </v-card-actions>
  </v-card>
</template>

<script>
export default {
name: 'Create',
    mounted() {
      this.$http.get('/api/recruitions/new',{
        withCredentials:true
      })
			.then((res)=>{
        console.log(res.data);
        this.caretargetlist=res.data.body
      }).catch(err =>{
				alert(err);
				console.log(err);
			})
    },
    data () {
      return {
         title: this.title,
         caretarget:this.caretarget,
         caretargetlist:[],
         pickSchedule: this.pickSchedule,
         schedulelist:[],
         startDay:this.startDay,
         endDay:this.endDay,
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
        btnLock: false,
        desiredDayWeek: [],
        menu: false,
      }
    },
    methods: {
      submit(){
        // var careTarget = {this.caretarget};
        // var careTargetSchedule= {this.careTargetSchedule};
         var test = {
           careTarget: {id:this.caretarget},
           careTargetSchedule: {id:this.pickSchedule},
           title: this.title,
           startDate: this.startDay,
           endDate: this.endDay,
           desiredDayWeek: this.desiredDayWeek.toString(),
           desiredCareSitterGender: this.sitterSex,
           pay: this.pay,
           comment: this.comment,
           desiredStartTime: this.startTime,
           desiredEndTime: this.endTime
         }
      this.$http
      .post('/api/recruitions/recruition',test,{
       withCredentials:true
      })
     .then(res => {
      console.log(res);
      })
     .catch(err => {
       console.log(err);
       console.log(this.hi)
    });
    alert('구인글이 등록 되었습니다.')
                
       this.$router.push({ path: '/recruitions' })
       location.reload();
  },
  back(){
    this.$router.push({
        path:'/recruitions'
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
      }).catch(err =>{
        console.log(this.caretarget)
				console.log(err);
			})
    this.showbtn=false,
    this.showSchedule=true
  } 
},
  computed: {
    formIsValid(){
      return (
        this.desiredDayWeek &&
        this.title &&
        this.caretarget &&
        this.pickSchedule &&
        this.startDay &&
        this.endDay &&
        this.startTime &&
        this.endTime &&
        this.day &&
        this.sitterSex &&
        this.paytimepick &&
        this.pay
      )},
    lockSchedulebtn(){
      return (
        this.caretarget
      )},
    }    
}

</script>

<style>

</style>