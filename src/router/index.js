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
    path: '/user',
    name: 'user',
    component: () => import('../components/personal')
  },
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,from,next) => {
  if(to.path != '/login'){
    next()
  }else{
    next()
  }
})

export default router
