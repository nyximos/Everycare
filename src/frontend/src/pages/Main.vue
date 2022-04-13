<template>
    <v-container fluid class="main">
        <componentindex />
        <componentmain />
        <ul>
            <li><router-link to="/dashboard/Careprofile">케어시터프로필 등록</router-link></li>
            <li><router-link to="/Carepeople">케어대상인 조회</router-link></li>
            <li><router-link to="/Profile2">프로필상세</router-link></li>
        </ul>
    </v-container>
</template>
<script>
import componentmain from '@/components/main'
import componentindex from '@/components/main/index';
export default {
    components: {
        componentindex,
        componentmain
    }
};
</script>
<style scoped lang="scss">
.main {
    min-height: 100vh;
}
</style>
