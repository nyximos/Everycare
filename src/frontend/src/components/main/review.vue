<template>
  <div>
      <h2>리뷰</h2>
      <table>
          <thead>
              <tr>
                  <th>Id</th>
                  <th>name</th>
                  <th>comment</th>
                  <th>rating</th>
              </tr>
          </thead>
          <tbody v-for="(r,index) in review" :key="index">
              <tr @click="move(r)">
                  <td>{{index+1}}</td>
                  <td>{{r.activityClassificationName}}</td>
                  <td>{{r.comment}}</td>
                  <td><v-rating v-model="review[index].rating"
                    background-color="teal"
                    color="teal"
                    size="30"
                    readonly
                    ></v-rating>
                    </td>
              </tr>
          </tbody>
      </table>
      <v-dialog
        transition="dialog-bottom-transition"
        max-width="600"
        v-model="dialog"
        @click:outside="closeDialog"
        @keydown.esc="closeDialog"
      >
          <v-card>
            <v-toolbar
              color="primary"
              dark
            >후기</v-toolbar>
            <v-card-text v-if="commentform">
                <div class="text-h4 pa-2"><h2 class="text-h4" style="text-align:center;">{{review_detail.activityClassificationName}}</h2></div>
                <div class="text-h5 pa-2"> <v-rating v-model="review_detail.rating"
                    background-color="teal"
                    color="teal"
                    size="35"
                    readonly
                    ></v-rating>
                </div>
                <!-- <div>
                    <v-textarea v-if="commentform01" required outlined name="input-7-4" label="Comment" value="review_detail.comment"></v-textarea>
                </div> -->
                <div class="text-h6 pa-2">{{review_detail.comment}}</div>
            </v-card-text>
            <v-card-actions class="justify-end" v-if="commentform">
              <v-btn text @click="open">수정하기</v-btn>
              <v-btn text @click="del(review_detail)">삭제하기</v-btn>
            </v-card-actions>

            <v-card-text v-if="commentform01">
                <div class="text-h4 pa-4" style="margin:0 auto;"><h2 class="text-h4" style="text-align:center;">{{review_detail.activityClassificationName}}</h2></div>
                <div class="text-h5 pa-3"> <v-rating v-model="rating"
                    background-color="teal"
                    color="teal"
                    size="35"
                    ></v-rating>
                </div>
                <div class="pa-3">
                    <v-textarea  v-model="comment" outlined name="input-7-4" label="Comment" ></v-textarea>
                </div>
            </v-card-text>
            <v-card-actions class="justify-end" v-if="commentform01">
              <v-btn text @click="submit(review_detail)">수정하기</v-btn>
            </v-card-actions>
          </v-card>
      </v-dialog>
  </div>
</template>

<script>
export default {
    data(){
        return{
            review:[],
            review_detail:[],
            dialog:false,
            id:this.$route.params.contentId,
            commentform:true,
            commentform01:false,
            comment:'',
            rating:0,
        }
    },
    mounted(){
        const id = this.$route.params.contentId;
        console.log(id)
        this.$http
        .get(`/api/carenote/${id}/reviews`,{
            withCredentail:true
        })
        .then((res)=>{
            console.log(res)
            this.review = res.data.body
        })
        .catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        move(r){
            const carenoteId = this.id;
            const reviewId = r.activityClassificationId
            console.log(carenoteId);
            console.log(reviewId);

            this.dialog=true
            this.$http
            .get(`/api/carenote/${carenoteId}/review/${reviewId}`,{
                withCredentail:true
            })
            .then((res)=>{
                console.log(res)
                this.review_detail = res.data.body
            })
            .catch((err)=>{
                console.log(err)
            })
        },
        del(review_detail){
            const carenoteId = this.id;
            const reviewId = review_detail.activityClassificationId
            this.$http
            .delete(`/api/carenote/${carenoteId}/review/${reviewId}`,{
                withCredentail:true
            })
            .then((res)=>{
                console.log(res)
            })
            .catch((err)=>{
                console.log(err)
            })
        },
        open(){
            this.commentform01 = true
            this.commentform = false
        },
        closeDialog(){
            this.commentform = true
            this.commentform01 = false
        },
        submit(review_detail){ 
            const carenoteId = this.id;
            const reviewId = review_detail.activityClassificationId
            console.log(carenoteId)
            console.log(reviewId)
            console.log(this.rating)
            console.log(this.comment)
            var formData = new FormData();
            formData.append('rating' , this.rating)
            formData.append('comment' , this.comment)
            formData.append('activityClassificationId' , review_detail.activityClassificationId)
            this.$http
            .patch(`/api/carenote/${carenoteId}/review/${reviewId}`,formData,{
                withCredentail:true
            })
            .then((res)=>{
                console.log(res);
            })
            .catch((err)=>{
                console.log(err)
            })
        }
    }
}
</script>

<style>

</style>