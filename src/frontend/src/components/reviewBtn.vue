<template>
  <div>
    <v-btn
      dark
      @click="sheet = !sheet"
    >
    <span>Review</span>
    <v-icon>mdi-star</v-icon>
    </v-btn>
    <v-bottom-sheet v-model="sheet">
      <v-sheet
        height="800px"
      >
        <v-btn
          class="mt-3"
          text
          @click="sheet = !sheet"
        >
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <div class="py-3 text-center">
         <prodReview/>
        </div>
      </v-sheet>
    </v-bottom-sheet>
  </div>
</template>

<script>
import prodReview from '@/components/prodReview'
  export default {
    components:{
      prodReview
    },
    data(){
      return{
        sheet: false
      }
    }
  }
</script>

<style>
    
</style>