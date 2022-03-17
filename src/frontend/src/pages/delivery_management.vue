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
	<Delivery />
</div>
</div>
</div>
</template>

<script>
import Navbar from '@/components/navbar.vue';
import Sidebar from '@/components/sidebar.vue';
import Delivery from '@/components/delivery_management.vue';

export default {
components:{
	Navbar,
	Sidebar,
	Delivery
}
}
</script>

<style>

</style>