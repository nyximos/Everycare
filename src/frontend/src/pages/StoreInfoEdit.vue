<template>
<div>
<div class="row">
	<Navbar />
</div>
<div class="row">
<div class="col-2"><Sidebar /></div>
<div class="col-10"><Storesubmit /></div>
</div>
</div>

</template>

<script>
import Navbar from '@/components/navbar.vue';
import Sidebar from '@/components/sidebar.vue';
import Storesubmit from '@/components/storesubmit.vue';
export default {
components:{
	Navbar,
	Sidebar,
	Storesubmit
}
}
</script>

<style>

</style>