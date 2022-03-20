<template>
    <div class="header">
        <div class="logo" @click="goMain()">logo</div>
        <div class="list">
            <v-btn text color="white">케어매칭</v-btn>
            <v-btn text color="white">케어노트</v-btn>
            <v-btn text color="white">케어스토어</v-btn>
        </div>
        <div>
            <v-btn text>로그인</v-btn>
            <v-btn text>회원가입</v-btn>
        </div>
    </div>
</template>

<script>
export default {
    methods: {
        goMain() {
            if (this.$router.history.current.fullPath == '/') {
                console.log('dsdf');
            } else {
                this.$router.push('/');
                console.log('/');
            }
        }
    }
};
</script>
<style scoped>
.header {
    display: flex;
    width: 100%;
    justify-content: space-between;
    height: 50px;
    color: white;
    background: #ec7676;
    align-items: center;
}
.logo {
    padding: 0 10;
    cursor: pointer;
}
.list {
    display: flex;
    align-items: center;
}
</style>
