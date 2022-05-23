<template>
    <header>
        <div class="header">
            <div class="logo" @click="goMain()">logo</div>
            <div class="list">
                <v-btn text>케어매칭</v-btn>
                <v-btn text>케어노트</v-btn>
                <v-btn text to="/stores">케어스토어</v-btn>
            </div>
            <div>
                <div>
                    <div v-if="this.$store.state.userStore.id !== ''">
                    <v-btn text v-on:click.native="signout()" to="/">로그아웃</v-btn>
                    <v-btn text v-on:click.native="mypage()" to="/mypage">{{this.$store.state.userStore.id}}님</v-btn>
                    </div>
                    <div v-else>
                    <v-btn text to="/login">로그인</v-btn>
                    <v-btn text to="/signup">회원가입</v-btn>
                    </div>
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
            }
        },
        signout() {
            this.$store.commit('userStore/clearUsername');
            $.ajax({
                url: '/api/token',
                type: 'DELETE',
                cache: false,
                contentType: false,
                processData: false,
                async: false
            }),
            location.href = '/';
        },
        // id, 이름, 권한 응답을 넣어준다
        log() {
            this.$http
                .get('/api/user', {
                    withCredentials: true
                })
                .then(res => {
                    // state에 저장
                    console.log(this.$store.state.userStore.id);
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }
}
    
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
