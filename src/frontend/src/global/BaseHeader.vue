<template>
    <header>
        <div class="header">
            <div class="logo" @click="goMain()">logo</div>
            <div class="list">
                <v-btn text>케어매칭</v-btn>
                <v-btn text>케어노트</v-btn>
                <v-btn text>케어스토어</v-btn>
                <a @click="testCookie">쿠키</a>
            </div>
            <div>
                <div>
                    <v-btn text v-on:click.native="signout()" to="/">로그아웃</v-btn>
                    <v-btn text to="/login">로그인</v-btn>
                    <v-btn text to="/signup">회원가입</v-btn>
                </div>
            </div>
        </div>
    </header>
</template>

<script>
import $ from 'jquery';

export default {

    methods: {
        goMain() {
            if (this.$router.history.current.fullPath == '/') {
                console.log('go main');
            } else {
                this.$router.push('/');
                console.log('/');
            }
        },
        signout() {
            $.ajax({
                url: '/api/token',
                type: 'DELETE',
                cache: false,
                contentType: false,
                processData: false,
                async: false
            }),
                (location.href = '/');
        },
        testCookie(){
            console.log(this.$cookies);
            const cookie = this.$cookies.get('Authorization');
            console.log(cookie);
        }
    },
};
</script>
<style scoped>
.header {
    display: flex;
    width: 100%;
    justify-content: space-between;
    height: 50px;
    color: rgb(255, 255, 255);
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
