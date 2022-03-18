<template>
<div>
<div class="row">
	<Navbar />
</div>
<div class="row">
<div class="col-2"><Sidebar /></div>
<div class="col-10">
	<Seminavbar />
	<ProdLookup />
	</div>
</div>
</div>

</template>

<script>
import Navbar from '@/components/navbar.vue';
import Sidebar from '@/components/sidebar.vue';
import Seminavbar from '@/components/Semi_navbar.vue';
import ProdLookup from '@/components/ProdLookup.vue';

export default {
components:{
	Navbar,
	Sidebar,
	Seminavbar,
	ProdLookup
}
}
</script>

<style>

</style>