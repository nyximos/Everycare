<template>
    <v-container fluid class="main">
        <componentpage/>
        <Main />
        <ul>
            <li class="menu"><router-link to="/Caresitters">케어매칭</router-link></li>
            <li><router-link to="/Profile">케어프로필</router-link></li>
            <li><router-link to="/Careprofile">케어시터프로필 등록</router-link></li>
            <li><router-link to="/Carepeople">케어대상인 조회</router-link></li>
            <li><router-link to="/Alarm">알림</router-link></li>
            <li><router-link to="/Profile2">프로필상세</router-link></li>
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
