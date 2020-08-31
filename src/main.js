import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/svg';
import SvgIcon from '@/components/SvgIcon';
import axios from './Interceptor/Interceptor';

Vue.component('SvgIcon', SvgIcon);

Vue.prototype.$axios = axios;


Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
