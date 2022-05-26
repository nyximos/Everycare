<template>
    <div>
        <div v-if="this.wishList.length==0">찜이 없습니다</div>
        <v-simple-table v-else>
            <template v-slot:default>
                <thead>
                    <tr>
                        <th class="text-left">id</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(item, index) in wishList" :key="index">
                        <td>{{ index }}/{{ item.id }}</td>
                        <td><v-btn @click="remove(item)">x</v-btn></td>
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
