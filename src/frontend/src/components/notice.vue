<template>
<v-container>
  <div class="text-end">
  <v-btn
    v-if="$store.state.userStore.id==='admin'"
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
    item-key="id"
    :search="search"
    class="elevation-1"
    @click:row="handleClick"
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
        headers:[{text:'번호', value:'id'},{text:'제목', value:'title'},{text:'작성자', value:'memberInfoDTO.name'}]
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
      path: '/admin/notice/new'
    })
  },
  handleClick(value){
    // console.log(value.id)
    this.$router.push({
      name: 'noticeDetail',
      params: {
        contentId: value.id
      }
    })
  }
}

}
</script>

<style>

</style>