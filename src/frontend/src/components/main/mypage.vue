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
    <router-link :to="{name:'memberSchedule', params:{caresitterId:this.id}}"><p>스케줄</p></router-link>
      <router-link :to="{name:'caresitter_contract', params:{caresitterId:this.id}}"><p>계약서조회</p></router-link><br>
      <router-link :to="{name:'mypage_joblist'}"><p>구인글 목록 조회</p></router-link><br>

      <router-link :to="{name:'carenote'}"><p>케어노트(시터용)</p></router-link><br>
      <router-link :to="{name:'membernote'}"><p>케어노트(멤버용)</p></router-link>
      <router-link :to="{name:'complete_contract'}"><p>완료된 계약서</p></router-link>
      <router-link :to="{name:'MyStore',params:{contentId:memid}}"><p>내 스토어</p></router-link>
      <router-link :to="{name:'MyStore',params:{contentId:memid}}"><p>상품 주문 내역</p></router-link>
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