<template>
    <div class="text-center">
        <v-dialog v-model="dialog.isDisplay" width="500">
            <v-card>
                <v-card-title class="text-h5 grey lighten-2">
                    {{ dialog.color }}
                </v-card-title>

                <v-card-text>
                    {{ dialog.message }}
                </v-card-text>

                <v-divider></v-divider>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" text @click="dialog = false">
                        I accept
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>
<script>
export default {
    name: 'BaseAlert',
    data() {
        return {
            dialog: this.$alert.alertData
        };
    }
};
</script>
