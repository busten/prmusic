<template>
  <div class="login">
    <div class="backgrounds">
      <div class="box" :class="{addboxheight:isaddboxheight}">
        <p>
          <span @click="login">登陆</span>|
          <span @click="register">注册</span>
        </p>
        <component :is="component_name" />
      </div>
    </div>
  </div>
</template>

<script>
import login from "../components/view_login/login";
import register from "../components/view_login/register";
export default {
  data() {
    return {
      component_name: "login",
      isaddboxheight: false,
    };
  },
  components: {
    login,
    register,
  },
  methods: {
    login() {
      this.component_name = "login";
      this.isaddboxheight = false;
    },
    register() {
      this.component_name = "register";
      this.isaddboxheight = true;
    },
  },
};
</script>

<style scoped>
.login {
  width: 100%;
  height: 100%;
}

.backgrounds {
  width: 100%;
  height: 100%;
  background: url("../assets/background/login.jpg");
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
  filter: brightness(105%);
  position: relative;
}

.box {
  width: 500px;
  height: 300px;
  background-color: rgba(255, 250, 250, 0.9);
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 100px;
  margin: auto;
  border-radius: 5px;
  box-shadow: 0px 0px 200px 10px white;
  overflow: hidden;
  transition: all 0.3s ease;
}

.addboxheight {
  height: 400px;
  transition: all 0.3s ease;
}

.box p {
  font-weight: 700;
  font-size: 20px;
  text-align: center;
  margin-top: 20px;
  cursor: default;
}

.box p span:hover {
  color: cornflowerblue;
}

@media only screen and (max-width: 500px) {
  .box {
    width: 100%;
    height: calc(100% - 100px);
    bottom: 0;
    padding-top: 100px;
  }
}
</style>
