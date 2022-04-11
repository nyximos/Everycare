import Vue from 'vue';
import Vuex from 'vuex';
//import modules from '@/store/modules/index.js';
<<<<<<< HEAD
import userStore from './userStore';
=======
// import userStore from './userStore';
>>>>>>> main
import careprofileStore from './careprofileStore'

Vue.use(Vuex);

export default new Vuex.Store({ 
  modules:{
<<<<<<< HEAD
    userStore,
    careprofileStore
  }

});
=======
    // userStore,
    careprofileStore
  }
>>>>>>> main

});