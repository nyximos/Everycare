<template>
    <v-app>
        <BaseHeader v-if="!$route.meta.hideNavbar"/>
        <router-view />
        <BaseAlert />
    </v-app>
</template>
<script>

export default {
    name: 'App',
};
</script>
