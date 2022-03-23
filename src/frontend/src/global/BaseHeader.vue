<template>
<div class="navbar">
    <v-app-bar max-height="50" color="blue">
      <ul>
          <li class="menu"><router-link to="/match">케어매칭</router-link></li>
          <li><router-link to="/Profile">케어프로필</router-link></li>
          <li><router-link to="/Careprofile">1</router-link></li>
          <li><router-link to="/Carepeople">케어대상인 조회</router-link></li>
          <li><router-link to="/Schedule">스케줄/상태</router-link></li>
      </ul>
    </v-app-bar>
    
</div>    
</template>
<script>
export default {};
</script>
<style>
    .menu{
        color: black;
        display: inline;
    }
</style>
