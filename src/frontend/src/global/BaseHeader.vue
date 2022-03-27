<template>
    <div class="header">
        <div class="logo" @click="goMain()">logo</div>
        <div class="list">
            <v-btn text>케어매칭</v-btn>
            <v-btn text>케어노트</v-btn>
            <v-btn text>케어스토어</v-btn>
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
                console.log('go main');
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
    color: black;
    background: #69f0ae;
    align-items: center;
}
.logo {
    padding-left: 10px;
    cursor: pointer;
}
.list {
    display: flex;
    align-items: center;
}
</style>
