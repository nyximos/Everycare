<template>
  <div class="del_com">
     <v-card
    class="mx-auto"
    max-width="344"
  >
    <v-card-text>
      <p class="text-h4 text--primary">
        삭제하시겠습니까
      </p>
    </v-card-text>
    <v-card-actions>
      <v-btn
        text
        color="blue"
        @click="delyes"
      >
        네
      </v-btn>
      <v-btn
        text
        color="red"
        @click="this.$router.go(-1);"
      >
        아니오
      </v-btn>
    </v-card-actions>

  </v-card>
  </div>
</template>

<script>
import Vue from 'vue'
import DatetimePicker from 'vuetify-datetime-picker'

Vue.use(DatetimePicker)
export default {
    data(){
        return{
            id:this.$route.params.memberscheduleId
        }
    },
 mounted(){

  },
   methods:{
        
     delyes(){
         this.$router.go(-1);
          var memschformData = new FormData();
            memschformData.append('name', this.name);
            memschformData.append('startDatetime', this.startDatetime);
            memschformData.append('endDatetime', this.endDatetime);
            memschformData.append('alarmTime', this.alarmTime);
            memschformData.append('addressName', this.addressName);
            memschformData.append('comment', this.comment);
            this.$http
                .delete(`/api/dashboard/calendar/${this.id}`, memschformData, {
                    withCredentials: true
                })
                .then(res => {
                    console.log(res);
                    // this.$router.go();
                })
                .catch(err => {
                    console.log(err);
                });
     }
  }
}
</script>

<style>
.del_com{
    margin: auto;
}
</style>