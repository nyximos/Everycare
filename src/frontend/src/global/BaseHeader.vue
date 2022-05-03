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

                <v-btn text @click="log()">클릭</v-btn>
                <router-link to="/login">로그인</router-link>
                <router-link to="/signup">회원가입</router-link>

            </div>
        </div>
    </header>
</template>

<script>
export default {
    computed: {},
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
            this.$stroe.commit('userStore2/clearUsername');
            this.$router.push('/');
        },
        log() {
            this.$http
                .get('/api/user', {
                    withCredentials: true
                })
                .then(res => {
                    console.log(res.data);
                })
                .catch(err => {
                    console.log(err);
                });
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
