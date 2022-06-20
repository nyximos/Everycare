<!-- <template>
  <div>
      <v-btn
            class="back_btn"
            color="grey"
            dark
            @click="back_pg"
          >
            뒤로
          </v-btn>  
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
                        </v-form>
                    </div>
                </v-card>
  </div>
</template>

<script>
export default {
  mounted(){
       const id = this.$route.params.caretargetsId;
        const scid = this.$route.params.scheduleId;
        this.$http
            .get(`/api/dashboard/caretargets/${id}/schedules/${scid}`, {
                withCredentials: true,
            })
            .then((res) => {
                console.log(res.body.body.careTargetScheduleDTO);
                this.name = res.body.body.careTargetScheduleDTO.name;
                this.startTime = res.body.body.careTargetScheduleDTO.startTime;
                this.endTime = res.body.body.careTargetScheduleDTO.endTime;
                
            })
            .catch((err) => {
                console.log(err);
            });
  },
  data(){
      return{
        tarid:this.$route.params.caretargetsId,
        schid:this.$route.params.scheduleId,
      }
  },
  methods:{
      back_pg(){
        this.$router.push({ name: 'schedule' });
        this.$router.go();
      },
      dialogcel(){
         this.$router.go(-1);
      },
     dialogsav(){
        this.$router.push({ name: 'schedule' });
         var upformData = new FormData() 
                upformData.append('id',this.schid);             
                upformData.append('name',this.name);
                upformData.append('startTime',this.startTime);
                upformData.append('endTime',this.endTime);
    this.$http
    .patch(`/api/dashboard/caretargets/${this.tarid}/schedules/${this.schid}`,upformData, {
    withCredentials: true
    })
     .then(res => {
      console.log(res);
    })
      .catch(err => {
       console.log(err);
    }); 
      this.$router.go();
     }
  }
}
</script>

<style>

</style> -->