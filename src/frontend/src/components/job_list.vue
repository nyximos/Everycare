<template>
 <div>
    <div class="text-end mt-4">
        <v-btn
        :to="{name: 'CreateWrite'}">
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
import axios from 'axios'
export default {
    name: 'componentjoblist',
    mounted() {
        try {
            const res = axios.get('/api/api/recruitions')
            this.list = res.data.postId
        } catch (error) {
            console.log(error)       
        }
    },
    data(){
        return{
            result: false,
            list: ['a','b','c','d','e','f','g'],
            dataPerPage: 3,
            curPageNum :1,
            searchText: '',
        }
    },
    methods:{
        onOrder(postId){
          this.$router.push({
              name: 'writedetail',
              params:{postId:postId}
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
    computed:{
        startOffset(){
            return ((this.curPageNum-1) * this.dataPerPage);
        },
        endOffset(){
            return (this.startOffset+ this.dataPerPage);
        },
        numOfPages(){ 
            return Math.ceil(this.list.length/this.dataPerPage);
        },
        calData(){
            return this.list.slice(this.startOffset, this.endOffset)
        },
        dateRangeText(){
            return this.dates.join('~')
        },
        filteredList(){
            if (this.searchText) {
                return this.list.filter(list => {
                    return list.includes(this.searchText)
                })
            } else {
                return this.calData
                
            }
        }
}
}
</script>

<style>

</style>