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
                        <input type="text">
                        &nbsp;&nbsp;&nbsp;&nbsp;          
                        <span class="gender_form">성별</span>
                        <v-btn class="ma-2" outlined small fab color="teal">남</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal">여</v-btn>
                    </li>
                    <li>
                        <span>생년월일</span>
                        <select class="date_form">
                            <option>년</option>
                            <option>2000년</option>
                            <option>2001</option>
                            <option>2002</option>
                            <option>2003</option>
                            <option>2004</option>
                            <option>2005</option>
                        </select>
                        <select>
                            <option>월</option>
                            <option>1월</option>
                            <option>2월</option>
                            <option>3월</option>
                            <option>4월</option>
                            <option>5월</option>
                            <option>6월</option>
                        </select>
                        <select>
                            <option>일</option>
                            <option>1일</option>
                            <option>2일</option>
                            <option>3일</option>
                            <option>4일</option>
                            <option>5일</option>
                            <option>6일</option>
                            <option>7일</option>
                        </select>
                    </li>
                    <li>
                        <span>키</span>
                        <input type="number">cm
                        &nbsp;&nbsp;
                        <span>몸무게</span>
                        <input type="number">kg
                    </li>
                    <li>
                        <span>주소</span>
                        <input type="text">
                        <button>우편찾기</button>
                    </li>
                    <li>
                        <span>장기요양동급</span>
                        <select>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                        </select>
                    </li>
                    <li>
                        <span>특이사항</span>
                        <br>
                        <textarea class="information"></textarea>
                    </li>
                    <li>
                        <span>반려동물</span>
                        <v-btn class="ma-2" outlined small fab color="teal">유</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal">무</v-btn>
                    </li>
                    <li>
                        <span>cctv 유무</span>
                        <v-btn class="ma-2" outlined small fab color="teal">O</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal">X</v-btn>
                    </li>
                    <li>
                        <span>케어유형</span>
                        <button>노인</button>
                        <button>아동</button>
                        <button>임산부</button>
                        <button>환자</button>
                    </li>
                    <li>
                        <span>코로나검사여부</span>
                        <v-btn class="ma-2" outlined small fab color="teal">O</v-btn>
                        <v-btn class="ma-2" outlined small fab color="teal">X</v-btn>
                    </li>
                </ul>
                <v-btn class="ma-2" outlined color="indigo">등록</v-btn>
            </div>
        </div>
            
      </div>
  </div>
</template>

<script>
export default {

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
</style>