<template>
  <div class="personal">
    <div class="personal_box">
      <div class="personal_box_header">
        <table>
          <tr>
            <th>用户名称</th>
          </tr>
          <tr>
            <td>UID:110255210</td>
          </tr>
          <tr>
            <td>注册时间:2020-08-20 14:25 PM</td>
          </tr>
          <tr>
            <td>上次登陆时间:2020-08-21 00:16 AM</td>
          </tr>
          <tr>
            <td>上次登陆地址:192.168.1.10</td>
          </tr>
          <tr>
            <td>
              <button @click="change_repassword">修改密码</button>
              <button @click="logout" style="margin-left:10px;">注销</button>
            </td>
          </tr>
          <div :class="{oprepassword:isoprepassword}" class="repassword">
            <tr>
              <td>
                输入新密码:
                <input type="password" />
              </td>
            </tr>
            <tr>
              <td>
                确认新密码:
                <input type="password" />
              </td>
            </tr>
            <tr>
              <td>
                输入验证码:
                <input type="text" />
              </td>
            </tr>
            <tr>
              <td>
                <button>确认更改</button>
                <button style="margin-left:10px;">获取验证码</button>
              </td>
            </tr>
          </div>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isoprepassword: false,
    };
  },
  methods: {
    change_repassword() {
      this.isoprepassword = !this.isoprepassword;
    },
    logout(){
      localStorage.removeItem("retoken");
      if(localStorage.getItem("retoken") == null){
        this.$router.go(0);
      }
    }
  },
};
</script>

<style scoped>
input {
  color: black;
  line-height: 18px;
}
.personal {
  width: 100%;
  height: 100%;
}

.personal_box {
  width: 80%;
  height: 100%;
  margin: auto;
  overflow: auto;
  background-color: rgba(255, 255, 255, 0.6);
}

.personal_box_header {
  width: 100%;
}

.personal_box_header img {
  width: 150px;
  height: 150px;
  padding: 10px;
  float: left;
}

.personal_box_header table {
  padding: 10px;
  float: left;
  text-align: left;
  text-align-last: left;
}

.personal_box_header tr {
  line-height: 30px;
}

.personal_box_header th {
  font-size: 20px;
  font-weight: bold;
  text-align: left;
}

.personal_box_header button {
  border: none;
  outline: none;
  padding: 5px 10px;
  background-color: green;
  border-radius: 5px;
}

.personal_box_header button:hover {
  background-color: darkgreen;
}

.repassword {
  height: 0;
  overflow: hidden;
  transition: all 0.3s ease;
}

.oprepassword {
  height: auto;
  transition: all 0.3s ease;
}
</style>
