<template>
   <div class="content">
      <div class="modify">
          <div class="div_modify">
              <div class="rwrite">
                  <div class="rwrite_div">
                      <div class="page_btn">
                          <span class="page_btn1 on"></span>
                          <span class="page_btn1 "></span>
                      </div>
                      <div class="con10 con1">
                          <h3 class="profile_title">케어시터 프로필 수정</h3>
                      </div>
                      <div class="con10_blank"></div>
                      <div class="area_profile">
                          <div class="div_text">
                          <br><br>
                          <h5>프로필 사진</h5>
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
                          <div class="con10_blank"></div>
                          <div class="con10 con1">
            </div>
                 <div class="area_day">
                    <h5>희망근무요일</h5>
                    <br>
                        <div class="area6_r_day">
                          <v-checkbox v-model="desiredDayWeek" value="월" label="월"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" value="화" label="화"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" value="수" label="수"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" value="목" label="목"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" value="금" label="금"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" value="토" label="토"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" value="일" label="일"></v-checkbox>
                        </div>
                      </div>
                      <div class="area_time">
                         <h5>희망시간</h5>
                         <br>
                        <div class="r_time_text" >
                          <v-select name="time" id="time"
                              v-model="activityTime"
                              :items="activity"
                              label="활동시간"
                              item-text="name"
                              item-value="value">
                          </v-select>
                        </div>
                      </div>
                      <div class="area_pay">
                        <h5>급여</h5>
                        <br>
                        <div class="r_pay">
                            <v-radio-group v-model="radios" mandatory row>
                          <v-radio label="시급" value="hourpay">
                          </v-radio>
                          <v-radio label="월급" value="monthpay">
                          </v-radio>
                          </v-radio-group>
                           <v-text-field v-model="desiredHourlyWage" label="시급제"></v-text-field>
                          <v-text-field v-model="monthlyWage" label="월급제"></v-text-field>
                        </div>
                      </div>
                      <div class="con10 con1">
                </div>
                    <div class="area_content01">
                        <h5>선호케어유형</h5>
                         <br>
                    </div>
                        <div class="r_seeking">
                          <div class="r_seeking_age">
                            <v-checkbox v-model="preferredType" value="newborn" label="신생아(0-6 month)"></v-checkbox>
                            <v-checkbox v-model="preferredType" value="baby" label="영아(7-36 month)"></v-checkbox>
                            <v-checkbox v-model="preferredType" value="child" label="유아(4-7 year)"></v-checkbox>
                            <v-checkbox v-model="preferredType" value="children" label="초등학생"></v-checkbox>
                            <v-checkbox v-model="preferredType" value="student" label="중고등학생"></v-checkbox>
                            <v-checkbox v-model="preferredType" value="anything" label="상관없음"></v-checkbox>
                          </div>
                      </div>
                      <br><br>
                      <div class="con1 con01">
                         <h5>백신</h5>
                         <br>
                          <div class="cInner">
                        <v-radio-group v-model="is_vaccinated" mandatory row>
                          <v-radio label="1차접종완료" value="0"></v-radio>
                          <v-radio label="추가접종완료" value="1"></v-radio>
                          <v-radio label="미접종" value="2"></v-radio>
                        </v-radio-group>
                        </div>
                      </div>
                      <div class="con1 con02">
                          <h5>cctv 동의여부</h5>
                          <br>
                          <div class="cInner_01">
                        <v-radio-group v-model="cctvAgreement" mandatory row>
                          <v-radio label="O" value="0"></v-radio>
                          <v-radio label="X" value="1"></v-radio>
                          </v-radio-group>
                          </div>
                      </div>
                      <div class="con1 con03">
                          <h5>게시여부</h5>
                          <br>
                          <div class="cInner_02">
                        <v-radio-group v-model="disclosureStatus" mandatory row>
                          <v-radio label="O" value="0"></v-radio>
                          <v-radio label="X" value="1"></v-radio>
                          </v-radio-group>
                          </div>
                          </div>
                          <br><br>
                          <h5>자기소개</h5>
                          <br>
                          <textarea class="content_add" placeholder="자기소개써주세요" v-model="introduction"></textarea>
                              <v-btn class="ma-2" outlined color="indigo" @click="profile_update">수정</v-btn>
                              <router-link to="/"> <v-btn class="ma-2" outlined color="indigo">취소</v-btn></router-link>
                         </div>
                      </div>
                  </div>
              </div>
          </div>
      </div>
