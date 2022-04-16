<template>
<v-card flat>
    <v-snackbar
      v-model="snackbar"
      absolute
      top
      right
      color="success"
    >
    <v-icon dark>
        mdi-checkbox-marked-circle
    </v-icon>
      <span>Registration successful!</span>
    </v-snackbar>
      <v-container fluid>
        <v-row class="mt-5">
            <v-text-field
            v-model="title"
            placeholder="제목을 입력하세요"
            outlined
          ></v-text-field>
        </v-row>
         <v-row>
          <v-col
            cols="6"
          >
      <v-select
      v-model="caretarget"
      :items="caretargetlist"
      label="케어대상인 선택"
      multiple
    >
      <template v-slot:selection="{ item, index }">
        <v-chip v-if="index === 0">
          <span>{{ item }}</span>
        </v-chip>
        <span
          v-if="index === 1"
          class="grey--text text-caption"
        >
          (+{{ caretarget.length - 1 }} others)
        </span>
      </template>
    </v-select>
          </v-col>
          <v-col
            cols="6">
            <v-btn
            @click="openSchedule">
            스케줄을 선택하세요
            </v-btn>
            <div v-if="Schedule">
              <v-radio-group 
              v-model="radioGroup">
            <v-radio
              v-for="pickSchedule in schedulelist"
              :key="pickSchedule"
              :label="`스케줄${pickSchedule}`"
              :value="`${pickSchedule}`"
            ></v-radio>
    </v-radio-group>
            </div>
          </v-col>
          </v-row>
        <v-row>
            <v-col
            cols="6"
            >
            <v-date-picker
                v-model="dates"
                range
            ></v-date-picker> 
                </v-col>
                <v-col
                cols="6"
                >
            <v-text-field
                v-model="dateRangeText"
                label="계약일을 선택해주세요"
                prepend-icon="mdi-calendar"
                readonly
            ></v-text-field>
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
              <v-subheader>희망 케어시터 성별</v-subheader> 
            </v-col>
            <v-col cols="8">
              <v-radio-group
             v-model="sittersex"
            mandatory
            row>
            <v-radio
              label="남성"
              value="Man">
              </v-radio>
            <v-radio
              label="여성"
              value="Woman">
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
          :to="{name:'Main'}"
        >
          Cancel
        </v-btn>
        <v-spacer></v-spacer>
        
        <v-btn
          :disabled="!formIsValid"
          text
          color="primary"
          @click="submit"
        >
          Register
        </v-btn>
        
      </v-card-actions>
  </v-card>
</template>

<script>
// import axios from 'axios';
export default {
name: 'Create',
    data () {
      return {
         title: this.title,
         caretarget:this.caretarget,
         caretargetlist:['이위화','김영한'],
         items: [],
         radioGroup: this.radioGroup,
         pickSchedule: this.pickSchedule,
         schedulelist:[1,2,3,4,5],
        dates: [],
        day: [],
        days: ['월', '화', '수', '목', '금', '토', '일'],
        sittersex: this.sittersex,
        paytimepick: this.paytimepick,
        pay: this.pay,
        comment: this.comment,
        snackbar: false,
        Schedule: false
      }
    },
    mounted(){
      // axios.get('/recruitions/new')
      // .then((result)=> {
      //   if(result=="ok"){
      //     this.caretargetlist = res.data.caretargetname;
      //   }else{
      //     console.log('실패')
      //   }
      // })
    },
    computed: {
      formIsValid () {
        return (
          this.title &&
          this.caretarget &&
          this.radioGroup &&
          this.dates &&
          this.day &&
          this.sittersex &&
          this.paytimepick &&
          this.pay
        )},
       dateRangeText(){
            return this.dates.join('~')
        },
    },
    methods: {
      openSchedule(){
        this.Schedule = true
          // axios.get('/recruitions/schedules')
          // .then((result)=>{
          //   if (result=="ok") {
          //     this.schedulelist = res.data.caretargetscheduleId;
          //   } else {
          //     console.log('실패')
          //   }
          // })
      },
      submit(){
        console.log('메소드 실행')
        var findpeople ={
          title: this.title,
          caretarget: this.caretarget,
          radioGroup: this.radioGroup,
          dates: this.dates,
          day: this.day,
          sittersex: this.sittersex,
          paytimepick: this.paytimepick,
          pay: this.pay,
          comment: this.comment
        }
        console.log(findpeople)
        // axios.post(`/api/api/recruitions/recruition/${유저아이디id}`,findpeople)
        // .then((res) => {
        // console.log(res)
        // this.snackbar = true
        // this.$router.push({
        // name:'Job_list'
        // })
      // }).catch((error)=>{
      //     console.log(error)
      //     })
      },
    },
}

</script>

<style>

</style>