<template>
  <div>
      <h2>마이페이지</h2>
      <div v-if="this.$store.state.userStore.careSitterId  !==null">
        <router-link :to="{name:'certification',params:{caresitterId:this.id}}"><p>자격증 등록</p></router-link>
        <router-link :to="{name:'mypage_certi' ,params:{caresitterId:this.id}}"><p>케어 자격증 조회</p></router-link>
          <router-link :to="{name:'mypage_profile' ,params:{caresitterId:this.id}}"><p>프로필 조회</p></router-link>
      </div>
      <!-- <router-link :to="{name:'addprofile2' , params:{caresitterId: .id}}"><p>프로필 조회</p></router-link> -->
      <!-- <p @click="sitter">프로필 보기</p> -->
      <div>
      <router-link :to="{name:'caresitter_contract', params:{caresitterId:this.id}}">계약서조회</router-link>
     </div>
     <div>
               <router-link :to="{name:'memberSchedule', params:{caresitterId:this.id}}">스케줄</router-link>
      </div>
      <div>
               <router-link :to="{name:'mypage_joblist', params:{caresitterId:this.id}}">내 구인글 목록</router-link>
      </div>
  </div>
</template>

<script>
export default {
    
    data(){
        return{
            id:this.$store.state.userStore.careSitterId,
            memid:this.$store.state.userStore.id,
            result:[],
        }
    },
    
}
</script>

<style>

</style>