</template>

<script>
export default {
    data(){
        return{
          activity:[
              {name:'1시간', value:'1시간'},
              {name:'2시간', value:'2시간'},
              {name:'3시간', valu:'3시간'},
              {name:'4시간', value:'4시간'},
              {name:'5시간', value:'5시간'},
              {name:'6시간', value:'6시간'},
              {name:'7시간', value:'7시간'},
              {name:'8시간', value:'8시간'},
              {name:'9시간', value:'9시간'},
              {name:'10시간', value:'10시간'},
              {name:'11시간', value:'11시간'},
              {name:'12시간', value:'12시간'},
              {name:'13시간', value:'13시간'},
              {name:'14시간', value:'14시간'},
              {name:'15시간', value:'15시간'},
              {name:'16시간', value:'16시간'},
              {name:'17시간', value:'17시간'},
              {name:'18시간', value:'18시간'},
              {name:'19시간', value:'19시간'},
              {name:'20시간', value:'20시간'},
              {name:'21시간', value:'21시간'},
              {name:'22시간', value:'22시간'},
              {name:'23시간', value:'23시간'},
              {name:'24시간', value:'24시간'},
            ],
            introduction:'',
            radios:'',
            desiredHourlyWage:0,
            monthlyWage:0,
            activityTime:'',
            desiredDayWeek: [],
            preferredType: [],
            cctvAgreement:'',
            is_vaccinated: '',
            attachFiles: [],
            disclosureStatus:'',
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
         console.log(res.data.body);
         // console.log(res.data.body)
         this.introduction = res.data.body.introduction
         this.cctvAgreement = res.data.body.cctvAgreement
         this.desiredDayWeek = res.data.body.desiredDayWeek
         this.desiredHourlyWage = res.data.body.hourlyWage
         this.monthlyWage = res.data.body.monthlyWage
         this.preferredType = res.data.body.preferredType
         this.activityTime = res.data.body.activityTime
         this.attachFiles = res.data.body.attachFiles
         this.hopefulRegion = res.data.body.hopefulRegion
         this.is_vaccinated = res.data.body.is_vaccinated
      this.disclosureStatus = res.data.body.disclosureStatus
      }).catch(err=>{
         console.log(err);
      })
    },
    methods:{
        profile_update(){  
        var formData= new FormData();
            formData.append('preferredType', this.preferredType);
            formData.append('vaccination', this.is_vaccinated);
            formData.append('cctvAgreement', this.cctvAgreement);
            formData.append('introduction', this.introduction);
            formData.append('desiredDayWeek', this.desiredDayWeek);
            // formData.append('hopefulRegion', this.hope_loc1+this.hopeloc1_detail);
            formData.append('desiredHourlyWage', this.desiredHourlyWage);
            formData.append('desiredMonthlyWage', this.monthlyWage);
            formData.append('disclosureStatus',this.disclosureStatus);
            formData.append('activityTime', this.activityTime);
            formData.append('disclosureStatus',this.disclosureStatus);
            // formData.append('activityTime', this.starttimepicker + this.endtimepicker);
           for(let i = 0; i< this.attachFiles.length; i++){
             formData.append('attachFiles', this.attachFiles[0]);
           } 
           console.log(this.attachFiles);
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
    div{
    display: block;
}
    .content{
        width: 100%;
        min-height: 800px;
        overflow: hidden;
    }
    .content .modify{
        width: 100%;
        max-width: 670px;
        margin: 50px auto 150px auto;
        position: relative;
        text-align: left;
    }
    .content .modify .div_modify{
        width: 100%;
        position: relative;
        box-sizing: border-box;
        text-align: center;
        background-color: #ffffff;
        border: 0;
        box-shadow: 0 0 18px #d3d3d3;
        border-radius: 10px;
        box-sizing: border-box;
        padding: 40px 20px 40px 20px;
    }
    .content .modify .div_modify .rwrite .rwrite_div{
        widows: 100%;
        position: relative;
        padding: 0 0 10px 0;
        overflow: hidden;
    }
    .content .modify .div_modify .rwrite .page_btn{
        width: 100%;
        position: relative;
        height: 25px;
        text-align: center;
    }
    .content .modify .div_modify .div_modify .rwrite .page_btn span{
        display: inline-block;
        border-radius: 17px;
        box-sizing: border-box;
        width: 17px;
        height: 17px;
        background-color: #cccccc;
        font-size: 0;
        line-height: 0;
        margin: 0 4px 0 4px;
    }
    .content .modify .div_modify .div_modify .rwrite .page_btn span on{
        background-color: #2c786c;
    }
    .content .modify .div_modify .rwrite .rwrite_div .con10{
        width: 100%;
        position: relative;
        overflow: hidden;
        padding: 30px 0 20px 0;
        text-align: left;
    }
    .profile_title{
        text-size:1.3em;
        font-weight: 400;
        text-align: center;
    }
    .content .con10_blank{
        width: 100%;
        position: relative;
        overflow: hidden;
        height: 5px;
    }
    .area_profile{
        width: 100%;
        position: relative;
        overflow: hidden;
        padding: 0 15px 0 15px;
        box-sizing: border-box;
        text-align: center;
    }
    .area_profile .div_img{
        position: relative;
        width: 160px;
        text-align: center;
        margin: 0 auto;
        height: 160px;
        overflow: hidden;
        border-radius: 160px;
        box-sizing: border-box;
        border: 1px solid #e3e3e3;
    }
    .area_profile .div_img img{
        width: 100%;
    }
    .content01{
    width: 100%;
    position: relative;
    box-sizing: border-box;
    overflow: hidden;
    padding: 0 15px 0 15px;
    display: none;
  }
  .work_area{
    width: 100%;
    margin: 0 auto;
    display: none;
    text-align: left;
  }
  .area_stitle{
    display: inline-block;
    width: 52px;
    font-weight: 500;
    box-sizing: border-box;
    padding: 9px 0 9px 9px 0;
    margin: 0 3px 0 0;
    vertical-align: middle;
    text-align: center;
    background-color: aquamarine;
    color: white;
    border-radius: 5px;
  }
  .select_css{
    border-radius: 5px;
    font-size: 1em;
    padding: 5px;
    width: 20%;
    height: 34px;
    margin: 0 3px 0 0;
    
    color: black;
  }
  .area_day{
    display: block;
    width: 100%;
    padding: 20px 0 10px 0;
  }
  .sub_title{
    display: block;
    width: 100%;
    padding: 10px 0 10px 0;
    border-bottom:1px solid #dedede ;
    font-size: 1.1em;
    text-align: center;
    font-weight: 400;
  }
  .area6_r_day{
    width: 100%;
    display: block;
    padding: 10px 0 0 0;
    text-align: center;
  }
  .area_day .r_day1{
    position: relative;
    background-color: #fefefe;
    border: 1px solid #dedede;
    color: #797979;
    border-radius: 50px;
    width: 20%;
    height: 40px;
    margin: 0 2px 15px 2px;
    font-size: 0.9em;
    letter-spacing: -0.05em;
  }
  .area_pay{
    display: block;
    width: 100%;
    padding: 30px 0 10px 0;
  }
  .r_pay{
    width: 100%;
    display: block;
    padding: 10px 0 0 0;
    text-align: center;
  }
  .area_pay .r_pay1{
    display: inline-block;
    margin: 0 10px 10px 0;
  }
  .area_pay .pay_plan.on{
    background-color: aqua;
    border: 1 px solid aqua;
    color: black;
  }
  .area_pay .select_css04{
    border-radius: 5px;
    font-size: 1em;
    padding: 5px;
    width: 120px;
    height: 40px;
    vertical-align: middle;
  }
  .area_pay .text_css01{
    height: 40px;
    border-radius:5px;
    box-sizing: border-box;
    border: 1px solid #666666;
    width: 30%;
    margin: 0 auto;
  }
  .content_add{
        width: 100%;
        height: 100px;
        border: 1px solid #cccccc;
        border-radius: 5px;
        overflow: hidden;
    }
</style>