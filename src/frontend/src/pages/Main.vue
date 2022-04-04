<template>
    <v-container fluid class="main">
        <Main />
        <ul>
            <li><router-link to="/dashboard/caresitter">케어시터프로필 등록</router-link></li>
            <li><router-link to="/Carepeople">케어대상인 조회</router-link></li>
            <li><router-link to="/Registration">카드1</router-link></li>
            <li><router-link to="/Picture">카드2</router-link></li>
        </ul>
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
