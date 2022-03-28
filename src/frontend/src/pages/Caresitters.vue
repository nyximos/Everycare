<template>
  <v-container class="content">
    <div class="list">
      <div class="div_list">
        <Caresitters />
        <Caresitters1 />
      </div>
    </div>
  </v-container>
  
</template>

<script>
import Caresitters from '@/components/main/caresitters'
import Caresitters1 from '@/components/main/caresitters1'

export default {
  components: {
       Caresitters,
       Caresitters1
       },

};
</script>

<style>
  .content{
    width: 100%;
    position: relative;
  }
  .content .list{
    width:100%;
    max-width: 670px;
    min-height: 800px;
    background-color: #ffffff;
    position: relative;
    margin: 0 auto;
    border-left: 1px solid #dedede;
    border-right: 1px solid #dedede;
  }
  .content .list .div_list{
    width: 100%;
    max-width: 100%;
    min-height: 800px;
    padding: 30px 15px 30px 15px;
    box-sizing: border-box;
    position:relative;
    background-color: #f4f4f4;
  }
</style>