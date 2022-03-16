<template>
  <div>
    <div class="choose">
      <span>케어시터</span>
      <span>일자리</span>
    </div>
  <div class="search">
    <form name="search_form" method="GET">
      <input type="hidden" name="area1_search" id="psido" value="">
    </form>
    <div class="text01" id="area_text">
      <span class="exam01">지역을 선택하세요</span>
    </div>
    <div class="text01" id="category_text">
      <span class="exam02">카테고리를 선택해주세요</span>
    </div>
    <div class="text01" id="day_text">
      <span class="exam03">날짜를 선택해주세요</span>
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
  .search{
    margin: auto;
    display: flex;  
    position: relative;
    display: inline-block;
    border: 1px solid #DEDEDE;
    border-radius: 5px;
    background-color: #ffffff;
    width: 70%;
    text-align: center;
    margin: 0 0 20px 0;
  }
  .search .text01{
    padding: 20px 20px 20px 40px;
    font-size: 1.1em;
    letter-spacing: 0;
    font-weight: 300;
  }
</style>