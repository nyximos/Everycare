<template>
    <div class="sc-component">
        <li class="sche_list" v-for="(postItem, i) in postItems" :key="i">
            <v-card class="mx-auto" max-width="300">
                <v-card-text>
                    {{ postItem.name }} <br />
                    {{ postItem.startTime }} -
                    {{ postItem.endTime }}
                </v-card-text>
                <v-card-actions>
                    <v-btn x-small color="primary" @click="update(postItem)" dark> 수정 </v-btn>
                    <v-btn x-small color="error" dark @click="delet(postItem)"> 삭제 </v-btn>
                    <v-btn x-small color="green" dark @click="actPage(postItem)"> 활동 </v-btn>
                </v-card-actions>
            </v-card>
        </li>
        <v-dialog v-model="dialogPg" @click:outside="closeDialog" width="500">
            <template v-slot:activator="{ on, attrs }">
                <v-btn class="sc-add-btn" color="#69f0ae" dark v-bind="attrs" v-on="on"> 스케줄 추가 </v-btn>
            </template>
            <div>
                <v-card class="mx-auto my-12" max-width="374">
                    <template slot="progress"> </template>
                    <v-form ref="form" lazy-validation>
                        <v-card-title>스케줄 등록</v-card-title>
                        <v-divider class="mx-4"></v-divider>
                        <div id="modalinput">
                            <v-card-text>
                                <v-text-field v-model="name" required class="sche-name" label="스케줄 명" hide-details="auto"></v-text-field>
                            </v-card-text>
                            <v-card-text class="v-time-picker">
                                <v-row>
                                    <v-col cols="4">
                                        <v-subheader>시작시간</v-subheader>
                                    </v-col>
                                    <v-col cols="8">
                                        <v-text-field v-model="startTime" required type="time"></v-text-field>
                                    </v-col>
                                    <v-col cols="4">
                                        <v-subheader>종료시간</v-subheader>
                                    </v-col>
                                    <v-col cols="8">
                                        <v-text-field v-model="endTime" required type="time"></v-text-field>
                                    </v-col>
                                </v-row>
                            </v-card-text>
                            <v-divider></v-divider>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="primary" text @click="dialogsav"> 저장 </v-btn>
                                <v-btn text @click="dialogcel"> 취소 </v-btn>
                            </v-card-actions>
                        </div>
                    </v-form>
                </v-card>
            </div>
        </v-dialog>
    </div>
</template>

<script>
export default {
    components: {},
    data() {
        return {
            postItems: [],
            name: '',
            startTime: '',
            endTime: '',
            id: this.$route.params.caretargetsId,
            schid: this.$route.params.scheduleIdId,
            dialogPg: false
        };
    },
    mounted() {
        this.$http
            .get(`/api/dashboard/caretargets/${this.id}/schedules`, {
                withCredentials: true
            })
            .then(res => {
                console.log(res.data.body);
                this.postItems = res.data.body;
            })
            .catch(err => {
                alert(err);
                console.log(err);
            });
    },
    methods: {
        delet(postItem) {
            this.$router.push({ name: 'schdelete', params: { scheduleId: postItem.id } });
        },
        update(postItem) {
            this.$router.push({ name: 'schupdate', params: { scheduleId: postItem.id } });
        },
        actPage(postItem) {
            this.$router.push({ name: 'scheActive', params: { scheduleId: postItem.id } });
        },
        vdpclose() {
            this.s_date = false;
        },
        closeDialog() {
            this.$refs.form.reset();
        },
        dialogsav() {
            var schformData = new FormData();
            schformData.append('name', this.name);
            schformData.append('startTime', this.startTime);
            schformData.append('endTime', this.endTime);
            this.$http
                .post(`/api/dashboard/caretargets/${this.id}/schedules`, schformData, {
                    withCredentials: true
                })
                .then(res => {
                    console.log(res);
                    this.dialogPg = false;
                    this.$router.go();
                })
                .catch(err => {
                    console.log(err);
                });
        },
        dialogcel() {
            this.dialogPg = false;
            this.$refs.form.reset();
        },
        s_date_search(v) {
            this.s_date = v;
            this.menu1 = false;
            this.$refs.menu1.save(v);
        }
    }
};
</script>

<style>
.sc-date {
    position: relative;
    left: 15px;
}
.sc-component {
    margin: auto;
}
.exp-input {
    position: relative;
    bottom: 35px;
}
.sc-add-btn {
    position: absolute;
    top: 10%;
    left: 10%;
}
.v-time {
    width: 200px;
}
.sc-add-btn {
    position: absolute;
    top: 10%;
    left: 10%;
}
.sche_list {
    list-style-type: none;
    margin: 30px;
}
.update_sch {
    position: relative;
    left: 180px;
    bottom: 74px;
}
</style>
