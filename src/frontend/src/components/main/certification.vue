<template>
  <div>
      <v-card
  elevation="2"
>
      <h2>자격증 등록</h2>
      <ul>
        <li>자격증 분류
            <v-select name="certification" id="certification"
                      v-model="certification"
                      :items="certi"
                      label="자격증"
                      item-text="name"
                      item-value="value"
                        on>
            </v-select>
        </li>
        <li>
            자격증 사진
            <v-file-input 
                v-model="attachFiles" 
                label="File input" 
                type="file"
                id="attachFiles"
                multiple="multiple"
                outlined dense>
            </v-file-input>
        </li>
        </ul>
            <input id="tokenField" type="hidden"/>
          <v-btn class="ma-2" outlined color="indigo" @click="submit">완료</v-btn>
          <router-link to="/"> <v-btn class="ma-2" outlined color="indigo">취소</v-btn></router-link>
    </v-card>
  </div>
  
</template>

<script>
export default {
    data(){    
        return{
            certi:[
                {name:'사회복지사1', value:'사회복지사1'},
                {name:'사회복지사2', value:'사회복지사2'},
                {name:'사회복지사3', value:'사회복지사3'},
            ],
            attachFiles:[],
            certification:'',
            id:this.$route.params.id,
            result:[]
        }   
    },
    mounted(){
     const id = Number(this.$route.params.id);
     this.$http
    .get(`/api/caresitters/${id}`, {
    withCredentials: true
    })
    .then(res => {
      const result = res.data.body;
      console.log(result)
      // this.id = result.id;
    })
      .catch(err => {
       console.log(err);
    });
},
    methods:{
        submit(){
            var formData = new FormData();
            console.log(id);
            formData.append('careSitterId',this.id);
            formData.append('classification', this.certification);
            for(let i = 0; i< this.attachFiles.length; i++){
            formData.append('attachFiles', this.attachFiles[0]);
            
          }
           this.$http
           .post(`/api/dashboard/caresitter/${this.id}/certifications`,formData,{
               withCredentials:true
           })
           .then(res=>{
               console.log(res);
           }).catch(err=>{
               console.log(err);
           })
        }
    }
}
</script>

<style>

</style>