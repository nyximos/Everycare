<template>
  <div>
    <section class="main">
            <div class="board">
            <div class="title">
              <h2 class="subtitle">리뷰 보기</h2>
            </div>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Comment</th>
                            <th>Rating</th>
                        </tr>
                    </thead>
                    <tbody v-for="(r,index) in review" :key="index">
                        <tr @click="move(r)">
                            <td>{{index+1}}</td>
                            <td>{{r.activityClassificationName}}</td>
                            <td>{{r.comment}}</td>
                            <td>
                                <v-rating v-model="review[index].rating"
                                background-color="#FFC400"
                                color="#FFC400"
                                size="28"
                                readonly
                                ></v-rating>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
    </section>

      <v-dialog
        transition="dialog-bottom-transition"
        max-width="600"
        v-model="dialog"
        @click:outside="closeDialog"
        @keydown.esc="closeDialog"
      >
          <v-card>
            <v-toolbar

              color="#69f0ae"

              dark
            >후기</v-toolbar>
            <v-card-text v-if="commentform">
                <div class="text-h4 pa-2"><h2 class="text-h4" style="text-align:center;">{{review_detail.activityClassificationName}}</h2></div>
                <div class="text-h5 pa-2"> <v-rating v-model="review_detail.rating"
                    background-color="#FFC400"
                    color="#FFC400"
                    size="35"
                    readonly
                    ></v-rating>
                </div>


                <div class="text-h6 pa-2">{{review_detail.comment}}</div>
            </v-card-text>
            <v-card-actions class="justify-end" v-if="commentform">
              <v-btn text @click="open">수정하기</v-btn>
              <v-btn text @click="del(review_detail)">삭제하기</v-btn>
            </v-card-actions>

            <v-card-text v-if="commentform01">
                <div class="text-h4 pa-4" style="margin:0 auto;"><h2 class="text-h4" style="text-align:center;">{{review_detail.activityClassificationName}}</h2></div>
                <div class="text-h5 pa-3"> <v-rating v-model="rating"
                    background-color="#FFC400"
                    color="#FFC400"
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
            if(confirm("삭제하시겠습니까??") ==true){

            const carenoteId = this.id;
            const reviewId = review_detail.activityClassificationId
            this.$http
            .delete(`/api/carenote/${carenoteId}/review/${reviewId}`,{
                withCredentail:true
            })
            .then((res)=>{
                console.log(res)

                alert("삭제완료")
                this.dialog = false

            })
            .catch((err)=>{
                console.log(err)
            })

            }
            return false

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

                alert("수정완료")
                
                this.dialog = false

            })
            .catch((err)=>{
                console.log(err)
            })
        }
    }
}
</script>

<style scoped>
.subtitle{
    margin-top:30px;
    margin-bottom: 40px;
}
.board{
  padding:1.5em;
  background: #f5f5f5
}
.title{
    width: 60%;
    margin: 0 auto;
}

table {
  margin: 0 auto;
  border: 1px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 2px 5px rgba(0,0,0,.25);
  width: 60%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}

th {
  text-align: center;
}
  
thead {
  font-weight: bold;
  color: #fff;
  background: teal;
}
  
 td, th {
  padding: 1em .5em;
  vertical-align: middle;
  text-align: center;
}
  
 td {
  border-bottom: 1px solid rgba(0,0,0,.1);
  background: #fff;
}

a {
  color: #73685d;
}
  
 @media all and (max-width: 768px) {
    
  table, thead, tbody, th, td, tr {
    display: block;
  }
  
  th {
    text-align: right;
  }
  
  table {
    position: relative; 
    padding-bottom: 0;
    border: none;
    box-shadow: 0 0 10px rgba(0,0,0,.2);
  }
  
  thead {
    float: left;
    white-space: nowrap;
  }
  
  tbody {
    overflow-x: auto;
    overflow-y: hidden;
    position: relative;
    white-space: nowrap;
  }
  
  tr {
    display: inline-block;
    vertical-align: top;
  }
  
  th {
    border-bottom: 1px solid #a39485;
  }
  
  td {
    border-bottom: 1px solid #e5e5e5;
  }  
  }

</style>