<template>
 <div>

    <div class="text-end mt-4">
        <v-btn>
          <v-icon left>
            mdi-pencil
          </v-icon>
            Create
        </v-btn>
    </div>
    <div class="header">
        <h1>찾아볼까요? 🔍👀</h1>
    </div>
    <div class="tabs text-center mt-4">
      <v-tabs>
         <v-tab>
            케어시터
        </v-tab>
        <v-tab-item>
        <v-row>
            <v-select
                v-model="hopeloc1"
                :items="hopelocation1"
                label="지역을 선택해주세요"
                prepend-icon="mdi-map"
                item-text="name"
                item-value="value">
            </v-select>
        </v-row>
        <v-row>
            <v-col cols="3">
                <v-checkbox
                v-model="selected"
                value="Mon"
                label="놀이"
                ></v-checkbox>
            </v-col>
            <v-col cols="3">
                <v-checkbox
                v-model="selected"
                value="Mon"
                label="학습"
                ></v-checkbox>
            </v-col>
            <v-col cols="3">
                <v-checkbox
                v-model="selected"
                value="Mon"
                label="산후조리"
                ></v-checkbox>
            </v-col>
            <v-col cols="3">
                <v-checkbox
                v-model="selected"
                value="Mon"
                label="노인"
                ></v-checkbox>
            </v-col>
            <v-col cols="3">
                <v-checkbox
                v-model="selected"
                value="Mon"
                label="등하원"
                ></v-checkbox>
            </v-col>
            <v-col cols="3">
                <v-checkbox
                v-model="selected"
                value="Mon"
                label="가사"
                ></v-checkbox>
            </v-col>
        </v-row>
        <v-row>
            <v-col
            cols="12"
            sm="6"
            >
            <v-date-picker
                v-model="dates"
                range
            ></v-date-picker> 
                </v-col>
                <v-col
                cols="12"
                sm="6"
                >
            <v-text-field
                v-model="dateRangeText"
                label="Date range"
                prepend-icon="mdi-calendar"
                readonly
            ></v-text-field>
         </v-col>
    </v-row>
    <v-btn
   depressed
   color="warning">
    Search
   </v-btn>
        </v-tab-item>

        <v-tab>
            일자리
        </v-tab>
        <v-tab-item>
        <v-row>
            <v-select
          v-model="e1"
          :items="states"
          menu-props="auto"
          label="지역을 선택해주세요"
          hide-details
          prepend-icon="mdi-map"
          single-line
        ></v-select>
        </v-row>
        <v-row>
        <v-select
          v-model="e1"
          :items="states"
          prepend-icon="✔"
          menu-props="auto"
          label="카테고리를 선택해주세요"
          hide-details 
          single-line
        ></v-select>
        </v-row>
        <v-row>
            <v-col
            cols="12"
            sm="6"
            >
            <v-date-picker
                v-model="dates"
                range
            ></v-date-picker> 
                </v-col>
                <v-col
                cols="12"
                sm="6"
                >
            <v-text-field
                v-model="dateRangeText"
                label="Date range"
                prepend-icon="mdi-calendar"
                readonly
            ></v-text-field>
         </v-col>
    </v-row>
    <v-btn
   depressed
   color="warning">
    Search
   </v-btn>
        </v-tab-item>
      </v-tabs>
</div>
 <hr>
    </div>
</template>

<script>
export default {
    name: 'componentjoblist',
    data(){
        return{
            dates: [],
            hopelocation1:[
            {name: '서울', value: 'seoul'},
            {name: '인천', value: 'incheon'},
            ],
        }
    },
    computed:{
        dateRangeText(){
            return this.dates.join('~')
        }
    }
}
</script>

<style>

</style>