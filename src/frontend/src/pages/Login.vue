<template>
    <v-container fluid class="main">
        <Login />
    </v-container>
</template>
<script>
import Login from '@/components/login.vue';
export default {
    components: {
        Login
    }
};
</script>
<style scoped lang="scss">
.main {
    min-height: 100vh;
    background: #f8f8f8;
}
</style>
