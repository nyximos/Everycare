<template>
    
<div class="container input-group m-5">
 <input
      class="form-control"
      type="text" 
      v-model="searchText"
      placeholder="Search"
      @keyup.enter="searchTodo">
    <span class="input-group-text" id="basic-addon2">🔍</span>
    </div>

</template>

<script>
export default {

}
</script>

<style>

</style>