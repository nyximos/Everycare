<template>
  <div>
      <h2>완료된 계약서</h2>
  </div>
</template>

<script>
export default {
    data(){
        return{
            contrat:[]
        }
    },
    mounted(){
        this.$http
        .get('/api/carenote/contracts/completion',{
            withCredential : true
        })
        .then((res) => {
            console.log(res.data.body)
            this.contract = res.data.body;

        }).catch((err) => {
            console.log(err) 
        });
    }
}
</script>

<style>

</style>