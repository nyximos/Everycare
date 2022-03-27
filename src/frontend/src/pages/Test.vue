<template>
    <v-container fluid class="main">
        <Test />
    </v-container>
</template>
<script>
import Test from '@/components/test.vue';
export default {
    components: {
        Test
    }
};
</script>
<style scoped lang="scss">
.main {
    min-height: 100vh;
    background: #f8f8f8;
}
</style>
