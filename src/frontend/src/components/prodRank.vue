<template>
<v-row>
	<p class="fs-4 mt-10 ml-15">HOT LIST</p>
    <div class="ranking col" v-for="item in storeRank" :key="item.index">
	<img :src="'https://localhost:8086/api/images/'+item.storeFileName" 
  class="rounded-circle mx-auto d-block" width="140" 
  height="140">
        <div class="text-center">
		<b class="mt-2">{{item.name}}</b><br>
<button class="btn btn-secondary" @click="detail(item)">View details &raquo;</button>
		</div>
      </div>
</v-row>  

</template>

<script>
export default {
props:{
    storeRank:{
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