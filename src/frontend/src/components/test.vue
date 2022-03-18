<template>
    <div>
        <p>hello</p>
    </div>
</template>

<script>
export default {
    methods: {
        get() {
            this.axios.get('/test').then(response => {
                this.connectData = response.data;
            });
        }
    }
};
</script>

<style></style>
