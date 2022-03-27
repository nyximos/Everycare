<template>
    <div>
        <p>hello</p>
        <p>{{ test }}</p>
    </div>
</template>

<script>
export default {
    date() {
        return {
            test: {}
        };
    },
    mounted() {
        this.load();
    },
    methods: {
        load() {
            this.$http
                .get('/api/test')
                .then(res => {
                    // this.test = response.data;
                    console.log(res);
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }
};
</script>

<style></style>
