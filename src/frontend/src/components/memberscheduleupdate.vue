<template>
  <div>
                <v-card class="mx-auto my-12" max-width="374">
                    <template slot="progress"> </template>
                    <v-card-title>스케줄 수정</v-card-title>
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
                                      <v-datetime-picker id="" label="시작 일시"  v-model="startDatetime"> </v-datetime-picker>
                                      <v-datetime-picker label="종료 일시"  v-model="endDatetime"> </v-datetime-picker>
                                      </v-card-text>  
                                    </v-col>
                                </v-row>
                                <v-card-text>
                                        <v-col cols="15" class="pb-0">
                                           <v-datetime-picker label="알람 시간"  v-model="alarmTime"> </v-datetime-picker>
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
</template>

<script>
import Vue from 'vue'
import DatetimePicker from 'vuetify-datetime-picker'

Vue.use(DatetimePicker)
export default {

}
</script>

<style>

</style>