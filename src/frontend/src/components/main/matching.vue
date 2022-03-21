<template>
  <div class="carematch_content">
    <div class="care_list">
      <div class="div_list">
        <div class="search">
          <div class="text01" id="area_text">
            <span class="exam01">지역을 선택해주세요</span>
          </div>
          <div class="text01" id="category_text">
            <span class="exam01">카테고리를 선택해주세요</span>
          </div>
          <div class="text01" id="data_text">
            <span class="exam01">날짜를 선택해주세요</span>
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
.choose{
  text-align: center;
  font-size: 35px;
}
.carematch_content{
  width: 100%;
  position: relative;
}
.care_list{
  width: 50%;
  max-width: 50%;
  min-height: 50px;
  background-color: #ffffff;
  position: relative;
  margin: 0 auto;
  border-left: 1px solid #dedede;
  border-right: 1px solid #DEDEDE;
}
.div_list{
  height: 200px;
}
  .carematch_content .care_list .div_list .search{
    display: inline-block;  
    position: relative;
    width: 100%;
    height:100%;
    border: 1px solid #DEDEDE;
    border-radius: 5px;
    background-color: #ffffff;
    margin: 0 0 20px 0;
  }
  .search .text01{
    padding: 20px 20px 20px 40px;
    font-size: 1.1em;
    letter-spacing: 0;
    font-weight: 300;
  }
  .carematch_content .care_list .div_list .search #area_text,#category_text{
    border-bottom: 1px solid #DEDEDE;
  }
  
</style>