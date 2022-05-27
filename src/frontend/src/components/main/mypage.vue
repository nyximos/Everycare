<template>
  <div>
      <h2>마이페이지</h2>
      
      <router-link :to="{name:'certification',params:{caresitterId:this.id}}"><p>자격증 등록</p></router-link>
      <router-link :to="{name:'mypage_certi' ,params:{caresitterId:this.id}}"><p>케어 자격증 조회</p></router-link>
      <div v-if="this.id=='' ">
         <router-link :to="{name:'careprofilecreate'}"><p>케어 프로필 생성</p></router-link>
      </div>
      <div v-else>
          <router-link :to="{name:'mypage_profile' ,params:{caresitterId:this.id}}"><p>프로필 조회</p></router-link>
      </div>
      <!-- <router-link :to="{name:'addprofile2' , params:{caresitterId: .id}}"><p>프로필 조회</p></router-link> -->
      <!-- <p @click="sitter">프로필 보기</p> -->
      <router-link :to="{name:'caresitter_contract', params:{caresitterId:this.id}}">계약서조회</router-link><br>
      <router-link :to="{name:'mypage_joblist'}">구인글 목록 조회</router-link>
  </div>
</template>

<script>
export default {
    
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
