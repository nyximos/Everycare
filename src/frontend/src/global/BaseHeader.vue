<template>
    <header>
        <div class="header">
            <div class="logo" @click="goMain()">logo</div>
            <div class="list">
                <v-btn text>케어매칭</v-btn>
                <v-btn text>케어노트</v-btn>
                <v-btn text>케어스토어</v-btn>
            </div>
            <div>
                <div v-if="cookie">
                    <router-link to="/" @click="logoutUser">로그아웃</router-link>
                </div>
                <div v-else>
                    <router-link to="/login">로그인</router-link>
                    <router-link to="/signup">회원가입</router-link>
                </div>
            </div>
        </div>
    </header>
</template>

<script>
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
                logoutUser() {
                    const delCookie = function delCookie_by_name(name) {
                        let date = new Date();
                        date.setDate(date.getDate() - 100);
                        let Cookie = `${name}=;Expires=${date.toUTCString()}`;
                        document.cookie = Cookie;
                    };
                    delCookie('Authorization');
                }
            }
        };
    },
    computed: {
        cookie() {
            return document.cookie;
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
