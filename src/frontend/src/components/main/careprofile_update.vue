<template>
  <div>
      <ul>
            <li><h3>> 선호 케어 유형:</h3>   
                <v-checkbox v-model="preferredType" value="newborn" label="신생아(0-6 month)"></v-checkbox>
                <v-checkbox v-model="preferredType" value="baby" label="영아(7-36 month)"></v-checkbox>
                <v-checkbox v-model="preferredType" value="child" label="유아(4-7 year)"></v-checkbox>
                <v-checkbox v-model="preferredType" value="children" label="초등학생"></v-checkbox>
                <v-checkbox v-model="preferredType" value="student" label="중고등학생"></v-checkbox>
                <v-checkbox v-model="preferredType" value="anything" label="상관없음"></v-checkbox>
            </li>
            <li><h3>> 희망 활동 요일:</h3>
                <!-- <span>월</span><input type="checkbox" v-model="desiredDayWeek" value="월요일">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>화</span><input type="checkbox" v-model="desiredDayWeek" value="화요일">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>수</span><input type="checkbox" v-model="desiredDayWeek" value="수요일">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>목</span><input type="checkbox" v-model="desiredDayWeek" value="목요일">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>금</span><input type="checkbox" v-model="desiredDayWeek" value="금요일">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>토</span><input type="checkbox" v-model="desiredDayWeek" value="토요일">&nbsp;&nbsp;&nbsp;&nbsp;
                <span>일</span><input type="checkbox" v-model="desiredDayWeek" value="일요일"> -->
                    <v-checkbox v-model="desiredDayWeek" value="월요일" label="월"></v-checkbox>
                    <v-checkbox v-model="desiredDayWeek" value="화요일" label="화"></v-checkbox>
                    <v-checkbox v-model="desiredDayWeek" value="수요일" label="수"></v-checkbox>
                    <v-checkbox v-model="desiredDayWeek" value="목요일" label="목"></v-checkbox>
                    <v-checkbox v-model="desiredDayWeek" value="금요일" label="금"></v-checkbox>
                    <v-checkbox v-model="desiredDayWeek" value="토요일" label="토"></v-checkbox>
                    <v-checkbox v-model="desiredDayWeek" value="일요일" label="일"></v-checkbox>
            </li>
            <li><h3>> 희망 활동 시간:</h3> 
                <div class="" style="display:block;">
                    <v-row
      justify="space-around"
      align="center"
    >
      <v-col style="width: 350px; flex: 0 1 auto;">
        <h2>Start:</h2>
        <v-time-picker
          v-model="starttimepicker"
          :max="end"
        ></v-time-picker>
      </v-col>
      <v-col style="width: 350px; flex: 0 1 auto;">
        <h2>End:</h2>
        <v-time-picker
          v-model="endtimepicker"
          :min="start"
        ></v-time-picker>
      </v-col>
    </v-row>
                </div>
            </li>
            <li><h3>>  희망 시급:</h3> <v-text-field v-model.number="desiredHourlyWage" label="hourpay" required></v-text-field>
            <li><h3>>  희망 월급:</h3><v-text-field v-model.number="desiredMonthlyWage" label="monthpay" required></v-text-field>
            <li><h3>>  CCTV 동의여부:</h3> 
                <v-radio-group v-model="cctvAgreement" mandatory row>
                            <v-radio label="O" value="0">O</v-radio>
                            <v-radio label="X" value="1">X</v-radio>
                  </v-radio-group>
            <li><h3>>  백신접종 유무:</h3> 
                  <v-radio-group v-model="vaccination" mandatory row>
                            <v-radio label="1차접종완료" value="0"></v-radio>
                            <v-radio label="추가접종완료" value="1"></v-radio>
                            <v-radio label="미접종" value="2"></v-radio>
                  </v-radio-group>
            </li>
            <li><h3>>  소개글:</h3>  <textarea class="content_add" placeholder="자기소개써주세요" v-model="introduction"></textarea></li>
            <li>공개 여부:
                <v-radio-group v-model="disclosureStatus" mandatory row>
                            <v-radio label="O" value="0">O</v-radio>
                            <v-radio label="X" value="1">X</v-radio>
                  </v-radio-group> 
                <li>이미지 파일들
                <v-file-input 
                              v-model="attachFiles" 
                              label="File input" 
                              type="file"
                              id="attachFiles"
                              multiple="multiple"
                              outlined dense>
                              </v-file-input>    
                <!-- <input type="file" multiple="multiple" name="attachFiles" id="attachFiles"/> -->
                </li>
        </ul>
        <v-btn class="ma-2" outlined color="indigo" @click="profile_update">수정</v-btn>
        <v-btn class="ma-2" outlined color="indigo">취소</v-btn>
  </div>
</template>

<script>
export default {
    data(){
        return{
            introduction:'',
            desiredHourlyWage:0,
            desiredMonthlyWage:0,
            activityTime:'',
            desiredDayWeek: [],
            starttimepicker:'',
            endtimepicker:'',
            preferredType: [],
            cctvAgreement:'',
            vaccination: '',
            attachFiles: [],
            id:this.$route.params.caresitterId
        }
        
    },
    mounted(){
		const id =this.$route.params.caresitterId;
		this.$http
		.get(`/api/caresitters/${id}`,{
			withCredentials:true
		})
		.then((res)=>{
			// console.log(res.data.body);
			// console.log(res.data.body)
			this.introduction = res.data.body.introduction
			this.cctvAgreement = res.data.body.cctvAgreement
			this.desiredDayWeek = res.data.body.desiredDayWeek
			this.desiredHourlyWage = res.data.body.desiredHourlyWage
			this.desiredMonthlyWage = res.data.body.desiredMonthlyWage
			this.preferredType = res.data.body.preferredType
			this.activityTime = res.data.body.activityTime
			this.attachFiles = res.data.body.attachFiles
			this.hopefulRegion = res.data.body.hopefulRegion
			this.vaccination = res.data.body.vaccination
		}).catch(err=>{
			console.log(err);
		})
    },
    methods:{
        profile_update(){  
        var formData= new FormData();
            formData.append('preferredType', this.preferredType);
            formData.append('vaccination', this.vaccination);
            formData.append('cctvAgreement', this.cctvAgreement);
            formData.append('introduction', this.introduction);
            formData.append('desiredDayWeek', this.desiredDayWeek);
            formData.append('hopefulRegion', this.hope_loc1+this.hopeloc1_detail);
            formData.append('desiredHourlyWage', this.desiredHourlyWage);
            formData.append('desiredMonthlyWage', this.desiredMonthlyWage);
            formData.append('disclosureStatus',this.disclosureStatus);
            formData.append('activityTime', this.starttimepicker+this.endtimepicker);
            // formData.append('activityTime', this.starttimepicker + this.endtimepicker);
           for(let i = 0; i< this.attachFiles.length; i++){
             formData.append('attachFiles', this.attachFiles[0]);
           } 
           this.$http
            .patch(`/api/dashboard/caresitter/${this.id}`, formData,{
                withCredentials:true
            })
            .then(res=>{
                console.log(res);
            }).catch(err=> {
                console.log(err);
            })
        }
    }
}
</script>

<style>
    .content_add{
        width: 100%;
        height: 100px;
        border: 1px solid #cccccc;
        border-radius: 5px;
        overflow: hidden;
    }
    ul{
        list-style: none;
    }
</style>