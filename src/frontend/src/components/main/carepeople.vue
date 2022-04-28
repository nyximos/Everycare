<template>
  <div class="content">
      <div class="join">
          <div class="div_join">
              <img src="@/assets/logo.png" alt="없음">
              <button type="button">등록</button>
            <div class="content1">
                <ul>
                    <li>
                        <span class="name_form">이름</span>
                        &nbsp;&nbsp;<input v-model="name" type="text">
                        &nbsp;&nbsp;&nbsp;&nbsp;          
                        <span class="gender_form">성별</span>
                        <v-radio-group type="text" v-model="gender" mandatory row>
                                    <v-radio label="남" value="M"></v-radio>
                                    <v-radio label="여" value="W"></v-radio>

 
 
 
                         </v-radio-group>
                        <!-- <v-btn class="ma-2" v-model="gender" outlined small fab color="teal">남</v-btn>
                        <v-btn class="ma-2" v-model="gender" outlined small fab color="teal">여</v-btn> -->
                        
                    </li>
                    <li>
                        <span>생년월일</span>
                        <v-row justify="center">
                            <v-date-picker type="date" v-model="birth" year-icon="mdi-calendar-blank"
                            prev-icon="mdi-skip-previous" next-icon="mdi-skip-next">
                        </v-date-picker>
                        </v-row>
                        <!-- <v-select class="date_form">
                            <option value="birth">년</option>
                            <option value="birth">2000년</option>
                            <option value="birth">2001</option>
                            <option value="birth">2002</option>
                            <option value="birth">2003</option>
                            <option value="birth">2004</option>
                            <option value="birth">2005</option>
                        </v-select>
                        <select>
                            <option value="birth">월</option>
                            <option value="birth">1월</option>
                            <option value="birth">2월</option>
                            <option value="birth">3월</option>
                            <option value="birth">4월</option>
                            <option value="birth">5월</option>
                            <option value="birth">6월</option>
                        </select>
                        <select>
                            <option value="birth">일</option>
                            <option value="birth">1일</option>
                            <option value="birth">2일</option>
                            <option value="birth">3일</option>
                            <option value="birth">4일</option>
                            <option value="birth">5일</option>
                            <option value="birth">6일</option>
                            <option value="birth">7일</option>
                        </select> -->
                    </li>
                    <li>
                        <span>키</span>
                        &nbsp;&nbsp;<input type="number" v-model="height" class="input_form_number">cm
                        &nbsp;&nbsp;
                        <span>몸무게</span>
                        &nbsp;&nbsp;<input type="number" v-model="weight" class="input_form_number">kg
                    </li>
                    <li>
                        <span>주소</span>
                        &nbsp;&nbsp;<input type="text" v-model="address">
                        <span>우편번호</span>
                        &nbsp;&nbsp;<input type="text" v-model="zipcode">
                        <span>상세주소</span>
                        &nbsp;&nbsp;<input type="text" v-model="detailedAddress">
                        <button>우편찾기</button>
                    </li>
                    <li>
                        <span>장기요양등급</span>
                        <select type="number" v-model="longTermCareGrade">
                            <option disabled value="">선택</option>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                        </select>
                    </li>
                    <li>
                        <span>특이사항</span>
                        <br>
                        <textarea type="text" class="information" placeholder="특이사항써주세요" v-model="comment"></textarea>
                    </li>
                    <li>
                        <span>반려동물 유무</span>
                        <v-radio-group type="number" v-model="pet" mandatory row>
                                    <v-radio label="O" value="0"></v-radio>
                                    <v-radio label="X" value="1"></v-radio>
                        </v-radio-group>
                        <!--
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="pet">유</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="pet">무</v-btn> -->
                    </li>
                    <li>
                        <span>cctv 유무</span>
                        <v-radio-group type="number" v-model="isCctvAgreement" mandatory row>
                                    <v-radio label="O" value="0"></v-radio>
                                    <v-radio label="X" value="1"></v-radio>
                        </v-radio-group>
                         <!--
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="cctv">O</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="cctv">X</v-btn> -->
                    </li>
                    <li>
                        <span>케어유형</span>
                        <v-radio-group type="text" v-model="careType" mandatory row>
                                    <v-radio label="노인" value="oldman"></v-radio>
                                    <v-radio label="아동" value="child"></v-radio>
                                    <v-radio label="임산부" value="pregnantwomen"></v-radio>
                                    <v-radio label="환자" value="patient"></v-radio>
                        </v-radio-group>
                        <!-- <v-btn class="ma-2" outlined small fab color="teal" v-model="caretype">노인</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="caretype">아동</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="caretype">임산부</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="caretype">환자</v-btn> -->
                    </li>
                    <li>
                        <span>코로나검사여부</span>
                        <v-radio-group type="number" v-model="coronaTest" mandatory row>
                                    <v-radio label="O" value="0"></v-radio>
                                    <v-radio label="X" value="1"></v-radio>
                        </v-radio-group>
                    </li>
                    <li>
                    <span>파일 업로드</span>
                    
                    <!-- <v-file-input multiple label="File input" @change="selectFile"></v-file-input> -->
 
                    <v-file-input type="file" v-model="attachFiles" name="attachFiles" id="attachFiles" multiple="multiple"></v-file-input>
                    <!-- <button @click="selectUploadFile()">이미지 선택</button> -->
                    </li>
                </ul>
                <v-btn class="ma-2" outlined color="indigo" id="clickme" @click="clickme">등록</v-btn>
            </div>
        </div>
            
      </div>
  </div>
