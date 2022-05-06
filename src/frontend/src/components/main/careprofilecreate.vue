<template>
  <div>
      <h2>사진</h2>
        <v-file-input  type="file" v-model="image" truncate-length="22" label="사진을 넣으세요" ></v-file-input>                
       <h5>자기소개</h5>
        <textarea type="text" class="content_add" placeholder="자기소개써주세요" v-model="introduction"></textarea>
        <h2>희망근무지역</h2> 
        <span class="area_stitle">1지망</span>
                <v-select name="sido1" id="sido1"
                    v-model="hopeloc1"
                    :items="hopelocation1"
                    label="시/도"
                    type="text"
                    item-text="name"
                    item-value="value"
                    onchange="categoryChange(this.value,area_2)" on>
                </v-select>
                <h5 class="sub_title">희망근무요일</h5>
                      <div class="area6_r_day">
                          <v-checkbox v-model="desiredDayWeek" type="text" value="월요일" label="월"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" type="text" value="화요일" label="화"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" type="text" value="수요일" label="수"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" type="text" value="목요일" label="목"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" type="text" value="금요일" label="금"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" type="text" value="토요일" label="토"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" type="text" value="일요일" label="일"></v-checkbox>
                          {{desiredDayWeek}}
                        </div>
                        <h5 class="sub_title">희망시간</h5>
                        <div class="r_time_text" style="display:block;">
                          <v-time-picker type="text" v-model="activityTime" ampm-in-title></v-time-picker>
                         <v-time-picker type="text" v-model="endtimepicker" ampm-in-title></v-time-picker>
                        </div>
                         <h5 class="sub_title">급여</h5>
                        <v-radio-group v-model="radios" mandatory row>
                          <v-radio label="시급" value="hourpay">
                          </v-radio>
                          <v-radio label="월급" value="monthpay">
                          </v-radio>
                          </v-radio-group>
                          <v-text-field type="text" v-model="desiredHourlyWage" label="시급제"></v-text-field>
                          <v-text-field type="text" v-model="desiredMonthlyWage" label="월급제"></v-text-field>
                            <div class="r_seeking_age">
                          <v-checkbox type="text" v-model="preferredType" value="newborn" label="신생아(0-6 month)"></v-checkbox>
                          <v-checkbox type="text" v-model="preferredType" value="baby" label="영아(7-36 month)"></v-checkbox>
                          <v-checkbox type="text" v-model="preferredType" value="child" label="유아(4-7 year)"></v-checkbox>
                          <v-checkbox type="text" v-model="preferredType" value="children" label="초등학생"></v-checkbox>
                          <v-checkbox type="text" v-model="preferredType" value="student" label="중고등학생"></v-checkbox>
                          <v-checkbox type="text" v-model="preferredType" value="anything" label="상관없음"></v-checkbox>
                        </div>
                          <div class="con1 con01">
                          <h5 class="sub_title">백신</h5>
                          <div class="cInner">
                        <v-radio-group v-model="vaccination" mandatory row>
                          <v-radio label="1차접종완료" value="0" type="int">
                          </v-radio>
                          <v-radio label="추가접종완료" value="1" type="int">
                          </v-radio>
                          <v-radio
                          label="미접종"
                          type="int"
                          value="2">
                          </v-radio>
                          </v-radio-group>
                          </div>
                      </div>
                      <div class="con1 con02">
                          <h5 class="sub_title">cctv 동의여부</h5>
                          <div class="cInner_01">
                        <v-radio-group
                        v-model="cctvAgreement"
                        mandatory
                        row>
                          <v-radio
                          label="⭕"
                          value="0"
                          type="int">
                          </v-radio>
                          <v-radio
                          label="❌"
                          value="1"
                          type="int">
                          </v-radio>
                          </v-radio-group>
                          </div>
                      </div>
                      <div class="con1 con03">
                          <h5 class="sub_title">자격증</h5>
                          <div class="">
                              <br>
                              <v-file-input 
                              v-model="attachFiles" 
                              label="File input" 
                              type="file"
                              id="attachFiles"
                              multiple="multiple"
                              outlined dense>
                              </v-file-input>
                          </div>
                      </div>
               <v-btn class="ma-2" outlined color="indigo" @click="submit">등록</v-btn>
  </div>
</template>

<script>
export default {
    name:'careprofilecreate',
    data(){
        return{
          hopelocation1:[
      {name: '서울', value: '서울'},
      {name: '인천', value: '인천'},
      {name: '부산', value: '부산'},
      {name: '대구', value: '대구'},
      {name: '충남', value: '충남'},
      {name: '충북', value: '충북'},
      {name: '광주', value: '광주'},
      {name: '대전', value: '대전'},
      {name: '울산', value: '울산'},
      {name: '강원', value: '강원'},
      {name: '경기', value: '경기'},
      {name: '경남', value: '경남'},
      {name: '경북', value: '경북'},
      {name: '전남', value: '전남'},
      {name: '전북', value: '전북'},
      {name: '제주', value: '제주'},
    ],
      image:[],
      radios:'',
      introduction:'',
      desiredHourlyWage:0,
      desiredMonthlyWage:0,
      hopeloc1:'',
      activityTime:'',
      desiredDayWeek: [],
      starttimepicker:'',
      endtimepicker:'',
      preferredType: [],
      cctvAgreement:'',
      vaccination: '',
      attachFiles: [],
        }
    },
    methods:{
        submit(){
            var formData= new FormData();

            formData.append('preferredType', this.preferredType);
            formData.append('vaccination', this.vaccination);
            formData.append('cctvAgreement', this.cctvAgreement);
            formData.append('introduction', this.introduction);
            formData.append('desiredDayWeek', this.desiredDayWeek);
            formData.append('hopefulRegion', this.hopeloc1);
            formData.append('desiredHourlyWage', this.desiredHourlyWage);
            formData.append('desiredMonthlyWage', this.desiredMonthlyWage);
            formData.append('activityTime', this.activityTime);
            // formData.append('activityTime', this.starttimepicker + this.endtimepicker);
           for(let i = 0; i< this.attachFiles.length; i++){
             formData.append('attachFiles', this.attachFiles[0]);
           }            // if('#attachFiles'[0].files[0] == undefined){
            //   alert("이미지를 첨부해주세요");
            //   return false;
            // }else{
            //   const size = document.getElementById('attachFiles').files.length;
            //   for(let i = 0; i< size; i++){
            //     formData.append('attachFiles', 'attachFiles'[0].files[i]);
            //   }
            // }
            // formData.append('hopefulRegion', this.hopeloc1); 
            // formData.append('hopefulRegion', this.hopeloc1);
            // preferredType:this.preferredType,
              // vaccination:this.vaccination,
              // cctvAgreement:this.cctvAgreement,
              // certification:this.files.name, 
              // uploadFileName : this.image.name,
              // introduction:this.introduction,
              // desiredDayWeek:this.desiredDayWeek,
              // activityTime:this.starttimepicker + this.endtimepicker,
              // hopefulRegion:this.hopeloc1,
              // desiredHourlyWage: this.desiredHourlyWage,
              // desiredMonthlyWage: this.desiredMonthlyWage,    
            this.$http
            .post('/api/dashboard/caresitter',formData,{
                withCredentials:true
            })
            .then(res=>{
                console.log(res);
            }).catch(err=>{
                console.log(err)
            })
        }
    }
}
</script>

<style>

</style>