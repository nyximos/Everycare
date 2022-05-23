<template>
  <div class="sc-component">
      <li class="sche_list" v-for="(postItem, i) in postItems" :key="i">
            <v-card 
            class="mx-auto" 
            max-width="300" 
            >
            <v-card-text>
                {{postItem.requirement}}
                </v-card-text>
                <v-card-actions>
                 <v-btn x-small color="primary" @click="update(postItem)" dark> 수정 </v-btn>
                <v-btn x-small color="error" dark @click="delet(postItem)"> 삭제 </v-btn>
           </v-card-actions>
           </v-card>
        </li>
          <v-btn
            class="back_btn"
            color="grey"
            dark
            @click="back_pg"
          >
            뒤로
          </v-btn>  
    <v-row justify="center">
        
      <v-dialog
        v-model="dialogPg"
        @click:outside="closeDialog"
        width="500"
        scrollable
      >
        <template v-slot:activator="{ on, attrs }">
          <v-btn
            class="sc-add-btn"
            color="#69f0ae"
            dark
            v-bind="attrs"
            v-on="on"
          >
            활동 추가
          </v-btn>
        </template>
        <v-card>
          <v-card-title>활동 등록</v-card-title>
          <v-divider></v-divider>
          <v-card-text style="height: 300px">
            <v-card-text class="v-time-picker">
              <v-row>
                <v-col cols="4">
                  <v-subheader v-model="actstartTime">시작시간</v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field required type="time"></v-text-field>
                </v-col>
                <v-col cols="4">
                  <v-subheader v-model="actendTime">종료시간</v-subheader>
                </v-col>
                <v-col cols="8">
                  <v-text-field required type="time"></v-text-field>
                </v-col>
              </v-row>
            </v-card-text>
            <v-container fluid>
              <v-textarea
                v-model="requirement"
                required
                label="요구사항"
              ></v-textarea>
            </v-container>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-btn color="blue darken-1" text @click="closeDialog">
              닫기
            </v-btn>
            <v-btn color="blue darken-1" text @click="dialogsav"> 저장 </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
export default {
    data() {
    return {
      postItems: [],
      actstartTime: "",
      actendTime: "",
      requirement: "",
      tarid: this.$route.params.caretargetsId,
      schid: this.$route.params.scheduleId,
      level: "",
      activityClassificationId: "",
      dialogPg: false,
    };
  },
  mounted() {
   const id = this.$route.params.caretargetsId;
    const scid = this.$route.params.scheduleId;
    this.$http
      .get(`/api/dashboard/caretargets/${id}/schedules/${scid}`, {
        withCredentials: true,
      })
      .then((res) => {
        console.log(res);
        this.postItems = res.data.body.activityInformationViewDTOs;
      })
      .catch((err) => {
        console.log(err);
      })
  },
  
  methods: {
      back_pg(){
        this.$router.push({ name: 'schedule' });
        window.location=document.referrer
      },
    closeDialog() {
      this.$refs.form.reset();
    },
    dialogsav() {
      var actformData = new FormData();
      actformData.append("activityClassificationId", 1);
      actformData.append("startTime", this.actstartTime);
      actformData.append("endTime", this.actendTime);
      actformData.append("requirement", this.requirement);
      this.$http
        .post(
          `/api/dashboard/caretargets/${this.tarid}/schedules/${this.schid}/activities`,
          actformData,
          {
            withCredentials: true,
          }
        )
        .then((res) => {
          console.log(res);
          this.dialogPg = false;
        })
        .catch((err) => {
          console.log(err);
        });
         
    },
    scheBack() {
      this.$router.push({ name: "schedule" });
      this.$router.go();
    },
    addActive() {
      this.dialogPg = true;
      console.log(this.id);
    },
    dialogcel() {
      this.dialogPg = false;
      this.$refs.form.reset();
    },
  },
};
</script>

<style>
.sc-component {
  margin: auto;
}

.sc-add-btn {
  position: absolute;
  top: 10%;
  left: 10%;
  width: auto;
}
.mx-auto {
  position: absolute;
  top: 15%;
}
.active_sel {
  position: relative;
  bottom: 20px;
  left: 5px;
}
.act_add {
  position: absolute;
  top: 15%;
  left: 15%;
}
.act_sel {
  position: relative;
  bottom: 20px;
}
.back_btn{
    position: absolute;
    top: 10%;
    left: 90%;
}
</style>
