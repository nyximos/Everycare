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
                <Userinfo />
            </div>
        </div>
    </div>
</template>

<script>
import Navbar from '@/components/navbar.vue';
import Sidebar from '@/components/sidebar.vue';
import Userinfo from '@/components/userinfo.vue';
export default {
    components: { Userinfo, Navbar, Sidebar }
};
</script>

<style></style>
