<template>
<div class="container mt-5">
<div class="row">
<div class="col-4">
    <img src="https://github.com/mdo.png" class="img-fluid rounded" alt="...">
</div>
<div class="col-8">
     <div class="row">
      <div class="col-3 text-center mt-2">상품명</div>
      <div class="col-5"><input type="text" class="form-control"></div>
    </div>
    <div class="row mt-3">
      <div class="col-3 text-center mt-2">가격</div>
      <div class="col-5"><input type="number" class="form-control"></div>
    </div>
    <div class="row mt-3">
      <div class="col-3 text-center mt-2">재고수량</div>
    <div class="col-5"><input type="number" class="form-control"></div>
    </div>
    <div class="row mt-3">
      <div class="col-3 text-center mt-2">이미지</div>
      <div class="col-5"><input type="file" class="form-control"></div>
    </div>
    <div class="row mt-3">
      <div class="col-3 text-center mt-2">설명</div>
      <div class="col-5">
          <div class="form-floating">
            <textarea class="form-control" id="floatingTextarea2" style="height: 100px"></textarea>
        </div>
      </div>
    </div>
    <div class="row mt-3">
      <div class="col-3 text-center mt-2">판매여부</div>
    <div class="col-5">
        <div class="form-floating">
            <select class="form-select" id="floatingSelect" aria-label="Floating label select example">
                <option selected>판매</option>
                <option value="1">품절</option>
                <option value="2">입고예정</option>
            </select>
        </div>
    </div>
    </div> 
    <div class="row mt-3">
        <div class="d-grid col-3 mx-auto mt-5">
  <button class="btn btn-primary" type="button">등록</button>
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

</style>