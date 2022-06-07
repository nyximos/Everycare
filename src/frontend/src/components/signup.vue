<template>
    <div class="signup-component">
        <div class="signup-box">
            <!-- <form name="signUpForm" class="form" role="form"> -->
            <br />
            <h2>회원가입</h2>
            <div id="in-input">
                <input type="text" v-model="id" id="id" name="username" class="form-control input-lg" placeholder="아이디" ng-required="true">
                <v-btn class="findid" @click="findID">ID중복조회</v-btn>
                <input type="password" v-model="password" name="password" class="form-control input-lg" placeholder="패스워드" ng-required="true" />
                <div>
                    <input type="text" v-model="name" name="name" class="form-control input-lg" placeholder="이름" ng-required="true" />
                </div>
                <div id="birthbox"><input class="form-control" v-model="birth" type="date" placeholder="생년월일" aria-label="default input example" /></div>
                <div id="genbox"><input type="radio" v-model="gender" name="gender" value="M" />남 <input type="radio" v-model="gender" name="gender" value="F" />여</div>
                <div id="number">
                    <input class="form-control" v-model="phone" id="phone" type="text" placeholder="전화번호" aria-label="default input example" />
                </div>
                <input class="form-control" v-model="email" type="email" placeholder="Email (선택)" aria-label="default input example" />
                <v-btn class="postbtn" @click="execDaumPostcode()">우편번호 찾기</v-btn>
                <input class="form-control" v-model="postcode" type="text" placeholder="우편번호" aria-label="input example" />
                <input class="form-control" id="address" v-model="address" type="text" placeholder="주소" aria-label="input example" />
                <input class="form-control" id="detailAddress" v-model="detailedAddress" type="text" placeholder="상세주소" aria-label="input example" />
                <input class="form-control" v-model="bank" type="text" name="bank" placeholder="은행" />
                <input class="form-control" v-model="accountNumber" type="text" name="accountNumber" placeholder="계좌번호" />
                
                <br />
            </div>
            <!-- </form> -->
            <button id="up-btn" class="btn btn-lg btn-primary btn-block signup-btn" type="submit" @click="signup">회원가입</button>
        </div>
    </div>
</template>

<script>
export default {
    mounted() {
         
    },
    data() {
        return {
            id: '',
            password: '',
            postcode:'',
            name: '',
            birth: '',
            gender: '',
            phone: '',
            email: '',
            zipcode: '',
            address: '',
            detailedAddress: '',
            bank: '',
            accountNumber: ''
        };
    },
    methods: {
        findID(){
            const id=document.getElementById('id').value;
            console.log(id)
            this.$http
                .get(`/api/signup/${id}`, {
                    withCredentials: true
                })
                .then(res => {
                    console.log(res);
                   
                })
                .catch(err => {
                    console.log(err);
                });
        },
        execDaumPostcode() {
            new window.daum.Postcode({
                oncomplete: data => {
                    if (this.extraAddress !== '') {
                        this.extraAddress = '';
                    }
                    if (data.userSelectedType === 'R') {
                        this.address = data.roadAddress;
                    } else {
                        this.address = data.jibunAddress;
                    }
                    if (data.userSelectedType === 'R') {
                        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                            this.extraAddress += data.bname;
                        }
                        if (data.buildingName !== '' && data.apartment === 'Y') {
                            this.extraAddress += this.extraAddress !== '' ? `, ${data.buildingName}` : data.buildingName;
                        }
                        if (this.extraAddress !== '') {
                            this.extraAddress = `(${this.extraAddress})`;
                        }
                    } else {
                        this.extraAddress = '';
                    }
                    this.postcode = data.zonecode;
                }
            }).open();
        },

        signup() {
            var formData = new FormData();
            formData.append('id', this.id);
            formData.append('password', this.password);
            formData.append('name', this.name);
            formData.append('birth', this.birth);
            formData.append('gender', this.gender);
            formData.append('phone', this.phone);
            formData.append('email', this.email);
            formData.append('zipcode', this.postcode);
            formData.append('address', this.address);
            formData.append('detailedAddress', this.detailedAddress);
            formData.append('bank', this.bank);
            formData.append('accountNumber', this.accountNumber);
            this.$http
                .post('/api/signup', formData, {
                    withCredentials: true
                })
                .then(res => {
                    console.log(res);
                    location.href = '/';
                })
                .catch(err => {
                    console.log(err);
                });
            //     initForm() {
            //         this.id = '';
            //         this.password = '';
            //         this.name = '';
            //         this.birth = '';
            //         this.gender = '';
            //         this.phone = '';
            //         this.email = '';
            //         this.zipcode = '';
            //         this.address = '';
            //         this.detailedAddress = '';
            //     }
        }
    }
};
</script>

<style scoped lang="scss">
.signup-box {
    position: relative;
    display: inline-block;
    top: 47%;
    left: 50%;
    align-items: center;
    transform: translate(-50%, -50%);
}
input {
    margin: 10px;
}
#in-input {
    width: 600px;
}
h2 {
    position: relative;
    left: 13px;
    color: #69f0ae;
    text-align: center;
    padding: 20px;
}
#up-btn {
    position: relative;
    left: 13px;
    margin: auto;
    display: block;
    background-color: #69f0ae;
    border: 1px solid #69f0ae;
    color: white;
}
.postbtn {
    position: relative;
    left: 10px;
}
.findid{
    position: relative;
    left: 10px;
}
</style>
