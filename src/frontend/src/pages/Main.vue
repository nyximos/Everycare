<template>
    <div>
      <componentpage />
    </div>
</template>
<script>
import componentpage from '@/components/main.vue';
export default {
    components: {
        componentpage,
    },
};
</script>
<style scoped lang="scss">
.main {
    min-height: 100vh;
}
</style>
