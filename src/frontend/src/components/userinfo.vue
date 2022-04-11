<template>
<div>
  <h2>내 정보</h2>
      <br>
    <table border="1" width="600px" cellpadding="15px" frame=void>
        <tr>
            <th id="id-line" width=20%>아이디</th>
            <td id="id-text" width=30%>{{id}}</td>
            <th id="pw-line" width=20%>비밀번호</th>
            <td id="pw-text" width=30%><button></button></td>
        </tr>
        <tr>
            <th id="name-line">이름</th>
            <td id="name-text" width=30%></td>
            <th id="gen-line" width=20%>성별/나이</th>
            <td id="gen-text-line" width=30%></td>
        </tr>
        <tr>
            <th id="bir-line">생년월일</th>
            <td id="bir-text-line" colspan="3"></td>
        </tr>
        <tr>
            <th id="num-line">연락처</th>
            <td id="num-text-line" colspan="3"></td>
        </tr>
        <tr>
            <th id="mail-line">이메일</th>
            <td id="mail-text-line" colspan="3"></td>
        </tr>
        <tr>
            <th id="addr-line"><br><br>주소<br><br><br></th>
            <td id="addr-text-line" colspan="3"><button>(변경)</button></td>   
        </tr>
    </table>
    <br>
    <div id="btn-box">
     <button type="button" id="ok-btn" class="btn btn-primary">수정</button>
     <button @click="mainreturn" type="button" id="cen-btn" class="btn btn-secondary">취소</button>
     </div>
     </div>
</template>

<script>
export default {
   data(){
     return{
       id:'dd'
     }
   },
   methods:{
     mainreturn(){
       window.location.href = 'http://localhost:8080';
     }
   }
}
</script>

<style>
table {
    width: 900px;
    position: relative;
    left: 20px;
    border:0;
    border-collapse:collapse;
  }
  th, td {
    border: solid 1px;
    
  }
  #id-line{
      border-left: none; 
      border-right-color: rgb(163, 163, 253);
      border-bottom-color: rgb(163, 163, 253);
      background-color: rgb(189, 215, 243); 
      border-top: 2px solid rgb(60, 33, 235);
      text-align: center;
      padding: 10px;
  }
  #id-text{
      border-top: 2px solid rgb(60, 33, 235);
  }
  #id-text, #name-text{
      border-right-color: rgb(163, 163, 253);
      border-bottom-color: rgb(163, 163, 253);
  }
  #pw-line{
      border-top: 2px solid rgb(60, 33, 235);
  }
  #pw-line, #gen-line{
    background-color: rgb(189, 215, 243);
    border-right-color: rgb(163, 163, 253);
      border-bottom-color: rgb(163, 163, 253);
    text-align: center;
  }
  #pw-text{  
    border-right: none;
    border-top: 2px solid rgb(60, 33, 235);
    border-bottom-color: rgb(163, 163, 253);
  }
  #name-line, #bir-line, #num-line, #mail-line,
  #con-line{
    border-left: none;
    background-color: rgb(189, 215, 243);
    text-align: center;
    border-right-color: rgb(163, 163, 253);
    border-bottom-color: rgb(163, 163, 253);
    padding: 10px;
  }
  #addr-line{
      background-color: rgb(189, 215, 243);
      border-right-color: rgb(163, 163, 253);
      border-bottom: 2px solid rgb(60, 33, 235);
      text-align: center;
      border-left: none;
  }
  #addr-text-line{
    border-right: none;  
    border-bottom: 2px solid rgb(60, 33, 235);
  }

  #bir-text-line, #num-text-line, #gen-text-line, #mail-text-line, #con-text-line{
      border-right: none;
      border-right-color: rgb(163, 163, 253);
      border-bottom-color: rgb(163, 163, 253);
  }
  #ok-btn{
      margin: auto;
      display: block;
      float: left;
      margin:10px;
  }
  #cen-btn{
      margin:auto;
      display: block;
      float: left;
      margin:10px;
  }
  #ok-btn{
      position: relative;
      left: 760px;
      bottom: 10px;
  }
  #cen-btn{
      position: relative;
      left: 760px;
      bottom: 10px;
  }
</style>