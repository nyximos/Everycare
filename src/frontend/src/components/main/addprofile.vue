<template>
  <div class="frame">
      <nav class="navigator">
          <h3 class="site-name">PROFILE</h3>
          <ul>
              <li><a class="nav-menu" href="/">삭제하기</a></li>
              <li><a class="nav-menu" href="/">게시여부</a></li>
          </ul>
      </nav>
      <div class="profile-section">
          <img id="profile-picture" src="@/assets/logo.png" alt="없음">
          <div class="introduction">
              <h2>김xx</h2>
              <p>남/50세</p>
              <h2>자기소개</h2>
              <p id="cap">hihihihhihih</p>
          </div>
      </div>

      <h5 class="value-menu">Hope</h5>
      <div class="value-section">
          <div class="value-intro">
              <h4>요일</h4>
              <h4>시간</h4>
              <h4>급여</h4>
          </div>
      </div>
      <h5 class="value-menu">intro</h5>
      <div class="value-section">
          <div class="value-intro">
              <h4>선호케어유형</h4>
              <h4>CCTV</h4>
              <h4>백신접종</h4>
              <h4>자격증</h4>
              <v-file-input accept="image/*" label="File input"></v-file-input>
          </div>
      </div>
      <div class="text-center">
          <V-btn class="ma-2" outlined color="indigo">등록</V-btn>
          <V-btn class="ma-2" outlined color="indigo">취소</V-btn>
      </div>
      <footer class="footer"></footer>
      <input />
  </div>
</template>

<script>
export default {

}


</script>

<style>
    .value-menu{
        font-size: 35px;
        margin: 30px 100px;
        color: black;
        border-top: 1px solid #dedede;
        border-left: 1px solid #dedede;
        border-right: 1px solid #dedede;
        border-radius: 10px 10px 0 0;
        background-color: #f4f4f4;
    }

    .value-section{
        display: flex;
        flex-direction: column;
        justify-content: center;
        margin: 100px 150px;
        margin-top: 20px;
        border: 1px solid black;
    }
    .value{
        display: flex;
        flex-direction: row;
        margin-bottom: 40px;
        align-items: center;
    }
    .valeu-intro{
        margin-left: 30px;
        border: 1px solid black;
        
    }

    /*profile-section*/
    .profile-section{
        display: flex;
        flex-direction: row;
        justify-content: center;
        align-items: flex-start;

        margin: 100px 100px;
    }
    #profile-picture{
        width: 400px;
        margin-right: 60px;
    }
    .introduction>h2{
        margin:0px;
        font-size: 35px;
    }
    .introduction>p{
        font-weight: 300;
        font-size: 16px;
        margin-bottom: 40px;
    }
    #cap{
        font-weight: 400;
        font-size: 20px;
        font-style: italic;
        line-height: 30px;
    }
    .btn{
        align-items: center;
        text-align: center;
    }
</style>