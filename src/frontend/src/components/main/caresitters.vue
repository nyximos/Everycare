<template>
  <div class="search">
    <form name="search_form" method="">
      <div class="text01" id="area_text">
        <span class="exam01">지역을 선택하세요.</span>
      </div>
      <div class="text01" id="category_text">
        <span class="exam01">카테고리를 선택해주세요.</span>
      </div>
      <div class="text01" id="date_text">
        <span class="exam01">날짜를 선택해주세요.</span>
      </div>

    </form>
  </div>
</template>

<script>
export default {
}
</script>

<style>
.search{
  position: relative;
  display: inline-block;
  width: 100%;
  border: 1px solid #dedede;
  border-radius: 5px;
  background-color: #ffffff;
  margin: 0 0 20px 0;
}
.search form{
  margin: 0;
  padding: 0;
  border: 0;
  outline: 0;
  font-size: 100%;
}
.search #area_text{
  border-bottom: 1px solid #eaeaea;
}
.search .text01{
  padding: 10px 20px 10px 20px;
  font-size: 1.2em;
  letter-spacing: 0;
  font-weight: 300;
}
.search #category_text{
  border-bottom: 1px solid #eaeaea;
}
</style>