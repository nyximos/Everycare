<template>
 <div>
    <div class="text-end mt-4">
        <v-btn @click="gocreate">
          <v-icon left>
            mdi-pencil
          </v-icon>
            Create
        </v-btn>
    </div>
    <div class="header">
        <v-row>
        <h1>구인리스트</h1>
        </v-row>
    </div>
 <hr>
  <v-text-field
    v-model="SearchText"
    placeholder="Search"
    filled
    rounded
    dense
  ></v-text-field>
   <div v-if="!listData.length">글이 없습니다</div>
  <div v-if="!filteredList.length && listData.length">
    검색결과가 없습니다
  </div>
  <v-card
  class="mt-3"
  v-for="(listItem, index) in filteredList"
  :key="index"
  outlined
  @click="detailList(listItem.subject)">
  <v-card-title>
    {{listItem.subject}}
  </v-card-title>
    <v-card-text>
    {{listItem.desc}}
    </v-card-text>
  </v-card>
  <div class="text-center">
    <v-pagination
      class="mt-3"
      v-model="curPageNum"
      :length="numOfPages"
      circle
    ></v-pagination>
  </div>
 </div>
</template>

<script>
export default {
    name: 'componentjoblist',
    // mounted() {
    //       this.$http
    //     .get(`/api/stores/${this.id}`, {
    //     withCredentials: true
    //     })
    //     .then(res => {
    //       const result = res.data.body;
    //       console.log(result)
    //       this.listData = result.id;
    //     })
    //       .catch(err => {
    //       console.log(err);
    //     });
    // },
    data(){
        return{
            listData:[ 
            {id: 1,subject: "쿠파",desc: "Wels"},
            {id: 2,subject: "키노피오",desc: "Sale"},
            {id: 3,subject: "마리오",desc: "Lahore"},
            {id: 1,subject: "와르르",desc: "Wels"},
            {id: 2,subject: "거북손",desc: "Sale"},
            {id: 3,subject: "거북손",desc: "Lahore"},
          ],
          dataPerPage:3,
          curPageNum:1,
          SearchText: '',
        }
    },
    methods:{
        gocreate(){
          this.$router.push({
            path: '/createwrite'
          })
        },
        detailList(index){
          this.$router.push({
            name:'detail',
            params:{
              contentId: index
            }
          })
        }
    },
    computed: {
      startOffset() {
        return ((this.curPageNum - 1) * this.dataPerPage);
      },
      endOffset() {
        return (this.startOffset + this.dataPerPage);
      },   
      numOfPages() {
        return Math.ceil(this.listData.length / this.dataPerPage);
      },
      calData() {
        return this.listData.slice(this.startOffset, this.endOffset)
      },
      filteredList(){
        if (this.SearchText) {
          return this.listData.filter(listItem => {
            return listItem.subject.includes(this.SearchText);
          });
        }else{
          return this.calData
        }
      }
    }
}
</script>

<style>

</style>