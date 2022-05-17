<template>
  <div>
      <h2>마이페이지</h2>
      
      <router-link :to="{name:'certification',params:{carsitterId:this.id}}"><p>자격증 등록</p></router-link>
      <router-link :to="{name:'careprofilecreate'}"><p>케어 프로필 생성</p></router-link>
      <!-- <router-link :to="{name:'addprofile2' , params:{caresitterId: .id}}"><p>프로필 조회</p></router-link> -->
      <!-- <p @click="sitter">프로필 보기</p> -->
  </div>
</template>

<script>
export default {
    name:'profile',
    mounted(){
        const id = this.$route.params.caresitterId;
        console.log(id)
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
        //     // this.caresitterId = res.data.body.id
        // }).catch(err=>{
        //     console.log(err);
        // })
    },
    
    data(){
        return{
            id:this.route.params.caresitterId,
        }
    },
    methods:{
        // sitter(){
        //     // const id = this.id;
        //     this.$router.push({name:'caresitterprofile' ,params:{caresitterId : this.id}})
        // }
    }
}
</script>

<style>

</style>