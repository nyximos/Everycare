<template>
    <div>
      <v-data-table
        :headers="headers"
        :items="desserts"
        :items-per-page="5"
        class="elevation-1"
      ></v-data-table>
    </div>
</template>

<script>
export default {
mounted() {
    this.$http.get('/api/admin/stores',{
        withCredentials:true
      })
		.then((res)=>{
        console.log(res.data);
        this.applications=res.data.body
      }).catch(err =>{
		console.log(err);
	})
},
data(){
    return{
        applications:[]
    }
}
}
</script>

<style>

</style>