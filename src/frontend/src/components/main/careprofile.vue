<template>
  <div class="content">
      <div class="join">   
          <div class="div_join">
              <div class="rwrite">
                  <form method="post" name="/" id="/"
                  enctype="multipart/form-data">
                    <div class="rwrite_div" id="rwite_05">
                        <div class="page_btn">
                            <span class="page_btn1 on"></span>
                            <span class="page_btn2"></span>
                            <span class="page_btn3"></span>
                        </div>
                        <div class="con1">
                            <h2>자기소개서</h2>
                        </div>
                        <div class="area_profile">
                            <label for="input_file">
                                <img src="" alt="사진없음">
                            </label>
                        </div>
                        <div class="div_text">
                            <strong>사진</strong>을 등록하세요.
                        </div>
                        <div>
    <v-text-field label="name" :rules="rules" hide-details="auto"></v-text-field>
    <v-textarea
      autocomplete="email"
      label="Intro"
    ></v-textarea>
    <v-btn depressed>
      Normal
    </v-btn>
    <button>2</button>
  </div>
                    </div>
                  </form>
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
.content{
    width: 100%;
    min-height: 800px;
    overflow: hidden;
}
.content .join{
    width:100%;
    max-width: 670px;
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
    box-shadow: 0 0  18px #d3d3d3;
    border-radius: 10px;
    padding: 40px 10px 40px 10px;
}
.rwrite{
    margin: 0;
    padding: 0;
    border: 0;
    outline: 0;
    font-size: 100%;
}
form{
    display: block;
    margin-top: 0em;;
}
.rwrite_div{
    width: 100%;
    position: relative;
    padding: 0 0 10px 0;
    overflow: hidden;
}
.rwrite .page_btn{
    width: 100%;
    position: relative;
    height: 25px;
    text-align: center;
}

.rwrite .page_btn span{
    display: inline-block;
    border-radius: 17px;
    box-sizing: border-box;
    width: 17px;
    height: 17px;
    font-size: 0;
    line-height: 0;
    margin: 0 4px 0 4px;
}
</style>