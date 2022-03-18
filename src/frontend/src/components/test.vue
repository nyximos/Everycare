<template>
    <div>
        <p>hello</p>
    </div>
</template>

<script>
export default {
    date() {
        return {
            test: ''
        };
    },
    mounted() {
        this.load();
    },
    methods: {
        load() {
            this.$http
                .get('/api/test')
                .then(response => {
                    // this.test = response.data;
                    console.log(response.data);
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }
};
</script>

<style></style>
