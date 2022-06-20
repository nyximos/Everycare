<template>
    <v-app>
        <BaseHeader />
        <router-view />
        <BaseAlert />
    </v-app>
</template>
<script>

export default {
    name: 'App',
};
</script>
