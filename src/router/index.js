import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/view_home'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'login',
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

router.beforeEach((to,from,next) => {
  if(to.path != '/login'){
    from.meta.keepAlive = false
    next()
  }else{
    next()
  }
})

export default router
