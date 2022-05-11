<template>
  <div>
      <h2>마이페이지</h2>
      <router-link :to="{name:'certification',params:{carsitterId:this.id}}"><p>자격증 등록</p></router-link>
  </div>
</template>

<script>
export default {
    mounted(){
        // const id = this.$route.params.caresitterId;
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

        // this.$http
        // .get(`/api/caresitters/${id}`,{
        //     withCredentials:true
        // })
        // .then((res)=>{
        //     console.log(res.data);
        //     this.result = res.data.body
        // }).catch(err=>{
        //     console.log(err);
        // })
    },
    data(){
        return{
            result:[],
            id:this.$route.params.caresitterId,
        }
    }
}
</script>

<style>

</style>