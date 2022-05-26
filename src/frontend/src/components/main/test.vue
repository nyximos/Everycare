<template>
  <div>
      <v-list-tile-content>
          <v-list-tile-title class="text--primary"></v-list-tile-title>
          <v-list-tile-action>
              <v-icon ripple @click="onEdit">
                  edit
              </v-icon>
          </v-list-tile-action>
      </v-list-tile-content>

      <v-dialog v-model="editDialog"
              persistent
              max-width="500px"
              @keydown.esc="closeDialog">
      <base-modal>
        <div slot="title"
             class="headline">Edit Data
        </div>
        <v-container slot="content"
                     grid-list-md>
          <v-layout wrap>
            <v-flex xs12>
              <v-text-field
                label="Subject"
                v-model="selectedData.subject"
                required>
              </v-text-field>
            </v-flex>
            <v-flex xs12>
              <v-text-field
                label="Desc"
                v-model="selectedData.desc"
                required
                @keydown.enter="editData">
              </v-text-field>
            </v-flex>
          </v-layout>
        </v-container>
        <v-btn slot="negative-button"
               color="blue darken-1"
               flat
               @click="closeDialog">
          Close
        </v-btn>
        <v-btn slot="positive-button"
               color="blue darken-1"
               flat
               @click="editData">
          Save
        </v-btn>
      </base-modal>
    </v-dialog>
  </div>
</template>

<script>
export default {
    data(){
        return{
            editDialog:false,
            selectedData:{
                id:Number,
                subject:'',
                desc:''
            }
        }
    },
    methods:{
        editData(){

        },
        onEdit(){
             this.editDialog = true
        },
        closeDialog(){
            this.editDialog = false;
        }
    }
}
</script>

<style>

</style>