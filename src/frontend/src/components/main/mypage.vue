<template>
  <div>
      <h2>마이페이지</h2>
      <router-link to="/certification"><p>자격증 등록</p></router-link>
  </div>
</template>

<script>
export default {
    mounted(){
        const id = Number(this.$route.params.id);
        // this.$http
        // .get(`/api/caresitters`,{
        //     withCredentials:true
        // })
        // .then((res)=>{
        //     console.log(res.data);
        //     this.result = res.data.body
        // }).catch(err=>{
        //     console.log(err);
        // })

        this.$http
        .get(`/api/caresitters/${id}`,{
            withCredentials:true
        })
        .then((res)=>{
            console.log(res.data);
            this.result = res.data.body
        }).catch(err=>{
            console.log(err);
        })
    },
    data(){
        return{
            result:[],
            id:this.$route.params.id
        }
    }
}
</script>

<style>

</style>