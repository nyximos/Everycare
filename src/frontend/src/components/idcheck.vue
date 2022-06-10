<template>
    <div>
        <v-col cols="12" sm="6" md="3">
            <v-text-field id="checkedid" v-model="chkid" label="아이디를 입력하세요" outlined></v-text-field>
            <v-btn @click="checkid">중복체크</v-btn>
        </v-col>
    </div>
</template>

<script>
export default {
    data() {
        return {
            chkid: ''
        };
    },
    methods: {
        checkid() {
            const id = document.getElementById('checkedid').value;
            console.log(id);
            var chkformData = new FormData();
            chkformData.append('id', this.shkid);
            this.$http
                .get(`/api/signup/${id}`, chkformData, {
                    withCredentials: true
                })
                .then(res => {
                    console.log(res);
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }
};
</script>

<style></style>
