<template>
    <v-app>
        <BaseHeader />
        <router-view></router-view>
        <BaseAlert />
        <BaseFooter/>
    </v-app>
</template>
<script>

export default {
    name: 'App',
};
</script>
