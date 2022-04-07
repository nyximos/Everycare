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
              <h3 class="profile_title">Hope</h3>
            </div>
            <div class="area_content01">
              <h5 class="sub_title">희망근무지역</h5>        
            </div>
             <div>
              <ul class="work_area" style="display:block;">
                <li>
                  <span class="area_stitle">1지망</span>
                    <v-select
                      v-model="hopeloc1"
                      :items="hopelocation1"
                      label="1지망"
                      item-text="name"
                      item-value="value">
                    </v-select>
                </li>
                  <li>
                   <span class="area_stitle">2지망</span>
                    <v-select
                      v-model="hopeloc2"
                      :items="hopelocation2"
                      label="2지망"
                      item-text="name"
                      item-value="value">
                    </v-select>
                  </li>
                    <li>
                     <span class="area_stitle">3지망</span>
                        <v-select
                          v-model="hopeloc3"
                          :items="hopelocation3"
                          label="3지망"
                          item-text="name"
                          item-value="value">
                        </v-select>
                    </li>
                  </ul>
                </div>
                  <div class="area_day">
                    <h5 class="sub_title">희망근무요일</h5>
                      <div class="area6_r_day">
                          <v-checkbox
                          v-model="selected"
                          value="Mon"
                          label="월"
                          ></v-checkbox
                          ><v-checkbox
                           v-model="selected"
                           value="Tue"
                          label="화">
                          </v-checkbox>
                          <v-checkbox
                           v-model="selected"
                           value="wed"
                          label="수"
                          ></v-checkbox>
                          <v-checkbox
                           v-model="selected"
                           value="thur"
                          label="목"
                          ></v-checkbox>
                          <v-checkbox
                           v-model="selected"
                           value="fri"
                          label="금"
                          ></v-checkbox>
                          <v-checkbox
                           v-model="selected"
                           value="sat"
                          label="토"
                          ></v-checkbox>
                          <v-checkbox
                           v-model="selected"
                           value="sun"
                          label="일"
                          ></v-checkbox>
                          {{selected}}
                        </div>
                      </div>
                      <div class="area_time">
                        <h5 class="sub_title">희망시간</h5>
                        <div class="r_time_text" style="display:block;">
                          <v-time-picker
                          v-model="starttimepicker"
                          ampm-in-title>
                          </v-time-picker>
                          {{starttimepicker}}
                         <v-time-picker
                         v-model="endtimepicker"
                         ampm-in-title
                         ></v-time-picker>
                         {{endtimepicker}}
                        </div>
                      </div>
                      <div class="area_pay">
                        <h5 class="sub_title">급여</h5>
                        <div class="r_pay">
                        <v-radio-group
                        v-model="radios"
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
                          <v-text-field
                          v-model="pay"
                          label="희망 시/월급을 입력하세요"
                          ></v-text-field>
                         
                        </div>
                      </div>
                      <router-link to="/Careprofile2">
                      <v-btn class="ma-2" outlined color="indigo" @click="console">다음</v-btn>
                      </router-link>
                      <router-link to="/Careprofile"><v-btn class="ma-2" outlined color="indigo">취소</v-btn></router-link>
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
    hopelocation1:[
      {name: '서울', value: 'seoul'},
      {name: '인천', value: 'incheon'},
    ],
    hopelocation2:[
      {name: '대구', value: 'daegu'},
      {name: '포항', value: 'pohang'},
    ],
    hopelocation3:[
      {name: '울산', value: 'ulsan'},
      {name: '제주', value: 'jeju'},
    ],
    hopeloc1:'',
    hopeloc2:'',
    hopeloc3:'',
    selected: [],
    starttimepicker:'',
    endtimepicker:'',
    radios:'',
    pay:''
    }

    },
    methods:{
      console(){
        const userData = {
          hopeloc1:this.hopeloc1,
          hopeloc2:this.hopeloc2,
          hopeloc3:this.hopeloc3,
          hopeday:this.selected,
          starttimepicker:this.starttimepicker,
          endtimepicker:this.endtimepicker,
          paytype : this.radios,
          pay: this.pay
        };
        // console.log(userData);
        try {
            this.$store.commit('careprofileStore/set_user2', userData);
            console.log(this.$store.state.careprofileStore.image)
        } catch (error) {
          console.log(error)
        }
      }
    }
  }
</script>

<style>
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
</style>