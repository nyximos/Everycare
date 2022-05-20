<template>
  <div>
      <ul v-for="(c,index) in certification" :key="index">
        <li>{{certification[index].id}}</li>
        <li>{{certification[index].name}}</li>
        <li>{{certification[index].createdAt}}</li>
        <li>{{certification[index].approvalDate}}</li>
      </ul>
      <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
  </div>
</template>

<script>
export default {
  data(){
    return{
      certification:[

      ],
      id:this.$store.state.userStore.careSitterId
    }
  },
  mounted(){
    const id = this.$store.state.userStore.careSitterId;

    this.$http
    .get(`/api/dashboard/caresitter/${id}/certifications`,{
        withCredentials:true
    })
    .then((res)=>{
      this.certification = res.data.body;
    }).catch(err=>{
      console.log(err);
    })
  }
}
</script>

<style>

</style>