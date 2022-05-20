<template>
<div>
<div class="black-bg" v-if="모달창열렸니 == true">
      <div class="white-bg">
        <h4>상세페이지</h4>
      </div>
</div>
  <div class="search">
    <form name="search_form" method="">
      <div class="text01" id="area_text">
        <span class="exam01"  @click="모달창열렸니 = true">지역을 선택하세요.</span>
      </div>
      <div class="text01" id="category_text">
        <span class="exam01">카테고리를 선택해주세요.</span>
      </div>
      <div class="text01" id="date_text">
        <span class="exam01">날짜를 선택해주세요.</span>
      </div>
      
    </form>
    
    <!-- <div v-show="is_show">
        <h5>지역선택</h5>
        <select>
          <option>서울</option>
          <option>대구</option>
          <option>울산</option>
        </select>
      </div> -->
      
  </div>
  </div>
</template>

<script>

export default {
 
  data(){
    return{
     모달창열렸니:false,
    }
  },
  methods:{
    
  }
}
</script>

<style scoped>
body{
  margin: 0;
}
div{
  box-sizing: border-box;
}
.black-bg{
  width: 50%;
  height: 50%;
  background: rgba(0, 0,0, 0.5);
  position: fixed;
  padding: 20px;
}
.white-bg{
  width: 100%;
  background: white;
  border-radius: 8px;
  padding: 20px;
}
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