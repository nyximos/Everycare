<template>
    <div>
        <h1>Main</h1>       
        <v-btn @click="showAlert()">Show Alert</v-btn>
    </div>
</template>
<script>
export default {
    name: 'Main',
    methods: {
        showAlert() {
            this.$alert.error('show alert error');
            console.log(this.$alert.alertData);
        }
    }
};
</script>
<style lang="scss" scoped></style>
