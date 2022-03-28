<template>
  <div class="sub_contents">
      <div class="sub_title">
          <h3>케어시터프로필</h3>
          <div class="location">
              <button type="button">삭제하기</button>
          </div>
      </div>
      <div class="icn_to2">
          <p class="to_dt">업데이트 <strong style="color:#000000l;">2022년 03월 24일 00시 05분</strong></p>
      </div>
      <div class="resumedetail2" id="carea01">
          <div class="tab_view">
              <span class="on">인적사항</span>
              <div class="contentsbg2">
                  <table width="100%" border="0" class="view3">
                      <colgroup>
                        <col width="130">
                        <col width="200">
                        <col width="250">
                        <col width="*">
                      </colgroup>
                      <tbody>
                          <tr>
                              <td class="profile" rowspan="6">
                                  <p>
                                      <img src="@/assets/user.png" width="97" height="122" title="회원사진" style="border-radius:5px;">
                                  </p>
                              </td>
                              <td colspan="3" style="border:0;">
                                  <span style="text-align:left; font-weight:bold; font-size:14px; color:#666;">
                                      전OO(만40세/여성)
                                  </span>
                              </td>
                          </tr>
                          <tr>
                              <th>전&nbsp;화&nbsp;번&nbsp;호</th>
                              <td>***-****-****</td>
                          </tr>
                          <tr>
                              <th>연&nbsp;락&nbsp;시&nbsp;간</th>
                              <td>종일</td>
                          </tr>
                          <tr>
                              <th>거&nbsp;주&nbsp;지&nbsp;역</th>
                              <td>서울 ~~~</td>
                          </tr>
                      </tbody>
                  </table>
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
    .sub_contents{
        overflow: hidden;
        width: auto;
    }
    .sub_title{
        height: 41px;
        border-bottom: 1px solid #dfdfdf;
        line-height: 41px;
    }
    .sub_title h3{
        position: relative;
        float: left;
        padding-left: 9px;
        font-size: 22px;
        font-weight: 400;
    }
    .sub_title .location{
        float: right;
    }
    .icn_to2{
        height: 35px;
        top: 15px;
        right: 15px;
        overflow: hidden;
    }
    .icn_to2 .to_dt{
        position: relative;
        top: 10px;
        display: inline;
        width: 150px;
        letter-spacing: -1px;
    }
    .resumedetail2{
        width: 100%;
        float: left;
        position: relative;
        margin: 5px 0 10px 0;
    }
    .tab_view{
        width: 100%;
    }
    .tab_view span.on{
        border-bottom: 3px solid red;
        font-weight: bold;
        font-size: 1.2em;
    }
    .contentsbg2{
        background-color: #ffffff;
        border: 1px solid #e1e1e1;
        box-sizing: border-box;
        padding: 10px;
    }
    .contentsbg2 .view3{
        width: 100%;
        margin: 0 auto;
        border-collapse: collapse;
    }
    colgroup{
        display: table-column-group;
    }
    col{
        display: table-column;
    }
    .contentsbg2 .view3 td.profile{
        padding: 8px 0 7px 0;
        text-align: center;
    }
    .contentsbg2 .view3 th{
        font-size: 12px;
        font-weight: bold;
        color: #73564c;
        background-color: #fbfbfb;
        text-align: center;
        padding: 8px 0 2px 0;
        vertical-align: middle;
    }
    .contentsbg2 .view3 td{
        padding: 8px 0 7px 10px;
        vertical-align: middle;
    }
</style>