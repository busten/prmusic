<template>
  <div class="inputbox">
    <form>
      <table>
        <tr>
          <td class="tip">账号</td>
          <td>
            <input type="text" v-model="user.account" />
          </td>
        </tr>
        <tr>
          <td class="tip">密码</td>
          <td>
            <input type="password" v-model="user.password" autocomplete />
          </td>
        </tr>
        <tr>
          <td class="tip">确认密码</td>
          <td>
            <input type="password" v-model="user.repassword" autocomplete />
          </td>
        </tr>
        <tr>
          <td class="tip">账号名称</td>
          <td>
            <input type="text" v-model="user.username" />
          </td>
        </tr>
        <tr>
          <td class="tip">邮箱</td>
          <td>
            <input type="text" v-model="user.email" />
          </td>
        </tr>
        <tr>
          <td class="tip">验证码</td>
          <td>
            <input type="text" class="setcode" v-model="user.captcha" maxlength="6"/>
            <button
              @click="getcode"
              class="getcode"
              type="button"
              :class="{dontcursor:isdontcursor}"
            >
              <van-loading
                v-show="showcode"
                class="codeloading"
                color="white"
                type="spinner"
                size="18px"
              />
              <span v-show="!showcode">{{btnname}}{{tips}}</span>
            </button>
          </td>
        </tr>
      </table>
    </form>
    <button @click="register" type="button" class="loginbtn" :class="{dontcursor:isdontcursor1}">
      <van-loading v-show="showloading" class="loading" color="white" type="spinner" size="18px" />
      <span>注册</span>
    </button>
  </div>
</template>

<script>
import { Loading } from "vant";
export default {
  components: {
    [Loading.name]: Loading,
  },
  data() {
    return {
      showloading: false,
      showcode: false,
      isdontcursor: false,
      isdontcursor1: false,
      btnname: "获取验证码",
      tips: "",
      user: {
        account: "",
        password: "",
        repassword: "",
        username: "",
        email: "",
        captcha: "",
      },
    };
  },
  props: {
    data_submit: { type: Boolean },
  },
  methods: {
    getcode() {
      if (!this.isdontcursor) {
        this.showcode = true;
        this.isdontcursor = true;
        this.$axios
          .post("/prmusic/user/getcode", {
            email: this.user.email,
          })
          .then((res) => {
            this.showcode = false;
            this.btnname = 60;
            this.tips = "秒后再次获取";
            let times = setInterval(() => {
              this.btnname--;
              if (this.btnname < 0) {
                clearInterval(times);
                this.btnname = "获取验证码";
                this.isdontcursor = false;
                this.tips = "";
              }
            }, 1000);
          })
          .catch(() => {
            this.showcode = false;
            this.isdontcursor = false;
          });
      }
    },
    register() {
      if (!this.isdontcursor1) {
        this.showloading = true;
        this.isdontcursor1 = true;
        this.$axios
          .post("/prmusic/user/register", {
            account: this.user.account,
            password: this.user.password,
            username: this.user.username,
            email: this.user.email,
            captcha: this.user.captcha,
          })
          .then((res) => {
            this.showloading = false;
            this.isdontcursor1 = false;
          })
          .catch((error) => {
            this.showloading = false;
            this.isdontcursor1 = false;
          });
      }
    },
  },
  watch: {
    data_submit: function () {
      this.register();
    },
  },
};
</script>

<style>
table {
  text-align-last: justify;
  margin: auto;
  margin-top: 30px;
  padding-left: 70px;
}

td {
  line-height: 40px;
}

input {
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
  width: 25%;
}

.getcode {
  border: none;
  width: 120px;
  height: 27px;
  position: relative;
}

.getcode span {
  display: block;
  float: left;
  width: 100%;
  text-align: center;
}

.codeloading {
  width: 18px;
  height: 100%;
  margin: auto;
  margin-right: 50px;
  margin-top: 3px;
}

.loginbtn {
  border: none;
  text-align: center;
  padding: 3px 100px;
  margin-top: 20px;
}
</style>
