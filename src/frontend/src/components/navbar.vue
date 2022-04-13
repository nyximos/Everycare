<template>
	<!--Navbar-->
<nav class="navbar navbar-expand-md navbar-dark bg-success">
  <div class="container-fluid">
    <p class="display-5 fw-bold text-white" href="#">Everycare</p>
    
      <ul class="navbar-nav nav justify-content-end">
        <li class="nav-item">
          <router-link to="/caresitters">케어매칭</router-link>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">케어노트</a>
        </li>
        <li class="nav-item">
          <a class="nav-link">케어스토어</a>
        </li>
      </ul>
    </div>
  
</nav>

</template>

<script>
export default {

}
</script>

<style>
nav {
	width: 100%;
	height:70px;

}


</style>