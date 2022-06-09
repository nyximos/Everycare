<template>
<div>
        <componentpage />
        <ul>
            <li><router-link to="/Carepeople">케어대상인 조회</router-link></li>
            <li><router-link to="/dashboard">마이페이지</router-link></li>
            <li><router-link to="/CaretargetsDetail">대상인상세조회</router-link></li>
            <p>{{ this.$store.state.userStore.careSitterId }}</p>
        </ul>
        </div>
</template>
<script>
import componentpage from '@/components/main.vue';
export default {
    components: {
        componentpage,
    },
};
</script>
<style scoped lang="scss">
.main {
    min-height: 100vh;
}
</style>
