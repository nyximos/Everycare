<template>
    <div>
        orderList
    </div>
</template>

<script>
export default {
    mounted() {
        this.$http.get('/api/dashboard/orders/payment-history',{
        withCredentials:true
      })
		.then((res)=>{
        console.log(res.data);
        
      }).catch(err =>{
				console.log(err);
			})
    },
}
</script>

<style>

</style>
