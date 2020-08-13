<template>
  <div class="inputbox">
    <table class="tbbox">
      <tr>
        <td class="tip">账号</td>
        <td>
          <input type="text" />
        </td>
      </tr>
      <tr>
        <td class="tip">密码</td>
        <td>
          <input type="password" />
        </td>
      </tr>
      <tr>
        <td class="tip">验证码</td>
        <td>
          <input type="text" class="setcode" />
          <button class="getcode">获取验证码</button>
        </td>
      </tr>
    </table>
    <button class="loginbtn">进入</button>
  </div>
</template>

<script>
export default {
  props:{
    data_submit:{type:Boolean}
  },
  watch:{
    data_submit:function(){
      alert("登陆")
    }
  }
}
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
}

.getcode {
  border: none;
  margin-left: 5px;
}

.loginbtn {
  border: none;
  text-align: center;
  padding: 3px 100px;
  margin-top: 20px;
}
</style>
