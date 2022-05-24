<template>
<v-container>
  <div class="text-end">
  <v-btn
    color="primary"
    dark
    class="mb-2"
    @click="newNotice">
    New Item
  </v-btn>
  </div>
    <v-data-table
    :headers="headers"
    :items="desserts"
    item-key="title"
    :search="search"
    class="elevation-1"
  >
  <template v-slot:top>
     <template>
          </template>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          class="mx-4"
        ></v-text-field>
      </template>
      </v-data-table>
</v-container>
</template>

<script>
export default {
data(){
    return{
        search: '',
        desserts: [],
        headers:[{text:'제목', value:'title'},{text:'작성자', value:'memberInfoDTO.name'}]
    }
},
mounted() {
this.$http
    .get('/api/notice', {
    withCredentials: true,
    })
    .then((res) => {
        console.log(res.data);
        this.desserts = res.data.body;
    })
    .catch((err) => {
        alert(err);
        console.log(err);
    });
},
methods:{
  newNotice(){
    this.$router.push({
      path: '/newNotice'
    })
  }
}

}
</script>

<style>

</style>