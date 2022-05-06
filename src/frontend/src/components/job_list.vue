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
  <!-- {{this.listData}} -->
  <ListItem class="mt-5" v-for="(listItem, index) in filteredList" :key="index"
  :listItem="listItem" @detail="detailShot"
  />
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
import ListItem from '@/components/listItem'
export default {
    name: 'componentjoblist',
    components:{
      ListItem
    },
    mounted() {
        this.$http
        .get('/api/recruitions', {
        withCredentials: true
        })
        .then(res => {
          console.log(res.data)
          this.listData = res.data.body;
        })
          .catch(err => {
          console.log(err);
        });
    },
    data(){
        return{
          listData:[],
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
        detailShot(id){
          this.$router.push({
            name: 'detail',
            params: {
              contentId: id
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
            return listItem.title.includes(this.SearchText);
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