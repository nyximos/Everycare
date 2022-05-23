<template>
    <div>
        <br /><br /><br />
        <v-hover>
            <v-card class="mx-auto" color="grey lighten-4" max-width="400">
                <v-img :aspect-ratio="16 / 10" :src="'https://localhost:8086/api/images/' + storeFile"> </v-img>
                <v-card-text class="pt-6" style="position: relative">
                    <v-btn absolute color="white" class="white--text" fab large right top @click="submit()">
                        <v-icon color="black">mdi-card-plus</v-icon>
                    </v-btn>
                    <h3 class="text-h5 font-weight-light black--text mb-2">
                        자격증 이름: <span>{{ detail.name }}</span>
                    </h3>
                    <div class="font-weight-light grey--text text-h7 mb-2">
                        회원 아이디: <span>{{ detail.memberId }}</span>
                    </div>
                    <div class="font-weight-light grey--text text-h7 mb-2">
                        회원 이름: <span>{{ detail.memberName }}</span>
                    </div>
                    <div class="font-weight-light grey--text text-h7 mb-2">
                        uploadName: <span>{{ detail.uploadName }}</span>
                    </div>
                    <div class="font-weight-light grey--text text-h7 mb-2">
                        등록일: <span>{{ detail.createdAt }}</span>
                    </div>
                </v-card-text>
            </v-card>
        </v-hover>
        <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    </div>
</template>

<script>
export default {
    name: 'certification_deatil',
    data() {
        return {
            detail: [],
            storeFile: this.storeFile,
        };
    },
    mounted() {
        const id = this.$route.params.contentId;

        this.$http
            .get(`/api/admin/caresitter-certifications/${id}`, {
                withCredentials: true,
            })
            .then((res) => {
                console.log(res.data.body);
                this.detail = res.data.body;
                this.storeFile = res.data.body.storeName;
            })
            .catch((err) => {
                console.log(err);
            });
    },
    methods: {
        submit() {
            const id = this.$route.params.contentId;
            console.log(id);
            this.$http
                .post(`/api/admin/certifications/${id}`, {
                    withCredentials: true,
                })
                .then((res) => {
                    console.log(res);
                })
                .catch((err) => {
                    alert(err);
                    console.log(err);
                });
        },
    },
};
</script>

<style></style>
