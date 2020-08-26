<template>
  <div class="admin_home">
    <div :style="{width:(isexpand_button?'300px':'')}" class="menu_list">
      <div @click="expand_button" class="expand_button">
        <van-icon
          :style="{transform: (isexpand_button?'rotateY(180deg)':'')}"
          class="expand_button_icon"
          size="20"
          name="arrow"
        />
      </div>
      <table v-show="isexpand_button">
        <tr>
          <th>Prmusic管理系统</th>
        </tr>
        <tr>
          <td>当前账号:用户名称</td>
        </tr>
        <tr>
          <td>
            账号权限:
            <span class="auth_identification">管理员</span>
          </td>
        </tr>
      </table>
      <div v-show="isexpand_button" class="menu">
        <menu_list @comName="iscomName" />
      </div>
    </div>
    <div class="menu_panel">
      <component :is="comName" />
    </div>
  </div>
</template>

<script>
import album_admin from "../../components/view_admin/home/admin_album";
import song_admin from "../../components/view_admin/home/admin_song";
import music_admin from "../../components/view_admin/home/admin_music";
import account_admin from "../../components/view_admin/home/admin_account";
import user_group_admin from "../../components/view_admin/home/admin_user_group";
import auth_admin from "../../components/view_admin/home/admin_auth";
import menu_list from "../../components/view_admin/home/menu";
import homepage from "../../components/view_admin/home/homepage";
import { Icon } from "vant";
export default {
  components: {
    [Icon.name]: Icon,
    menu_list,
    homepage,
    album_admin,
    song_admin,
    music_admin,
    account_admin,
    user_group_admin,
    auth_admin,
  },
  data() {
    return {
      isexpand_button: false,
      comName: "homepage",
    };
  },
  methods: {
    expand_button() {
      this.isexpand_button = !this.isexpand_button;
    },
    iscomName(name) {
      this.comName = name;
    },
  },
};
</script>

<style>
.admin_home {
  width: 100%;
  height: 100%;
  background-color: rgb(20, 20, 94);
}

.menu_list {
  position: absolute;
  width: 0px;
  height: calc(100% - 20px);
  background-color: rgb(5, 26, 83);
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  color: white;
  padding: 10px 0;
  transition: all 0.3s ease;
  border-right: 1px solid rgba(129, 148, 201, 0.3);
  z-index: 10;
}

.expand_button {
  width: 50px;
  height: 30px;
  background-color: rgb(5, 26, 83);
  position: absolute;
  top: 0;
  right: -52px;
  border-radius: 0 30px 30px 0;
  border: 1px solid rgba(129, 148, 201, 0.3);
  cursor: pointer;
}

.expand_button_icon {
  margin-left: 10px;
  margin-top: 5px;
}

.menu_list table {
  text-align: center;
  padding: 10px 0;
  overflow: hidden;
  white-space: nowrap;
}

.menu_list th {
  font-size: 20px;
  font-weight: bold;
}

.menu {
  width: 100%;
  height: calc(100% - 200px);
  overflow: hidden;
}

.auth_identification {
  color: chartreuse;
}

.menu_panel {
  width: 100%;
  height: 100%;
  color: white;
}
</style>