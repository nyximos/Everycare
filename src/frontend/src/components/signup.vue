<template>
    <div>
        
        <!-- <form name="signUpForm" class="form" role="form"> -->
            <br>
            <h2>회원가입</h2>
            {{ this.$store.state.userStore.id }}
            <div id="ininput">
            <input type="text" v-model="id" name="username" class="form-control input-lg" placeholder="아이디" ng-required="true" />
            <input type="password" v-model="password" name="password" class="form-control input-lg" placeholder="패스워드" ng-required="true" />
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
            </div>
        <!-- </form> -->
        <button id="upbtn" class="btn btn-lg btn-primary btn-block signup-btn" type="submit" @click="signup">회원가입</button>
       
    </div>
</template>

<script>
import { registerUser } from '@/api/core/index';

export default {
    data() {
        return {
            image: '',
        name: '',
        sex: '',
        age: '',
        comment: '',
        hopeloc1: '',
        hopeloc2: '',
        hopeloc3: '',
        hopeday: '',
        starttimepicker: '',
        endtimepicker: '',
        paytype: '',
        pay: '',
        hopetype:'',
        hopeage:'',
        vaccine:'',
        cctv:'',
        certification:''
        };
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
                this.$store.commit('userStore/signUp', updata);
                registerUser(updata);
            } catch (error) {
                console.log(error);
            }
        },
        initForm(){
            this.id='';
            this.password='';
            this.name='';
            this.birth='';
            this.gender='';
            this.phone='';
            this.email='';
            this.zipcode='';
            this.address='';
            this.detailedAddress='';
        }
    }
};
</script>

<style>
input{
    margin: 10px;
}
#ininput{
    width: 600px;
    position: relative;
    left: 20%;
}
#upbtn{
    position: relative;
    left: 21%;
    bottom: 15px;
}
</style>
