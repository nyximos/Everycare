<template>
  <div>
      <ul>
            <li>선호 케어 유형:
                <span>신생아</span><input type="checkbox" v-model="preferredType" value="신생아">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>영아</span><input type="checkbox" v-model="preferredType" value="영아">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>유아</span><input type="checkbox" v-model="preferredType" value="유아">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>초등학생</span><input type="checkbox" v-model="preferredType" value="초등학생">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>중고등학생</span><input type="checkbox" v-model="preferredType" value="중고등학생">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>상관없음</span><input type="checkbox" v-model="preferredType" value="상관없음">
            <li>희망 활동 요일:
                <span>월</span><input type="checkbox" v-model="desiredDayWeek" value="월요일">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>화</span><input type="checkbox" v-model="desiredDayWeek" value="화요일">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>수</span><input type="checkbox" v-model="desiredDayWeek" value="수요일">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>목</span><input type="checkbox" v-model="desiredDayWeek" value="목요일">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>금</span><input type="checkbox" v-model="desiredDayWeek" value="금요일">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>토</span><input type="checkbox" v-model="desiredDayWeek" value="토요일">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>일</span><input type="checkbox" v-model="desiredDayWeek" value="일요일">
                <!-- <v-checkbox v-model="selected" value="월요일" label="월"></v-checkbox>
                <v-checkbox v-model="selected" value="화요일" label="화"></v-checkbox>
                <v-checkbox v-model="selected" value="수요일" label="수"></v-checkbox>
                <v-checkbox v-model="selected" value="목요일" label="목"></v-checkbox>
                <v-checkbox v-model="selected" value="금요일" label="금"></v-checkbox>
                <v-checkbox v-model="selected" value="토요일" label="토"></v-checkbox>
                <v-checkbox v-model="selected" value="일요일" label="일"></v-checkbox> -->
            </li>
            <li>희망 활동 시간: 
                <div class="" style="display:block;">
                          <v-time-picker v-model="starttimepicker" ampm-in-title></v-time-picker>
                         <v-time-picker v-model="endtimepicker" ampm-in-title></v-time-picker>
                </div>
            </li>
            <li>희망 시급: <v-text-field v-model.number="desiredHourlyWage" label="hourpay" required></v-text-field>
            <li>희망 월급:<v-text-field v-model.number="desiredMonthlyWage" label="monthpay" required></v-text-field>
            <li>CCTV 동의여부: 
                <v-radio-group v-model="cctvAgreement" mandatory row>
                            <v-radio label="O" value="0">O</v-radio>
                            <v-radio label="X" value="1">X</v-radio>
                  </v-radio-group>
                 <input type="number" name="cctvAgreement" id="cctvAgreement" th:value="${careSitter.cctvAgreement}"/></li>
            <li>백신접종 유무: 
                  <v-radio-group v-model="vaccination" mandatory row>
                            <v-radio label="1차접종완료" value="0"></v-radio>
                            <v-radio label="추가접종완료" value="1"></v-radio>
                            <v-radio label="미접종" value="2"></v-radio>
                  </v-radio-group>
            </li>
            <li>소개글:  <textarea class="content_add" placeholder="자기소개써주세요" v-model="introduction"></textarea></li>
            <li>공개 여부:
                <v-radio-group v-model="disclosureStatus" mandatory row>
                            <v-radio label="O" value="0">O</v-radio>
                            <v-radio label="X" value="1">X</v-radio>
                  </v-radio-group> 
                <li>이미지 파일들<input type="file" multiple="multiple" name="attachFiles" id="attachFiles"/></li>
        </ul>
        <v-btn class="ma-2" outlined color="indigo" @click="profile_update">수정</v-btn>
        <v-btn class="ma-2" outlined color="indigo">취소</v-btn>
  </div>
</template>

<script>
export default {
    data(){
        return{
            preferredType:'',
            cctvAgreement:'',
            vaccination:'',
            desiredDayWeek:[],
            activityTiee:'',
            desireHourlyWage:'',
            desireMonthlyWage:'',
            introduction:'',
        }
        
    },
    methods:{
        profile_update(){
            const updateData ={
                preferredType:this.preferredType,
                cctvAgreement:this.cctvAgreement,
                vaccination:this.vaccination,
                desiredDayWeek:this.desiredDayWeek,
                activityTime:this.starttimepicker + this.endtimepicker,
                desireHourlyWage:this.desireHourlyWage,
                desireMonthlyWage:this.desireMonthlyWage,
                introduction:this.introduction
            }
            this.$store.commit('careprofileStore',updateData)
        }
    }
}
</script>

<style>

</style>