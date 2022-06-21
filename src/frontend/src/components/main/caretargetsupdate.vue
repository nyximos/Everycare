<template>
  <div class="content">
      <div class="join">
          <div class="div_join">
              <div class="contents">
    <div class="upload-box">
      <div id="drop-file" class="drag-file">
          <div class="div_img">
                              <label for="input_file">
                                  <img :src="avatar" class="img-avatar">                                  
                              </label>
                          </div>
                         
                              
         <img src="https://img.icons8.com/pastel-glyph/2x/image-file.png" alt="파일 아이콘" class="image" >
        <p class="message">사진을 등록 해주세요.</p>
        <span><input type="file" v-bind:src="attachFiles" accept="image/gif,image/jpg,image/png" @change="changeImage($event)" ref="avatarInput" class="preview"></span>
         <img src="" alt="미리보기 이미지" class="preview"> 
      </div>
      <label class="file-label" for="attachFiles">이미지 찾기</label>
      <v-file-input class="file" v-model="attachFiles" id="attachFiles" type="file" multiple="multiple" accept="image/png, image/jpeg, image/gif"></v-file-input>
    </div>
  </div>
  <br>
            <div class="content1">
                <ul>
                    
                      <li><span >이름 </span><br><input type="text" v-model="name" placeholder = "이름을 입력하세요" class = 'box'/>
                      
                        
                        </li>

                     <li><span>생년월일</span><br><input type="date" v-model="birth" placeholder = "생년월일을 입력하세요." class = 'box'/>&nbsp;&nbsp;
                     <span class="gender_form">성별</span>
                        <input type="radio" v-model="gender" id="gender1" value="M" checked="checked">&nbsp;&nbsp;&nbsp;
                            <label for="gender1">남</label>&nbsp;&nbsp;
                        <input type="radio" v-model="gender" id="gender2" value="W">
                            <label for="gender2" >여</label>
                    </li>
                    <li><span>키,몸무게</span><br><input type="number" v-model="height" placeholder = "생년월일을 입력하세요." class = 'box1'/>&nbsp;cm
                    <input type="number" v-model="weight" placeholder = "생년월일을 입력하세요." class = 'box1'/>&nbsp;kg
                    
                    <li>
                    <span>주소</span><br>  
                    <input type="text" v-model="zipcode" name="zipcode" placeholder = "우편번호" class = 'box1' readonly/>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <button class="addressbutton" @click="execDaumPostcode()">주소 찾기</button>
                    <br><input type="text" v-model="address" name="address" placeholder = "도로명주소" class = 'box' readonly />
                    <br><input type="text" v-model="detailedAddress" name="detailedAddress" placeholder = "상세주소" class = 'box'/>
                        <!-- <span>주소</span>
                        &nbsp;&nbsp;<input type="text" v-model="address">
                        <span>우편번호</span>
                        &nbsp;&nbsp;<input type="text" v-model="zipcode">
                        <span>상세주소</span>
                        &nbsp;&nbsp;<input type="text" v-model="detailedAddress">
                        <button>우편찾기</button> -->
                    </li>
                    <li>
                        <span>장기요양등급</span><br>
                        <!-- <v-col class="d-flex" cols="12" sm="6">
                        <v-select :items="longTermCareGrade" outlined></v-select></v-col> -->
                        
                        <select type="number" v-model="longTermCareGrade" class="box1">
                            
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                        </select>
                        등급
                    </li>
                    <li>
                        <span>특이사항</span>
                        <br>
                        <textarea type="text" class="information" placeholder="특이사항써주세요" v-model="comment"></textarea>
                    </li>
                    <li>
                      <span>반려동물 유무</span>
                        <input type="radio" v-model="pet" id="O" value="0" checked="checked">&nbsp;&nbsp;&nbsp;
                            <label for="O">O</label>&nbsp;&nbsp;
                        <input type="radio" v-model="pet" id="X" value="1">
                            <label for="X">X</label>
                        <!-- <span>반려동물 유무</span>
                        <v-radio-group type="number" v-model="pet" mandatory row>
                                    <v-radio label="O" value="0"></v-radio>
                                    <v-radio label="X" value="1"></v-radio>
                        </v-radio-group> -->
                        <!--
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="pet">유</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="pet">무</v-btn> -->
                    </li>
                    <li>
                        <span>cctv 유무</span>
                        <input type="radio" v-model="isCctvAgreement" id="O1" value="0" checked="checked">&nbsp;&nbsp;&nbsp;
                            <label for="O1">O</label>&nbsp;&nbsp;
                        <input type="radio" v-model="isCctvAgreement" id="X1" value="1">
                            <label for="X1">X</label>
                        <!-- <v-radio-group type="number" v-model="isCctvAgreement" mandatory row>
                                    <v-radio label="O" value="0"></v-radio>
                                    <v-radio label="X" value="1"></v-radio>
                        </v-radio-group> -->
                         <!--
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="cctv">O</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="cctv">X</v-btn> -->
                    </li>
                    <li>
                        <span>케어유형</span>
                        <input type="radio" v-model="careType" id="oldman" value="oldman" checked="checked">&nbsp;&nbsp;&nbsp;
                            <label for="oldman">노인</label>&nbsp;&nbsp;
                        <input type="radio" v-model="careType" id="child" value="child">
                            <label for="child">아동</label>
                        <input type="radio" v-model="careType" id="pregnantwomen" value="pregnantwomen">
                            <label for="pregnantwomen">임산부</label>
                        <input type="radio" v-model="careType" id="patient" value="patient">
                            <label for="patient">환자</label>
                        <!-- <v-radio-group type="text" v-model="careType" mandatory row>
                                    <v-radio label="노인" value="oldman"></v-radio>
                                    <v-radio label="아동" value="child"></v-radio>
                                    <v-radio label="임산부" value="pregnantwomen"></v-radio>
                                    <v-radio label="환자" value="patient"></v-radio>
                        </v-radio-group> -->
                        <!-- <v-btn class="ma-2" outlined small fab color="teal" v-model="caretype">노인</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="caretype">아동</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="caretype">임산부</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal" v-model="caretype">환자</v-btn> -->
                    </li>
                    <li>
                        <span>코로나검사여부</span>
                        <input type="radio" v-model="coronaTest" id="O2" value="0" checked="checked">&nbsp;&nbsp;&nbsp;
                            <label for="O2">O</label>&nbsp;&nbsp;
                        <input type="radio" v-model="coronaTest" id="X2" value="1">
                            <label for="X2">X</label>
                        <!-- <v-radio-group type="number" v-model="coronaTest" mandatory row>
                                    <v-radio label="O" value="0"></v-radio>
                                    <v-radio label="X" value="1"></v-radio>
                        </v-radio-group> -->
                         <div v-for="(i,index) in imgfile" :key="index">
                              <img :src="'https://localhost:8086/api/images/' +i.storeFilename, " width="250px" height="250px" alt="">
                              <v-btn @click="del(i)">삭제하기</v-btn>
                      </div>
                    </li>
                    <li>
                      
                    <span>파일 업로드</span>
                    
                    
                    
 
                    <v-file-input type="file" v-model="attachFiles" name="attachFiles" id="attachFiles" multiple="multiple"></v-file-input>
                    
                    </li>
                </ul>
                <v-btn class="ma-2" outlined color="indigo" @click="update">수정</v-btn>
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
        avatar:'',
        name: '',
        gender: '',
        birth: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
        height:'',
        weight:'',
        zipcode:'',
        address:'',
        detailedAddress:'',
        longTermCareGrade:['1','2','3'],
        comment:'',
        pet:'',
        isCctvAgreement:'',
        careType:'',
        coronaTest:'',
        attachFiles:'',
        id:this.$route.params.caretargetsId,
        imgfile:''
    }
},
mounted(){
const id = this.$route.params.caretargetsId;
	this.$http.get(`/api/dashboard/caretargets/${id}`, {
		withCredentials: true
	})
	
    .then((res)=>{
        console.log(res.data.body);
        this.name = res.data.body.name
        this.gender = res.data.body.gender
        this.birth = res.data.body.birth
        this.height = res.data.body.height
        this.weight = res.data.body.weight
        this.zipcode = res.data.body.zipcode
        this.address = res.data.body.address
        this.detailedAddress = res.data.body.detailedAddress
        this.longTermCareGrade = res.data.body.longTermCareGrade
		this.comment = res.data.body.comment
		this.pet = res.data.body.pet
		this.isCctvAgreement = res.data.body.isCctvAgreement
		this.careType = res.data.body.careType
		this.coronaTest = res.data.body.coronaTest
		// this.attachFiles = res.data.body.attachFiles
    this.imgfile = res.data.body.imageDTOs
    console.log(this.imgfile)
    })
    .catch((err)=>{
        console.log(err)
    })
},
methods:{  
  update(){ 
        location.href = '/caretargets';
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
                formData.append('attachFiles', this.attachFiles[0]);
                }
                
    this.$http
    .patch(`/api/dashboard/caretargets/${this.id}`,formData, {
    withCredentials: true
    })
     .then(res => {
      console.log(res);
    })
      .catch(err => {
       console.log(err);
    });    
    
        }, 
execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address = data.jibunAddress;
          }
 
          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
          // 우편번호를 입력한다.
          this.zipcode = data.zonecode;
        },
      }).open();
    },
  
        changeImage(e){
        var file = e.target.files[0]
        var reader = new FileReader()
        var that = this
        reader.readAsDataURL(file)
        reader.onload = function(){
            that.avatar = this.result
        }
    },
  

    },
}

</script>

<style scoped>
.contents {
  display: flex;
  flex-direction: row;
  margin-top: 30px;
}
.contents .upload-box {
  width: 100%;
  margin-right: 30px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.contents .upload-box .drag-file {
  position: relative;
  width: 100%;
  height: 360px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  border: 3px dashed #dbdbdb;
}
.contents .upload-box .drag-file.highlight {
  border: 3px dashed red;
}
.contents .upload-box .drag-file .image {
  width: 40px;
}
.contents .upload-box .drag-file .message {
  margin-bottom: 0;
}
.contents .upload-box .drag-file .preview {
  display: none;
  position: absolute;
  left: 0;
  height: 0;
  width: 100%;
  height: 100%;
}
.contents .upload-box .file-label {
  margin-top: 30px;
  background-color: #5b975b;
  color: #fff;
  text-align: center;
  padding: 10px 0;
  width: 65%;
  border-radius: 6px;
  cursor: pointer;
}
.contents .upload-box .file {
  display: none;
}

@media (max-width: 700px) {
  .contents {
    display: flex;
    flex-direction: column;
    margin-top: 30px;
  }
  .contents .upload-box {
    width: 100%;
    box-sizing: border-box;
    margin-right: 0;
  }
  .contents .upload-box .drag-file {
    height: 150px;
  }
  .contents .files {
    width: 100%;
    box-sizing: border-box;
    margin-right: 0;
    overflow: initial;
  }
}
        

.box{width: 450px; height: 45px; border: 1px solid #666; padding: 10px;}
.box1{width: 250px; height: 45px; border: 1px solid #666; padding: 10px;}
.pbox{width: 120px; height: 45px; border: 1px solid #666; padding: 10px;}


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
        border: 1px solid #666;
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
    

input[type=radio]{
	width: 0;
  height: 0;
  position: absolute;
  left: -9999px;
  
}
input[type=radio] + label{
  line-height: 50px;
  width: 50px;
  height: 50px;
  
  box-sizing: border-box;
  position: relative;
  display: inline-block;
  border: solid 1px #DDD;
  background-color: #FFF;
  
  text-align: center;
  box-shadow: 0 0 0 rgba(255, 255, 255, 0);
  transition: border-color .15s ease-out,  color .25s ease-out,  background-color .15s ease-out, box-shadow .15s ease-out;
  cursor: pointer;
  border-radius: 100%;
}
input[type=radio]:checked + label{
	background-color: #69f0ae;
  color: #FFF;
  
  border-color: #69f0ae;
  z-index: 1;
}
.addressbutton{
  
    background-color: #5b975b;
    color: #fff;
    text-align: center;
    
    width: 250px;
    height: 50px;
    border-radius: 6px;
    
    line-height: 50px;
}

</style>