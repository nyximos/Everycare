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
                    <v-select name="sido1" id="sido1"
                      v-model="hopeloc1"
                      :items="hopelocation1"
                      label="시/도"
                      item-text="name"
                      item-value="value"
                      onchange="categoryChange(this.value,area_2)" on>

                    </v-select>
                    <select name="area_2">
                      <option value="">선택</option>
                    </select>
                </li>
                  <li>
                   <span class="area_stitle">2지망</span>
                    <v-select
                      v-model="hopeloc2"
                      :items="hopelocation1"
                      label="2지망"
                      item-text="name"
                      item-value="value">
                    </v-select>
                  </li>
                    <li>
                     <span class="area_stitle">3지망</span>
                        <v-select
                          v-model="hopeloc3"
                          :items="hopelocation1"
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
                          <v-checkbox v-model="selected" value="월요일" label="월"></v-checkbox>
                          <v-checkbox v-model="selected" value="화요일" label="화"></v-checkbox>
                          <v-checkbox v-model="selected" value="수요일" label="수"></v-checkbox>
                          <v-checkbox v-model="selected" value="목요일" label="목"></v-checkbox>
                          <v-checkbox v-model="selected" value="금요일" label="금"></v-checkbox>
                          <v-checkbox v-model="selected" value="토요일" label="토"></v-checkbox>
                          <v-checkbox v-model="selected" value="일요일" label="일"></v-checkbox>
                          {{selected}}
                        </div>
                      </div>
                      <div class="area_time">
                        <h5 class="sub_title">희망시간</h5>
                        <div class="r_time_text" style="display:block;">
                          <v-time-picker v-model="starttimepicker" ampm-in-title></v-time-picker>
                         <v-time-picker v-model="endtimepicker" ampm-in-title></v-time-picker>
                        </div>
                      </div>
                      <div class="area_pay">
                        <h5 class="sub_title">급여</h5>
                        <div class="r_pay">
                        <v-radio-group v-model="radios" mandatory row>
                          <v-radio label="시급" value="hourpay">
                          </v-radio>
                          <v-radio label="월급" value="monthpay">
                          </v-radio>
                          </v-radio-group>
                          <v-text-field v-model="pay" label="희망 시/월급을 입력하세요"
                          ></v-text-field>
                          <!-- <span>{{this.$store.state.careprofileStore.name}}</span> -->
                        </div>
                      </div>
                      <v-btn class="ma-2" outlined color="indigo" @click="console">다음</v-btn>
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
      {name: '부산', value: 'busan'},
      {name: '대구', value: 'daegu'},
      {name: '충남', value: 'chungnam'},
      {name: '충북', value: 'chungbuk'},
      {name: '광주', value: 'gwangju'},
      {name: '대전', value: 'daejeon'},
      {name: '울산', value: 'ulsan'},
      {name: '강원', value: 'gangwon'},
      {name: '경기', value: 'gyeonggi'},
      {name: '경남', value: 'gyeongnam'},
      {name: '경북', value: 'gyeongbuk'},
      {name: '전남', value: 'jeonnam'},
      {name: '전북', value: 'jeonbuk'},
      {name: '제주', value: 'jeju'},
    ],
    area1 : [
      {name:'강남구', value:'강남구'},
      {name:'강남구', value:'강남구'},
      {name:'강남구', value:'강남구'},
      {name:'강남구', value:'강남구'}
    ],
    area2:[
      {name:'강북구', value:'강북구'},
      {name:'강북구', value:'강북구'},
      {name:'강북구', value:'강북구'},
      {name:'강북구', value:'강북구'},
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
      categoryChange(key,sel){
        if(key == "seoul"){
          document. = this.area1;
          
        
        }
       
      },
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
            if(this.hopeloc1 ==""){
                alert("희망근무지역을 선택해주세요!");
                this.hopeloc1.focus();
                return;
            }
            if(this.selected == ""){
                alert("희망근무요알을 선택해주세요!"); 
                return;
            }
            if(this.starttimpicker ==""){
                 alert("희망시간을 선택해주세요!");
                 return;
            }
            if(this.endtimpicker ==""){
                 alert("희망시간을 입력해주세요!");
                 return; 
            }
            if(this.pay ==""){
              alert("급여를 입력해주세요");
              return;
            }else{
            this.$store.commit('careprofileStore/set_user2', userData)
            this.$router.push({ path: '/dashboard/careprofile2' })
            }
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