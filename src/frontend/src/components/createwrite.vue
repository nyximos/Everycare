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
      label="케어대상인 선택"
      multiple>
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
          <v-select
          v-model="pickSchedule"
          :items="schedulelist"
          label="스케줄선택"
          chips
        ></v-select>
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
          :to="{name:'Main'}">
          Cancel
        </v-btn>
        <v-spacer></v-spacer>
        
        <v-btn
          :disabled="!formIsValid"
          text
          color="primary"
          @click="submit">
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
         pickSchedule: this.pickSchedule,
         schedulelist:[1,2,3,4,5],
        dates: [],
        day: [],
        days: ['월', '화', '수', '목', '금', '토', '일'],
        sitterSex: this.sitterSex,
        paytimepick: this.paytimepick,
        pay: this.pay,
        comment: this.comment,
      }
    },
    methods: {
      submit(){
      var formData = new FormData();
      formData.append('title',this.title);
      formData.append('caretarget', this.caretarget.toString());
      formData.append('pickSchedule', this.pickSchedule);
      formData.append('dates', this.dates.toString());
      formData.append('day', this.day.toString());
      formData.append('sitterSex', this.sitterSex);
      formData.append('paytimepick', this.paytimepick);
      formData.append('pay', this.pay);
      formData.append('comment',this.comment);
      this.$http
      .post('/api/recruitions/new', formData,{
       withCredentials: true
      })
     .then(res => {
      console.log(res);
      })
     .catch(err => {
       console.log(err);
    });
  },
},
  computed: {
    formIsValid () {
      return (
        this.title &&
        this.caretarget &&
        this.pickSchedule &&
        this.dates &&
        this.day &&
        this.sitterSex &&
        this.paytimepick &&
        this.pay
      )},
    dateRangeText(){
      return this.dates.join('~')
    },
  },
}

</script>

<style>

</style>