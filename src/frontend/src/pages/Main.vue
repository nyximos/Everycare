<template>
    <v-container fluid class="main">
        <componentpage/>
        <Main />
        <ul>
            <li><router-link to="/dashboard/Careprofile">케어시터프로필 등록</router-link></li>
            <li><router-link to="/Carepeople">케어대상인 조회</router-link></li>
            <li><router-link to="/Profile2">프로필상세</router-link></li>
            <li><router-link to="/Caresitterprofile_delete">프로필상세수정삭제</router-link></li>
        </ul>
    </v-container>
</template>
<script>
import componentpage from '@/components/main.vue'
import Main from '@/components/main/index';
export default {
    components: {
        Main,
        componentpage
    }
};
</script>
<style scoped lang="scss">
.main {
    min-height: 100vh;
}
</style>
