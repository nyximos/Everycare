<template>
    <div class="login-component">
        <div class="login-box">
            <div class="login-title"><span class="login-title-L">L</span>ogin{{this.$store.state.userStore.id}}</div>
            <div>
                <div class="login-input-botton">
                    <v-text-field v-model="Id" label="Id" placeholder="Placeholder" dense outlined></v-text-field>
                    
                    <v-text-field v-model="Password" type="password" label="Password" placeholder="Placeholder" dense outlined></v-text-field>
                </div>
                <div><v-btn @click="login" color="accent" elevation="2" class="login-button">Login</v-btn></div>
            </div>
        </div>
    </div>
</template>
<script>
import { loginUser } from '@/api/core/index';

export default {
    data() {
        return {
            Id:'',
            Password:''
        };
    },
    components: {
        // Main
    },
    // mounted() {
    //     if (sessionStorage.getItem('isLogin') == 'true') {
    //         this.$router.push('/');
    //     }
    // },
    methods: {
        async login() {
            const userdata = {
                username: this.Id,
                password: this.Password
            };
            const response = await loginUser(userdata);
            console.log(response);
            this.$store.commit('userStore2/sgnIn', userdata);
           // this.logMessage = `${config.data.username}님`;
            this.$router.push('/');

            if (this.Id === '') {
                alert('아이디를 입력하세요.');
                return;
            } else if (this.Password === '') {
                alert('비밀번호를 입력하세요.');
                return;
            }
        }
    }
};
</script>

<style scoped lang="scss">
.main {
    min-height: 100vh;
}
.login-component {
    position: absolute;
    top: 40%;
    left: 50%;
    transform: translate(-50%, -50%); // x축 y축
    width: 300px;
    height: 500px;
    background: white;
    box-shadow: 5px 5px 5px 5px #c4c4c4;
    border-radius: 11px;
}
.login-box {
    padding: 15% 8%;
    display: flex;
    justify-content: space-between;
    flex-direction: column;
    align-items: center;
    height: 100%;
}
.login-title {
    font-family: jost;
    font-size: 30px;
    color: #ec7676;
}
.login-title-L {
    font-family: jost;
    font-size: 40px;
    color: #ec7676;
}
.login-input-button {
    display: flex;
    flex-direction: column;
    align-items: center;
}
.login-button {
    margin-top: 10%;
    width: 100%;
}
</style>
