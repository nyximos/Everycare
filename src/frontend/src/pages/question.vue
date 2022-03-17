<template>
<div>
<div class="row">
	<Navbar />
</div>
<div class="row">
<div class="col-2">
	<Sidebar />
</div>
<div class="col-10">
<Question />
</div>
</div>
</div>
</template>

<script>
import Navbar from '@/components/navbar.vue';
import Sidebar from '@/components/sidebar.vue';
import Question from '@/components/question.vue';

export default {
components:{
	Navbar,
	Sidebar,
	Question
	
}
}
</script>

<style>

</style>