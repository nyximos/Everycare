<template>
<div class="findinfo">

        <v-card class="mx-auto" max-width="344">
            <v-col cols="12" sm="10" md="10">
                <v-text-field id="newpw" v-model="password" label="기존 비밀번호 입력" outlined></v-text-field>
                <v-text-field id="newpw" v-model="newpassword" label="새 비밀번호 입력" outlined></v-text-field>
                <v-card-actions>
                    <v-btn class="chpw" color="#69f0ae" @click="chpw">비밀번호 변경</v-btn>
                </v-card-actions>
            </v-col>
        </v-card>
    </div>
</template>

<script>
export default {
    data() {
        return {
            newpassword: '',
            id: this.$store.state.userStore.id
        };
    },
    methods: {
        chpw() {
            console.log(this.id);
            var pwformData = new FormData();
            pwformData.append('newPassword', this.newpassword);
            this.$http
                .patch(`/api/member/${this.id}/password`, pwformData, {
                    withCredentials: true
                })
                .then(res => {
                    console.log(res);
                    this.$router.push({ name: 'main' });
                    this.$router.go();
                    this.$store.commit('userStore/clearUsername');
                    $.ajax({
                        url: '/api/token',
                        type: 'DELETE',
                        cache: false,
                        contentType: false,
                        processData: false,
                        async: false
                    });
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }
};
</script>

<style>
.mx-auto {
    margin: 400px;
}
.chpw {
    position: relative;
    right: 5px;
}
</style>
