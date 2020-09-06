import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/view_home'
import { Notify } from 'vant';

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: "login",
    component: () => import('../views/view_login')
  },
  {
    path: '/admin',
    name: 'admin',
    component: () => import('../views/admin/view_admin_login')
  },
  {
    path: '/admin_home',
    name: 'admin_home',
    component: () => import('../views/admin/view_admin_home')
  },
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path == '/admin' || to.path == '/admin_home') {
    next()
  } else {
    if (localStorage.getItem("retoken") != undefined) {
      if (to.path == '/') {
        next();
      } else {
        next('/')
      }
    }
    else {
      if (to.path == '/login') {
        next();
      } else {
        next('/login');
        Notify({ type: 'danger', message: '请先登陆' });
      }
    }
  }
})

export default router
