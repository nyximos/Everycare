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
                    <h3 class="profile_title">
                        Information
                    </h3>
                </div>
                    <div class="area_content01">
                        <h5 class="sub_title">
                            선호케어유형
                        </h5>
                         
                    </div>
                        <div class="r_seeking">
                            <v-checkbox
                            v-model="caretype"
                            value="baby"
                            label="베이비"
                            ></v-checkbox>
                            <v-checkbox
                            v-model="caretype"
                            value="housework+clean"
                            label="육아+가사">
                            </v-checkbox>
                          <v-checkbox
                           v-model="caretype"
                           value="pickup"
                          label="등하원"
                          ></v-checkbox>
                          <v-checkbox
                           v-model="caretype"
                           value="play"
                          label="놀이"
                          ></v-checkbox>
                          <v-checkbox
                           v-model="caretype"
                           value="housework"
                          label="가사"
                          ></v-checkbox>
                          <v-checkbox
                           v-model="caretype"
                           value="clean"
                          label="청소"
                          ></v-checkbox>
                          
                            
                          <h5 class="sub_title1">연령</h5>
                        <div class="r_seeking_age">
                            <v-checkbox
                          v-model="ageselect"
                          value="newborn"
                          label="신생아(0-6 month)"
                          ></v-checkbox
                          ><v-checkbox
                           v-model="ageselect"
                           value="baby"
                          label="영아(7-36 month)">
                          </v-checkbox>
                          <v-checkbox
                           v-model="ageselect"
                           value="child"
                          label="유아(4-7 year)"
                          ></v-checkbox>
                          <v-checkbox
                           v-model="ageselect"
                           value="children"
                          label="초등학생"
                          ></v-checkbox>
                          <v-checkbox
                           v-model="ageselect"
                           value="student"
                          label="중고등학생"
                          ></v-checkbox>
                          <v-checkbox
                           v-model="ageselect"
                           value="anything"
                          label="상관없음"
                          ></v-checkbox>
                        </div>
                      </div>
                      <br><br>
                      <div class="con1 con01">
                          <h5 class="sub_title">백신</h5>
                          <div class="cInner">
                        <v-radio-group
                        v-model="vaccine"
                        mandatory
                        row>
                          <v-radio
                          label="1차접종완료"
                          value="1vaccine">
                          </v-radio>
                          <v-radio
                          label="추가접종완료"
                          value="additionvaccine">
                          </v-radio>
                          <v-radio
                          label="미접종"
                          value="novaccine">
                          </v-radio>
                          </v-radio-group>
                          </div>
                      </div>
                      <div class="con1 con02">
                          <h5 class="sub_title">cctv 동의여부</h5>
                          <div class="cInner_01">
                        <v-radio-group
                        v-model="cctv"
                        mandatory
                        row>
                          <v-radio
                          label="⭕"
                          value="agree">
                          </v-radio>
                          <v-radio
                          label="❌"
                          value="disagree">
                          </v-radio>
                          </v-radio-group>
                          </div>
                      </div>
                      <div class="con1 con03">
                          <h5 class="sub_title">자격증</h5>
                          <div class="">
                              <br>
                              <v-file-input 
                              v-model="files" 
                              label="File input" 
                              outlined dense>
                              </v-file-input>
                          </div>
                      </div>
                  </div>
                  
              </div>
              <br><br>
               <router-link :to="{name: 'Main'}">
               <v-btn class="ma-2" outlined color="indigo" @click="clickme">등록</v-btn>
               </router-link>
                <router-link to="/Careprofile1"><v-btn class="ma-2" outlined color="indigo">취소</v-btn></router-link>
          </div>
          
      </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {

data(){
   
    return{
        caretype: [],
        ageselect: [],
        cctv:'',
        vaccine: '',
        files: [],
        
    }
  },
  methods:{
      clickme(){
          var userData={
              caretype:this.caretype,
              ageselect:this.ageselect,
              cctv:this.cctv,
              vaccine:this.vaccine,
              files:this.files.name
          }
          const axiosuserData = {
              image: this.$store.state.careprofileStore.image,
              name: this.$store.state.careprofileStore.name,
              sex: this.$store.state.careprofileStore.sex,
              age: this.$store.state.careprofileStore.age,
              comment: this.$store.state.careprofileStore.comment,
              hoploc1: this.$store.state.careprofileStore.hoploc1,
              hoploc2: this.$store.state.careprofileStore.hoploc2,
              hoploc3: this.$store.state.careprofileStore.hoploc3,
              hopeday: this.$store.state.careprofileStore.hopeday,
              starttimepicker: this.$store.state.careprofileStore.starttimepicker,
              endtimepicker: this.$store.state.careprofileStore.endtimepicker,
              paytype: this.$store.state.careprofileStore.paytype,
              pay: this.$store.state.careprofileStore.pay,              
              caretype:this.$store.state.careprofileStore.caretype,
              ageselect:this.$store.state.careprofileStore.ageselect,
              cctv:this.$store.state.careprofileStore.cctv,
              vaccine:this.$store.state.careprofileStore.vaccine,
              files:this.$store.state.careprofileStore.files
          }
        //   const formData = new FormData();
        //   formData.append("key",new Blob([JSON.stringify(axiosuserData)],{ type : "application.json"}));
          console.log(userData)
          try {
              this.$store.commit('careprofileStore/set_user3', userData);
              console.log('데이터 저장')
              console.log(this.$store.state.careprofileStore.image)
              console.log('이미지 받았다~')
              axios.post('/api/dashboard/caresitter', axiosuserData)
              .then((res)=> {
                  console.log(res.data)
              })
              .catch((err)=>{
                  console.log(err)
              })
          } catch (error) {
              console.log(error)
          }
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
    .content .r_seeking{
        width: 100%;
        position: relative;
        display: inline-block;
        padding: 30px 0 10px 0;
    }
    .content .r_seeking1{
        position: relative;
        background-color: #fefefe;
        border: 1px solid black;
        color: black;
        border-radius: 50px;
        width: 120px;
        height: 40px;
        margin: 0 2px 15px 2px;
        font-size: 0.9em;
        letter-spacing: -0.05em;
    }
    .r_seeking_age span{
        width:100%;
        display: inline-block;
        font-size: 0.8em;
        letter-spacing: 0;
    }
   .cInner{
       position: relative;
       width: 100%;
       box-sizing: border-box;
       padding: 0 0 0 0;
       text-align: center;
   }
</style>