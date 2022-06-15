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
            this.$http
                .get(`/api/member/${id}`, {
                    withCredentials: true
                })
                .then(res => {
                    console.log(res);
                    this.$store.commit('checkId/checkIds', id);
                    //window.close();
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }
};
</script>

<style></style>
