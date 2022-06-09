<template>
    <div>
        <div>
            <img src='https://cdn.vuetifyjs.com/images/carousel/planet.jpg' width="100%" height="500">
        </div>
        <v-container fluid>
<v-row dense class="mt-15">
        <v-col
          v-for="card in cards"
          :key="card.title"
          :cols="card.flex"
        >
          <v-card elevation="4" class="mt-10 v-card">  
              <v-card-title class="fw-bold" v-text="card.title"></v-card-title>
              <v-img
              :src="card.src"
              class="white--text align-end"
              gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
              height="500"
            >
            </v-img>
          </v-card>
        </v-col>
      </v-row>
      </v-container>
        <div class="main-form">
            <div @click="go('caresitters')" class="find-care-sitter">
                케어시터 찾기
            </div>
            <v-divider vertical></v-divider>
            <div @click="go('joblist')" class="find-job">
                일자리 찾기
            </div>
        </div>
    </div></template
>
<script>
export default {
    name: 'Main',
    data() {
        return {
           		items: [
          {
            src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg'
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/cards/road.jpg'
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
          },
        ],
        cards: [
        { title: 'Pre-fab homes', src: 'https://cdn.vuetifyjs.com/images/cards/house.jpg', flex: 12 },
        { title: 'Favorite road trips', src: 'https://cdn.vuetifyjs.com/images/cards/road.jpg', flex: 4 },
        { title: '공지사항', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', flex: 4 },
        { title: '고양이파우치', src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg', flex: 4 },
        { title: '춘식이', src: 'https://cdn.vuetifyjs.com/images/cards/plane.jpg', flex: 6 },
        { title: '띠용~?', src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg', flex: 6 },


      ],
        };
    },
    methods: {
        go(link) {
            this.$router.push('/' + link);
        }
    }
};
</script>
<style lang="scss" scoped>
.main-form {
    display: flex;
    height: calc(100vh - 350px);
}
.find-care-sitter {
    width: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
}
.find-job {
    width: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
}

</style>
