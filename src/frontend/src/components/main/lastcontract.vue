<template>
  <div>

  </div>
</template>

<script>
export default {
    data(){
        return{
        }
    },
    mounted(){
        this.$http
        .get(`/api/dashboard/contracts/payments?&contractId=${id}`,{
            withCredentials:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.contract = res.data.body; 
        }).catch(err=>{
            console.log(err);
        })
    }
}
</script>

<style>

</style>