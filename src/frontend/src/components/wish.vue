<template>
    <div>
        <v-simple-table>
            <template v-slot:default>
                <thead>
                    <tr>
                        <th class="text-left">id</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(item, index) in wishList" :key="index">
                        <td>{{ index }}/{{ item.productId }}</td>
                        <td><v-btn @click="remove(item, index)">x</v-btn></td>
                    </tr>
                </tbody>
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
        // drop(){
        //   this.$http.delete(`/api/dashboard/wishlist/${this.listItem.id}`,{
        //   withCredentials: true
        // })
        // .then((res)=> {
        //   console.log(res)
        // }).catch((err)=>{
        //   console.log(err)
        //     })
        //   },
        remove(item, index) {
            this.$http
                .delete(`/api/dashboard/wishlist/${item.id}`, {
                    withCredentials: true,
                })
                .then((res) => {
                    console.log(res);
                    console.log(this.item.index);
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