</template>

<script>
//  import axios from 'axios'
export default {
    
data(){
    return{
        name: '',
        gender: '',
        birth: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
        height:'',
        weight:'',
        zipcode:'',
        address:'',
        detailedAddress:'',
        longTermCareGrade:'',
        comment:'',
        pet:'',
        isCctvAgreement:'',
        careType:'',
        coronaTest:'',
        attachFiles:''
    }
},
methods:{  
    
    clickme(){ 
        let formData = new FormData() 
                
                formData.append('name',this.name);
                formData.append('gender',this.gender);
                formData.append('birth',this.birth);
                formData.append('height',this.height);
                formData.append('weight',this.weight);
                formData.append('zipcode',this.zipcode);
                formData.append('address',this.address);
                formData.append('detailedAddress',this.detailedAddress);
                formData.append('longTermCareGrade',this.longTermCareGrade);
                formData.append('comment',this.comment);
                formData.append('pet',this.pet);
                formData.append('isCctvAgreement',this.isCctvAgreement);
                formData.append('careType',this.careType);
                formData.append('coronaTest',this.coronaTest);
                

                for (let i = 0; i < this.attachFiles.length; i++) {
                formData.append('attachFiles', this.attachFiles[i]);
                }
                
    this.$http
    .post('/api/carenote/caretargets/new',formData, {
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
}

</script>

<style>
ul{
    list-style: none;
}
    .content{
        width:100%;
        min-height: 800px;
        overflow: hidden;
    }
    .content .join{
        width:100%;
        max-width: 800px;
        margin: 50px auto 150px auto;
        position: relative;
        text-align: left;
        box-sizing: border-box;
        padding: 0 15px 0 15px;
    }
    .content .join .div_join{
        width: 100%;
        position: relative;
        box-sizing: border-box;
        text-align: center;
        background-color: #ffffff;
        border: 0;
        box-shadow: 0 0 18px #d3d3d3;
        border-radius: 10px;
        padding: 40px 10px 40px 10px;
    }
    .content1{
        text-align: left;
        display: block;
        
    }
    .content1 span{
        font-size: 20px;
        
    }
    .content1 input[type="text"],
    input[type="number"]{
        border-radius: 5px;
        border: 1px #e3e3e3 solid;
        outlinecolor: black;
    }
    .data_form{
        border: 2px black solid;
        border-radius: 3;
    }
    .submit{
        text-align: center;
        
    }
    .information{
        width: 700px;
        height: 150px;
        border: 2px #e3e3e3 double;
    }
    .input_form_number{
        text-align: right;
    }
</style>