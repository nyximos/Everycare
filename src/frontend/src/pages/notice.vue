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
<Notice />
</div>
</div>
</div>
</template>

<script>
import Navbar from '@/components/navbar.vue';
import Sidebar from '@/components/sidebar.vue';
import Notice from '@/components/notice.vue';

export default {
components:{
	Navbar,
	Sidebar,
	Notice
	
}
}
</script>

<style>

</style>