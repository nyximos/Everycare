<template>
    <div class="mem_detail_com">
        <v-card class="mx-auto" max-width="344" width="500">
            <v-card-text>
                <p class="text-h4 text--primary">
                    {{ memschdata.name }}
                </p>
            </v-card-text>
            <v-card-text>
                <div class="text--primary">
                    시작일시
                </div>
                <div>
                    {{ startDatetime }}
                </div>
            </v-card-text>

            <v-card-text>
                <div class="text--primary">
                    종료일시
                </div>
                <div>
                    {{ endDatetime }}
                </div>
            </v-card-text>

            <v-card-text>
                <div class="text--primary">
                    알람시간
                </div>
                <div>
                    {{ alarmTime }}
                </div>
            </v-card-text>
            <v-card-text>
                <div class="text--primary">
                    장소
                </div>
                <div>
                    {{ memschdata.addressName }}
                </div>
            </v-card-text>
            <v-card-text>
                <div class="text--primary">
                    설명
                </div>
                <div>
                    {{ memschdata.comment }}
                </div>
            </v-card-text>

            <v-card-actions class="justify-end">
                <v-placer></v-placer>

                <v-btn text color="red"
                @click="back_btn">
                    취소
                </v-btn>
            </v-card-actions>
        </v-card>
    </div>
</template>

<script>
export default {
    methods:{
        back_btn(){
            this.$router.go(-1);
        }
    },
    mounted() {
        const id = this.$route.params.memberscheduleId;

        this.$http
            .get(`/api/dashboard/calendar/${id}`, {
                withCredentials: true
            })
            .then(res => {
                console.log(res.body.body);
                this.memschdata = res.body.body;
                this.name = res.body.body.name;
                this.startDatetime = res.body.body.startDatetime.replace('T', ' . ');
                this.endDatetime = res.body.body.endDatetime.replace('T', ' . ');
                this.alarmTime = res.body.body.alarmTime.replace('T', ' . ');
                this.comment = res.body.body.comment;
                this.addressName = res.body.body.addressName;
            })
            .catch(err => {
                console.log(err);
            });
    },
    data() {
        return {
            id: this.$route.params.memberscheduleId,
            memschdata: []
        };
    }
};
</script>

<style>
.mem_detail_com {
    margin: auto;
}
</style>
