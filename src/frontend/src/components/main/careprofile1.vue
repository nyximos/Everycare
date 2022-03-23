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
                      <div class="con10_blank"></div>
                      <div class="area_content01">
                        <h5 class="sub_title">희망근무지역</h5>
                      </div>
                      <div>
                        <ul class="work_area" style="display:block;">
                          <li>
                            <span class="area_stitle">1지망</span>
                            <select name="" class="select_css">
                              <option value="">시/도</option>
                              <option value="서울">서울</option>
                              <option value="경기">경기</option>
                              <option value="인천">인천</option>
                              <option value="부산">부산</option>
                              <option value="대구">대구</option>
                              <option value="대전">대전</option>
                              <option value="울산">울산</option>
                            </select>
                            <select name="">
                              <option value="">구/군</option>
                            </select>
                            <select name="">
                              <option value="">동/읍/면</option>
                            </select>
                          </li>
                          <li>
                            <span class="area_stitle">2지망</span>
                            <select name="" class="select_css">
                              <option value="">시/도</option>
                              <option value="서울">서울</option>
                              <option value="경기">경기</option>
                              <option value="인천">인천</option>
                              <option value="부산">부산</option>
                              <option value="대구">대구</option>
                              <option value="대전">대전</option>
                              <option value="울산">울산</option>
                            </select>
                            <select name="">
                              <option value="">구/군</option>
                            </select>
                            <select name="">
                              <option value="">동/읍/면</option>
                            </select>
                          </li>
                          <li>
                            <span class="area_stitle">3지망</span>
                            <select name="" class="select_css">
                              <option value="">시/도</option>
                              <option value="서울">서울</option>
                              <option value="경기">경기</option>
                              <option value="인천">인천</option>
                              <option value="부산">부산</option>
                              <option value="대구">대구</option>
                              <option value="대전">대전</option>
                              <option value="울산">울산</option>
                            </select>
                            <select name="">
                              <option value="">구/군</option>
                            </select>
                            <select name="">
                              <option value="">동/읍/면</option>
                            </select>
                          </li>
                        </ul>
                      </div>
                      <div class="area_day">
                        <h5 class="sub_title">희망근무요일</h5>
                        <div class="area6_r_day">
                          <input type="button" value="월" class="r_day1" data-val="1">
                          <input type="button" value="화" class="r_day1" data-val="2">
                          <input type="button" value="수" class="r_day1" data-val="3">
                          <input type="button" value="목" class="r_day1" data-val="4">
                          <input type="button" value="금" class="r_day1" data-val="5">
                          <input type="button" value="토" class="r_day1" data-val="6">
                          <input type="button" value="일" class="r_day1" data-val="7">
                          <input type="button" value="all" class="r_day1" data-val="7">
                        </div>
                      </div>
                      <div class="area_time">
                        <h5 class="sub_title">희망시간</h5>
                        <div class="r_time_text" style="display:block;">
                          <select name="" id="">
                            <option value="시작시간">시작시간</option>
                            <optgroup label="오전">
                              <option value="01:00">오전 1:00</option>
                              <option value="02:00">오전 2:00</option>
                              <option value="03:00">오전 3:00</option>
                              <option value="04:00">오전 4:00</option>
                              <option value="05:00">오전 5:00</option>
                            </optgroup>
                          </select>
                          <strong>부터</strong>&nbsp;&nbsp;
                          <select name="" id="">
                            <option value="끝나는시간">끝나는시간</option>
                            <optgroup label="오전">
                              <option value="01:00">오전 1:00</option>
                              <option value="02:00">오전 2:00</option>
                              <option value="03:00">오전 3:00</option>
                              <option value="04:00">오전 4:00</option>
                              <option value="05:00">오전 5:00</option>
                            </optgroup>
                          </select>
                        </div>
                      </div>
                      <div class="area_pay">
                        <h5 class="sub_title">급여</h5>
                        <div class="r_pay">
                          <span class="r_pay1">
                            <button type="button" class="pay_plan on" data-val="시간제">시간제</button>
                            <select name="pay_time" id="pay_time" class="select_css04">
                              <option value>선택</option>
                              <option value="">9,160원</option>
                              <option value="">10,000원</option>
                              <option value="">11,000원</option>
                              <option value="">12,000원</option>
                              <option value="">13,000원</option>
                              <option value="">14,000원</option>
                              <option value="">직접선택</option>
                            </select>
                            <input type="text" name="pay_time_text" id="pay_time_text" class="text_css01" style value>
                          </span>
                          <span class="r_pay1">
                            <button type="button" class="pay_plan on" data-val="월급제">월급제</button>
                            <select name="pay_month" id="pay_month" class="select_css04">
                              <option value>선택</option>
                              <option value>50만원</option>
                              <option value>60만원</option>
                              <option value>70만원</option>
                              <option value>80만원</option>
                              <option value>90만원</option>
                              <option value>100만원</option>
                              <option value>직접입력</option>
                            </select>
                            <input type="text" name="pay_month_text" id="play_month_text" class="text_css01" style="display:none;" value>
                          </span>
                        </div>
                      </div>
                      <router-link to="/Careprofile2"><v-btn class="ma-2" outlined color="indigo">다음</v-btn></router-link>
                      <router-link to="/Careprofile"><v-btn class="ma-2" outlined color="indigo">취소</v-btn></router-link>
                  </div>
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