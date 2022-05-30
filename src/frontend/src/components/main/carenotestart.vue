<template>
    <div class="content">
        <h2 class="title">출근 사진 등록</h2>
        <h5 class="title1">케어 대상인:{{start.careTargetName}}</h5>
            <br>
            <v-card class="mx-auto" max-width="344" >
                <label for="uppic">
                    <v-img :src="avatar" width="225px" height="225px" alt="사진없음" style="margin:0 auto; padding:20px;" class="img-avatar" id="preview" />
                    <!-- <input id="coverRoute" type="text" placeholder="Cover"> -->
                </label>
            <v-card-subtitle style="margin-top:-10px;" class="subtitle">
                <!-- <v-file-input label="File input" outlined dense></v-file-input> -->
                <input type="file" name="avatar" id="uppic"  accept="image/gif,image/gif,image/jpg,image/png" @change="changeImage($event);" ref="avatarInput" class="uppic" multiple="multiple">
            </v-card-subtitle>
        <v-card-actions>
            <div class="button" >
                <v-btn class="ma-2" outlined color="indigo" @click="add">추가</v-btn>
                <v-btn class="ma-2" outlined color="indigo" @click="move">등록</v-btn>
            </div>
        </v-card-actions>
     </v-card>
     <br><br><br>
    </div>
</template>

<script>
export default {
    data(){
        return{
            id:this.$route.params.contentId,
            start:[],
            attachFile:[],
            sId:this.id,
            avatar:require('@/assets/user.png'),
            avatarInput:'',
            file:this.file
        }
    },
    mounted(){
        const id = this.$route.params.contentId;
        console.log(id);
        this.$http
        .get(`/api/carenotes/${id}`,{
            withCredentail:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.start = res.data.body
            this.sId = res.data.body.id
            // console.log(this.sId);
        })
        .catch((err)=>{
            console.log(err);
        })
    },
    methods:{
        add(){
            // const id = this.$route.params.contentId;
            const sId = this.sId;

            console.log(this.file)
        //    console.log(this.avatar)
            var formData = new FormData();

            // formData.append('attachFile', this.avatar);
             formData.append('attachFile', this.file);
            
            this.$http
            .patch(`/api/carenotes/${sId}/photo`, formData,{
                withCredentail:true
            })
            .then(res=>{
                console.log(res);
            }).catch(err=>{
                console.log(err);
            })
        },
        move(){
            this.$router.push({name:'careschedule' , params:{contentId:this.sId}  })
        },
        changeImage(e) {
         document.getElementById("uppic").click();   
        
         var file = e.target.files[0]
         console.log(file)
         this.file = file
         var reader = new FileReader()
         var that = this
         reader.readAsDataURL(file)
         reader.onload = function(e) {
           that.avatar = this.result
         }
       },
        
        
    }
}
</script>

<style>
    .content{
        margin-top: 50px;
    }
    .title{
        text-align: center;
    }
    .title1{
        text-align: center;
    }
    .button{
        margin: 0 auto;
        text-align: center;
    }
    .ma-2{
        width: 200px;
        /* align-items: center; */
        padding: 3px;
        margin: 0 auto;   
    }
    .subtitle{
        margin-top: 0px;
    }
    .img{
        margin-top: 20px;
    }
    label{
        display: block;
    }
    input[type="file"]{
        position: absolute;
        overflow: hidden;
        clip: rect(0,0,0,0);
    }
</style>