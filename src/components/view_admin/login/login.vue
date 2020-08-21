<template>
  <div class="login">
    <div class="login_box">
      <p class="title">Prmusic管理系统</p>
      <div class="input_box">
        <table>
          <tr>
            <td>
              <input class="input_box_input" type="text" placeholder="管理员账号" />
            </td>
          </tr>
          <tr>
            <td>
              <input class="input_box_input" type="password" placeholder="密码" />
            </td>
          </tr>
          <tr>
            <td>
              <input class="incode" type="text" placeholder="验证码" />
              <van-button class="getcode" plain type="primary">获取验证码</van-button>
            </td>
          </tr>
          <tr>
            <td>
              <van-button class="login_btn" type="primary">登陆</van-button>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import { Button } from "vant";
export default {
  components: {
    [Button.name]: Button,
  },
};
</script>

<style scoped>
.login {
  width: 100%;
  height: 100%;
  background-color: antiquewhite;
}

.login_box {
  position: absolute;
  width: 400px;
  height: 300px;
  background-color: wheat;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 20px;
}

.title {
  width: 100%;
  color: white;
  text-align: center;
}

.input_box table {
  margin: 50px auto;
}

.input_box td {
  text-align: justify;
  text-align-last: justify;
  white-space: nowrap;
  line-height: 40px;
}

.input_box_input {
  width: 200px;
  border: none;
  line-height: 30px;
  padding: 0 5px;
}

.incode {
  border: none;
  line-height: 30px;
  padding: 0 5px;
  width: 100px;
}

.getcode {
  width: 90px;
  height: 30px;
  margin-left: 10px;
}

.login_btn {
  width: 100%;
  height: 30px;
}

@media only screen and (max-width: 850px) {
  .login_box {
    width: 100%;
    height: 100%;
    padding: 0;
  }
}
</style>
