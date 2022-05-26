<template>
    <div class="sc-component">
        
        <!-- <li class="sche_list" v-for="(postItem, i) in postItems" :key="i">
            <v-card 
            class="mx-auto" 
            max-width="300" 
            >
            <v-card-text>
                <br>
                
                </v-card-text>
                <v-card-actions>
                 <v-btn x-small color="primary" @click="update(postItem)" dark> 수정 </v-btn>
                <v-btn x-small color="error" dark @click="delet(postItem)"> 삭제 </v-btn>
                 <v-btn x-small color="green" dark @click="actPage(postItem)"> 활동 </v-btn>
           </v-card-actions>
           </v-card>
        </li> -->
        <v-dialog v-model="dialogPg" @click:outside="closeDialog" width="500">
            <template v-slot:activator="{ on, attrs }">
                <v-btn class="sc-add-btn" color="#69f0ae" dark v-bind="attrs" v-on="on"> 스케줄 추가 </v-btn>
            </template>
            <div>
                <v-card class="mx-auto my-12" max-width="374">
                    <template slot="progress"> </template>
                    <v-card-title>스케줄 등록</v-card-title>
                    <v-divider class="mx-4"></v-divider>
                    <div id="modalinput">
                        <v-form ref="form" lazy-validation>
                            <v-card-text>
                                <v-text-field v-model="name" required class="sche-name" label="스케줄 명" hide-details="auto"></v-text-field>
                            </v-card-text>

                            <v-card-text class="v-time-picker">
                                <v-row class="sc-date">
                                    <v-col cols="15" lg="20">
                                        <v-card-text>
                                      <v-datetime-picker label="시작 일시" dateFormat="yyyy-MM-dd'T'HH:mm:ss" v-model="startDatetime"> </v-datetime-picker>
                                      <v-datetime-picker label="종료 일시" dateFormat="yyyy-MM-dd'T'HH:mm:ss" v-model="endDatetime"> </v-datetime-picker>
                                      </v-card-text>  
                                    </v-col>
                                </v-row>
                                <v-card-text>
                                        <v-col cols="15" class="pb-0">
                                           <v-datetime-picker label="알람 시간" dateFormat="yyyy-MM-dd'T'HH:mm:ss" v-model="alarmTime"> </v-datetime-picker>
                                        </v-col>
                                       
                                    <v-text-field label="장소" v-model="addressName"></v-text-field>
                                </v-card-text>
                                <v-container fluid>
                                    <v-textarea label="설명" v-model="comment"></v-textarea>
                                </v-container>
                            </v-card-text>
                        
                            <v-divider></v-divider>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="primary" text @click="dialogsav"> 저장 </v-btn>
                                <v-btn text @click="dialogcel"> 취소 </v-btn>
                            </v-card-actions>
                        </v-form>
                    </div>
                </v-card>
            </div>
        </v-dialog>
    </div>
</template>

<script>
import Vue from 'vue'
import DatetimePicker from 'vuetify-datetime-picker'

Vue.use(DatetimePicker)

export default {
    data() {
        return {
            postItems: [],
            name: '',
            startDatetime: '',
            endDatetime: '',
            alarmTime: '',
            addressName: '',
            comment: '',
            dialogPg: false,
        };
    },
    mounted() {
        this.$http
            .get('/api/dashboard/calendar', {
                withCredentials: true
            })
            .then(res => {
                console.log(res);
            })
            .catch(err => {
                alert(err);
                console.log(err);
            });
    },
    methods: {
        closeDialog() {
            this.$refs.form.reset();
        },
        dialogsav() {
            var memschformData = new FormData();
            memschformData.append('name', this.name);
            memschformData.append('startDatetime', this.startDatetime);
            memschformData.append('endDatetime', this.endDatetime);
            memschformData.append('alarmTime', this.alarmTime);
            memschformData.append('addressName', this.addressName);
            memschformData.append('comment', this.comment);
            this.$http
                .post('/api/dashboard/calendar', memschformData, {
                    withCredentials: true
                })
                .then(res => {
                    console.log(res);
                    
                    // this.$router.go();
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
