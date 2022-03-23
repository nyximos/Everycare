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
                          <h3 class="profile_title">Information</h3>
                      </div>
                      <div class="con10_blank"></div>
                      <div class="area_content01">
                        <h5 class="sub_title">선호케어유형</h5>
                      </div>
                      <div class="r_seeking">
                          <button type="button" class="r_seeking1 r_sphere_baby">베이비</button>
                          <button type="button" class="r_seeking1 r_sphere_baby">육아+가사</button>
                          <button type="button" class="r_seeking1 r_sphere_baby">등하원</button>
                          <button type="button" class="r_seeking1 r_sphere_baby">놀이</button>
                          <button type="button" class="r_seeking1 r_sphere_baby">가사</button>
                          <button type="button" class="r_seeking1 r_sphere_baby">청소</button>
                          <button type="button" class="r_seeking1 r_sphere_baby">2</button>
                          <button type="button" class="r_seeking1 r_sphere_baby">1</button>
                            <br><br><br>
                          <h5 class="sub_title1">연령</h5>
                        <div class="r_seeking_age">
                            <button type="button" class="r_seeking1 age">신생아<span>(0~6개월)</span></button>
                            <button type="button" class="r_seeking1 age">영아<span>(7~36개월)</span></button>
                            <button type="button" class="r_seeking1 age">유아<span>(4~7세)</span></button>
                            <button type="button" class="r_seeking1 age">초등학생<span>(8~13세)</span></button>
                            <button type="button" class="r_seeking1 age">중 고등학생<span>(14~19세)</span></button>
                            <button type="button" class="r_seeking1 age" style="width:98%">상관없음</button>
                        </div>
                      </div>
                      <br><br>
                      <div class="con1 con01">
                          <h5 class="sub_title">백신</h5>
                          <div class="cInner">
                              <button type="button" class="r_seeking1 vaccine">1차접종완료</button>
                              <button type="button" class="r_seeking1 vaccine">추가접종완료</button>
                              <button type="button" class="r_seeking1 vaccine">미접종</button>
                          </div>
                      </div>
                      <div class="con1 con02">
                          <h5 class="sub_title">cctv 동의여부</h5>
                          <div class="cInner_01">
                              <button type="button" class="r_seeking1 cctv">O</button>
                              <button type="button" class="r_seeking1 cctv">X</button>
                          </div>
                      </div>
                      <div class="con1 con03">
                          <h5 class="sub_title">자격증</h5>
                          <div class="">
                              <input type="file">
                          </div>
                      </div>
                  </div>
                  
              </div>
              <br><br>
               <router-link to="/"><v-btn class="ma-2" outlined color="indigo">등록</v-btn></router-link>
                <router-link to="/Careprofile1"><v-btn class="ma-2" outlined color="indigo">취소</v-btn></router-link>
          </div>
          
      </div>
  </div>
</template>

<script>
export default {

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