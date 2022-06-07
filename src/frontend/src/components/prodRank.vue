<template>
<div class="row">
	<p class="fs-2 mt-5 fw-bold">Top 3</p>
    <div class="ranking col" v-for="item in storeList.slice(0,3)" :key="item.index">
	<img :src="'https://localhost:8086/api/images/'+item.storeFileName" 
  class="rounded-circle mx-auto d-block" width="140" 
  height="140">
        <div class="text-center">
		<h3 class="mt-2">{{item.name}}</h3>
<button class="btn btn-secondary" @click="detail(item)">View details &raquo;</button>
		</div>
      </div>
</div>  
</template>

<script>
export default {
props:{
    storeList:{
        required:true
    }
},
methods:{
   detail(item){
      this.$emit('detail',item.id);
  }
 }
}
</script>

<style>

</style>