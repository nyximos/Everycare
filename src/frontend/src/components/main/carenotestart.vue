<template>
  <div>
      <h2>출근</h2>
     <v-btn class="ma-2" outlined color="indigo" >추가</v-btn>
     <v-btn class="ma-2" outlined color="indigo" >등록</v-btn>
     
    </div>
</template>

<script>
export default {
    data(){
        return{
            id:this.$route.params.contentId,
            start:[]
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
        })
        .catch((err)=>{
            console.log(err);
        })
    }
}
</script>

<style>

</style>