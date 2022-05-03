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
                      @change="categoryChange($event)" on>
                    </v-select>
                    <v-select name="#" id="#"
                      v-model="hopeloc1_detail"
                      :items="area"
                      label="시/군/구"
                      item-text="name"
                      item-value="value">
                    </v-select>
                </li>
                  <li>
                   <span class="area_stitle">2지망</span>
                    <v-select
                      v-model="hopeloc2"
                      :items="area_2"
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
                          <v-checkbox v-model="desiredDayWeek" value="월요일" label="월"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" value="화요일" label="화"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" value="수요일" label="수"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" value="목요일" label="목"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" value="금요일" label="금"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" value="토요일" label="토"></v-checkbox>
                          <v-checkbox v-model="desiredDayWeek" value="일요일" label="일"></v-checkbox>
                          {{desiredDayWeek}}
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
                          <v-text-field v-model="desiredHourlyWage" label="시급제"></v-text-field>
                          <v-text-field v-model="desiredMonthlyWage" label="월급제"></v-text-field>
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
      {name: '서울', value: '서울'},
      {name: '인천', value: '인천'},
      {name: '경기', value: '경기'},
      {name: '부산', value: '부산'},
      {name: '대구', value: '대구'},
      {name: '대전', value: '대전'},
      {name: '세종', value: '세종'},
      {name: '광주', value: '광주'},
      {name: '울산', value: '울산'},
      {name: '강원', value: '강원'},
      {name: '경남', value: '경남'},
      {name: '경북', value: '경북'},
      {name: '전남', value: '전남'},
      {name: '전북', value: '전북'},
      {name:'충남', value:'충남'},
      {name:'충북', value:'충북'},
      {name: '제주', value: '제주'},
    ],
    area1 : [
      {name:'강남구', value:'강남구'},
      {name:'강동구', value:'강동구'},
      {name:'강북구', value:'강북구'},
      {name:'강서구', value:'강서구'},
      {name:'관악구', value:'관악구'},
      {name:'광진구', value:'광진구'},
      {name:'구로구', value:'구로구'},
      {name:'금천구', value:'금천구'},
      {name:'노원구', value:'노원구'},
      {name:'도봉구', value:'도봉구'},
      {name:'동대문구', value:'동대문구'},
      {name:'동작구', value:'동작구'},
      {name:'마포구', value:'마포구'},
      {name:'서대문구', value:'서대문구'},
      {name:'서초구', value:'서초구'},
      {name:'성동구', value:'성동구'},
      {name:'송파구', value:'송파구'},
      {name:'양천구', value:'양천구'},
      {name:'영등포구', value:'영등포구'},
      {name:'용산구', value:'용산구'},
      {name:'은평구', value:'은평구'},
      {name:'종로구', value:'종로구'},
      {name:'중구', value:'중구'},
      {name:'중랑구', value:'중랑구'},
    ],
    area2:[
      {name:'중구', value:'중구'},
      {name:'계양구', value:'계양구'},
      {name:'서구', value:'서구'},
      {name:'남동구', value:'남동구'},
      {name:'옹진구', value:'옹진구'},
      {name:'동구', value:'동구'},
      {name:'연수구', value:'연수구'},
      {name:'부평구', value:'부평구'},
      {name:'강화구', value:'강화구'},
      {name:'미추홀구', value:'미추홀구'}
    ],
    area3:[
      {name:'가평군', value:'가평군'},
      {name:'연천군', value:'연천군'},
      {name:'동두천시', value:'동두천시'},
      {name:'안산시', value:'안산시'},
      {name:'시흥시', value:'시흥시'},
      {name:'광주시', value:'광주시'},
      {name:'오산시', value:'오산시'},
      {name:'양주시', value:'양주시'},
      {name:'포천시', value:'포천시'},
      {name:'용인시', value:'용인시'},
      {name:'파주시', value:'파주시'},
      {name:'구리시', value:'구리시'},
      {name:'이천시', value:'이천시'},
      {name:'의정부시', value:'의정부시'},
      {name:'안양시', value:'안양시'},
      {name:'수원시', value:'수원시'},
      {name:'성남시', value:'성남시'},
      {name:'양평시', value:'양평시'},
      {name:'하남시', value:'하남시'},
      {name:'부천시', value:'부천시'},
      {name:'의왕시', value:'의왕시'},
      {name:'평택시', value:'평택시'},
      {name:'군포시', value:'군포시'},
      {name:'화성시', value:'화성시'},
      {name:'남양주시', value:'남양주시'},
      {name:'광명시', value:'광명시'},
      {name:'고양시', value:'고양시'},
      {name:'여주시', value:'여주시'},
      {name:'안성시', value:'안성시'},
      {name:'과천시', value:'과천시'},
      {name:'김포시', value:'김포시'},
    ],
    area4:[
      {name:'수영구', value:'수영구'},
      {name:'해운대구', value:'해운대구'},
      {name:'중구', value:'중구'},
      {name:'동구', value:'동구'},
      {name:'남구', value:'남구'},
      {name:'연제구', value:'연제구'},
      {name:'금정구', value:'금정구'},
      {name:'부산진구', value:'부산진구'},
      {name:'북구', value:'북구'},
      {name:'사하구', value:'사하구'},
      {name:'동래구', value:'동래구'},
      {name:'강서구', value:'강서구'},
      {name:'서구', value:'서구'},
      {name:'사상구', value:'사상구'},
      {name:'영도구', value:'영도구'},
      {name:'기장군', value:'기장군'},
    ],
    area5:[
      {name:'동구', value:'수영구'},
      {name:'남구', value:'수영구'},
      {name:'수성구', value:'수성구'},
      {name:'중구', value:'중구'},
      {name:'서구', value:'서구'},
      {name:'북구', value:'북구'},
      {name:'달성군', value:'달성군'},
      {name:'달서구', value:'달서구'},
    ],
    area6:[
      {name:'대덕구', value:'대덕구'},
      {name:'동구', value:'동구'},
      {name:'서구', value:'서구'},
      {name:'유성구', value:'유성구'},
      {name:'중구', value:'중구'},
    ],
    area7:[
      {name:'가람동', value:'가람동'},
      {name:'갈산리', value:'갈산리'},
    ],
    area8:[
      {name:'광산구', value:'광산구'},
      {name:'남구', value:'남구'},
      {name:'동구', value:'동구'},
      {name:'북구', value:'북구'},
      {name:'서구', value:'서구'},
    ],
    area9:[
      {name:'남구', value:'남구'},
      {name:'동구', value:'동구'},
      {name:'북구', value:'북구'},
      {name:'울주구', value:'울주군'},
      {name:'중구', value:'중구'},
    ],
    area10:[
      {name:'강릉시', value:'수영구'},
      {name:'고성군', value:'고성군'},
      {name:'동해시', value:'동해시'},
      {name:'삼척시', value:'삼척시'},
      {name:'속초시', value:'속초시'},
      {name:'양구군', value:'양구군'},
      {name:'양양군', value:'양양군'},
      {name:'영월군', value:'영월군'},
      {name:'원주시', value:'원주시'},
      {name:'인제군', value:'인제군'},
      {name:'정선군', value:'정선군'},
      {name:'철원군', value:'철원군'},
      {name:'춘천시', value:'춘천시'},
      {name:'태백시', value:'태백시'},
      {name:'평창군', value:'평창군'},
      {name:'홍천군', value:'홍천군'},
      {name:'화천군', value:'화천군'},
      {name:'횡성군', value:'횡성군'},
    ],
    area11:[
      {name:'진주시', value:'진주시'},
      {name:'고성군', value:'고성군'},
      {name:'김해시', value:'김해시'},
      {name:'밀양시', value:'밀양시'},
      {name:'산청군', value:'산청군'},
      {name:'남해군', value:'남해군'},
      {name:'의령군', value:'의령군'},
      {name:'창녕군', value:'창녕군'},
      {name:'양산시', value:'양산시'},
      {name:'거제시', value:'거제시'},
      {name:'창원시', value:'창원시'},
      {name:'통영시', value:'통영시'},
      {name:'거창군', value:'거창군'},
      {name:'사천시', value:'사천시'},
      {name:'함양군', value:'함양군'},
      {name:'하동군', value:'하동군'},
      {name:'함안군', value:'함안군'},
      {name:'합천군', value:'합천군'},
    ],
    area12:[
      {name:'안동시', value:'안동시'},
      {name:'김천시', value:'김천시'},
      {name:'경주시', value:'경주시'},
      {name:'울진군', value:'울진군'},
      {name:'포항시', value:'포항시'},
      {name:'군위군', value:'군위군'},
      {name:'경산시', value:'경산시'},
      {name:'구미시', value:'구미시'},
      {name:'영양군', value:'영야군'},
      {name:'영천시', value:'영천시'},
      {name:'성주군', value:'성주군'},
      {name:'영주시', value:'영주시'},
      {name:'상주시', value:'상주시'},
      {name:'청도군', value:'청도군'},
      {name:'울릉군', value:'울릉군'},
      {name:'봉화군', value:'봉화군'},
      {name:'예천군', value:'예천군'},
      {name:'의성군', value:'의성군'},
      {name:'문경시', value:'문경시'},
      {name:'청송군', value:'청송군'},
      {name:'영덕군', value:'영덕군'},
      {name:'칠곡군', value:'칠곡군'},
      {name:'고령군', value:'고령군'},
    ],
    area13:[
      {name:'영암군', value:'영암구'},
      {name:'영광군', value:'영광구'},
      {name:'완도군', value:'완도군'},
      {name:'화순군', value:'화순군'},
      {name:'담양군', value:'담양구'},
      {name:'구례군', value:'구례구'},
      {name:'신안군', value:'신안구'},
      {name:'장흥군', value:'장흥군'},
      {name:'광양시', value:'광양시'},
      {name:'해남군', value:'해남군'},
      {name:'무안군', value:'무안군'},
      {name:'함평군', value:'함평군'},
      {name:'장성군', value:'장성군'},
      {name:'보성군', value:'보성군'},
      {name:'나주시', value:'나주시'},
      {name:'순천시', value:'순천시'},
      {name:'여수시', value:'여수시'},
      {name:'곡성군', value:'곡성군'},
      {name:'목포시', value:'목포시'},
      {name:'진도군', value:'진도군'},
      {name:'강진군', value:'강진군'},
      {name:'고흥군', value:'고흥군'},
    ],
    area14:[
      {name:'군산시', value:'군산시'},
      {name:'임실군', value:'임실군'},
      {name:'순창군', value:'순창군'},
      {name:'완주군', value:'완주군'},
      {name:'무주군', value:'무주군'},
      {name:'고창군', value:'고창군'},
      {name:'김제시', value:'김제시'},
      {name:'남원시', value:'남원시'},
      {name:'장수군', value:'장수군'},
      {name:'익산시', value:'익산시'},
      {name:'진안군', value:'진안군'},
      {name:'정읍시', value:'정읍시'},
      {name:'전주시', value:'전주시'},
      {name:'부안군', value:'부안군'},
    ],
    area15:[
      {name:'천안시', value:'천안시'},
      {name:'보령시', value:'보령시'},
      {name:'예산군', value:'예산군'},
      {name:'청양군', value:'청양군'},
      {name:'서산시', value:'서산시'},
      {name:'서천군', value:'서천군'},
      {name:'논산시', value:'논산시'},
      {name:'당진시', value:'당진시'},
      {name:'금산군', value:'금산군'},
      {name:'태안군', value:'태안군'},
      {name:'홍성군', value:'홍성군'},
      {name:'공주시', value:'공주시'},
      {name:'계롱시', value:'계롱시'},
      {name:'아산시', value:'아산시'},
      {name:'부여군', value:'부여군'},
    ],
    area16:[
      {name:'보은군', value:'보은군'},
      {name:'영동군', value:'영동군'},
      {name:'증평군', value:'증평군'},
      {name:'괴산군', value:'괴산군'},
      {name:'충주시', value:'충주시'},
      {name:'청주시', value:'청주시'},
      {name:'제천시', value:'제천시'},
      {name:'옥천군', value:'옥천군'},
      {name:'진천군', value:'진천군'},
      {name:'음성군', value:'음성군'},
      {name:'단양군', value:'단양군'},
    ],
    area17:[
      {name:'서귀포시', value:'서귀포시'},
      {name:'제주시', value:'제주시'},
    ],
    hopeloc1:'',
    hopeloc2:'',
    hopeloc3:'',
    hopeloc1_detail:'',
    desiredDayWeek: [],
    starttimepicker:'',
    endtimepicker:'',
    radios:'',
    desiredHourlyWage:'',
    desiredMonthlyWage:'',
    area:''
    }

    },
    methods:{
      // categoryChange(key,sel){
      //   if(key == "seoul"){
      //     document. = this.area1;
          
        
      //   }
       
      // },
      categoryChange(event){
        if(event =='서울'){
          this.area = this.area1;
        }else if(event == '인천'){
          this.area = this.area2;
        }else if(event =='경기'){
          this.area = this.area3;
        }else if(event =='부산'){
          this.area = this.area4
        }else if(event =='대구'){
          this.area = this.area5
        }else if(event =='대전'){
          this.area = this.area6
        }else if(event =='세종'){
          this.area = this.area7
        }else if(event =='광주'){
          this.area = this.area8
        }else if(event =='울산'){
          this.area = this.area9
        }else if(event =='강원'){
          this.area = this.area10
        }else if(event =='경남'){
          this.area = this.area11
        }else if(event =='경북'){
          this.area = this.area12
        }else if(event =='전남'){
          this.area = this.area13
        }else if(event =='전북'){
          this.area = this.area14
        }else if(event =='충남'){
          this.area = this.area15
        }else if(event =='충북'){
          this.area = this.area16
        }else if(event =='제주'){
          this.area = this.area17
        }
        
      },
      console(){
        const userData = {
          hopeloc1:this.hopeloc1,
          hopeloc2:this.hopeloc2,
          hopeloc3:this.hopeloc3,
          desiredDayWeek:this.desiredDayWeek,
          activityTime:this.starttimepicker + this.endtimepicker,
          paytype : this.radios,
          desiredHourlyWage: this.desiredHourlyWage,
          desiredMonthlyWage: this.desiredMonthlyWage,
        };
        // console.log(userData);
        try {
            if(this.hopeloc1 ==""){
                alert("희망근무지역을 선택해주세요!");
                this.hopeloc1.focus();
                return;
            }
            if(this.desiredDayWeek == ""){
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