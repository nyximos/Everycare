<template>
<div class="text-center">
  <h1><v-icon color="green" class="mt-10" large> mdi-checkbox-marked-circle</v-icon></h1>
<h1 class="fw-bold mt-10" >결제가 완료되었습니다</h1>
  <v-btn outlined @click="main" class="mt-10" color="grey">메인으로</v-btn>
</div>
  
</template>

<script>
export default {
  data() {
    return {
      
    }
  },
  methods:{
    main(){
      this.$router.push({
      path: '/',
      })
    }
  }
}
</script>