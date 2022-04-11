<template>
    <div>
        <form name="signUpForm" class="form" role="form">
            <h2>회원가입</h2>
            {{ this.$store.state.userStore.upid }}
            <input type="text" v-model="id" name="username" class="form-control input-lg" placeholder="아이디" ng-required="true" />
            <br />
            <input type="password" v-model="password" name="password" class="form-control input-lg" placeholder="패스워드" ng-required="true" />
            <br />
            <div>
                <input type="text" v-model="name" name="name" class="form-control input-lg" placeholder="이름" ng-required="true" />
            </div>
            <div id="birthbox"><input class="form-control" v-model="birth" type="text" placeholder="생년월일" aria-label="default input example" /></div>
            <div id="genbox"><input type="radio" v-model="gender" name="gender" value="M" />남 <input type="radio" v-model="gender" name="gender" value="F" />여</div>
            <div id="number">
                <input class="form-control" v-model="phone" id="phone" type="text" placeholder="전화번호" aria-label="default input example" />
            </div>
            <input class="form-control" v-model="email" type="text" placeholder="Email (선택)" aria-label="default input example" />
            <input class="form-control" v-model="zipcode" type="text" placeholder="우편번호" aria-label="input example" />
             <input class="form-control" v-model="address"  type="text" placeholder="주소" aria-label="input example" />
            <input class="form-control" v-model="detailedAddress" type="text" placeholder="상세주소" aria-label="default input example" />
            <br />
        </form>
        <button class="btn btn-lg btn-primary btn-block signup-btn" type="submit" @click="signup">회원가입</button>
    </div>
</template>

<script>
import { registerUser } from '@/api/core/index.js';

export default {
    data() {
        return {};
    },
    methods: {
        signup() {
            const updata = {
                id: this.id,
                password: this.password,
                name: this.name,
                birth: this.birth,
                gender: this.gender,
                phone: this.phone,
                email: this.email,
                zipcode: this.zipcode,
                address: this.address,
                detailedAddress: this.detailedAddress
            };
            try {
                console.log(updata);
                this.$store.commit('userStore/sgnUp', updata);
                registerUser(updata);
            } catch (error) {
                console.log(error);
            }
        }
    }
};
</script>

<style>
/*
input.ng-invalid {
  border: 5px solid red;
}
#signup-box{
    text-align: center;
    width: 500px;
    position: relative;
    left: 200px;
    top: 100px;
}
#genbox{
    position: relative;
    right:200px;
    bottom:12px;
}
.form-select{
    width: 100px;
}
#number2{
    width: 150px;
    position: relative;
    left: 110px;
    bottom: 38px;
}
#number3{
    width: 150px;
    position: relative;
    left: 270px;
    bottom: 76px;
}
#birth{
    position: relative;
    bottom: 50px;
}
#address{
    width: 100px;
    position: relative;
    
}
#addr1{
    position: relative;
    bottom: 38px;
    right: 90px;
}
#addr2{
    position: relative;
    bottom: 15px;
}
#email{
    position: relative;
    bottom: 26px;
}*/
</style>
