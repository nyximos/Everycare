<template>
    <header>
        <div class="header">
            <button class="logo" @click="goMain">logo</button>
            <div class="list">
                <v-btn text>케어매칭</v-btn>
                <v-btn text>케어노트</v-btn>
                <v-btn text>케어스토어</v-btn>
            </div>
            <div>
                <router-link to="/login">로그인</router-link>
                <router-link to="/signup">회원가입</router-link>
                <router-link to="/">로그아웃</router-link>
            </div>
        </div>
    </header>
</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
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
                    axios({
                        url: '/api/token',
                        type: 'DELETE',
                        cache: false,
                        contentType: false,
                        processData: false,
                        async: false
                    }),
                    location.href = '/';
                }
            }
        };
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
