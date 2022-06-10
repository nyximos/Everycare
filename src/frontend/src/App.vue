<template>
    <v-app>
        <BaseHeader v-if="!$route.meta.hideNavbar"/>
        <router-view />
        <BaseFooter v-if="!$route.meta.hideFooter"/>
        <BaseAlert/>
    </v-app>
</template>
<script>

export default {
    name: 'App',
};
</script>
