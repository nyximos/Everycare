<template>
    <div>
        <div>
            <v-carousel v-model="model" height="300px" hide-delimiters cycle interval="3000">
                <v-carousel-item v-for="(color, i) in colors" :key="color">
                    <v-sheet :color="color" height="100%" tile>
                        <v-row class="fill-height" align="center" justify="center">
                            <div class="text-h2">Slide {{ i + 1 }}</div>
                        </v-row>
                    </v-sheet>
                </v-carousel-item>
            </v-carousel>
        </div>

        <div class="main-form">
            <div @click="go('caresitters')" class="find-care-sitter">
                케어시터 찾기
            </div>
            <v-divider vertical></v-divider>
            <div @click="go('works')" class="find-job">
                일자리 찾기
            </div>
        </div>
    </div></template
>
<script>
export default {
    name: 'Main',
    data() {
        return {
            model: 0,
            colors: ['primary', 'secondary', 'yellow darken-2', 'red', 'orange']
        };
    },
    methods: {
        go(link) {
            this.$router.push('/' + link);
        }
    }
};
</script>
<style lang="scss" scoped>
.main-form {
    display: flex;
    height: calc(100vh - 350px);
}
.find-care-sitter {
    width: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
}
.find-job {
    width: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
}
</style>
