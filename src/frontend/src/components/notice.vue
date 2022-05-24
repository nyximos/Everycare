<template>
<div class="container">
<div class="row">
<p class="display-5 mt-5">공지사항</p>
</div>
<div class="row">
 <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            title
          </th>
          <th class="text-left">
            name
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="item in desserts"
          :key="item.name"
        >
          <td>{{ item.title }}</td>
          <td>{{ item.memberInfoDTO.name }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
  <v-pagination
      class="mt-3"
      v-model="curPageNum"
      :length="numOfPages"
    ></v-pagination>
</div>
</div>
</template>

<script>
export default {
data(){
    return{
        desserts: [],
        dataPerPage:3,
        curPageNum:1,
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

}
</script>

<style>

</style>