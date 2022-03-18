<template>
<div>
<div class="row">
	<Navbar />
</div>
<div class="row">
<div class="col-2"><Sidebar /></div>
<div class="col-10">
	<Seminavbar />
	<ProdDelete />
	</div>
</div>
</div>

</template>

<script>
import Navbar from '@/components/navbar.vue';
import Sidebar from '@/components/sidebar.vue';
import Seminavbar from '@/components/Semi_navbar.vue';
import ProdDelete from '@/components/ProdDelete.vue';

export default {
components:{
	Navbar,
	Sidebar,
	Seminavbar,
	ProdDelete
}
}
</script>

<style>

</style>