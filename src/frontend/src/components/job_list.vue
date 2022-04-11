<template>
 <div>
    <div class="text-end mt-4">
        <v-btn @click="gocreate">
          <v-icon left>
            mdi-pencil
          </v-icon>
            Create
        </v-btn>
    </div>
    <div class="header">
        <v-row>
        <h1>찾아볼까요? 🔍👀</h1>
        </v-row>
    </div>
    <v-row class="mt-5">
    <v-radio-group
      v-model="radios"
      row
    >
      <v-radio
        label="케어시터"
        value="caresitter"
      ></v-radio>
      <v-radio
        label="일자리"
        value="findjob"
      ></v-radio>
    </v-radio-group>
    </v-row>
        <v-row>
            <v-select
                v-model="location"
                placeholder="지역을 선택해주세요"
                prepend-icon="mdi-map"
                :items="pickloc"
                item-text="name"
                item-value="value">
            </v-select>
        </v-row>
        <v-row>
            <v-col cols="3">
                <v-checkbox
                v-model="selected"
                value="play"
                label="놀이"
                ></v-checkbox>
            </v-col>
            <v-col cols="3">
                <v-checkbox
                v-model="selected"
                value="study"
                label="학습"
                ></v-checkbox>
            </v-col>
            <v-col cols="3">
                <v-checkbox
                v-model="selected"
                value="momscare"
                label="산후조리"
                ></v-checkbox>
            </v-col>
            <v-col cols="3">
                <v-checkbox
                v-model="selected"
                value="oldman"
                label="노인"
                ></v-checkbox>
            </v-col>
            <v-col cols="3">
                <v-checkbox
                v-model="selected"
                value="pickup"
                label="등하원"
                ></v-checkbox>
            </v-col>
            <v-col cols="3">
                <v-checkbox
                v-model="selected"
                value="housework"
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
  <div class="text-center">
    <v-row>
        <v-col>
        <v-btn
          depressed
          color="warning"
          @click="search">
          Search
        </v-btn> 
        </v-col>
   </v-row>
  </div>
 <hr>
 <div v-if="result">
   <v-card
    class="mx-auto mt-3"
    max-width="700"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-content>
        <v-row>
        <v-list-item-title class="text-h5 fw-bold mt-4">
          5세 여아 등하원 도우미 구합니다
        </v-list-item-title>
        </v-row>
        <v-list-item-subtitle class="mt-1">
          <v-row>
            <v-col cols="2">
        <v-list-item-avatar
        tile
        size="100"
        color="grey"
      ></v-list-item-avatar>
      </v-col>
      <v-col cols="10" class="mt-5">
              {{this.radios}}
              {{this.selected}}
              {{this.location}}
              </v-col>
          </v-row>
        </v-list-item-subtitle>
      </v-list-item-content>
    </v-list-item>
    <v-card-actions>
      <v-btn
        outlined
        rounded
        text
      >
        Best Cleaner✨
      </v-btn>
    </v-card-actions>
  </v-card>
  <div class="text-center">
    <v-pagination
      v-model="page"
      :length="4"
      circle
      class="mt-3"
    ></v-pagination>
  </div>
 </div>
</div>
</template>

<script>
export default {
    name: 'componentjoblist',
    data(){
        return{
            radios: '',
            location:'',
            pickloc:[
                {name: '서울', value: 'seoul'},
                {name: '인천', value: 'incheon'},
            ],
            selected: [],
            dates: [],
            result: false,
            page: 1,   
        }
    },
    methods:{
        search(){
            const Searchdata={
                radios: this.radios,
                location: this.location,
                selected: this.selected,
                dates: this.dates
            }
            console.log(Searchdata)
            this.result=true
        },
        gocreate(){
          this.$router.push({
            path: '/createwrite'
          })
        }
    },
    computed:{
        dateRangeText(){
            return this.dates.join('~')
        },
    }
}
</script>

<style>

</style>