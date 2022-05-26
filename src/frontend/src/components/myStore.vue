<template>
    <div>
        hi{{id}}
    </div>
</template>

<script>
export default {
    mounted() {
        this.$http.get('/api/dashboard/store/products',{
        withCredentials:true
      })
		.then((res)=>{
        console.log(res.data);
        // this.applications=res.data.body
      }).catch(err =>{
		console.log(err);
	})
    },
data(){
    return{
        id: this.$route.params.contentId
    }
}
}
</script>

<style>

</style>