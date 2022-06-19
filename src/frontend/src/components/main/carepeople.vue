<template>
<body>
    <div class="container">
        <div class="py-5 text-center">

            <h2>케어 대상인 등록</h2>
            <!-- <p class="lead">케어 대상인 등록</p> -->
        </div>

        <div class="col-md-12 order-md-1">
            <h4 class="mb-3">케어대상인 정보</h4>



                <div class="mb-3">
                    <label for="username">이름</label>
                    <div class="input-group">
                        
                        <input type="text" class="form-control" id="username" placeholder="이름을 입력하세요" required name="user-id">
                    </div>        
                </div>
                
                <div class="mb-3">
                    <label for="password">생년월일</label> 
                    <input type="date" class="form-control" id="password" placeholder="" value="" required name="user-password">

                </div>

                <div class="mb-3">
                    <label for="firstName">성별</label> 
                    <br>
                    <input id="toggle-on" class="toggle toggle-left" name="toggle" value="false" type="radio" checked>
<label for="toggle-on" class="btn">Yes</label>
<input id="toggle-off" class="toggle toggle-right" name="toggle" value="true" type="radio">
<label for="toggle-off" class="btn">No</label>

                </div>

                <div class="mb-3">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" placeholder="you@example.com" name="user-email">
                    <div class="invalid-feedback">올바른 이메일을 입력하세요.</div>
                </div>

                <div class="mb-3">
                    <label for="address">좋아하는 음식</label> <input type="text" class="form-control" id="food" placeholder="예) 치킨" required name="user-food">
                    <div class="invalid-feedback">좋아하는 음식을 입력하세요.</div>
                </div>

                <hr class="mb-4">
                <button class="btn btn-primary btn-lg btn-block" type="submit">가입하기</button>
                <hr class="mb-4">

                <footer th:replace="/fragments/semantic :: footer"></footer>

        </div>

    </div>

</body>
  
</template>

<script>



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
        attachFiles:''
    }
},
methods:{  
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
                // console.log(formData);
    this.$http
    .post('/api/dashboard/caretargets/new',formData, {
    withCredentials: true
    })
     .then(res => {
      console.log(res);
    })
      .catch(err => {
       console.log(err);
    });    
    // location.href = '/caretargets';
        },        
        
    },
}

</script>

<style scoped lang="scss">


.btn{
    border: 3px solid #1a1a1a;
    border-radius: 3px;
    display: inline-block;
    padding: 10px;
    position: relative;
    text-align: center;
    transition: background 600ms ease, color 600ms ease;
}

input[type="radio"].toggle {
    display: none;
    & + label{
        cursor: pointer;
        min-width: 60px;
        &:hover{
            background: #1a1a1a;
            color: #1a1a1a;
        }
        &:after{
            background: #1a1a1a;
            content: "";
            height: 100%;
            position: absolute;
            top: 0;
            transition: left 200ms cubic-bezier(0.77, 0, 0.175, 1);
            width: 100%;
            z-index: -1;
        }
    }
    &.toggle-left + label {
        border-right: 0;
        &:after{
            left: 100%
        }
    }
    &.toggle-right + label{
        margin-left: -5px;
        &:after{
            left: -100%;
        }
    }
    &:checked + label {
        cursor: default;
        color: #1a1a1a;
        transition: color 200ms;
        &:after{
            left: 0;
        }
    }
}
html,
body {
  height: 100%;
  background: #efefef;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100%;

}

// body {
//   display: flex;
//   align-items: center;
//   padding-top: 40px;
//   padding-bottom: 40px;
//   background-color: #f5f5f5;
// }

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}

.form-signin .checkbox {
  font-weight: 400;
}

.form-signin .form-floating:focus-within {
  z-index: 2;
}

.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}

/* .signup-box {
    margin-left: 60px;
}
input {
    margin: 10px;
}
#in-input {
    width: 600px;
}
h2 {
    position: relative;
    left: 13px;
    color: #69f0ae;
    text-align: center;
    padding: 20px;
}
#up-btn {
    position: relative;
    left: 13px;
    margin: auto;
    display: block;
    background-color: #69f0ae;
    border: 1px solid #69f0ae;
    color: white;
}
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
} */



















    
</style>