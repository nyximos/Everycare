<template>
  
</template>

<script>
export default {
    data(){
        return{

        }
    },
    mounted(){
        this.$http
        .get(`/api/member/caretargets/${caretargetId}/contracts/complition/${contractId}/carenotes/${id}`,{
            withCredential:true
        }).then((res)=>{
            console.log(res)
        }).catch((err)=>{
            console.log(err)
        })
    }
}
</script>

<style>

</style>