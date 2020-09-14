import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import '@/svg';
import SvgIcon from '@/components/SvgIcon';
import axios, { fetchGet, fetchPost } from './Interceptor/Interceptor';

Vue.component('SvgIcon', SvgIcon);

axios.defaults.withCredentials = true;

Vue.prototype.$axios = axios;
Vue.prototype.$fetchGet = fetchGet;
Vue.prototype.$fetchPost = fetchPost;

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
