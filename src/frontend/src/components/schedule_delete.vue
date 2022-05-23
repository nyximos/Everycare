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
export default {
    data(){
        return{
          tarid:this.$route.params.caretargetsId,
          schid:this.$route.params.scheduleId
        }
    },
 mounted(){
    //    const id = this.$route.params.caretargetsId;
    //     const scid = this.$route.params.scheduleId;
    //     this.$http
    //         .get(`/api/dashboard/caretargets/${id}/schedules/${scid}`, {
    //             withCredentials: true,
    //         })
    //         .then((res) => {
    //             console.log(res.body.body.careTargetScheduleDTO);
    //             this.name = res.body.body.careTargetScheduleDTO.name;
    //             this.startTime = res.body.body.careTargetScheduleDTO.startTime;
    //             this.endTime = res.body.body.careTargetScheduleDTO.endTime;
    //         })
    //         .catch((err) => {
    //             console.log(err);
    //         });
  },
   methods:{
     delyes(){
         this.$router.go(-1);
         var delformData = new FormData() 
                delformData.append('id',this.schid);             
                delformData.append('name',this.name);
                delformData.append('startTime',this.startTime);
                delformData.append('endTime',this.endTime);
    this.$http
    .delete(`/api/dashboard/caretargets/${this.tarid}/schedules/${this.schid}`,delformData, {
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
}
</script>

<style>
.del_com{
    margin: auto;
}
</style>