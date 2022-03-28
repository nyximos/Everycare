<template>
    <v-app>
        <BaseHeader />
        <router-view />
        <BaseFooter />
        <BaseAlert />
    </v-app>
</template>
<script>

export default {
    name: 'App',
};
</script>
