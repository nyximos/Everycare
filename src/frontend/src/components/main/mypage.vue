<template>
  <div>
      <h2>마이페이지</h2>
      <router-link :to="{name:'certification',params:{caresitterId:this.id}}"><p>자격증 등록</p></router-link>
      <div v-if="this.id=='' ">
         <router-link :to="{name:'careprofilecreate'}"><p>케어 프로필 생성</p></router-link>
      </div>
      <div v-else>
          <router-link to=""><p>프로필 조회</p></router-link>
      </div>
      <router-link :to="{name:'#'}"><p>케어 자격증 조회</p></router-link>
      <!-- <router-link :to="{name:'addprofile2' , params:{caresitterId: .id}}"><p>프로필 조회</p></router-link> -->
      <!-- <p @click="sitter">프로필 보기</p> -->
  </div>
</template>

<script>
export default {
    name:'profile',
    mounted(){
            const id = this.id;
            console.log(id)
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
            id:this.$store.state.userStore.careSitterId,
            result:[],
        }
    },
    
}
</script>

<style>

</style>