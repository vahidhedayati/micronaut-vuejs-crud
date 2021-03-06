// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from './store'
import axios from 'axios'
import VueAxios from 'vue-axios'
import Vuetify from 'vuetify'
import BootstrapVue from 'bootstrap-vue';
//import * as uiv from 'uiv'
import './assets/css/bootstrap.css'
import './assets/css/grails.css'
import './assets/css/main.css'
//import VueAuthenticate from 'vue-authenticate'
import {Tabs, Tab} from 'vue-tabs-component';
import VueAuthenticate from 'vue-authenticate'
import VueDropdown from 'vue-dynamic-dropdown'
//import { currency } from './currency'
//import VuejsDialog from "vuejs-dialog"

//import VuejsDialogMixin from "vuejs-dialog/vuejs-dialog-mixin.min.js" // only needed in custom components

// include the default style
//import 'vuejs-dialog/vuejs-dialog.min.css'


// Tell Vue to install the plugin.
//Vue.use(VuejsDialog)

Vue.config.productionTip = false

//Vue.use(uiv)
Vue.router = router;
//Vue.use(abilitiesPlugin, ability);
///Vue.use(SuiVue);
//Vue.use(uiv)
Vue.use(VueAxios, axios);
Vue.use(BootstrapVue);
Vue.use(Vuetify);
Vue.component('vue-dropdown', VueDropdown);

Vue.component('tabs', Tabs);
Vue.component('tab', Tab);
//Vue.use(require('vue-moment'));

// Vue Auth
Vue.use(VueAuthenticate, {
  baseUrl: 'http://localhost:8080/',
  bindRequestInterceptor () {
    this.$http.interceptors.request.use((config) => {
      if (this.isAuthenticated()) {
      config.headers['Authorization'] = [
        this.options.tokenType, this.getToken()
      ].join(' ')
    } else {
      delete config.headers['Authorization']
    }
    return config
  })
  },
  bindResponseInterceptor () {
    this.$http.interceptors.response.use((response) => {
      console.log(response)
      this.setToken(response)
      return response
    })
  }
});


Vue.config.productionTip = false;

//Global mixin
Vue.mixin({
  methods: {
    pressed(val) {
      alert(val);
    }
  },
  data() {
    return {
      item: ''
    }
  }
});
/**
 * Below element provide a custom input which does pattern matching
 * <custom-input-required  pattern="(?=.*[A-Z]).{2,3}" v-model="hotel.name" title="Hotel code: Upper Case A-Z 2 to 3 characters only "></custom-input-required>
 *
 * <custom-input-required  pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}" v-model="hotel.name"
 * title="Must contain at least one number and one uppercase and lowercase letter, and be between 8 and 16 characters."></custom-input-required>
 */
Vue.component('custom-input-required',{
  props: ['pattern', 'title', 'vModel'],
  template:`<input type="text"  required :pattern="pattern" :title="title" :v-model="vModel"/>`
  }
);
/**
 * Below element provide a custom input - which does pattern matching
 * <custom-input pattern="(?=.*[A-Z]).{2,3}" v-model="hotel.name" title="Hotel code: Upper Case A-Z 2 to 3 characters only "></custom-input-required>
 *
 * <custom-input name="test" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}" v-model="hotel.name"
 * title="Must contain at least one number and one uppercase and lowercase letter, and be between 8 and 16 characters."></custom-input-required>
 */

Vue.component('custom-input',{
    props: ['pattern', 'title', 'vModel'],
    template:`<input type="text" :pattern="pattern" :title="title" :v-model="vModel"/>`
  }
)
// Global event bus
Vue.prototype.$eventHub = new Vue();
//using   this.$root.$emit('rental-counter',this.counter); rather than
//declaring above  then calling   this.$eventHub.$emit('rental-counter',this.counter);


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },

  template: '<App/>'
})
