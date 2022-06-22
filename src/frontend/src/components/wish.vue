<template>
    <div>
        <div v-if="this.wishList.length==0">찜이 없습니다</div>
        <v-simple-table v-else>
            <template v-slot:default>
            <v-card v-for="item in wishList" :key="item.index" class="d-flex flex-no-wrap justify-space-between mb-5" elevation="1">
              <div>
                <v-card-title
                  class="text-h6"
                  v-text="item.name"
                ></v-card-title>

                <v-card-subtitle>{{item.price}} 원</v-card-subtitle>

                <v-card-actions>
                  <v-btn
                    @click="remove(item)"
                    class="ml-2 mt-5"
                    outlined
                    rounded
                    small
                  >
                    삭제
                  </v-btn>
                </v-card-actions>
              </div>

              <v-avatar
                class="ma-3"
                size="125"
                tile
              >
                <v-img :src="'https://localhost:8086/api/images/'+item.storeFileName" alt="사진"></v-img>
              </v-avatar>
            
          </v-card>
            </template>
        </v-simple-table>
    </div>
</template>

<script>
export default {
    mounted() {
        this.$http
            .get('/api/dashboard/wishlist', {
                withCredentials: true,
            })
            .then((res) => {
                console.log(res);
                this.wishList = res.data.body;
            })
            .catch((err) => {
                console.log(err);
            });
    },
    data() {
        return {
            wishList: [],
        };
    },
    methods: {
        remove(item) {
            this.$http
                .delete(`/api/dashboard/wishlist/${item.id}`, {
                    withCredentials: true,
                })
                .then((res) => {
                    console.log(res);
                    this.$store.commit('wish/remoteList', index);
                })
                .catch((err) => {
                    console.log(err);
                });
        },
    },
};
</script>

<style></style>
