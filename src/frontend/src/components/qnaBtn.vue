<template>
  <div>
    <v-btn
      dark
      @click="sheet = !sheet"
    >
    <span>Question</span>
    <v-icon>mdi-help</v-icon>
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
         <prodQna/>
        </div>
      </v-sheet>
    </v-bottom-sheet>
  </div>
</template>

<script>
import prodQna from '@/components/prodQna'
  export default {
    components:{
      prodQna
    },
    data: () => ({
      sheet: false,
      tiles: [
        { img: 'keep.png', title: 'Keep' },
        { img: 'inbox.png', title: 'Inbox' },
        { img: 'hangouts.png', title: 'Hangouts' },
        { img: 'messenger.png', title: 'Messenger' },
        { img: 'google.png', title: 'Google+' },
      ],
    }),
  }
</script>

<style>
    
</style>