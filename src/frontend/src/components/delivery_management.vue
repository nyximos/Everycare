<template>
<div class="container" id="content">
    <div class="card mt-5 row">
  <div class="card-header">
    배송 관리
  </div>
  <div class="card-body">
    <div class="row">
      <div class="col-2 mt-2">주문번호</div>
      <div class="col-5"><Search /></div>
    </div>
  </div>
</div>
<div v-if="!aaa" class="row mt-5">
  <div class="button-group">
  <button class="btn btn-outline-secondary col-2">입금완료</button>
  <button class="btn btn-outline-secondary col-2">배송대기</button>
  <button class="btn btn-outline-secondary col-2">상품발송</button>
  <button class="btn btn-outline-secondary col-2">주문취소</button>
  </div>
  <table class="table table-hover text-center mt-2">
  <thead>
    <tr>
      <th scope="col">상품ID</th>
      <th scope="col">주문번호</th>
      <th scope="col">상품명</th>
      <th scope="col">주문자</th>
      <th scope="col">상태</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">
      <input type="checkbox" name="" id=""> 
      3342057</th>
      <td>234546</td>
      <td>튼튼 휠체어</td>
      <td>김영화</td>
      <td>-</td>

    </tr>
  </tbody>
</table>
</div>

</div>
</template>

<script>
import Search from '@/components/Search.vue';
export default {
components:{
    Search
}
}
</script>

<style>

</style>