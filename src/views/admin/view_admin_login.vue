<template>
  <div class="admin_login">
    <component :is="comName"/>
  </div>
</template>

<script>
import login from '../../components/view_admin/login/login';
export default {
  components:{
    login,
  },
  data(){
    return{
      comName: 'login',
    }
  }
}
</script>

<style scoped>
.admin_login{
    width: 100%;
    height: 100%;
}
</style>
