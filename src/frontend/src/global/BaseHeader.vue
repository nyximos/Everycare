<template>
<div class="navbar">
    <v-app-bar max-height="50" color="blue">
      <ul>
          <li class="menu"><router-link to="/match">케어매칭</router-link></li>
          <li><router-link to="/Profile">케어노트</router-link></li>
          <li><router-link to="/">케어스토어</router-link></li>
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
