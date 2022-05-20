<template>
  <div>
      <ul>
          <!-- <li>{{contract.id}}</li> -->
      </ul>
  </div>
</template>

<script>
export default {
    date(){
        return{
            // contract:[

            // ]
        }
    },
    mounted(){
        const id = this.$route.params.contractId;
        console.log(id)
        this.$http
        .get(`/api/dashboard/caresitter/contracts/${id}`,{
            withCredentials:true
        })
        .then((res)=>{
            console.log(res.data.body);
            // this.contract = res.data.body
        }).catch(err=>{
            console.log(err);
        })
    }
}
</script>

<style>

</style>