<template>
<v-card flat>
    <v-snackbar
      v-model="snackbar"
      absolute
      top
      right
      color="success"
    >
    <v-icon dark>
        mdi-checkbox-marked-circle
    </v-icon>
      <span>Registration successful!</span>
    </v-snackbar>
    <v-form
      ref="form"
      @submit.prevent="submit"
    >
      <v-container fluid>
         <v-row>
          <v-col
            cols="6"
          >
      <v-select
      v-model="form.caretarget"
      :items="items"
      label="케어대상인 선택"
      multiple
    >
      <template v-slot:selection="{ item, index }">
        <v-chip v-if="index === 0">
          <span>{{ item }}</span>
        </v-chip>
        <span
          v-if="index === 1"
          class="grey--text text-caption"
        >
          (+{{ form.caretarget.length - 1 }} others)
        </span>
      </template>
    </v-select>
          </v-col>
          <v-col
            cols="6">
            <v-select
            v-model="form.schedul"
            :items="schedulelist"
            attach
            chips
            label="스케줄 선택"
          ></v-select>
          </v-col>
          </v-row>
        <v-row>
            <v-col
            cols="6"
            >
            <v-date-picker
                v-model="form.dates"
                range
            ></v-date-picker> 
                </v-col>
                <v-col
                cols="6"
                >
            <v-text-field
                v-model="dateRangeText"
                label="계약일을 선택해주세요"
                prepend-icon="mdi-calendar"
                readonly
            ></v-text-field>
         </v-col>
    </v-row>
          <v-row>
            <v-col cols="4">
             <v-subheader>요일 선택</v-subheader>
            </v-col>
            <v-col cols="8">
            <v-select
            v-model="form.day"
            :items="days"
            attach
            chips
            multiple>
          </v-select>
            </v-col>
          </v-row>
          <v-row>
            <v-col cols="4">
              <v-subheader>희망 케어시터 성별</v-subheader> 
            </v-col>
            <v-col cols="8">
              <v-radio-group
             v-model="form.sittersex"
            mandatory
            row>
            <v-radio
              label="남성"
              value="Man">
              </v-radio>
            <v-radio
              label="여성"
              value="Woman">
              </v-radio>
          </v-radio-group>
            </v-col>
          </v-row>
    <v-row>
          <v-col
            cols="4"
          >
           <v-radio-group
             v-model="form.paytimepick"
            mandatory
            row>
            <v-radio
              label="시급"
              value="hourpay">
              </v-radio>
            <v-radio
              label="월급"
              value="monthpay">
              </v-radio>
          </v-radio-group>
          </v-col>
          <v-col
            cols="8"
          >
            <v-text-field
              v-model="form.pay"
              color="blue darken-2"
              label="희망 시/월급을 입력하세요"
              required
            ></v-text-field>
          </v-col>
          </v-row>
          <v-col cols="12">
            <v-textarea
              v-model="form.comment"
              label="남기고싶은 말 (optional)"
            >
            </v-textarea>
          </v-col>
      </v-container>
      <v-card-actions>
        <v-btn
          text
          @click="goback"
        >
          Cancel
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn
          :disabled="!formIsValid"
          text
          color="primary"
          type="submit"
        >
          Register
        </v-btn>
      </v-card-actions>
    </v-form>
  </v-card>
</template>

<script>
export default {
name: 'Create',
    data () {
      const defaultForm = Object.freeze({
        caretarget: [],
        schedul: '',
        dates: [],
        day:[],
        sittersex:'',
        paytimepick:null,
        pay: '',
        comment: '',
      })
      return {
         caretarget:this.caretarget,
         items: [this.$store.state.findpeople.caretarget, '이위화', '차윤식'],
         schedul:this.schedul,
         schedulelist:['2021-12-02', '2021-10-11'],
        dates: [],
        day: [],
        days: ['월', '화', '수', '목', '금', '토', '일'],
        sittersex: this.sittersex,
        paytimepick: this.paytimepick,
        form: Object.assign({}, defaultForm),
        conditions: false,
        content: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. Vestibulum lacinia arcu eget nulla. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur sodales ligula in libero. Sed dignissim lacinia nunc.',
        snackbar: false,
        defaultForm,
      }
    },

    computed: {
      formIsValid () {
        return (
          this.form.caretarget &&
          this.form.schedul&&
          this.form.dates &&
          this.form.day &&
          this.form.sittersex &&
          this.form.paytimepick &&
          this.form.pay
        )},
       dateRangeText(){
            return this.form.dates.join('~')
        },
    },

    methods: {
      goback () {
        this.$router.push({
          path: '/'
        })
      },
      submit () {
        const findpeople ={
          caretarget: this.form.caretarget,
          schedul: this.form.schedul,
          dates: this.form.dates,
          day: this.form.day,
          sittersex: this.form.sittersex,
          paytimepick: this.form.paytimepick,
          pay: this.form.pay,
          comment: this.form.comment
        }
        this.snackbar = true
        try {
          this.$store.commit('findpeople/set_data', findpeople)
          console.log(this.$store.state.findpeople.caretarget)
        } catch (error) {
          console.log(error)
        }
        
      },
    },
  }

</script>

<style>

</style>