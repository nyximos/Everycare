<template>
    <v-container fluid class="main">
        <Main />
    </v-container>
</template>
<script>
import Main from '@/components/main/index';
export default {
    components: {
        Main
    }
};
</script>
<style scoped lang="scss">
.main {
    min-height: 100vh;
}
</style>
