<template>
  <div class="inputbox">
    <form>
      <table class="tbbox">
        <tr>
          <td class="tip">账号</td>
          <td>
            <input type="text" v-model="user.username" />
          </td>
        </tr>
        <tr>
          <td class="tip">密码</td>
          <td>
            <input type="password" v-model="user.password" autocomplete />
          </td>
        </tr>
        <tr>
          <td class="tip">验证码</td>
          <td>
            <input type="text" v-model="user.captcha" class="setcode" maxlength="6" />
            <div class="getcode">
              <img @click="getverificationCode" :src="verificationCode" />
            </div>
          </td>
        </tr>
      </table>
      <p class="rememberme">
        <input type="checkbox" v-model="user.rememberme" />
        <span>7天免登录</span>
      </p>
      <button @click="login" :class="{dontcursor:isdontcursor}" class="loginbtn" type="button">
        <van-loading v-show="showloading" class="loading" color="white" type="spinner" size="18px" />
        <span>进入</span>
      </button>
    </form>
  </div>
</template>

<script>
import { Loading } from "vant";
export default {
  components: {
    [Loading.name]: Loading,
  },
  props: {
    data_submit: { type: Boolean },
  },
  data() {
    return {
      verificationCode: "/prmusic/user/verificationCode",
      showloading: false,
      isdontcursor: false,
      user: {
        username: "",
        password: "",
        captcha: "",
        rememberme: false,
      },
    };
  },
  methods: {
    getverificationCode() {
      this.verificationCode =
        "/prmusic/user/verificationCode" +
        "?code=" +
        parseInt((Math.random() * 10000).toFixed(2));
    },
    login() {
      if (!this.isdontcursor) {
        this.showloading = true;
        this.isdontcursor = true;
        this.$axios
          .post("/prmusic/user/login", {
            username: this.user.username,
            password: this.user.password,
            captcha: this.user.captcha,
            rememberme: this.user.rememberme,
          })
          .then((res) => {
            if (res.headers.retoken != undefined) {
              localStorage.setItem("retoken", res.headers.retoken);
            }
            if (!this.user.rememberme) {
              localStorage.removeItem("retoken");
            }
            this.showloading = false;
            this.isdontcursor = false;
            setTimeout(() => {
              this.$router.push("/");
            }, 2000);
          })
          .catch((error) => {
            this.showloading = false;
            this.isdontcursor = false;
          });
      }
    },
  },
  watch: {
    data_submit: function () {
      this.login();
    },
  },
};
</script>

<style>
.tbbox {
  text-align-last: justify;
  margin: auto;
  margin-top: 30px;
  padding-left: 70px;
}

td {
  line-height: 40px;
}

input {
  width: 200px;
  border: 1px solid silver;
  line-height: 25px;
  border-radius: 5px;
  padding-left: 5px;
}

button {
  line-height: 25px;
  outline: none;
  border-radius: 5px;
  background-color: skyblue;
}

button:hover {
  background-color: deepskyblue;
}

button:active {
  background-color: dodgerblue;
}
.inputbox {
  width: 100%;
  text-align: center;
}

.tip {
  width: 70px;
  padding-right: 10px;
}

.setcode {
  width: 33%;
  float: left;
}

.getcode {
  border: none;
  margin-left: 5px;
  float: left;
}

.loginbtn {
  border: none;
  text-align: center;
  margin-top: 10px;
  position: relative;
}

.loginbtn span {
  padding-left: 10px;
}

.rememberme {
  border: none;
  text-align: left;
  margin-left: 140px;
  margin-top: 10px;
}

.rememberme input {
  width: auto;
}

.loading {
  float: left;
  margin-right: 5px;
  position: absolute;
  left: 80px;
}

.dontcursor {
  background-color: gainsboro;
  cursor: no-drop;
}

.dontcursor:hover {
  background-color: gainsboro;
}
</style>
