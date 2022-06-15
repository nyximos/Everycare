<template>
    <div id="input-id">   
                    <input type="text" v-model="chkid" id="checkedid" class="form-control input-lg" placeholder="아이디" ng-required="true" />
                <v-btn class="findid" @click="checkid">ID중복체크</v-btn>
                <div>
                <v-btn v-if="useid" class="submitid" @click="submitid">사용하기</v-btn>
                </div>
                </div>  
</template>

<script>
export default {
    data() {
        return {
            chkid: '',
            useid: false
        };
    },
    methods: {
        checkid() {
            const id = document.getElementById('checkedid').value;
            if(id == ''){
                alert('아이디를 입력해주세요.')
            }
            console.log(id);
            this.$http
                .get(`/api/member/${id}`, {
                    withCredentials: true
                })
                .then(res => {
                    console.log(res);
                    this.$store.commit('checkId/checkIds', id);
                    //window.close();
                    if(res.data.message == '성공'){
                        alert('사용하실수 있는 아이디입니다.');
                        this.useid=true;
                    }else if(res.data.message == '실패'){
                        alert('이미 존재하는 아이디입니다.');
                    }
                })
                .catch(err => {
                    console.log(err);
                });
        },
    }
};
</script>

<style>
#input-id{
    display: flex;
    width:400px;
}
.findid{
  position: relative;
  left: 10px;
}
.submitid{
    position: relative;
    left: 20px;
}
</style>
